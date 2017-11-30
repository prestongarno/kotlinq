package com.prestongarno.ktq.compiler

import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QUnionType
import com.prestongarno.ktq.org.antlr4.gen.GraphQLSchemaLexer
import com.prestongarno.ktq.org.antlr4.gen.GraphQLSchemaParser
import com.squareup.kotlinpoet.FileSpec
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Token
import java.io.File
import kotlin.properties.Delegates

typealias SchemaRule = Set<SchemaType<*>>.() -> Unit
typealias SymbolScopeRule = Set<ScopedSymbol>.(ScopedDeclarationType<*>) -> Unit

/** TODO use antlr4's listener hooks to stop unnecessary iteration for validation rules */
class GraphQLCompiler(
    private val schema: Schema,
    private val scope: Configuration.() -> Unit = { /* nothing */ }
) {

  val config = Configuration.fromContext(schema, scope)

  /** This is kept in sync by the [GraphQLCompiler.definitions] variable. Do NOT add definitions here */
  internal val symtab: MutableMap<String, SchemaType<*>> = ScalarPrimitives.values().map {
    it.typeDef.name to it.typeDef
  }.toMap(mutableMapOf())

  private val definitions: MutableSet<SchemaType<*>> by Delegates.observable(mutableSetOf()) { _, _, newValue ->
    newValue.filter { symtab[it.name] == null }.forEach { defn ->
      symtab[defn.name] = defn
    }
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

    // Get schema
    val input: CharStream = when (config.schema) {
      is StringSchema -> CharStreams.fromString(config.schema.source)
      is FileSchema -> CharStreams.fromPath(File(config.schema.path).toPath())
    }

    // Parse/lexify it
    val lexer = GraphQLSchemaLexer(input)
    val stream = CommonTokenStream(lexer)
    val parser = GraphQLSchemaParser(stream)

    val result = parser.graphqlSchema()

    // Add type definitions to context
    definitions += result.enumDef().map { EnumDef(it) }
    definitions += result.inputTypeDef().map { InputDef(it) }
    definitions += result.interfaceDef().map { InterfaceDef(it) }
    definitions += result.typeDef().map { TypeDef(it) }
    definitions += result.scalarDef().map { ScalarDef(it) }
    definitions += result.unionDef().map { UnionDef(it) }
    symtab.putAll(definitions.map { it.name to it })

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

    val specs = definitions.map(SchemaType<*>::toKotlin)

    val sourceClasses = specs.map {
      var exact = it.toString()

      if (it.superinterfaces.find {
        it.toString().contains(UnionDef.CLASS_DELEGATE_MARKER)
      } != null) exact = exact.replace(UnionDef.CLASS_DELEGATE_MARKER,
          " by ${QUnionType::class.qualifiedName!!}.create()")
          .replace("^import.*\n".toRegex(), "")

      return@map exact
    }.joinToString("\n\n") { it }

    val metadata = FileSpec.builder(config.packageName, config.kotlinFileName).apply {
      definitions.forEach { addType(it.toKotlin()) }
    }.build().let { if (it.packageName.isNotEmpty()) "package ${it.packageName}\n\n" else "\n" }

    return metadata + sourceClasses
  }

  private fun attrFieldTypes() = definitions.filterIsInstance<ScopedDeclarationType<*>>()
      .flatMap(ScopedDeclarationType<*>::expandSymbols)
      .forEach { (symbol, typeContext) ->
        symbol.type = this@GraphQLCompiler.symtab[symbol.typeName] ?: throw symbol.unknownTypeExc(typeContext)
      }

  private fun attrUnions() {
    fun UnionDef.setLateinitPossibilities(defs: Set<TypeDef>) {
      possibilities = defs
    }

    definitions.on<UnionDef> {

      context.unionTypes().typeName().map {
        it.Name().text
      }.map {
        symtab[it] as TypeDef // safe cast - rule will prevent this
      }.also { options ->
        this.setLateinitPossibilities(options.toSet())
      }
    }
  }


  /**
   * Apply validation to a set of symbols within the same scope */
  private fun inspectFields(vararg rules: SymbolScopeRule) {
    definitions.on<ScopedDeclarationType<*>> {
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
    require(ScalarPrimitives.named[defn.name] == null) {
      "Illegal schema declaration name '${defn.name}' at" + defn.context.sourceInterval.a
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

private fun ScopedSymbol.unknownTypeExc(idlContext: ScopedDeclarationType<*>) = IllegalArgumentException(
    "Unknown type '$typeName' for field ${idlContext.name}::$name at " + context.start.toCoordinates()
)

private fun ScopedDeclarationType<*>.expandSymbols() = run {
  listOf<Iterable<ScopedSymbol>>(this@expandSymbols.fields,
      this@expandSymbols.fields.flatMap { it.arguments })
      .flatten()
      .zip(generateSequence { this@expandSymbols }
          .asIterable())
}

