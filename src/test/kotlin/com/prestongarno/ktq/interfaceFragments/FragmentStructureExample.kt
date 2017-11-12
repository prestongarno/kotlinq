package com.prestongarno.ktq.interfaceFragments

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.adapters.IntegerDelegate
import com.prestongarno.ktq.stubs.FragmentContext
import org.intellij.lang.annotations.Language
import org.junit.Test


interface Object : QType, QInterface {
  val value: IntegerDelegate<ArgBuilder>
}

object SubObject : QType, Object {
  override val value by QScalar.intStub()
}

object UnrelatedObject : QType

class UnrelatedModel : QModel<UnrelatedObject>(UnrelatedObject)

object Query : QType {
  val objectValue by QInterfaces.stub<Object>()
  val objectValueList by QInterfaceLists.stub<Object>()
}


class MyObject : QModel<SubObject>(SubObject) {
  val result by model.value {
    default = 3000
  }
}

class TestFragmentsBasic {

  @Test fun `make sure fragment is possible`() {
    require(MyObject().result == 3000)
    require(MyObject().apply { onResponse("{\"value\": 69}") }.result == 69)

    val query = object : QModel<Query>(Query) {

      val field by model.objectValue {
        on { MyObject() }
        config { "Hello" with "World" }
      }

      val list by model.objectValueList {
        on { MyObject() }
      }

    }

    println(query.toGraphql(false))
    assertThat(query.toGraphql(false)).isEqualTo("""
      {objectValue(\"Hello\": \"World\"){__typename,... on SubObject{value}},objectValueList{__typename,... on SubObject{value}}}
    """.trimIndent())

    @Language("JSON") val response = """
      {
        "objectValue": {
            "__typename": "SubObject",
            "value": "35"
          },
          "objectValueList": [
            {
              "__typename": "SubObject",
              "value": "0"
            },
            {
              "__typename": "SubObject",
              "value": "1"
            }
          ]
      }
      """

    require(query.onResponse(response))
    require(query.field is MyObject)
    require((query.field as? MyObject)?.result == 35)
    //query.list.filterIsInstance<MyObject>().forEachIndexed { index, obj -> require(obj.result == index) }
  }

}
