package com.prestongarno.ktq.compiler

import org.junit.Test

class UnionToKotlinTest : JavacTest() {

  // todo use class delegate branch for kotlinpoet so fqnames can be replaced with imports
  @Test fun `string matching schema to kotlin`() {
    val schema = """
      |
      |type Droid {uuid: String}
      |
      |union Actor = Jedi | Droid
      |
      |type Jedi {name: String}
      |
      """
    val result = compileOut(schema.trimMargin("|"))

    val expect = """

        object Droid : com.prestongarno.ktq.QType {
          val uuid: com.prestongarno.ktq.stubs.StringDelegate.Query by com.prestongarno.ktq.QSchemaType.QScalar.String.stub()
        }


        object Jedi : com.prestongarno.ktq.QType {
          val name: com.prestongarno.ktq.stubs.StringDelegate.Query by com.prestongarno.ktq.QSchemaType.QScalar.String.stub()
        }


        object Actor : com.prestongarno.ktq.QUnionType by com.prestongarno.ktq.QUnionType.create() {
          fun onJedi(init: () -> com.prestongarno.ktq.QModel<Jedi>) {
            on(init)}

          fun onDroid(init: () -> com.prestongarno.ktq.QModel<Droid>) {
            on(init)}
        }

        """.trimIndent()

    result eq expect

    val loader = jvmCompileAndLoad(schema.trimMargin("|"), "com.test").apply {

      loadClass("com.test.Actor") {

        func("onDroid") { it.parameters[0].toString() eq "instance of fun com.test.Actor.onDroid(" +
                  "() -> com.prestongarno.ktq.QModel<com.test.Droid>): kotlin.Unit" }

        func("onJedi") { it.parameters[0].toString() eq "instance of fun com.test.Actor.onJedi(" +
                  "() -> com.prestongarno.ktq.QModel<com.test.Jedi>): kotlin.Unit" }

      }
    }
  }





}
