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

package com.prestongarno.ktq.primitives

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.stubs.BooleanArrayDelegate
import com.prestongarno.ktq.stubs.FloatArrayDelegate
import com.prestongarno.ktq.stubs.IntArrayDelegate
import com.prestongarno.ktq.stubs.StringArrayDelegate
import org.junit.Test

object AnonymousClassroom : QType {

  val studentNames: StringArrayDelegate.Query
      by QScalar.List.String.stub()

  val studentAges: IntArrayDelegate.Query
      by QScalar.List.Int.stub()

  val studentGpa: FloatArrayDelegate.Query
      by QScalar.List.Float.stub()

  val studentPassing: BooleanArrayDelegate.Query
      by QScalar.List.Boolean.stub()
}

class BasicPrimitiveArray {

  @Test fun `string array is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {
      val names by model.studentNames
    }

    query::names.returnType.classifier eq Array<String>::class
    query.toGraphql() eq "{studentNames}"
  }

  @Test fun `integer array is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {
      val ages by model.studentAges
    }
    query::ages.returnType.classifier eq IntArray::class
    query.toGraphql() eq "{studentAges}"
  }

  @Test fun `float array is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {
      val gpas by model.studentGpa
    }
    query::gpas.returnType.classifier eq FloatArray::class
    query.toGraphql() eq "{studentGpa}"
  }

  @Test fun `boolean array is possible`() {
    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {
      val passing by model.studentPassing
    }
    query::passing.returnType.classifier eq BooleanArray::class
    query.toGraphql() eq "{studentPassing}"
  }

  @Test fun `arrays of all types with empty invocation blocks is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {

      val names by model.studentNames { /* nothing */ }

      val ages by model.studentAges { /* nothing */ }

      val gpas by model.studentGpa { /* nothing */ }

      val passing by model.studentPassing { /* nothing */ }

    }

    query.toGraphql() eq "{studentNames,studentAges,studentGpa,studentPassing}"
  }

  @Test fun `arrays of all types with dynamic arguments is possible`() {

    val query = object : QModel<AnonymousClassroom>(AnonymousClassroom) {

      val names by model.studentNames {
        config {
          "Hello" with "World"
        }
      }

      val ages by model.studentAges {
        config { "NumberArgument" with 9000 }
      }

      val gpas by model.studentGpa {
        config { "BooleanArgument" with true }
      }

      val passing by model.studentPassing {
        config { "FloatArgument" with 5.005f }
      }

    }

    query.toGraphql() eq
        """{studentNames(Hello: \"World\"),studentAges(NumberArgument: 9000),""" +
          """studentGpa(BooleanArgument: true),studentPassing(FloatArgument: 5.005f)}"""
  }
}