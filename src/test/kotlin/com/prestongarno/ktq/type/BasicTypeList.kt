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

package com.prestongarno.ktq.type

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.primitives.eq
import org.junit.Test

object Team : QType {

  val members by QTypes.List.stub<Person>()

  val configMembers by QTypes.List.configStub<Person, ConfigMembersArgs>()

  class ConfigMembersArgs(limit: Int) : ArgBuilder() {

    init {
      "limit" with limit
    }

    var first : Int? = null
    var orderBy : ListOrder? = null
  }

}

enum class ListOrder : QEnumType {
  ASCENDING, DESCENDING
}

class BasicTypeList {

  class PersonModel : QModel<Person>(Person) {
    val name by model.name
    val age by model.age
  }

  @Test fun `type list field is possible`() {
    
    val query = object : QModel<Team>(Team) {
      val teamMembers by model.members.query(::PersonModel)
    }

    query::teamMembers.returnType.arguments
        .firstOrNull()?.type?.classifier eq PersonModel::class
    query.toGraphql(false) eq "{members{name,age}}"
  }

  @Test fun `configured type list field is possible`() {

    val query = object : QModel<Team>(Team) {
      val members by model.configMembers
          .query(::PersonModel)(Team.ConfigMembersArgs(100))
    }
    query::members.returnType.arguments
        .firstOrNull()?.type?.classifier eq PersonModel::class
    query.toGraphql(false) eq "{configMembers(limit: 100){name,age}}"
  }
}