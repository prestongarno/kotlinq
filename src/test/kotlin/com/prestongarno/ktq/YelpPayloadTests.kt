/*package com.prestongarno.ktq

import com.google.common.truth.Truth
import com.prestongarno.ktq.yelp.Business
import com.prestongarno.ktq.yelp.Businesses
import com.prestongarno.ktq.yelp.Coordinates
import org.jetbrains.kotlin.preprocessor.mkdirsOrFail
import org.junit.Ignore
import org.junit.Test
import java.io.File

const val PACK: String = "com.ktq.yelp"

@Suppress("unused")
class YelpPayloadTests {


  var compileOutputDir: File = File("${System.getProperty("java.io.tmpdir")}/ktq-gradle-temp/").apply {
    mkdirsOrFail()
    deleteOnExit()
  }
  var codegenOutputFile: File = File(compileOutputDir.parent.plus("/KtqKotlinPoet.kt"))

  @Ignore @Test
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

  @Ignore @Test
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
  }
}*/
