package com.prestongarno.kotlinq.compiler

import org.junit.Test
import kotlin.reflect.KClass


class KeywordsAsSymbols {

  @Test fun `single line schema with keywords as symbols compiles successfully`() {


    // Get schema
    val schema = "scalar MyCustomScalar " +
        "type Foo implements FooBarEnterprise, Bar { enum(input: String!): Int bar: String! scalar: Int string: Foo type: Int d: Int } " +
        "type Input { enum: [[Int]] } " +
        "scalar FOO " +
        "interface FooBarEnterprise { d: Int } " +
        "union SampletypeUnion = Foo | Input " +
        "interface Bar { enum(input: String!): Int }"

    GraphQLCompiler(StringSchema(schema)).toKotlinApi()

    val allTypes = GraphQLCompiler(StringSchema(schema)).apply(GraphQLCompiler::compile).schemaTypes
        .map { it.name to it }
        .toMap()

    allTypes containsEntry "Foo" withType matching<TypeDef>()
    allTypes containsEntry "FooBarEnterprise" withType matching<InterfaceDef>()
    allTypes containsEntry "Bar" withType matching<InterfaceDef>()
    allTypes containsEntry "FOO" withType matching<ScalarDef>()
    allTypes containsEntry "MyCustomScalar" withType matching<ScalarDef>()
    allTypes containsEntry "SampletypeUnion" withType matching<UnionDef>()
    allTypes containsEntry "Input" withType matching<TypeDef>()

    allTypes.entries.size eq 7

    (allTypes["Foo"]!! as TypeDef).apply {
      supertypes.forEachIndexed { index, iface ->
        when (index) {
          0 -> require(iface == allTypes["FooBarEnterprise"])
          1 -> require(iface == allTypes["Bar"])
        }
      }
      fields.forEachIndexed { index, field ->
        when (index) {
          0 -> field.apply {
            requiresConfiguration eq true
            inheritsFrom.size eq 1
            inheritsFrom.first().name eq "Bar"
            arguments.size eq 1
            arguments.first().apply {
              isAbstract eq false
              type.name eq "String"
              name eq "input"
              nullable eq false
            }
          }
        }
      }
    }
  }

  @Test fun `github public api compilation test`() {

    val schema = this::class.java.classLoader
        .getResourceAsStream("graphql.schema.graphqls")
        .reader()
        .readText()

    GraphQLCompiler(StringSchema(schema)).apply {
      compile()
      schemaTypes.count() eq 312
    }
  }
}

private infix fun Map<String, SchemaType>.containsEntry(name: String) =
    this[name] ?: throw IllegalArgumentException("No such type '$name'")

private infix fun <T: SchemaType> SchemaType.withType(clazz: KClass<T>) = require(this::class == clazz)

private inline fun <reified T: SchemaType> matching() = T::class