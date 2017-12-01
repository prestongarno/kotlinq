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

package com.prestongarno.kotlinq.core.compiler

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
      |object User : com.prestongarno.kotlinq.core.QType {
      |  val name: com.prestongarno.kotlinq.core.stubs.StringDelegate.Query by com.prestongarno.kotlinq.core.QSchemaType.QScalar.String.stub()
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
      |object User : com.prestongarno.kotlinq.core.QType {
      |  val value: com.prestongarno.kotlinq.core.stubs.FloatDelegate.Query by com.prestongarno.kotlinq.core.QSchemaType.QScalar.Float.stub()
      |
      |  val friends: com.prestongarno.kotlinq.core.stubs.TypeListStub.Query<User> by com.prestongarno.kotlinq.core.QSchemaType.QTypes.List.stub<User>()
      |}
      |""".trimMargin("|")

    assertThat(result)
        .isEqualTo(expect)
  }

}

