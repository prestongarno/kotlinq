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

package com.prestongarno.kotlinq.core.formatting

import com.google.common.truth.Truth.assertThat
import com.languages.Architecture
import com.languages.Date
import com.languages.Feature
import com.languages.Framework
import com.languages.OperatingSystem
import com.languages.ProgrammingLanguage
import com.languages.Query
import com.languages.SoftwareComponent
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.custom.StringScalarMapper
import com.prestongarno.kotlinq.core.getFragments
import org.junit.Test
import java.time.Instant


/**
 * Simple test cases covering the front end of a GraphQL client: making requests.
 *
 * Using the generated test module API package [com.languages]
 * Implemented classes using the kotlinq API are at the end of the file
 * GraphQL SDL file is in the kotlinq-test-api module resources folder
 *
 * @author prestongarno
 */
class ProgrammingLanguagesSchemaQueryTests {

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

  // non-recursive nested fragment for ^^^ test to prevent cycle
  // also non-local class because kotlin class fqnames can't be escaped
  class NestedFrameworkModel : QModel<Framework>(Framework) {
    val name by model.name
    val platform by model.platform {
      on(::Language)
      on(::OperatingSys)
    }
    val features by model.primaryFeatures.query(::FeatureModel)
  }

}

/** Mock Implementation classes covering basic use cases in graphql */

class TechnologyQuery(
    searchTerm: String = "kotlin",
    limit: Int = 10,
    block: SoftwareComponent.() -> Unit
) : QModel<Query>(Query) {

  val query by model.search(Query.SearchArgs(searchTerm, limit)) {
    fragment(block)
  }

}


class Language : QModel<ProgrammingLanguage>(ProgrammingLanguage) {

  val name by model.name

  val features by model.primaryFeatures.query(::FeatureModel)

  val isTyped by model.isTyped

  // TODO make this invokable so can deserialize without explicit method call
  val since by model.since.map(StringScalarMapper(
      Date::parseFromQuery))

}

class OperatingSys : QModel<OperatingSystem>(OperatingSystem) {

  val name by model.name

  val since by model.since.map(StringScalarMapper(
      Date::parseFromQuery))

  val architecture by model.archSupport { default = Architecture.X86_64 }

}

class FrameworkModel(
    dependencyScope: SoftwareComponent.() -> Unit
) : QModel<Framework>(Framework) {

  val name by model.name

  val languagesUsed by model.languagesUsed.query(::Language)

  val dependencies by model.dependencies {
    fragment(dependencyScope)
  }
}


class FeatureModel : QModel<Feature>(Feature) {

  val name by model.name

  val description by model.description

}

fun Date.parseFromQuery(value: String): java.util.Date =
    java.util.Date.from(Instant.parse(value))

