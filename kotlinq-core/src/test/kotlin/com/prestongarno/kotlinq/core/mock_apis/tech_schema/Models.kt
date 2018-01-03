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

@file:Suppress("unused")

package com.prestongarno.kotlinq.core.mock_apis.tech_schema

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
import java.time.Instant
import com.prestongarno.kotlinq.core.eq


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

/************************************************************
 *
 * Mock Implementation classes covering basic use cases of graphql spec
 *
 ***********************************************************/

class TechnologyQuery(
    searchTerm: String = "kotlin",
    limit: Int = 10,
    block: SoftwareComponent.() -> Unit
) : QModel<Query>(Query) {

  val query: QModel<*>? by model.search(Query.SearchArgs(searchTerm, limit)) {
    fragment(block)
  }

}


class Language : QModel<ProgrammingLanguage>(ProgrammingLanguage) {

  val name: String by model.name

  val features: List<FeatureModel> by model.primaryFeatures.query(::FeatureModel)

  val isTyped: Boolean by model.isTyped

  // Custopm deserializing from graphql fields
  val since: java.util.Date by model.since.map(StringScalarMapper(
      Date::parseFromQuery))

}

class OperatingSys : QModel<OperatingSystem>(OperatingSystem) {

  val name: String by model.name

  val since: java.util.Date by model.since.map(StringScalarMapper(
      Date::parseFromQuery))

  val architecture by model.archSupport { default = Architecture.X86_64 }

}

class FrameworkModel(
    dependencyScope: SoftwareComponent.() -> Unit
) : QModel<Framework>(Framework) {

  val name: String by model.name

  val languagesUsed: List<Language> by model.languagesUsed.query(::Language)

  val dependencies: List<QModel<*>> by model.dependencies {
    fragment(dependencyScope)
  }
}


class FeatureModel : QModel<Feature>(Feature) {

  val name: String by model.name

  val description: String by model.description

}

@Suppress("unused") fun Date.parseFromQuery(value: String): java.util.Date =
    java.util.Date.from(Instant.parse(value))

