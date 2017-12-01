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

import com.prestongarno.kotlinq.core.compiler.KTypeSubject.Companion.reifiedArgumentsMatching
import org.junit.Test

class InputTypes : JavacTest() {

  @Test fun `input type with scalar fields is generated correctly to kotlin`() {
    val schema = """
      |
      |type Query {
      |
      |  search(args: InputDef!): [String]
      |
      |}
      |
      |input InputDef {
      |
      |  stringArg: String
      |
      |  intArg: Int
      |
      |  floatArg: Float
      |
      |  booleanArg: Boolean
      |
      |}
      """.trimMargin("|")

    jvmCompileAndLoad(schema, "com.prestongarno").apply {

      val inputTypeClass = loadClass("com.prestongarno.InputDef") {

        kprop("stringArg") {
          it requireReturns String::class
        }

        kprop("intArg") {
          it requireReturns Int::class
        }

        kprop("floatArg") {
          it requireReturns Float::class
        }

        kprop("booleanArg") {
          it requireReturns Boolean::class
        }

      }

      val argumentsClass = loadClass("com.prestongarno.Query\$SearchArgs") {
        this constructorParametersMatchExactly mapOf("args" to inputTypeClass)
      }

      loadClass("com.prestongarno.Query") {

        kprop("search") {
          it.returnType mustHave reifiedArgumentsMatching(argumentsClass)
        }

      }

    }

  }
}