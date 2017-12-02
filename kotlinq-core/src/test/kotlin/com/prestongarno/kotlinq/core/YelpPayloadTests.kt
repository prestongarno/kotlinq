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
