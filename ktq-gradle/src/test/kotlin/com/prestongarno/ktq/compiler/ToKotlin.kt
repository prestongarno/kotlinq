package com.prestongarno.ktq.compiler

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ToKotlin {

  // yay!
  @Test fun `simple type to kotlin`() {
    val mockSchema = """
      |
      |type User {
      |  name: String
      |}
    """.trimMargin("|")

    compileOut(mockSchema) eq """
      |
      |object User : com.prestongarno.ktq.QType {
      |  val name: com.prestongarno.ktq.stubs.StringDelegate.Query by com.prestongarno.ktq.QSchemaType.QScalar.String.stub()
      |}
      |""".trimMargin("|")
  }

  @Test fun `two field with type field`() {
    val result = compileOut("""
      |
      |type User {
      |  value: Float
      |  friends: [User]
      |}
      |""".trimMargin("|"))

    val expect = """
      |
      |object User : com.prestongarno.ktq.QType {
      |  val value: com.prestongarno.ktq.stubs.FloatDelegate.Query by com.prestongarno.ktq.QSchemaType.QScalar.Float.stub()
      |
      |  val friends: com.prestongarno.ktq.stubs.TypeListStub.Query<User> by com.prestongarno.ktq.QSchemaType.QTypes.List.stub<User>()
      |}
      |""".trimMargin("|")

    assertThat(result)
        .isEqualTo(expect)
  }

}

