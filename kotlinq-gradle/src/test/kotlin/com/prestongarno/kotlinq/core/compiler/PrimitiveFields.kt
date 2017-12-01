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

import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.stubs.BooleanDelegate
import com.prestongarno.kotlinq.core.stubs.FloatDelegate
import com.prestongarno.kotlinq.core.stubs.IntDelegate
import com.prestongarno.kotlinq.core.stubs.StringDelegate
import org.junit.Test

class PrimitiveFields : JavacTest() {

  @Test fun `single integer field compiles and returns correct type`() {
    val loader = jvmCompileAndLoad("""
      |type Definition {
      |  value: Int
      |}
      """.trimMargin("|"), "com.test")


    loader.loadClass("com.test.Definition") {
      this directlyImplements QType::class

      kprop("value") {
        it requireReturns IntDelegate.Query::class
      }
    }
  }

  @Test fun `string field returns correct type`() {
    val loader = jvmCompileAndLoad("""
      |type Def2 {  fieldValue: String
      |}
      |""".trimMargin("|"))

    loader.loadClass("Def2") {
      this directlyImplements QType::class

      kprop("fieldValue") {
        it requireReturns StringDelegate.Query::class
      }
    }
  }

  @Test fun `float field returns correct type`() {
    val loader = jvmCompileAndLoad("""
      |type Def35{
      |floatfield: Float
      |}
      |""".trimMargin("|"))

    loader.loadClass("Def35") {
      this directlyImplements QType::class

      kprop("floatfield") {
        it requireReturns FloatDelegate.Query::class
      }
    }

  }

  @Test fun `boolean field returns correctly`() {
    val schemaClass = jvmCompileAndLoad("""
      |type StarWars                                                  {
      |                    boo: Boolean
      |                                   }
      |""".trimMargin("|"))
        .loadObject("StarWars")::class

    schemaClass directlyImplements QType::class
    schemaClass.kprop("boo") {
      it requireReturns BooleanDelegate.Query::class
    }
  }
}
