package com.prestongarno.ktq

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.yelp.Business
import com.prestongarno.ktq.yelp.Businesses
import com.prestongarno.ktq.yelp.Coordinates
import org.junit.Test

@Suppress("unused") class YelpPayloadTests {

  @Test fun assertSingleRequestCompiles() {
    val sample = object : QModel<Business>(Business) {
      val businessName by model.name.withDefault("FooBar Enterprises")
      val businessPhone by model.phone
    }

    assertThat(sample.toGraphql())
        .isEqualTo("""
          |{
          |  name,
          |  phone
          |}
          """.trimMargin("|"))
  }

  @Test fun assertRequestCompilerTwo() {

    val businessListModel = object : QModel<Businesses>(Businesses) {

      val amountOf by model.total
      val nodes by model.business.init { object : QModel<Business>(Business) {

          val name by model.name.withDefault("")
          val displayPhonw by model.display_phone

          val displayCoord by model.coordinates.init { object : QModel<Coordinates>(Coordinates) {
              val lat by model.latitude;
              val long by model.longitude
            }
          }
        }
      }
    }

    assertThat(businessListModel.toGraphql())
        .isEqualTo("""
          |{
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
}

fun String.out() = println(this)
