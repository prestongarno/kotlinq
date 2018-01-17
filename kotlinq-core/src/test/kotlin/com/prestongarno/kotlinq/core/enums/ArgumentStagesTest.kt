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

package com.prestongarno.kotlinq.core.enums

import com.google.common.truth.Truth.assertThat
import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.stubs.ConfiguredProperty
import com.prestongarno.kotlinq.core.schema.stubs.OptionallyConfiguredProperty
import org.junit.Test

class ArgumentStagesTest {

  @Test fun sampleConfiguration() {
    assertThat(ClassQuery1().toGraphql())
        .isEqualTo("{classLevel}")
    assertThat(ClassQuery1().level)
        .isEqualTo(ClassLevel.UNDERGRADUATE)
  }

  @Test fun `config block on NoArg stub shows up`() {
    val query = object : QModel<Class>(Class) {
      val level by Class.classLevel {
        config { arguments.put("Hello", "World") }
      }
    }

    assertThat(query.toGraphql())
        .isEqualTo("""{classLevel(Hello: "World")}""")
  }

  @Test fun `required argument type is enforced on field`() {

    // Arguments to pass to the graphql query
    val arguments = Class.ClassLevelArgs(100, "100", 100.0f, true)

    val query = object : QModel<Class>(Class) {
      val levelWithArgs by Class.classLevelWithArgs(arguments) {
        config {
          integer = 1200
          string = "1200"
          boolean = true
          float = 1200.0f
        }
      }
    }
    assertThat(query.toGraphql())
        .isEqualTo("{classLevelWithArgs(" +
            "requiredInteger: 100," +
            "requiredString: \"100\"," +
            "requiredFloat: 100.0f," +
            "requiredBoolean: true," +
            "integer: 1200," +
            "string: \"1200\"," +
            "boolean: true," +
            "float: 1200.0f)}")
  }

  @Test fun `optional arguments with no args passed`() {
    val query = object : QModel<Class>(Class) {
      val levelNoArgs by model.classLevelOptionalArgs
    }
    assertThat(query.toGraphql())
        .isEqualTo("{${Class::classLevelOptionalArgs.name}}")
  }

  @Test fun `optional arguments with arguments passed in DSL closure`() {
    val query = object : QModel<Class>(Class) {
      val levelWithArgs by Class.classLevelOptionalArgs(Class.OptionalClassLevelArgs()) {
        default = ClassLevel.HIGH_SCHOOL
        config {
          intArgument = 3535
          stringArgument = "Hello, World!"
        }
      }
    }
    assertThat(query.toGraphql())
        .isEqualTo("""{${Class::classLevelOptionalArgs.name}
          |(${Class.OptionalClassLevelArgs::intArgument.name}: 3535,
          |${Class.OptionalClassLevelArgs::stringArgument.name}: "Hello, World!"
          |)}""".trimMargin().flatLine())
  }

  @Test fun `optional arguments with not all arguments set in config block`() {
    val query = object : QModel<Class>(Class) {
      val levelWithArgs by Class.classLevelOptionalArgs(Class.OptionalClassLevelArgs()) {
        default = ClassLevel.HIGH_SCHOOL
        config {
          intArgument = 353535
        }
      }
    }
    assertThat(query.toGraphql())
        .isEqualTo("""{${Class::classLevelOptionalArgs.name}
          |(${Class.OptionalClassLevelArgs::intArgument.name}: 353535
          |)}""".trimMargin().flatLine())
  }

  private fun String.flatLine() = this.replace("\\s*\\n\\s*".toRegex(RegexOption.MULTILINE), "")
}

object Class : QType {
  val classLevel: GraphqlDslBuilder.NoArgContext<ClassLevel>
      by QSchemaType.QEnum.stub<ClassLevel>()

  val classLevelWithArgs: ConfiguredProperty<ClassLevel, ClassLevelArgs>
      by QSchemaType.QEnum.configured()

  val classLevelOptionalArgs: OptionallyConfiguredProperty<ClassLevel?, OptionalClassLevelArgs>
      by QSchemaType.QEnum.optionallyConfigured<ClassLevel, OptionalClassLevelArgs>().asNullable()

  class OptionalClassLevelArgs : ArgBuilder() {
    var intArgument: Int? by arguments
    var stringArgument: String? by arguments
  }

  class ClassLevelArgs(
      requiredInteger: Int,
      requiredString: String,
      requiredFloat: Float,
      requiredBoolean: Boolean
  ) : ArgBuilder() {

    init {
      "requiredInteger" with requiredInteger
      "requiredString" with requiredString
      "requiredFloat" with requiredFloat
      "requiredBoolean" with requiredBoolean
    }

    var integer: Int? by arguments
    var string: String? by arguments
    var float: Float? by arguments
    var boolean: Boolean? by arguments
  }
}

enum class ClassLevel : QEnumType {
  ELEMENTARY,
  MIDDLE_SCHOOL,
  HIGH_SCHOOL,
  UNDERGRADUATE,
  GRADUATE
}

class ClassQuery1 : QModel<Class>(Class) {
  val level by model.classLevel {
    default = ClassLevel.UNDERGRADUATE
  }
}


