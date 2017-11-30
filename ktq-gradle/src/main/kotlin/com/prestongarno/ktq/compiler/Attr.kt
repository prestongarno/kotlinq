package com.prestongarno.ktq.compiler

import com.prestongarno.ktq.org.antlr4.gen.GraphQLSchemaParser
import com.prestongarno.ktq.org.antlr4.gen.GraphQLSchemaParser.*

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
internal fun GraphQLCompiler.attrInheritance() {
  schemaTypes.on<TypeDef> {

    val fieldSuperTable = fields
        .map(FieldDefinition::newCache)
        .toMap(mutableMapOf())

    context.implementationDefs()
        ?.typeName()
        ?.map(TypeNameContext::toNameString)
        ?.map(this@attrInheritance::fromSymtab)
        ?.onEach { supertype ->
          supertype.fields
              .onEach(this::requireOverrides)
              .map(this::joiningWithImplementation)
              .map(::second)
              .map(supertype::registerAsSuper)
              .forEach(fieldSuperTable::cacheSymbol)
        }?.toSet()
        ?.let(this@on::setSupertypes) ?: setSupertypes(emptySet())

    fields.onEach(fieldSuperTable::setFieldInheritanceContext)
        .forEach(this::assignArgBuilder)
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

private fun FieldDefinition.setSupertypes(supers: Set<InterfaceDef>?) {
  this.inheritsFrom = supers ?: emptySet()
}

private fun GraphQLSchemaParser.TypeNameContext.toNameString(): String = Name().text

private fun FieldDefinition.newCache(): Pair<FieldDefinition, MutableSet<InterfaceDef>> = this to mutableSetOf()

private fun TypeDef.assignArgBuilder(field: FieldDefinition) {
  if (field.arguments.isNotEmpty()) field.argBuilder = ArgBuilderDef(field, this)
}
