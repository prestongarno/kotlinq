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

import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.compiler.KTypeSubject.Companion.argumentsMatching
import com.prestongarno.kotlinq.compiler.KTypeSubject.Companion.reifiedArgumentsMatching
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.schema.stubs.EnumStub
import org.junit.After
import org.junit.Before
import org.junit.Test

class EnumCompileTest : JavacTest() {

  lateinit var loader: KtqCompileWrapper

  @Before fun generateClasses() {
    loader = jvmCompileAndLoad("""
      |
      |enum GraphQLEnum { HOT, NOT }
      |
      |type Foo {
      |  enumProperty: GraphQLEnum
      |}
      |
      |type Bar { enumProp(inputObject: Foo): GraphQLEnum }
      """.trimMargin("|"), "")
  }

  @After fun kill() {
    loader.classLoader.close()
  }

  @Test fun `enum exists and has correct options`() = loader.loadClass("Foo") {
    this directlyImplements QType::class

    val enumClazz = loader.loadClass("GraphQLEnum") {
      this directlyImplements Enum::class
      this directlyImplements QEnumType::class
    }

    kprop("enumProperty") { gqlEnum ->
      gqlEnum requireReturns DelegateProvider.NoArgDelegate::class
      gqlEnum.returnType mustHave reifiedArgumentsMatching(enumClazz)
    }
  }.ignore()

  @Test fun `enum field with required arguments`() = loader.loadClass("Bar") {

    val enumClazz = loader.loadClass("GraphQLEnum")

    val propArgs = loader.loadClass("Bar\$EnumPropArgs") {
      kprop("inputObject") { prop ->
        prop requireReturns loader.loadClass("Foo")
      }
    }

    kprop("enumProp") {
      it requireReturns EnumStub.OptionallyConfigured::class
      it.returnType mustHave argumentsMatching("GraphQLEnum", "Bar.EnumPropArgs")
    }
  }.ignore()
}