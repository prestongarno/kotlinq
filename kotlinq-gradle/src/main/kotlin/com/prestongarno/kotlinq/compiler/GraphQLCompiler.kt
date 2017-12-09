/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.compiler

import com.squareup.kotlinpoet.FileSpec
import org.antlr.v4.runtime.Token
import kotlin.properties.Delegates

typealias SchemaRule = Set<SchemaType>.() -> Unit
typealias SymbolScopeRule = Set<ScopedSymbol>.(ScopedDeclarationType) -> Unit

/** TODO use antlr4's listener hooks to stop unnecessary iteration for validation rules */
class GraphQLCompiler(
    schema: Schema,
    scope: Configuration.() -> Unit = { /* nothing */ }
) {

  private val config = Configuration.fromContext(schema, scope)

  /** This is kept in sync by the [GraphQLCompiler.definitions] variable. Do NOT add definitions here */
  val symtab: MutableMap<String, SchemaType> = mutableMapOf()

  private val definitions: MutableSet<SchemaType> by Delegates.observable(mutableSetOf()) { _, _, newValue ->
    newValue.filter { symtab[it.name] == null }.forEach { defn ->
      symtab[defn.name] = defn
    }
  }

  init {
    ScalarSymbols.values().map {
      it.typeDef.name to it.typeDef
    }.also { symtab.putAll(it) }
  }

  val schemaTypes get() = definitions.toSet()

  private val schemaRules = listOf<SchemaRule>(
      `duplicate type names check`(),
      `unique supertype declarations`(),
      `type name does not match scalar primitive`()
  )

  private val scopedSymbolRules = listOf<SymbolScopeRule>(
      `no duplicate symbol names`()
  )

  fun compile() {

    definitions += GraphQLsLexer(config.schema).parse()
    definitions.forEach { symtab[it.name] = it }

    // intermediate
    attrFieldTypes()
    attrInheritance()
    attrUnions()

    schemaRules.onEach { it(definitions) }
    inspectFields(*scopedSymbolRules.toTypedArray())
  }

  /**
   * Fix for [UnionDef] class signature for implicit delegation
   */
  fun toKotlinApi(): String {

    if (definitions.isEmpty())
      compile()
    if (definitions.isEmpty())
      throw IllegalArgumentException("No schema types defined")

    val specs = definitions.associate { it to it.toKotlin() }

    val sourceClasses = specs.map { (ir, kotlinSpec) ->
      var exact = kotlinSpec.toString()

      if (kotlinSpec.superinterfaces.find {
        it.toString().contains(SchemaType.CLASS_DELEGATE_MARKER)
      } != null) exact = exact.replace(SchemaType.CLASS_DELEGATE_MARKER,
          " by ${ir.schemaTypeClass.qualifiedName}.new()")
          .replace("^import.*\n".toRegex(), "")

      exact = exact.replace(FieldDefinition.OUT_VARIANCE_MARKER, "out")

      return@map exact
    }.joinToString("\n\n") { it }

    val metadata = FileSpec.builder(config.packageName, config.kotlinFileName).apply {
      definitions.forEach { addType(it.toKotlin()) }
    }.build().let { if (it.packageName.isNotEmpty()) "package ${it.packageName}\n\n" else "\n" }

    return metadata + sourceClasses
  }

  private fun attrFieldTypes() = definitions.filterIsInstance<ScopedDeclarationType>()
      .flatMap(ScopedDeclarationType::expandSymbols)
      .forEach { (symbol, typeContext) ->
        symbol.type = this@GraphQLCompiler.symtab[symbol.typeName] ?: throw symbol.unknownTypeExc(typeContext)
      }

  private fun attrUnions() {
    fun UnionDef.setLateinitPossibilities(defs: Set<TypeDef>) {
      possibilities = defs
    }

    definitions.filterIsInstance<UnionDef>().forEach {

      it.types.map {
        symtab[it] as TypeDef
      }.also { options ->
        it.setLateinitPossibilities(options.toSet())
      }
    }
  }


  /**
   * Apply validation to a set of symbols within the same scope */
  private fun inspectFields(vararg rules: SymbolScopeRule) {
    definitions.on<ScopedDeclarationType> {
      rules.forEach { rule -> rule(fields, this@on) }
    }
  }

  class Configuration private constructor(val schema: Schema) {

    var packageName: String = ""

    var kotlinFileName: String = "GraphQL.kt"

    companion object {
      internal fun fromContext(schema: Schema, scope: Configuration.() -> Unit): Configuration =
          Configuration(schema).apply(scope)
    }
  }
}

/** Require each type has a different name */
private fun `duplicate type names check`(): SchemaRule = {
  // TODO is this really that inefficient? Github schema is 300+ definitions....
  forEach { defn -> require(count { it.name == defn.name } == 1) }
}

private fun `type name does not match scalar primitive`(): SchemaRule = {
  forEach { defn ->
    require(ScalarSymbols.named[defn.name] == null) {
      "Illegal schema declaration name '${defn.name}' at${defn.name}"
    }
  }
}

private fun `unique supertype declarations`(): SchemaRule = {
  on<TypeDef> {
    require(supertypes.distinct().size == supertypes.size) {
      "Illegal supertype declaration at type $name (duplicate declaration)"
    }
  }
}

/** Require each symbol name to be unique within its enclosing scope */
private fun `no duplicate symbol names`(): SymbolScopeRule = {
  forEach { symbol ->
    require(count { it.name == symbol.name } == 1)
  }
}

private fun Token.toCoordinates() = "[${this.line},${this.startIndex}]"

inline fun <reified T> Collection<*>.on(action: T.() -> Unit) = this.filterIsInstance<T>().forEach(action)

private fun ScopedSymbol.unknownTypeExc(idlContext: ScopedDeclarationType) = IllegalArgumentException(
    "Unknown type '$typeName' for field ${idlContext.name}::$name at " + context.start.toCoordinates()
)

private fun ScopedDeclarationType.expandSymbols() = run {
  listOf<Iterable<ScopedSymbol>>(this@expandSymbols.fields,
      this@expandSymbols.fields.flatMap { it.arguments })
      .flatten()
      .zip(generateSequence { this@expandSymbols }
          .asIterable())
}

