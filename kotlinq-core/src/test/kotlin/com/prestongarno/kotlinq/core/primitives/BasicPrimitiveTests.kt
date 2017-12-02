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

package com.prestongarno.kotlinq.core.primitives

import com.google.common.truth.Truth.assertThat
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType.*
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.stubs.BooleanDelegate
import com.prestongarno.kotlinq.core.stubs.FloatDelegate
import com.prestongarno.kotlinq.core.stubs.IntDelegate
import com.prestongarno.kotlinq.core.stubs.StringDelegate
import org.junit.Test
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers

infix fun Any?.eq(other: Any?): Boolean =
    if (other === Nothing::class) true else assertThat(this).isEqualTo(other) == Unit

object Person : QType {

  val name: StringDelegate.Query by QScalar.String.stub()

  val age: IntDelegate.Query by QScalar.Int.stub()

  val gpa: FloatDelegate.Query by QScalar.Float.stub()

  val hasHighSchoolDiploma: BooleanDelegate.Query by QScalar.Boolean.stub()

}

class BasicPrimitiveTests {

  @Test fun `string field is possible`() {
    val query = object : QModel<Person>(Person) {
      val name by Person.name
    }
    assertThat(query::name.returnType.classifier)
        .isEqualTo(String::class)
    assertThat(query.toGraphql())
        .isEqualTo("{name}")
  }

  @Test fun `integer field is possible`() {
    val query = object : QModel<Person>(Person) {
      val age by Person.age
    }
    assertThat(query::age.returnType.classifier)
        .isEqualTo(Int::class)
    assertThat(query.toGraphql())
        .isEqualTo("{age}")
  }

  @Test fun `float field is possible`() {
    val query = object : QModel<Person>(Person) {
      val gpa by Person.gpa
    }
    assertThat(query::gpa.returnType.classifier)
        .isEqualTo(Float::class)
    assertThat(query.toGraphql())
        .isEqualTo("{gpa}")
  }

  @Test fun `boolean field is possible`() {
    val query = object : QModel<Person>(Person) {
      val gradated by Person.hasHighSchoolDiploma
    }
    assertThat(query::gradated.returnType.classifier)
        .isEqualTo(Boolean::class)
    assertThat(query.toGraphql())
        .isEqualTo("{hasHighSchoolDiploma}")
  }

  @Test fun `delegate with empty invocation blocks is possible`() {
    val query = object : QModel<Person>(Person) {
      val name by Person.name { /* Nothing */ }
      val age by Person.age { /* Nothing */ }
      val gpa by Person.gpa { /* Nothing */ }
      val isGrad by Person.hasHighSchoolDiploma { /* Nothing */ }
    }

    query.fields.entries.map {
      it.key to it.value.qproperty.graphqlType
    }.forEach { (symbol, graphqlType) ->
      graphqlType eq (when (symbol) {
        "name" -> "String"
        "age" -> "Int"
        "gpa" -> "Float"
        "hasHighSchoolDiploma" -> "Boolean"
        else -> null!!
      })
    }

    query::class.declaredMembers.forEach {
      it.returnType.classifier eq when (it.name) {
        "name" -> String::class
        "age" -> Int::class
        "gpa" -> Float::class
        "hasHighSchoolDiploma" -> Boolean::class
        else -> Nothing::class // hack to iterate through reflective properties
      }
    }

    assertThat(query.toGraphql())
        .isEqualTo("{name,age,gpa,hasHighSchoolDiploma}")
  }

  @Test fun `graphql string with default value is possible`() {

    val query = object : QModel<Person>(Person) {

      val name by Person.name {
        default = "Preston Garno"
      }

      val age by Person.age {
        default = 22
      }

      val gpa by Person.gpa {
        default = 3.9F
      }

      val isGrad by Person.hasHighSchoolDiploma {
        default = true
      }
    }

    query.toGraphql() eq "{name,age,gpa,hasHighSchoolDiploma}"

    query.name   eq   "Preston Garno"
    query.age    eq   22
    query.gpa    eq   3.9F
    query.isGrad eq   true
  }

  @Test fun `graphql with custom arguments added is possible`() {

    val query = object : QModel<Person>(Person) {

      val name by Person.name {
        config { "Hello" with "World" }
      }

      val age by Person.age {
        config { "Number" with 69 }
      }

      val gpa by Person.gpa {
        config { "Boolean" with false }
      }

      val grad by Person.hasHighSchoolDiploma {
        config { "floatArgument" with 4.0000067f }
      }
    }

    query.toGraphql() eq
        """{name(Hello: \"World\"),age(Number: 69),gpa(Boolean: false)""" +
            """,hasHighSchoolDiploma(floatArgument: 4.0000067f)}"""
  }

  /** This is so fucking key right here. */
  @Test fun `delegate to reusable delegate object is possible`() {

    val delegateStub = Person.name().apply {
      default = "First Query Expected Default"
      config { "Hello" with "World" }
    }

    val query = object : QModel<Person>(Person) {
      val name by delegateStub
    }

    delegateStub.default = "Second Query Expected Default"

    val query2 = object : QModel<Person>(Person) {
      val name by delegateStub
    }

    query.toGraphql() eq "{name(Hello: \\\"World\\\")}"
    query2.toGraphql() eq query.toGraphql()

    delegateStub.default = null
    query.name eq "First Query Expected Default"
    delegateStub.default eq null
    query2.name eq "Second Query Expected Default"
    delegateStub.default eq null
  }

}

