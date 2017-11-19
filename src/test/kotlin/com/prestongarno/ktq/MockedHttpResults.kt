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

import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QScalarList
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.intellij.lang.annotations.Language
import org.junit.Ignore

class MockedHttpResults {
  @Ignore @Test fun mockResponse() {

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
    //(firstGoal.fields.find { it.qproperty.graphqlName == "name" } as ScalarStubAdapter<String, ArgBuilder>).value = "1.0 release of this project"
    //@Suppress("UNCHECKED_CAST")
    //(firstGoal.fields.find { it.qproperty.graphqlName == "description" } as ScalarStubAdapter<String, ArgBuilder>).value = "Safe, easy-to-use graphql queries"

    assertThat(firstGoal.name).isEqualTo("1.0 release of this project")
    assertThat(firstGoal.desc).isEqualTo("Safe, easy-to-use graphql queries")
  }

  @Ignore @Test fun mockMarshalledResponse() {

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

  @Ignore @Test fun mockMarshalledResponseWithIntFields() {

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

  @Ignore @Test fun mockMarshallWithEnumField() {
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

  @Ignore @Test fun mockMarshallWithListField() {
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

  @Ignore @Test fun mockMarshalWithListOfEnums() {
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
  val name: Stub<String> by QScalar.stubPrimitive()

  val description: Stub<String> by QScalar.stubPrimitive()

  val difficulty: Stub<Int> by QScalar.stubPrimitive()

  val status: Stub<GoalStatus> by QScalar.stubPrimitive()

  val notes: ListStub<String> by QScalarList.stub()

  val statuses: ListStub<GoalStatus> by QScalarList.stub()
}

enum class GoalStatus : QSchemaType {
  IN_PROGRESS,
  COMPLETED
}*/
