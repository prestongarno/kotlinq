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

package com.prestongarno.ktq


/*
class MockResponsesNestedTypes {

  @Ignore @Test fun singleNestedType() {

    val nestedUser = object : QModel<User>(User) {
      val name by model.name
      val number by model.number
    }

    val myUserModel = object : QModel<User>(User) {
      val name by model.name
      val age by model.age
      val number by model.number
      val forEmergency by model.emergencyContact.querying { nestedUser }
    }

    assertThat(myUserModel.toGraphql())
        .isEqualTo("""
          |{
          |  name,
          |  age,
          |  number,
          |  emergencyContact{
          |    name,
          |    number
          |  }
          |}
          """.trimMargin("|"))

    @Language("JSON") val response = """{
      "name": "Preston Garno",
      "age": 22,
      "number": 1234567890,
      "emergencyContact": {
          "name": "Anon",
          "number": 198765432
        }
      }"""

    myUserModel.run {
      onResponse(response)

      assertTrue(name == "Preston Garno")
      assertTrue(age == 22)
      assertTrue(number == 1234567890)
      assertTrue(forEmergency.name == "Anon")
      assertTrue(forEmergency.number == 198765432)
      @Suppress("USELESS_IS_CHECK") assertTrue(forEmergency is QModel<User>)
      assertTrue(forEmergency == nestedUser)
    }
  }

  @Ignore @Test fun singleNestedTypeAsList() {
    val myUser = object : QModel<User>(User) {
      val name by model.name
      val friendList by model.friends
          .querying { BasicUserModel() }
    }

    assertThat(myUser.toGraphql())
        .isEqualTo("""
          |{
          |  name,
          |  friends{
          |    name,
          |    age,
          |    isHandicapped
          |  }
          |}
          """.trimMargin("|").replace("[\\s\\n\n]*".toRegex(), ""))

    @Language("JSON") val response = """{
      "name": "Chow",
      "friends": [
        {"name": "Preston", "age": 22},
        {"name": "Garno", "age": 21}
      ]
    }"""

    myUser.run {
      onResponse(response)
      assertTrue(myUser.name == "Chow")
      assertEquals(myUser.friendList.size, 2)
      myUser.friendList[0].run { assertTrue(name == "Preston" && age == 22) }
      myUser.friendList[1].run { assertTrue(name == "Garno" && age == 21) }
    }
  }

  @Ignore @Test fun nestedTypesWithEnumFields() {
    val myUser = object : QModel<User>(User) {
      val name by model.name
      val friendList by model.friends
          .querying { BasicUserModel() }
    }
    assertThat(myUser.toGraphql())
        .isEqualTo("""
          |{
          |  name,
          |  friends{
          |    name,
          |    age,
          |    isHandicapped
          |  }
          |}
          """.trimMargin("|"))

    @Language("JSON") val response = """{
      "name": "Chow",
      "friends": [
        {"name": "Preston", "age": 22, "isHandicapped": "HEALTHY"},
        {"name": "Garno", "age": 21, "isHandicapped": "BED_RIDDEN"}
      ]
    }"""

    myUser.run {
      onResponse(response)

      assertTrue(myUser.name == "Chow")
      assertEquals(myUser.friendList.size, 2)
      myUser.friendList[0].run {
        assertTrue(
            name == "Preston"
                && age == 22
                && health == HealthStatus.HEALTHY)
      }
      myUser.friendList[1].run {
        assertTrue(
            name == "Garno"
                && age == 21
                && health == HealthStatus.BED_RIDDEN)
      }
    }
  }
}


*/
/**
 * The test schema objects the above tests go off of *//*

object User : QSchemaType {
  val name: Stub<String> by QScalar.stubPrimitive()

  val age: Stub<Int> by QScalar.stubPrimitive()

  val number: Stub<Int> by QScalar.stubPrimitive()

  val emergencyContact: InitStub<User> by QType.stub()

  val friends: ListInitStub<User> by QTypeList.stub()

  val isHandicapped: Stub<HealthStatus> by QScalar.stubPrimitive()
}

enum class HealthStatus : QSchemaType {
  HEALTHY,
  IMMOBILE,
  BED_RIDDEN
}

class BasicUserModel : QModel<User>(User) {
  val name by model.name
  val age by model.age
  val health by model.isHandicapped

  override fun toString(): String = "BasicUserModel: '$name' (Age:$age)"
}*/
