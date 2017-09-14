package com.prestongarno.ktq

import com.google.common.truth.Truth
import com.intellij.ui.mac.foundation.ID
import com.prestongarno.ktq.compiler.QCompiler
import com.prestongarno.ktq.compiler.asFile
import com.prestongarno.ktq.yelp.Business
import com.prestongarno.ktq.yelp.Businesses
import com.prestongarno.ktq.yelp.Coordinates
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments
import org.jetbrains.kotlin.cli.common.messages.MessageRenderer
import org.jetbrains.kotlin.cli.common.messages.PrintingMessageCollector
import org.jetbrains.kotlin.cli.jvm.K2JVMCompiler
import org.jetbrains.kotlin.config.Services
import org.jetbrains.kotlin.preprocessor.mkdirsOrFail
import org.junit.Before
import org.junit.Test
import java.io.File
import java.nio.charset.Charset

const val PACK: String = "com.ktq.yelp"

@Suppress("unused")
class YelpPayloadTests {


  lateinit var compileOutputDir: File
  lateinit var codegenOutputFile: File

  init {
    compileOutputDir = File("${System.getProperty("java.io.tmpdir")}/ktq-gradle-temp/").apply {
      mkdirsOrFail()
      deleteOnExit()
    }
    codegenOutputFile = File(compileOutputDir.parent.plus("/KtqKotlinPoet.kt"))
  }

  @Test
  fun assertSingleRequestCompiles() {
    val sample = object : QModel<Business>(Business) {
      val businessName by model.name.withDefault("FooBar Enterprises")
      val businessPhone by model.phone
    }

    Truth.assertThat(sample.toGraphql())
        .isEqualTo("""
          |{
          |  name,
          |  phone
          |}
          """.trimMargin("|"))
  }

  @Test
  fun assertRequestCompilerTwo() {

    val businessListModel = object : QModel<Businesses>(Businesses) {

      val amountOf by model.total
      val nodes by model.business.init {
        object : QModel<Business>(Business) {

          val name by model.name.withDefault("")
          val displayPhonw by model.display_phone

          val displayCoord by model.coordinates.init {
            object : QModel<Coordinates>(Coordinates) {
              val lat by model.latitude
              val long by model.longitude
            }
          }
        }
      }
    }

    Truth.assertThat(
        businessListModel.toGraphql())
        .isEqualTo(""" |{
            |  total,
            |  business{
            |    name,
            |    display_phone,
            |    coordinates{
            |      latitude,
            |      longitude
            |    }
            |  }
            |}
            """.trimMargin("|"))
    businessListModel.toGraphql().out()
  }

  @Test
  fun generateSourceToPayload() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema("""
          |type Taco {
          |  contents: [String]
          |}
          """.trimMargin("|"))
        .compile()
        .writeToFile(codegenOutputFile)

    require(JvmCompile
        .exe(codegenOutputFile, compileOutputDir).getFileTree().let {
        it.size == 1 && it[0].name == "Taco.class"})

  }
  @Test
  fun gitHubIntegration() {
    QCompiler.initialize()
        .packageName(PACK)
        .schema(File("./src/test/resources/graphql.schema.graphqls"))
        .compile()
        .writeToFile(codegenOutputFile)
        .result { println(it) }

    require(JvmCompile
        .exe(codegenOutputFile, compileOutputDir).exists())

  }



}

fun String.out() = println(this)

fun File.getFileTree(): List<File> {
  return this.walkTopDown().asSequence().distinct()
      .filter { it.isFile }
      .toList()
}

