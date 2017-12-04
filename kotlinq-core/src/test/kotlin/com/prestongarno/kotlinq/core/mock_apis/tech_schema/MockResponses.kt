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

package com.prestongarno.kotlinq.core.mock_apis.tech_schema

import com.prestongarno.kotlinq.core.internal.resolve.resolve
import com.prestongarno.kotlinq.core.primitives.eq
import org.junit.Test
import java.util.*

class MockResponses {

  @Test fun `pass me some json please`() {

    val query = TechnologyQuery(searchTerm = "kotlin") {
      onProgrammingLanguage(::Language)
    }

    @org.intellij.lang.annotations.Language("JSON")
    val response = """
      {
        "data": {
          "search": {
              "__typename": "ProgrammingLanguage",
              "name": "Kotlin",
              "primaryFeatures": [{
                "name": "DSLs",
                "description": "hot asf"
              }],
              "isTyped": true,
              "since": "2012-02-19T22:10:51.00Z"
            }
         }
      }
      """.trimMargin()

    query.resolve(response.byteInputStream()) eq true

    val result = query.query as Language

    result.apply {
      name eq "Kotlin"
      features.isNotEmpty() eq true
      features.first().apply {
        name eq "DSLs"
        description eq "hot asf"
      }
      isTyped eq true
      Calendar.getInstance().run {

        time = this@apply.since

        get(Calendar.YEAR) eq 2012
        time = this@apply.since
        get(Calendar.DAY_OF_MONTH) eq 19
        get(Calendar.MONTH) eq 1
        // literally have no clue how this date works
        get(Calendar.HOUR_OF_DAY) eq 16
      }
    }

  }
}