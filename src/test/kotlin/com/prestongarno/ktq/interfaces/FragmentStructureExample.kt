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

package com.prestongarno.ktq.interfaces
/*
import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import org.intellij.lang.annotations.Language
import org.junit.Ignore
import org.junit.Test


interface Object : QType, QInterface {
  val value: IntegerDelegate<ArgBuilder>
}

object SubObject : QType, Object {
  override val value by QScalar.intStub()

  val maximum by QScalar.intConfigStub<MaximumArgs>()

  class MaximumArgs : ArgBuilder() {
    var factor: Int? by arguments
  }
}

object UnrelatedObject : QType

class UnrelatedModel : QModel<UnrelatedObject>(UnrelatedObject)

object Query : QType {
  val objectValue by QInterfaces.stub<Object>()
  //val objectValueList by QInterfaceLists.stub<Object>()
}


class MyObject : QModel<SubObject>(SubObject) {
  val result by model.value// { default = 3000 }

  val max by model.maximum(SubObject.MaximumArgs()) {
    default = 100_000_000
    config { factor = 100 }
  }
}

class TestFragmentsBasic {

  @Ignore @Test fun `make sure fragment is possible`() {
    require(MyObject().result == 3000)
    require(MyObject().apply { onResponse("{\"value\": 69}") }.result == 69)

    val query = object : QModel<Query>(Query) {

      val field by model.objectValue {
        on { MyObject() }
        config { "Hello" with "World" }
      }

      //val list by model.objectValueList { on { MyObject() } }

    }

    assertThat(query.toGraphql(false)).isEqualTo("{objectValue(Hello: \\\"World\\\"){" +
        "__typename," +
          "... on SubObject{value,maximum(factor: 100)}" +
        "}," +
        "objectValueList{" +
          "__typename," +
          "... on SubObject{value,maximum(factor: 100)}" +
        "}}")

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
    //query.list.filterIsInstance<MyObject>().forEachIndexed { index, obj -> require(obj.max == 100_000_000) require(obj.result == index) }
    println(query.toGraphql(false))
  }

}*/
