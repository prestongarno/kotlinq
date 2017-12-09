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

import com.prestongarno.kotlinq.org.antlr4.definitions.GraphQLSchemaParser

/**
 *
 * Strategy for entering field inheritance info for kotlinpoet generic args on fields:
 *   1. enter supertypes to type
 *   2.   cache names from super to concrete type
 *   3. enter fields
 *   4.   get values from local scoped cache
 *   5.   validate
 *   6.   create type info
 *
 */
// TODO pass type && supertype to [registerAsSuper] method for diagnostics
internal fun GraphQLCompiler.attrInheritance() = schemaTypes.on<TypeDef> {

  val fieldSuperTable = fields
      .map(FieldDefinition::newCache)
      .toMap(mutableMapOf())

  this.supertypeNames.map(this@attrInheritance::fromSymtab)
      ?.onEach { supertype ->

        supertype.fields
            .onEach(this::requireOverrides)
            .onEach { supertype.assignArgumentSpec(subType = this, symbol = it.name) }
            .map(this::joiningWithImplementation)
            .map(::second)
            .map(supertype::registerAsSuper)
            .forEach(fieldSuperTable::cacheSymbol)

      }?.toSet()
      ?.let(this@on::setSupertypes) ?: setSupertypes(emptySet())

  fields.onEach(fieldSuperTable::setFieldInheritanceContext)
      .onEach(this::assignArgumentSpec)
      .forEach { field ->
        if (field.inheritsFrom.isNotEmpty() && field.arguments.find { !it.nullable } != null)
          field.inheritsFrom.map { it.symtab[field.name]!! }.forEach(FieldDefinition::flagAsRequiringConfiguration)
      }
}


/** get the supertype from schema type symbol table */
private fun GraphQLCompiler.fromSymtab(sym: String): InterfaceDef {
  val superTypeDef = symtab[sym] ?: throw IllegalArgumentException("Unknown supertype name $sym on type declaration")
  return superTypeDef as? InterfaceDef ?: throw IllegalArgumentException("Supertype declaration '$sym' is not an interface type!")
}

/** explicit lateinit property access */
private fun TypeDef.setSupertypes(def: Set<InterfaceDef>) {
  this.supertypes = def
}

private fun TypeDef.joiningWithImplementation(abstractField: FieldDefinition): Pair<FieldDefinition, FieldDefinition> {
  val concrete = symtab[abstractField.name] ?: throw IllegalArgumentException("Type ${name} does not override abstract " +
      "graphql field ${abstractField.name} declared in ${supertypes.flatMap(InterfaceDef::fields).find {
        it.name == abstractField.name
      }?.name}")
  return abstractField to concrete
}

private fun TypeDef.requireOverrides(abstract: FieldDefinition) {
  val concrete = symtab[abstract.name] ?: throw IllegalArgumentException(
      "Abstract field ${abstract.name} not implemented in type $name")
  require(concrete.run {
    isList == abstract.isList
        && nullable == abstract.nullable
        && type == abstract.type
        && arguments.containsAll(abstract.arguments)
  }) { "Field ${abstract.name} does not override supertype property" }
}

private fun second(pair: Pair<FieldDefinition, FieldDefinition>): FieldDefinition = pair.second

private fun InterfaceDef.registerAsSuper(field: FieldDefinition): Pair<FieldDefinition, InterfaceDef> = field to this

private fun MutableMap<FieldDefinition, MutableSet<InterfaceDef>>.cacheSymbol(pair: Pair<FieldDefinition, InterfaceDef>) {
  require(!pair.first.isAbstract)
  this[pair.first] = this[pair.first]?.apply { add(pair.second) } ?: mutableSetOf(pair.second)
}

private fun MutableMap<FieldDefinition, MutableSet<InterfaceDef>>.setFieldInheritanceContext(field: FieldDefinition) {
  field.inheritsFrom = this[field]?.toSet() ?: emptySet()
}

private fun GraphQLSchemaParser.TypeNameContext.toNameString(): String = Name().text

private fun FieldDefinition.newCache(): Pair<FieldDefinition, MutableSet<InterfaceDef>> = this to mutableSetOf()

private fun TypeDef.assignArgumentSpec(field: FieldDefinition) {
  if (field.arguments.isNotEmpty())
    field.argBuilder = ArgumentSpecDef(field, this)
}

/**
 * Checks the subtype and assigns the supertype field's argbuilder definition (if applicable)
 *
 * The rule is that:
 *
 *    If a single concrete type is enforced configured, all superinterfaces and their fields must also match.
 *         |-> i.e. 'Optionally' configured fields are given lowest priority because they exist only for convenience
 *
 * By now, the field -> superfield inheritance checks have been done
 */
private fun InterfaceDef.assignArgumentSpec(subType: TypeDef, symbol: String) {

  val subField = subType.symtab[symbol]
      ?: throw IllegalArgumentException("Expected overriden subtype field of interface $name on ${subType.name}")
  val superField = symtab[symbol]
      ?: throw IllegalArgumentException("Expected superinterface field '$symbol' on def '$name'")

  val Req_Args = 2
  val Optional_Args = 1
  val No_Args = 0

  fun argumentRequirements(abstractField: FieldDefinition, concreteField: FieldDefinition): Int = when {
    abstractField.arguments.isEmpty()
        && concreteField.arguments.isEmpty() -> No_Args
    concreteField.arguments.isNotEmpty()
        && concreteField.arguments.find { !it.nullable } == null -> Optional_Args
    else -> Req_Args
  }

  fun asConfigStub(field: FieldDefinition) {
    require(field.isAbstract)
    field.argBuilder = ArgumentSpecDef(field, this)
  }


  // Check the **CONCRETE** field for it's argument requirements
  // and set the interface argument builder type based off of that
  // supertype field should match the same 1/3 arg type
  superField.argBuilder = when (argumentRequirements(superField, subField)) {
    Req_Args -> ArgumentSpecDef(superField, this)
    Optional_Args -> ArgumentSpecDef(superField, this)
    No_Args -> null
    else -> throw IllegalStateException("lol")
  }
}















