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
import com.prestongarno.kotlinq.core.getFragments
import com.prestongarno.kotlinq.core.primitives.eq
import org.junit.Test


/**
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
      |{
      |  search(term: \"kotlin\",limit: 9000) {
      |    ... on ProgrammingLanguage {
      |      name
      |      primaryFeatures {
      |        name
      |        description
      |      }
      |      isTyped
      |      since
      |    }
      |  }
      |}
      """.trimMargin("|")

    assertThat(result).isEqualTo(expected)
  }

  @Test fun `query on operating system`() {

    val query = TechnologyQuery(searchTerm = "Windows", limit = 69) {
      onOperatingSystem(::OperatingSys)
    }

    val result = query.toGraphql(pretty = true)

    val expect = """
      |{
      |  search(term: \"Windows\",limit: 69) {
      |    ... on OperatingSystem {
      |      name
      |      since
      |      archSupport
      |    }
      |  }
      |}
      """.trimMargin("|")
  }

  @Test fun `query fragment programming lang and operating system`() {

    val query = TechnologyQuery(searchTerm = "unix", limit = 35) {
      onProgrammingLanguage(::Language)
      onOperatingSystem(::OperatingSys)
    }

    val result = query.toGraphql(pretty = true)

    val expect = """
      |{
      |  search(term: \"unix\",limit: 35) {
      |    ... on ProgrammingLanguage {
      |      name
      |      primaryFeatures {
      |        name
      |        description
      |      }
      |      isTyped
      |      since
      |    }
      |    ... on OperatingSystem {
      |      name
      |      since
      |      archSupport
      |    }
      |  }
      |}
      """.trimMargin("|")

    assertThat(result).isEqualTo(expect)
  }

  @Test fun `nested fragment multiple levels on framework`() {


    val query = TechnologyQuery(searchTerm = "python") {
      onFramework {
        FrameworkModel {
          onProgrammingLanguage(::Language)
          onOperatingSystem(::OperatingSys)
          onFramework(::NestedFrameworkModel)
        }
      }
    }
    val query2 = TechnologyQuery(searchTerm = "python") {
      onFramework {
        FrameworkModel {
          onProgrammingLanguage(::Language)
          onOperatingSystem(::OperatingSys)
          onFramework(::NestedFrameworkModel)
        }
      }
    }
    query.hashCode() eq query2.hashCode()
    query eq query2

    val result = query.toGraphql(pretty = true)

    // 5 fragments in query, but 2 are exactly equal
    assertThat(query.getFragments().size)
        .isEqualTo(4)

    val expect = """
      |{
      |  search(term: \"python\",limit: 10) {
      |    ... on Framework {
      |      name
      |      languagesUsed {
      |        name
      |        primaryFeatures {
      |          name
      |          description
      |        }
      |        isTyped
      |        since
      |      }
      |      dependencies {
      |        ... on ProgrammingLanguage {
      |          name
      |          primaryFeatures {
      |            name
      |            description
      |          }
      |          isTyped
      |          since
      |        }
      |        ... on OperatingSystem {
      |          name
      |          since
      |          archSupport
      |        }
      |        ... on Framework {
      |          name
      |          platform {
      |            ... on ProgrammingLanguage {
      |              name
      |              primaryFeatures {
      |                name
      |                description
      |              }
      |              isTyped
      |              since
      |            }
      |            ... on OperatingSystem {
      |              name
      |              since
      |              archSupport
      |            }
      |          }
      |          primaryFeatures {
      |            name
      |            description
      |          }
      |        }
      |      }
      |    }
      |  }
      |}
      """.trimMargin("|")
  }


}
