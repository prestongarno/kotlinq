@file:SuppressWarnings("UNUSED_VARIABLE")
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

import com.google.common.truth.Truth.assertThat
import org.intellij.lang.annotations.Language
import org.junit.Test
import com.prestongarno.kotlinq.core.eq

/*
 * Four simple test cases using the kolinq-core library which covers
 * the front end of a GraphQL client: making requests.
 *
 * Using classes from the (build-time generated) test module package [com.languages]
 *
 * Implemented subclasses for making queries are at the end of the file
 *
 * GraphQL SDL file is in the kotlinq-test-api module resources folder
 *
 * @author prestongarno
 */
class TechSchemaQueryTests {

  @Test fun `query fragment on programming lang type`() {

    val query = TechnologyQuery(searchTerm = "kotlin", limit = 9000) {
      onProgrammingLanguage(::Language)
    }

    val result = query.toGraphql(pretty = true)

    val expected = """
      {
        search(term: "kotlin",limit: 9000) {
          __typename
          ...fragProgrammingLanguage0
        }
      }
      fragment fragProgrammingLanguage0 on ProgrammingLanguage {
        name
        primaryFeatures {
          name
          description
        }
        isTyped
        since
      }
      """.trimIndent()

    assertThat(result).isEqualTo(expected)
  }

  @Test fun `query on operating system`() {

    val query = TechnologyQuery(searchTerm = "Windows", limit = 69) {
      onOperatingSystem(::OperatingSys)
    }

    val result = query.toGraphql(pretty = true)

    @Language("GraphQL")
    val expect = """
      {
        search(term: "Windows",limit: 69) {
          __typename
          ...fragOperatingSystem0
        }
      }
      fragment fragOperatingSystem0 on OperatingSystem {
        name
        since
        archSupport
      }
      """.trimIndent()
  }

  @Test fun `query fragment programming lang and operating system`() {

    val query = TechnologyQuery(searchTerm = "unix", limit = 35) {
      onProgrammingLanguage(::Language)
      onOperatingSystem(::OperatingSys)
    }

    val result = query.toGraphql(pretty = true)

    @Language("GraphQL") val expect = """
      {
        search(term: "unix",limit: 35) {
          __typename
          ...fragProgrammingLanguage0
          ...fragOperatingSystem1
        }
      }
      fragment fragOperatingSystem1 on OperatingSystem {
        name
        since
        archSupport
      }
      fragment fragProgrammingLanguage0 on ProgrammingLanguage {
        name
        primaryFeatures {
          name
          description
        }
        isTyped
        since
      }
      """.trimIndent()

    assertThat(result).isEqualTo(expect)
  }

}
