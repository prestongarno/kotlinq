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

@file:Suppress("UNUSED_VARIABLE")

package com.prestongarno.kotlinq.compiler

import com.google.common.truth.Truth.assertThat
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

        object Droid : com.prestongarno.kotlinq.core.schema.QType {
          val uuid: com.prestongarno.kotlinq.core.schema.stubs.StringDelegate.Query by com.prestongarno.kotlinq.core.QSchemaType.QScalar.String.stub()
        }


        object Actor : com.prestongarno.kotlinq.core.schema.QUnionType by com.prestongarno.kotlinq.core.schema.QUnionType.new() {
          fun onJedi(init: () -> com.prestongarno.kotlinq.core.QModel<Jedi>) {
            on(init)}

          fun onDroid(init: () -> com.prestongarno.kotlinq.core.QModel<Droid>) {
            on(init)}
        }


        object Jedi : com.prestongarno.kotlinq.core.schema.QType {
          val name: com.prestongarno.kotlinq.core.schema.stubs.StringDelegate.Query by com.prestongarno.kotlinq.core.QSchemaType.QScalar.String.stub()
        }

        """.trimIndent()

    assertThat(result).isEqualTo(expect)
    result eq expect

    val loader = jvmCompileAndLoad(schema.trimMargin("|"), "com.test").apply {

      loadClass("com.test.Actor") {

        func("onDroid") { it.parameters[0].toString() eq "instance of fun com.test.Actor.onDroid(" +
                  "() -> com.prestongarno.kotlinq.core.QModel<com.test.Droid>): kotlin.Unit" }

        func("onJedi") { it.parameters[0].toString() eq "instance of fun com.test.Actor.onJedi(" +
                  "() -> com.prestongarno.kotlinq.core.QModel<com.test.Jedi>): kotlin.Unit" }

      }
    }
  }





}
