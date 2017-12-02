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

package com.prestongarno.kotlinq.core.type

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.QEnumType
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType.*
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.primitives.eq
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
      val teamMembers by Team.members.query(BasicTypeList::PersonModel)
    }

    query::teamMembers.returnType.arguments
        .firstOrNull()?.type?.classifier eq PersonModel::class
    query.toGraphql() eq "{members{name,age}}"
  }

  @Test fun `configured type list field is possible`() {

    val query = object : QModel<Team>(Team) {
      val members by Team.configMembers
          .query(BasicTypeList::PersonModel)(Team.ConfigMembersArgs(100))
    }
    query::members.returnType.arguments
        .firstOrNull()?.type?.classifier eq PersonModel::class
    query.toGraphql() eq "{configMembers(limit: 100){name,age}}"
  }
}