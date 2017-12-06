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

import com.facebook.FriendsConnection
import com.facebook.Human
import com.facebook.Query
import com.facebook.SearchResult
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.custom.StringScalarMapper


class StarWarsQuery(query: String, fragments: SearchResult.() -> Unit) : QModel<Query>(Query) {

  val search by model.search {
    fragment(fragments)
  }

}

class HumanModel : QModel<Human>(Human) {

  val name by model.name

  val mass by model.mass

  val friendsConnection by model.friendsConnection.query(::FriendsConn)
}

class FriendsConn : QModel<FriendsConnection>(FriendsConnection) {

  val totalCount by model.totalCount

  val friends by model.friends {
    on(::NestedHumanModel)
  }
}

class NestedHumanModel : QModel<Human>(Human) {
  val name by model.name
  val id by model.id.map(StringScalarMapper { it })
}