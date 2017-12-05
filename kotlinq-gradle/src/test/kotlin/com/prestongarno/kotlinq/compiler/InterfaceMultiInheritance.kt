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

class InterfaceMultiInheritance {

  @Test fun `multi inherited field is assigned superinterfaces from all superinterface argbuilder types`() {

    val schema = """
      |
      |interface Entity {
      |  friends(query: String, first: Int, after: ID): [Entity]
      |}
      |
      |scalar ID
      |
      |interface Actor {
      |  friends(first: Int, after: ID): [Entity]
      |}
      |
      |type Wookie implements Entity, Actor {
      |  friends(query: String, first: Int, after: ID): [Entity]
      |}
      |
      |""".trimMargin("|")

    assertThrows<NullPointerException> {
      compileOut(schema)
    }
  }
}