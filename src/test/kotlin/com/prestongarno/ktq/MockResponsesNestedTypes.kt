package com.prestongarno.ktq

import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QType
import com.prestongarno.ktq.QSchemaType.QTypeList
import com.google.common.truth.Truth.assertThat
import org.intellij.lang.annotations.Language
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class MockResponsesNestedTypes {

  @Test fun singleNestedType() {

    val nestedUser = object : QModel<User>(User) {
      val name by model.name
      val number by model.number
    }

    val myUserModel = object : QModel<User>(User) {
      val name by model.name
      val age by model.age
      val number by model.number
      val forEmergency by model.emergencyContact.init { nestedUser }
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

  @Test fun singleNestedTypeAsList() {
    val myUser = object : QModel<User>(User) {
      val name by model.name
      val friendList by model.friends
          .init { BasicUserModel() }
    }
    assertThat(myUser.toGraphql())
        .isEqualTo("""
          |{
          |  name,
          |  friends{
          |    name,
          |    age
          |  }
          |}
          """.trimMargin("|"))

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

  @Test fun nestedTypesWithEnumFields() {
    val myUser = object : QModel<User>(User) {
      val name by model.name
      val friendList by model.friends
          .init { BasicUserModel() }
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


/**
 * The test schema objects the above tests go off of */
object User : QSchemaType {
  val name: Stub<String> by QScalar.stub()

  val age: Stub<Int> by QScalar.stub()

  val number: Stub<Int> by QScalar.stub()

  val emergencyContact: InitStub<User> by QType.stub()

  val friends: ListInitStub<User> by QTypeList.stub()

  val isHandicapped: Stub<HealthStatus> by QScalar.stub()
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
}