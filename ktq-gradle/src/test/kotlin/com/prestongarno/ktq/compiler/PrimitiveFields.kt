package com.prestongarno.ktq.compiler

import com.prestongarno.ktq.QType
import com.prestongarno.ktq.stubs.BooleanDelegate
import com.prestongarno.ktq.stubs.FloatDelegate
import com.prestongarno.ktq.stubs.IntDelegate
import com.prestongarno.ktq.stubs.StringDelegate
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
