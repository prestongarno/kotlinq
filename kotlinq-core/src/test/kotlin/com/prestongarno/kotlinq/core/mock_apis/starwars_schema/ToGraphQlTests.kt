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

package com.prestongarno.kotlinq.core.mock_apis.starwars_schema

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ToGraphQlTests {

  @Test fun `huge project milestone query`() {

    val query = StarWarsQuery(query = "Han Solo") {
      onHuman(::HumanModel)
    }

    val expect = """
      |{
      |  search {
      |    ... on Human {
      |      name
      |      mass
      |      friendsConnection {
      |        totalCount
      |        friends {
      |          ... on Human {
      |            name
      |            id
      |          }
      |        }
      |      }
      |    }
      |  }
      |}
      """.trimMargin("|")

    assertThat(query.toGraphql(pretty = true))
        .isEqualTo(expect)
  }
}