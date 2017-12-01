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

/*package com.prestongarno.ktq

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.compiler.QCompiler
import org.jetbrains.kotlin.preprocessor.mkdirsOrFail
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import java.io.File
import java.util.concurrent.ThreadLocalRandom
import kotlin.reflect.KClass
import kotlin.reflect.full.allSupertypes
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.superclasses
import kotlin.reflect.jvm.jvmErasure
import kotlin.test.assertTrue
import kotlin.test.fail

class IntegrationTest {

  lateinit var compileOutputDir: File
  lateinit var codegenOutputFile: File

  @Before fun setUp() {
    codegenOutputFile = File("${System.getProperty("java.io.tmpdir")}/KtqKotlin.kt")
    compileOutputDir = File(System.getProperty("java.io.tmpdir") +
        "/ktq-gradle-temp/${ThreadLocalRandom.current().nextLong()}").apply {
      mkdirsOrFail()
      deleteOnExit()
    }
  }

  @After fun tearDown() {
    codegenOutputFile.delete()
    compileOutputDir.deleteRecursively()
  }

  @Ignore @Test fun generateSourceToPayload() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |type Taco {
          |  contents: [String]
          |}
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))
    val obj = KtqCompileWrapper(compileOutputDir).loadObject("$PACK.Taco")

    assertThat(QModel(obj).allGraphQl())
        .isEqualTo("""
          |{
          |  contents
          |}
          """.trimMargin("|"))
  }

  @Ignore @Test fun generateMultiFieldTypes() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |type Taco {
          |  contents: [String]
          |  weight: Float
          |  orderNumber: Int
          |}
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))
    val obj = KtqCompileWrapper(compileOutputDir).loadObject("$PACK.Taco")

    assertThat(QModel(obj).allGraphQl())
        .isEqualTo("""
          |{
          |  contents,
          |  orderNumber,
          |  weight
          |}
          """.trimMargin("|"))
  }

  @Ignore @Test fun generateMultiFieldSelected() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |type Taco {
          |  contents: [String]
          |  weight: Float
          |  orderNumber: Int
          |}
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))
    val obj = KtqCompileWrapper(compileOutputDir).loadObject("$PACK.Taco")

    assertThat(QModel(obj).mockGraphql(listOf("weight")))
        .isEqualTo("""
          |{
          |  weight
          |}
          """.trimMargin("|"))
  }

  @Ignore @Test fun generateMutation() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |type Burger {
          |  addToppingMutation(selection: [String], burger: Burger): Boolean
          |}
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))
    val obj = KtqCompileWrapper(compileOutputDir).loadObject("$PACK.Burger")

    assertThat(QModel(obj).mockInputGraphql("addToppingMutation",
        mapOf(Pair("selection", listOf("burger", "lettuce", "tomato", "ketchup", "pickles")))).toGraphql())
        .isEqualTo("""
          |{
          |  addToppingMutation(selection: [ \"burger\", \"lettuce\", \"tomato\", \"ketchup\", \"pickles\" ])
          |}
          """.trimMargin("|"))
  }

  @Ignore @Test fun recursiveTypes() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |type User {
          |  friends: [User]
          |}
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))
    val obj = KtqCompileWrapper(compileOutputDir).loadObject("$PACK.User")
    // Force a SO error by recursive graphql definition
    val initializer = {
      val res = object : QModel<QSchemaType>(obj) {}
      res.setDelegateProvidingValue("friends", { res })
      res
    }
    try {
      initializer.invoke().allGraphQl()
      fail()
    } catch (expected: Error) {
      require(expected is StackOverflowError) { expected.printStackTrace() }
    }
  }

  @Ignore @Test
  fun inheritanceTestSingle() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |interface Actor {
          |  login: String!
          |}
          |
          |type User implements Actor {
          |  login: String!
          |  email: String
          |}
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))
    KtqCompileWrapper(compileOutputDir).run {
      val user = loadObject("$PACK.User")
      val actor = loadInterface("$PACK.Actor")

      assertTrue(user::class.superclasses.contains(actor))
      assertThat(QModel(user).allGraphQl())
          .isEqualTo("""
          |{
          |  email,
          |  login
          |}
          """.trimMargin("|"))
    }


  }

  @Ignore @Test fun inheritanceTestMultiple() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |interface Actor {
          |  login: String!
          |}
          |
          |interface Node {
          |  uid: String!
          |}
          |
          |type User implements Actor, Node {
          |  login: String!
          |  email: String
          |  uid: String
          |}
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val user = loadObject("$PACK.User")
      val actor = loadInterface("$PACK.Actor")
      val node = loadInterface("$PACK.Node")

      assertTrue(user::class.superclasses.contains(actor))
      assertTrue(user::class.superclasses.contains(node))

      assertThat(QModel(user).allGraphQl())
          .isEqualTo(("""
            |{
            |  email,
            |  login,
            |  uid
            |}
            """.trimMargin("|")))
    }
  }

  @Ignore @Test fun customScalarTest() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |type Foo {
          |  url: URL
          |}
          |
          |scalar URL
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")

      foo::class.declaredMemberProperties.first().run {
        assertTrue(name == "url")
        assertTrue(this.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo).allGraphQl())
          .isEqualTo("""
            |{
            |  url
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun customScalarInterfaceTest() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |interface Bar {
          |  url: URL
          |}
          |
          |type Foo implements Bar{
          |  url: URL
          |}
          |
          |scalar URL
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")
      val bar = loadInterface("$PACK.Bar")

      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(bar))

      foo::class.declaredMemberProperties.first().run {
        assertTrue(name == "url")
        assertTrue(this.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo).allGraphQl())
          .isEqualTo("""
            |{
            |  url
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun customScalarInterfaceMultipleFields() {
        QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |interface Bar {
          |  url: URL
          |}
          |
          |type Foo implements Bar{
          |  number: Int
          |  url: URL
          |}
          |
          |scalar URL
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")
      val bar = loadInterface("$PACK.Bar")

      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(bar))

      foo::class.declaredMemberProperties.find { it.name == "url" }!!.run {
        assertTrue(this.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo).allGraphQl())
          .isEqualTo("""
            |{
            |  number,
            |  url
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun customScalarInheritingMultipleFields() {
            QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |interface Bar {
          |  number: Int
          |  url: URL
          |}
          |
          |type Foo implements Bar  {
          |  number: Int
          |  url: URL
          |}
          |
          |scalar URL
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")
      val bar = loadInterface("$PACK.Bar")

      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(bar))

      foo::class.declaredMemberProperties.find { it.name == "url" }!!.run {
        assertTrue(this.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo).allGraphQl())
          .isEqualTo("""
            |{
            |  number,
            |  url
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun testCustomScalarMultipleCustomFields() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |type Foo {
          |
          |  url: URL
          |
          |    next: URL
          |
          |}
          |
          |scalar URL
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")

      foo::class.declaredMemberProperties.forEach {
        assertTrue(it.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo).allGraphQl())
          .isEqualTo("""
            |{
            |  next,
            |  url
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun testCustomScalarMultipleInherited() {
        QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |interface Bar {
          |  url: URL
          |}
          |
          |interface Baz {
          |  url: URL
          |}
          |type Foo implements Bar, Baz {
          |  url: URL
          |}
          |
          |scalar URL
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")
      val bar = loadInterface("$PACK.Bar")
      val baz = loadInterface("$PACK.Baz")

      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(bar))
      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(baz))

      foo::class.declaredMemberProperties.forEach {
        assertTrue(it.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo).allGraphQl())
          .isEqualTo("""
            |{
            |  url
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun testCustomScalarFieldWithInput() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |scalar URL
          |type Foo {
          |  url(shortened: Boolean): URL
          |}
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")

      foo::class.declaredMemberProperties.forEach {
        assertTrue(it.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo).allGraphQl())
          .isEqualTo("""
            |{
            |  url
            |}
            """.trimMargin("|"))
      assertThat(QModel(foo).mockInputGraphql("url", mapOf(Pair("shortened", true))).toGraphql())
          .isEqualTo("""
            |{
            |  url(shortened: true)
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun testCustomScalarFieldWithMultipleInputArgs() {
        QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |scalar URL
          |type Foo {
          |  url(
          |    shortened: Boolean,
          |    encoding: String
          |  ): URL
          |}
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")

      foo::class.declaredMemberProperties.forEach {
        assertTrue(it.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo).allGraphQl())
          .isEqualTo("""
            |{
            |  url
            |}
            """.trimMargin("|"))
      assertThat(QModel(foo)
          .mockInputGraphql("url", mapOf(
              Pair("shortened", true),
              Pair("encoding", "UTF-8")
          )).toGraphql())
          .isEqualTo("""
            |{
            |  url(encoding: \"UTF-8\",shortened: true)
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun testCustomScalarFieldWithEnumAsArgument() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |scalar URL
          |
          |enum What {
          |  THIS,
          |  THAT,
          |}
          |
          |
          |
          |type Foo {
          |  url(
          |    shortened: Boolean,
          |    what: What
          |  ): URL
          |}
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")
      @Suppress("UNCHECKED_CAST") val what = (loadInterface("$PACK.What") as KClass<Enum<*>>)


      foo::class.declaredMemberProperties.forEach {
        assertTrue(it.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo)
          .mockInputGraphql("url", mapOf(
              Pair("shortened", true),
              Pair("what", what.java.enumConstants[0])
          )).toGraphql())
          .isEqualTo("""
            |{
            |  url(shortened: true,what: THIS)
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun testDiamondTypesWithCustomScalarField() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |interface Bar {
          |  url(shortened: Boolean): URL
          |}
          |
          |interface Baz {
          |  url(shortened: Boolean): URL
          |}
          |type Foo implements Bar, Baz {
          |  url(shortened: Boolean): URL
          |}
          |
          |scalar URL
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")
      val bar = loadInterface("$PACK.Bar")
      val baz = loadInterface("$PACK.Baz")

      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(bar))
      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(baz))

      foo::class.declaredMemberProperties.forEach {
        assertTrue(it.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo)
          .mockInputGraphql("url", mapOf(
              Pair("shortened", true)
          )).toGraphql())
          .isEqualTo("""
            |{
            |  url(shortened: true)
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun testDiamondConflictingFieldsCustomScalar() {
        QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |
          |interface Bar {
          |  url(shortened: Boolean): URL
          |}
          |
          |interface Baz {
          |  url(shortened: Boolean,
          |    encoding: String
          |  ): URL
          |}
          |type Foo implements Bar, Baz {
          |  url(shortened: Boolean,encoding: String): URL
          |}
          |
          |scalar URL
          |
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))

    KtqCompileWrapper(compileOutputDir).run {
      val url = loadObject("$PACK.URL")
      val foo = loadObject("$PACK.Foo")
      val bar = loadInterface("$PACK.Bar")
      val baz = loadInterface("$PACK.Baz")

      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(bar))
      assertTrue(foo::class.allSupertypes.map { it.jvmErasure }.contains(baz))

      foo::class.declaredMemberProperties.forEach {
        assertTrue(it.returnType.arguments[0].type?.jvmErasure == url::class)
      }

      assertThat(QModel(foo)
          .mockInputGraphql("url", mapOf(
              Pair("shortened", true),
              Pair("encoding", "UTF-8")
          )).toGraphql())
          .isEqualTo("""
            |{
            |  url(encoding: \"UTF-8\",shortened: true)
            |}
            """.trimMargin("|"))
    }
  }

  @Ignore @Test fun gitHubIntegration() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema(File("./src/test/resources/graphql.schema.graphqls"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile.exe(codegenOutputFile, compileOutputDir))
  }
}
*/
