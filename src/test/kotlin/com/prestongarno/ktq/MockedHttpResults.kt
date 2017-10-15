package com.prestongarno.ktq

import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QScalarList
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.adapters.ScalarStubAdapter
import org.intellij.lang.annotations.Language

class MockedHttpResults {
  @Test fun mockResponse() {

    val firstGoal = object : QModel<Goal>(Goal) {
      val name by model.name
      val desc by model.description
    }

    assertThat(firstGoal.toGraphql()).isEqualTo("""
          |{
          |  name,
          |  description
          |}
          """.trimMargin("|"))

    //@Suppress("UNCHECKED_CAST")
    //(firstGoal.fields.find { it.graphqlProperty.graphqlName == "name" } as ScalarStubAdapter<String, ArgBuilder>).value = "1.0 release of this project"
    //@Suppress("UNCHECKED_CAST")
    //(firstGoal.fields.find { it.graphqlProperty.graphqlName == "description" } as ScalarStubAdapter<String, ArgBuilder>).value = "Safe, easy-to-use graphql queries"

    assertThat(firstGoal.name).isEqualTo("1.0 release of this project")
    assertThat(firstGoal.desc).isEqualTo("Safe, easy-to-use graphql queries")
  }

  @Test fun mockMarshalledResponse() {

    val firstGoal = object : QModel<Goal>(Goal) {
      val name by model.name
      val desc by model.description
    }

    @Language("JSON") val jsonResponse = """{
        "name": "1.0 release of this project",
        "description": "Safe, easy-to-use graphql queries"
      }"""

    firstGoal.onResponse(jsonResponse)

    assertThat(firstGoal.name).isEqualTo("1.0 release of this project")
    assertThat(firstGoal.desc).isEqualTo("Safe, easy-to-use graphql queries")
  }

  @Test fun mockMarshalledResponseWithIntFields() {

    val firstGoal = object : QModel<Goal>(Goal) {
      val name by model.name
      val level by model.difficulty
    }
    @Language("JSON") val jsonResponse = """{
        "name": "1.0 Release",
        "difficulty": 10
      }"""

    firstGoal.run {
      onResponse(jsonResponse)

      assertThat(name).isEqualTo("1.0 Release")
      assertThat(level).isEqualTo(10)
    }
  }

  @Test fun mockMarshallWithEnumField() {
    val myGoal = object : QModel<Goal>(Goal) {
      val name by model.name
      val level by model.difficulty
      val status by model.status
    }
    @Language("JSON") val response = """{
        "name": "1.0 Release",
        "difficulty": 7,
        "status": "IN_PROGRESS"
      }"""

    myGoal.run {
      onResponse(response)

      assertThat(name).isEqualTo("1.0 Release")
      assertThat(level).isEqualTo(7)
      assertThat(status).isEqualTo(GoalStatus.IN_PROGRESS)
    }
  }

  @Test fun mockMarshallWithListField() {
    val myGoal = object : QModel<Goal>(Goal) {
      val name by model.name
      val notes by model.notes
    }
    @Language("JSON") val response = """{
        "name": "1.0 Release",
        "notes": ["Create Interface", "Incremental Releases"]
      }"""
    myGoal.run {
      onResponse(response)
      assertThat(name).isEqualTo("1.0 Release")
      assertThat(notes.size == 2)
      assertThat(notes[0]).isEqualTo("Create Interface")
      assertThat(notes[1]).isEqualTo("Incremental Releases")
    }
  }

  @Test fun mockMarshalWithListOfEnums() {
    val myGoal = object : QModel<Goal>(Goal) {
      val name by model.name
      val justForShow by model.statuses
    }
    @Language("JSON") val response = """{
        "name": "1.0 Release",
        "statuses": ["IN_PROGRESS", "COMPLETED"]
      }"""
    myGoal.run {
      onResponse(response)
      assertThat(name).isEqualTo("1.0 Release")
      assertThat(justForShow.size).isEqualTo(2)
      assertThat(justForShow[0] == GoalStatus.IN_PROGRESS)
      assertThat(justForShow[1] == GoalStatus.COMPLETED)
    }
  }
}

object Goal : QSchemaType {
  val name: Stub<String> by QScalar.stub()

  val description: Stub<String> by QScalar.stub()

  val difficulty: Stub<Int> by QScalar.stub()

  val status: Stub<GoalStatus> by QScalar.stub()

  val notes: ListStub<String> by QScalarList.stub()

  val statuses: ListStub<GoalStatus> by QScalarList.stub()
}

enum class GoalStatus : QSchemaType {
  IN_PROGRESS,
  COMPLETED
}