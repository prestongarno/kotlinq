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
      |interface Droid : com.prestongarno.kotlinq.core.QType, com.prestongarno.kotlinq.core.QInterface {
      |  val uuid: com.prestongarno.kotlinq.core.stubs.StringDelegate.Query
      |}
      |
      |
      |object Cyborg : com.prestongarno.kotlinq.core.QType {
      |  val uuid: com.prestongarno.kotlinq.core.stubs.StringDelegate.Query by com.prestongarno.kotlinq.core.QSchemaType.QScalar.String.stub()
      |
      |  val humanName: com.prestongarno.kotlinq.core.stubs.StringDelegate.Query by com.prestongarno.kotlinq.core.QSchemaType.QScalar.String.stub()
      |}
      |
      |
      |object Astromech : com.prestongarno.kotlinq.core.QType {
      |  val uuid: com.prestongarno.kotlinq.core.stubs.StringDelegate.Query by com.prestongarno.kotlinq.core.QSchemaType.QScalar.String.stub()
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
