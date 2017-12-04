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
import com.languages.OperatingSystem
import com.languages.ProgrammingLanguage
import com.languages.Query
import com.languages.SoftwareComponent
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.custom.StringScalarMapper
import org.junit.Test
import java.time.Instant


/** Implementation classes covering all types in graphql */
class ProgrammingLanguagesQuery(
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


class FeatureModel : QModel<Feature>(Feature) {

  val name by model.name

  val description by model.description

}

fun Date.parseFromQuery(value: String): java.util.Date =
    java.util.Date.from(Instant.parse(value))


class ProgrammingLanguagesSchemaQueryTests {

  @Test fun `query fragment on programminglang type`() {

    val query = ProgrammingLanguagesQuery {
      onProgrammingLanguage(::Language)
    }

    val result = query.toGraphql(pretty = true)

    val expected = """
      |{
      |  search(term: \"kotlin\",limit: 10) {
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
  }
}

