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

package com.prestongarno.ktq.enums

import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.primitives.eq
import org.intellij.lang.annotations.Language
import org.junit.Test

object Data : QType {
  val numberEnums by QEnum.List.stub<Kind>()
}

enum class Kind(val num: Int) : QEnumType {
  ZERO(0),
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9);

  companion object {
    fun fromNum(value: Int): Kind? = Kind.values().find { it.num == value }
  }
}

class BasicEnumLists {

  @Test fun `enum list basic delegate type is possible`() {

    val query = object : QModel<Data>(Data) {
      val numbers by model.numberEnums
    }
    query::numbers.returnType
        .arguments.firstOrNull()?.type?.classifier eq Kind::class
    query.toGraphql() eq "{numberEnums}"
  }

  @Test fun `enum list from response is valid`() {

    val query = object : QModel<Data>(Data) {
      val numbers by model.numberEnums
    }
    query.toGraphql() eq "{numberEnums}"

    @Language("JSON") val response = """
      {
        "numberEnums": [
          "ZERO",
          "ONE",
          "TWO",
          "THREE",
          "FOUR",
          "FIVE",
          "SIX",
          "SEVEN",
          "EIGHT"
        ]
      }
      """

    require(query.onResponse(response))
    query.numbers.size eq 9
    query.numbers.forEachIndexed { index, kind ->
      kind eq Kind.fromNum(index)
    }
  }

}