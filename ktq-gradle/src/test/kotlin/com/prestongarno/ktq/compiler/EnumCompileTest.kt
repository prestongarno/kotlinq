package com.prestongarno.ktq.compiler

import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.compiler.KTypeSubject.Companion.argumentsMatching
import com.prestongarno.ktq.compiler.KTypeSubject.Companion.reifiedArgumentsMatching
import com.prestongarno.ktq.stubs.EnumStub
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
      """.trimMargin("|"), "", System.out)
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
      gqlEnum requireReturns EnumStub.Query::class
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
      it requireReturns EnumStub.OptionalConfigQuery::class
      it.returnType mustHave argumentsMatching("GraphQLEnum", "Bar.EnumPropArgs")
    }
  }.ignore()
}