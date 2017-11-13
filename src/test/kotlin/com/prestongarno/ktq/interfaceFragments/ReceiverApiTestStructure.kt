package com.prestongarno.ktq.interfaceFragments

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.EnumStub
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.adapters.StringDelegate
import com.prestongarno.ktq.hooks.NoArgConfig
import org.intellij.lang.annotations.Language
import org.junit.Test

enum class ConceptType : QEnumType {
  TANGIBLE,
  INTANGIBLE
}

interface Concept : QType, QInterface {
  // TODO -> 3-factor API with argbuilders
  val name : StringDelegate<ArgBuilder>

  val type : NoArgConfig<EnumStub<ConceptType, ArgBuilder>, ConceptType>
}

object Persistence : Concept {
  override val name by QScalar.stringStub()

  override val type by QEnum.stub<ConceptType>()
}

object Timber : Concept {
  override val name by QScalar.stringStub()

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
  class MyTimber : QModel<Timber>(Timber) {
    val name by model.name
    val type by model.type
  }

  @Test fun `single interface field query with parsing response`() {
    val query = object : QModel<Random>(Random) {
      // TODO -> not allow a providedelegate without fragmenting!
      val result by model.getRandomConcept {
        on(::MyPersistence)
        on(::MyTimber)
      }
    }

    assertThat(query.toGraphql(false))
        .isEqualTo(
            "{getRandomConcept{__typename, ... on Persistence{name,type}, ... on Timber{name,type}}}")

    @Language("JSON") val response = """
      {
        "getRandomConcept": {
          "__typename": "Persistence",
          "name": "Pretty sure this isn't how you would use GraphQL but whatever",
          "type": "INTANGIBLE"
        }
      }
      """

    require(query.onResponse(response))
    assertThat(query.result).isInstanceOf(QModel::class.java)
    assertThat((query.result as QModel<*>).model)
        .isEqualTo(Persistence)
    assertThat((query.result as MyPersistence).name)
        .isEqualTo("Pretty sure this isn't how you would use GraphQL but whatever")
    assertThat((query.result as MyPersistence).type)
        .isEqualTo(ConceptType.INTANGIBLE)

  }
}
