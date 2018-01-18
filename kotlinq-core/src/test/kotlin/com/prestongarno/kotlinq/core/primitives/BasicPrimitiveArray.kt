/*
 * Copyright (C) 2018 Preston Garno
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

@file:Suppress("unused")

package com.prestongarno.kotlinq.core.primitives

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType.QScalar
import com.prestongarno.kotlinq.core.schema.QType
import org.junit.Test
import com.prestongarno.kotlinq.core.eq
import com.prestongarno.kotlinq.core.schema.properties.ConfiguredProperty
import com.prestongarno.kotlinq.core.schema.properties.OptionallyConfiguredProperty
import com.prestongarno.kotlinq.core.schema.properties.Property

object AnonymousClassroom : QType {

  val studentNames: Property<Array<String>> by QScalar.List.String.stub()
  val studentAges: Property<IntArray> by QScalar.List.Int.stub()
  val studentGpa: Property<FloatArray> by QScalar.List.Float.stub()
  val studentPassing: Property<BooleanArray> by QScalar.List.Boolean.stub()

  val studentNamesArgs: OptionallyConfiguredProperty<Array<String>, FooArgs> by QScalar.List.String.optionallyConfigured()
  val studentAgesArgs: OptionallyConfiguredProperty<IntArray, FooArgs> by QScalar.List.Int.optionallyConfigured()
  val studentGpaArgs: OptionallyConfiguredProperty<FloatArray, FooArgs> by QScalar.List.Float.optionallyConfigured()
  val studentPassingArgs: OptionallyConfiguredProperty<BooleanArray, FooArgs> by QScalar.List.Boolean.optionallyConfigured()

  val studentWithNamesArgs: ConfiguredProperty<Array<String>, FooArgs> by QScalar.List.String.configured()
  val studentWithAgesArgs: ConfiguredProperty<IntArray, FooArgs> by QScalar.List.Int.configured()
  val studentWithGpaArgs: ConfiguredProperty<FloatArray, FooArgs> by QScalar.List.Float.configured()
  val studentWithPassingArgs: ConfiguredProperty<BooleanArray, FooArgs> by QScalar.List.Boolean.configured()


  val nullStudentNames: Property<Array<String>?> by QScalar.List.String.stub().asNullable()
  val nullStudentAges: Property<IntArray?> by QScalar.List.Int.stub().asNullable()
  val nullStudentGpa: Property<FloatArray?> by QScalar.List.Float.stub().asNullable()
  val nullStudentPassing: Property<BooleanArray?> by QScalar.List.Boolean.stub().asNullable()

  val nullStudentNamesArgs: OptionallyConfiguredProperty<Array<String>?, FooArgs> by QScalar.List.String.optionallyConfigured<FooArgs>().asNullable()
  val nullStudentAgesArgs: OptionallyConfiguredProperty<IntArray?, FooArgs> by QScalar.List.Int.optionallyConfigured<FooArgs>().asNullable()
  val nullStudentGpaArgs: OptionallyConfiguredProperty<FloatArray?, FooArgs> by QScalar.List.Float.optionallyConfigured<FooArgs>().asNullable()
  val nullStudentPassingArgs: OptionallyConfiguredProperty<BooleanArray?, FooArgs> by QScalar.List.Boolean.optionallyConfigured<FooArgs>().asNullable()

  val nullStudentWithNamesArgs: ConfiguredProperty<Array<String>?, FooArgs> by QScalar.List.String.configured<FooArgs>().asNullable()
  val nullStudentWithAgesArgs: ConfiguredProperty<IntArray?, FooArgs> by QScalar.List.Int.configured<FooArgs>().asNullable()
  val nullStudentWithGpaArgs: ConfiguredProperty<FloatArray?, FooArgs> by QScalar.List.Float.configured<FooArgs>().asNullable()
  val nullStudentWithPassingArgs: ConfiguredProperty<BooleanArray?, FooArgs> by QScalar.List.Boolean.configured<FooArgs>().asNullable()

  class FooArgs : ArgBuilder()
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
          arguments.put("Hello", "World")
        }
      }

      val ages by model.studentAges {
        config { arguments.put("NumberArgument", 9000) }
      }

      val gpas by model.studentGpa {
        config { arguments.put("BooleanArgument", true) }
      }

      val passing by model.studentPassing {
        config { arguments.put("FloatArgument", 5.005f) }
      }

    }

    query.toGraphql() eq
        """{studentNames(Hello: "World"),studentAges(NumberArgument: 9000),""" +
            """studentGpa(BooleanArgument: true),studentPassing(FloatArgument: 5.005f)}"""
  }
}