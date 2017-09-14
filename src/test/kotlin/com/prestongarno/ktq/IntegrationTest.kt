package com.prestongarno.ktq

import com.google.common.truth.Truth
import com.prestongarno.ktq.compiler.QCompiler
import org.jetbrains.kotlin.preprocessor.mkdirsOrFail
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import java.io.File
import java.util.concurrent.ThreadLocalRandom
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

  @Test fun generateSourceToPayload() {
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

    Truth.assertThat(QModel(obj).allGraphQl())
        .isEqualTo("""
          |{
          |  contents
          |}
          """.trimMargin("|"))
  }

  @Test fun generateMultiFieldTypes() {
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

    Truth.assertThat(QModel(obj).allGraphQl())
        .isEqualTo("""
          |{
          |  contents,
          |  orderNumber,
          |  weight
          |}
          """.trimMargin("|"))
  }

  @Test fun generateMultiFieldSelected() {
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

    Truth.assertThat(QModel(obj).mockGraphql(listOf("weight")))
        .isEqualTo("""
          |{
          |  weight
          |}
          """.trimMargin("|"))
  }

  @Test fun generateMutation() {
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

    Truth.assertThat(QModel(obj).mockInputGraphql("addToppingMutation",
        mapOf(Pair("selection", listOf("burger", "lettuce", "tomato", "ketchup", "pickles")))).toGraphql())
        .isEqualTo("""
          |{
          |  addToppingMutation(selection: [ "burger", "lettuce", "tomato", "ketchup", "pickles" ])
          |}
          """.trimMargin("|"))
  }

  @Test fun recursiveTypes() {
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

  @Test
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

       Truth.assertThat(user::class.isInstance(actor))
       Truth.assertThat(QModel(user).allGraphQl())
           .isEqualTo("""
          |{
          |  email,
          |  login
          |}
          """.trimMargin("|"))
     }


  }

  @Test fun inheritanceTestMultiple() {
    QCompiler.initialize()
        .packageName(PACK)
        /* TODO = Fail a compile on the gradle when nonnullable
         super-field with nullable inherited? */
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

      Truth.assertThat(user::class.isInstance(actor))
      Truth.assertThat(user::class.isInstance(node))

      Truth.assertThat(QModel(user).allGraphQl())
          .isEqualTo(("""
            |{
            |  email,
            |  login,
            |  uid
            |}
            """.trimMargin("|")))
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

fun String.out() = println(this)

