/*
 * Copyright (C) 2018 Preston Garno
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

class ToGraphQLTests2 {

  @Test fun `Droid nested fragments query`() {

    val query = StarWarsQuery(query = "R2D2") {
      onDroid(::DroidModel)
      onHuman(::HumanModel)
    }

    assertThat(query.toGraphql(pretty = true))
        .isEqualTo("""
          {
            search(text: "R2D2") {
              __typename
              ...fragDroid0
              ...fragHuman1
            }
          }
          fragment fragDroid0 on Droid {
            name
            primaryFunction
            friendsConnection {
              totalCount
              friends {
                __typename
                ...fragHuman1
                ...fragDroid2
              }
            }
            appearsIn
          }
          fragment fragDroid2 on Droid {
            name
            primaryFunction
          }
          fragment fragHuman1 on Human {
            name
            mass
            friendsConnection {
              totalCount
              friends {
                __typename
                ...fragDroid2
              }
            }
          }
          """.trimIndent())
  }
}
