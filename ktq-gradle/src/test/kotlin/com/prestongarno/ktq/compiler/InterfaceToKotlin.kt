package com.prestongarno.ktq.compiler

import org.junit.Test

class InterfaceToKotlin : JavacTest() {


  @Test fun interfaceKotlinCodeGen() {
    val schema = """
      |
      |interface Droid {
      |  uuid: String!
      |}
      |
      |type Cyborg {
      |  uuid: String!
      |  humanName: String!
      |}
      |
      |type Astromech { uuid: String! }
      |
      """.trimMargin("|")

    val expect = """
      |
      |interface Droid : com.prestongarno.ktq.QType, com.prestongarno.ktq.QInterface {
      |  val uuid: com.prestongarno.ktq.stubs.StringDelegate.Query
      |}
      |
      |
      |object Cyborg : com.prestongarno.ktq.QType {
      |  val uuid: com.prestongarno.ktq.stubs.StringDelegate.Query by com.prestongarno.ktq.QSchemaType.QScalar.String.stub()
      |
      |  val humanName: com.prestongarno.ktq.stubs.StringDelegate.Query by com.prestongarno.ktq.QSchemaType.QScalar.String.stub()
      |}
      |
      |
      |object Astromech : com.prestongarno.ktq.QType {
      |  val uuid: com.prestongarno.ktq.stubs.StringDelegate.Query by com.prestongarno.ktq.QSchemaType.QScalar.String.stub()
      |}
      |""".trimMargin("|")

    expect eq compileOut(schema)

    val loader = jvmCompileAndLoad(schema, "")

    loader.loadClass("Droid") {

      kprop("uuid") {
        isAbstract eq true
      }
    }
  }
}
