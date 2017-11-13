package com.prestongarno.ktq.enums

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QType
import org.junit.Test

object Class : QType {
  val classLevel by QSchemaType.QEnum.stub<ClassLevel>()

  val classLevelWithArgs by QSchemaType.QEnum.configStub<ClassLevel, ClassLevelArgs>()

  val classLevelOptionalArgs by QSchemaType.QEnum.optionalConfigStub<ClassLevel, OptionalClassLevelArgs>()

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

class ArgumentStagesTest {
  @Test fun sampleConfiguration() {
    assertThat(ClassQuery1().toGraphql(false))
        .isEqualTo("{classLevel}")
    assertThat(ClassQuery1().level)
        .isEqualTo(ClassLevel.UNDERGRADUATE)
  }

  @Test fun `config block on NoArg stub shows up`() {

    val query = object : QModel<Class>(Class) {
      val level by model.classLevel {
        config { "Hello" with "World" }
      }
    }

    assertThat(query.toGraphql(false))
        .isEqualTo("""{classLevel(Hello: \"World\")}""")
  }

  @Test fun `required argument type is enforced on field`() {

    // Arguments to pass to the graphql query
    val arguments = Class.ClassLevelArgs(100, "100", 100.0f, true)

    val query = object : QModel<Class>(Class) {
      val levelWithArgs by model.classLevelWithArgs(arguments) {
        config {
          integer = 1200
          string = "1200"
          boolean = true
          float = 1200.0f
        }
      }
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("""
          {classLevelWithArgs(
            requiredInteger: 100,
            requiredString: \"100\",
            requiredFloat: 100.0f,
            requiredBoolean: true,
            integer: 1200,
            string: \"1200\",
            boolean: true,
            float: 1200.0f)}
        """.flatLine())
  }

  @Test fun `optional arguments with no args passed`() {
    val query = object : QModel<Class>(Class) {
      val levelNoArgs by model.classLevelOptionalArgs
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("{${Class::classLevelOptionalArgs.name}}")
  }

  @Test fun `optional arguments with arguments passed in DSL closure`() {
    val query = object : QModel<Class>(Class) {
      val levelWithArgs by model.classLevelOptionalArgs(Class.OptionalClassLevelArgs()) {
        default = ClassLevel.HIGH_SCHOOL
        config {
          intArgument = 3535
          stringArgument = "Hello, World!"
        }
      }
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("""{${Class::classLevelOptionalArgs.name}
          |(${Class.OptionalClassLevelArgs::intArgument.name}: 3535,
          |${Class.OptionalClassLevelArgs::stringArgument.name}: \"Hello, World!\"
          |)}""".trimMargin().flatLine())
  }

  @Test fun `optional arguments with not all arguments set in config block`() {
    val query = object : QModel<Class>(Class) {
      val levelWithArgs by model.classLevelOptionalArgs(Class.OptionalClassLevelArgs()) {
        default = ClassLevel.HIGH_SCHOOL
        config {
          intArgument = 353535
        }
      }
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("""{${Class::classLevelOptionalArgs.name}
          |(${Class.OptionalClassLevelArgs::intArgument.name}: 353535
          |)}""".trimMargin().flatLine())
  }

  private fun String.flatLine() = this.replace("\\s*\\n\\s*".toRegex(RegexOption.MULTILINE), "")
}

