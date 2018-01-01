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

package com.prestongarno.kotlinq.core.interfaces

import com.google.common.truth.Truth.assertThat
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType.QEnum
import com.prestongarno.kotlinq.core.QSchemaType.QInterfaces
import com.prestongarno.kotlinq.core.QSchemaType.QScalar
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.stubs.EnumStub
import com.prestongarno.kotlinq.core.schema.stubs.StringDelegate
import org.intellij.lang.annotations.Language
import org.junit.Test

enum class ConceptType : QEnumType {
  TANGIBLE,
  INTANGIBLE
}

interface Concept : QType, QInterface {
  // TODO -> 3-factor API with argbuilders
  val name : StringDelegate.Query

  val type : EnumStub.Query<ConceptType>
}

object Persistence : Concept {
  override val name by QScalar.String.stub()

  override val type by QEnum.stub<ConceptType>()
}

object Unknown : Concept {
  override val name by QScalar.String.stub()

  override val type by QEnum.stub<ConceptType>()
}

object Random : QType {
  val getRandomConcept by QInterfaces.stub<Concept>()
}

class ReceiverApiTestStructure {

  class MyPersistence : QModel<Persistence>(Persistence) {
    val name by model.name
    val type by model.type
  }
  class UnknownModel : QModel<Unknown>(Unknown) {
    val name by model.name
    val type by model.type
  }

  @Test fun `single interface field query with parsing response`() {
    val query = object : QModel<Random>(Random) {
      // TODO -> not allow a providedelegate without fragmenting!
      val result by Random.getRandomConcept {
        on(ReceiverApiTestStructure::MyPersistence)
        on(ReceiverApiTestStructure::UnknownModel)
      }
    }

    assertThat(query.toGraphql())
        .isEqualTo("{getRandomConcept{" +
            "__typename," +
            "...fragPersistence0" +
            "...fragUnknown1}}" +
            "fragment fragPersistence0 on Persistence{" +
            "name," +
            "type}," +
            "fragment fragUnknown1 on Unknown{" +
            "name," +
            "type}")

    @Language("JSON") val response = """
      {
        "getRandomConcept": {
          "__typename": "Persistence",
          "name": "Pretty sure this isn't how a GraphQL API would be but whatever",
          "type": "INTANGIBLE"
        }
      }
      """

    require(query.onResponse(response))
    assertThat(query.result).isInstanceOf(QModel::class.java)
    assertThat((query.result as QModel<*>).model)
        .isEqualTo(Persistence)
    assertThat((query.result as MyPersistence).name)
        .isEqualTo("Pretty sure this isn't how a GraphQL API would be but whatever")
    assertThat((query.result as MyPersistence).type)
        .isEqualTo(ConceptType.INTANGIBLE)

  }
}
