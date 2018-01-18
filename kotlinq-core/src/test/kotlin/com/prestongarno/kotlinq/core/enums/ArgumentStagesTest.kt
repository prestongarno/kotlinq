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
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.QSchemaType.QCustomScalar
import com.prestongarno.kotlinq.core.QSchemaType.QEnum
import com.prestongarno.kotlinq.core.QSchemaType.QScalar
import com.prestongarno.kotlinq.core.QSchemaType.QTypes
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.schema.CustomScalar
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.prestongarno.kotlinq.core.schema.properties.ConfiguredProperty
import com.prestongarno.kotlinq.core.schema.properties.OptionallyConfiguredProperty
import com.prestongarno.kotlinq.core.schema.properties.Property
import com.prestongarno.kotlinq.core.schema.properties.iface.ConfiguredInterfaceListProperty
import com.prestongarno.kotlinq.core.schema.properties.iface.ConfiguredInterfaceProperty
import com.prestongarno.kotlinq.core.schema.properties.iface.InterfaceListProperty
import com.prestongarno.kotlinq.core.schema.properties.iface.InterfaceProperty
import com.prestongarno.kotlinq.core.schema.properties.iface.OptionallyConfiguredInterfaceListProperty
import com.prestongarno.kotlinq.core.schema.properties.iface.OptionallyConfiguredInterfaceProperty
import com.prestongarno.kotlinq.core.schema.properties.scalar.BooleanProperty
import com.prestongarno.kotlinq.core.schema.properties.scalar.ConfiguredCustomScalarListProperty
import com.prestongarno.kotlinq.core.schema.properties.scalar.ConfiguredCustomScalarProperty
import com.prestongarno.kotlinq.core.schema.properties.scalar.CustomScalarListProperty
import com.prestongarno.kotlinq.core.schema.properties.scalar.CustomScalarProperty
import com.prestongarno.kotlinq.core.schema.properties.scalar.OptionallyConfiguredCustomScalarListProperty
import com.prestongarno.kotlinq.core.schema.properties.scalar.OptionallyConfiguredCustomScalarProperty
import com.prestongarno.kotlinq.core.schema.properties.type.ConfiguredTypeListProperty
import com.prestongarno.kotlinq.core.schema.properties.type.ConfiguredTypeProperty
import com.prestongarno.kotlinq.core.schema.properties.type.NullableConfiguredTypeProperty
import com.prestongarno.kotlinq.core.schema.properties.type.NullableOptionallyConfiguredTypeProperty
import com.prestongarno.kotlinq.core.schema.properties.type.NullableTypeProperty
import com.prestongarno.kotlinq.core.schema.properties.type.OptionallyConfiguredTypeListProperty
import com.prestongarno.kotlinq.core.schema.properties.type.OptionallyConfiguredTypeProperty
import com.prestongarno.kotlinq.core.schema.properties.type.TypeListProperty
import com.prestongarno.kotlinq.core.schema.properties.type.TypeProperty
import com.prestongarno.kotlinq.core.schema.properties.union.ConfiguredUnionListProperty
import com.prestongarno.kotlinq.core.schema.properties.union.ConfiguredUnionProperty
import com.prestongarno.kotlinq.core.schema.properties.union.OptionallyConfiguredUnionListProperty
import com.prestongarno.kotlinq.core.schema.properties.union.OptionallyConfiguredUnionProperty
import com.prestongarno.kotlinq.core.schema.properties.union.UnionListProperty
import com.prestongarno.kotlinq.core.schema.properties.union.UnionProperty
import org.junit.Test
import java.time.Instant

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

  @Test fun `no arg type field returns correct stub type`() {


  }

  private fun String.flatLine() = this.replace("\\s*\\n\\s*".toRegex(RegexOption.MULTILINE), "")
}

object Class : QType {

  val teacher: TypeProperty<Person> by QTypes.stub()
  val teacherWithArgs: OptionallyConfiguredTypeProperty<Person, ClassLevelArgs> by QTypes.optionallyConfigured()
  val teacherRequiringArgs: ConfiguredTypeProperty<Person, ClassLevelArgs> by QTypes.optionallyConfigured()

  val nullableTeacher: NullableTypeProperty<Person> by QTypes.stub<Person>().asNullable()
  val nullableTeacherWithArgs: NullableOptionallyConfiguredTypeProperty<Person, ClassLevelArgs> by QTypes.optionallyConfigured<Person, ClassLevelArgs>().asNullable()
  val nullableTeacherRequiringArgs: NullableConfiguredTypeProperty<Person, ClassLevelArgs> by QTypes.configured<Person, ClassLevelArgs>().asNullable()

  val roster: TypeListProperty<Person> by QTypes.List.stub()
  val rosterWithArgs: OptionallyConfiguredTypeListProperty<Person, ClassLevelArgs> by QTypes.List.optionallyConfigured()
  val rosterRequiringArgs: ConfiguredTypeListProperty<Person, ClassLevelArgs> by QTypes.List.configured()

  val classLevel: GraphqlDslBuilder.NoArgContext<ClassLevel>
      by QEnum.stub()

  val classLevelWithArgs: ConfiguredProperty<ClassLevel, ClassLevelArgs>
      by QEnum.configured()

  val classLevelOptionalArgs: OptionallyConfiguredProperty<ClassLevel?, OptionalClassLevelArgs>
      by QEnum.optionallyConfigured<ClassLevel, OptionalClassLevelArgs>().asNullable()

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

interface WritingInstrument : QType, QInterface {
  val isPermanent: BooleanProperty
  val assignmentsUsedFor: ConfiguredTypeListProperty<Assignment, AssignmentsUsedForArgs>

  class AssignmentsUsedForArgs(first: Int) : ArgBuilder() {
    val first by arguments.notNull("first", first)
    var after: Int? by arguments
    var before: Int? by arguments
    var last: Int? by arguments
  }
}

object Pen : WritingInstrument {
  override val isPermanent: BooleanProperty by QScalar.Boolean.stub()
  override val assignmentsUsedFor: ConfiguredTypeListProperty<Assignment, WritingInstrument.AssignmentsUsedForArgs> by QTypes.List.configured()
}

object Marker : WritingInstrument {
  override val isPermanent: BooleanProperty by QScalar.Boolean.stub()
  override val assignmentsUsedFor: ConfiguredTypeListProperty<Assignment, WritingInstrument.AssignmentsUsedForArgs> by QTypes.List.configured()
}

object Assignment : QType {
  // TODO make nullable results for custom scalars

  val dueDate: CustomScalarProperty<Date> by QCustomScalar.stub()
  val dueDatesWithArgs: OptionallyConfiguredCustomScalarProperty<Date, DatesArgs> by QCustomScalar.optionallyConfigured()
  val dueDatesOnArgs: ConfiguredCustomScalarProperty<Date, DatesArgs> by QCustomScalar.configured()

  val daysWorkedOn: CustomScalarListProperty<Date> by QCustomScalar.List.stub()
  val daysWorkedOnWithArgs: OptionallyConfiguredCustomScalarListProperty<Date, DatesArgs> by QCustomScalar.List.optionallyConfigured()
  val daysWorkedOnOnArgs: ConfiguredCustomScalarListProperty<Date, DatesArgs> by QCustomScalar.List.configured()

  val lastInstrumentsUsed: InterfaceProperty<WritingInstrument> by QSchemaType.QInterfaces.stub()
  val lastInstrumentsUsedArgs: OptionallyConfiguredInterfaceProperty<WritingInstrument, Assignment.BlahArgs> by QSchemaType.QInterfaces.optionallyConfigured()
  val lastInstrumentsUsedWithArgs: ConfiguredInterfaceProperty<WritingInstrument, Assignment.BlahArgs> by QSchemaType.QInterfaces.configured()

  val instrumentsUsed: InterfaceListProperty<WritingInstrument> by QSchemaType.QInterfaces.List.stub()
  val instrumentsUsedArgs: OptionallyConfiguredInterfaceListProperty<WritingInstrument, BlahArgs> by QSchemaType.QInterfaces.List.optionallyConfigured()
  val instrumentsUsedWithArgs: ConfiguredInterfaceListProperty<WritingInstrument, BlahArgs> by QSchemaType.QInterfaces.List.configured()

  val f by QSchemaType.QInterfaces.List.stub<WritingInstrument>()

  class BlahArgs : ArgBuilder()
  class DatesArgs : ArgBuilder()

  private
  class AssignmentModel : QModel<Assignment>(Assignment) {

    val lastInstrumentUsed by model.lastInstrumentsUsed { on(::PenModel); on(::MarkerModel) }
    val lastInstrumentUsedArgs by model.lastInstrumentsUsedArgs { on(::PenModel); on(::MarkerModel) }
    val lastInstrumentUsedArgsDef by model.lastInstrumentsUsedWithArgs(BlahArgs()) { on(::PenModel); on(::MarkerModel) }

    val instrumentUsed by model.instrumentsUsed { on(::PenModel); on(::MarkerModel) }
    val instrumentUsedArgs by model.instrumentsUsedArgs { on(::PenModel); on(::MarkerModel) }
    val instrumentUsedArgsDef by model.instrumentsUsedWithArgs(BlahArgs()) { on(::PenModel); on(::MarkerModel) }

    val f: List<QModel<WritingInstrument>> by model.f(block = { on(::PenModel) })
  }

  private
  class PenModel : QModel<Pen>(Pen) {
    val isPermanent by model.isPermanent { default = true }
  }
  private
  class MarkerModel : QModel<Marker>(Marker) {
    val isPermanent by model.isPermanent { default = true }
  }
}

object Mark : QUnionType by QUnionType.new() {
  fun <U : QModel<Person>> onPerson(init: () -> U) = on(init)
  fun <U : QModel<Assignment>> onAssignment(init: () -> U) = on(init)
}

object Date : CustomScalar

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


class AssignmentModel : QModel<Assignment>(Assignment) {

  val dueDate: String by model.dueDate
  val dueDateOpt: String by model.dueDatesWithArgs
  val dueDateArgs: String by model.dueDatesOnArgs.fromString { it }.invoke(Assignment.DatesArgs())

  val datesDidWork: List<String> by model.daysWorkedOn
  val datesDidWorkOpt: List<Int> by model.daysWorkedOnWithArgs.fromString(String::toInt)
  val datesDidWorkArgs: List<java.util.Date> by model.daysWorkedOnWithArgs.fromString { java.util.Date.from(Instant.parse(it)) }

}


object Person : QType {
  val name by QScalar.String.stub()

  val age by QScalar.Int.stub()

  val latestMark: UnionProperty<Mark> by QSchemaType.QUnion.stub(Mark)
  val latestMarkArgs: OptionallyConfiguredUnionProperty<Mark, Assignment.DatesArgs> by QSchemaType.QUnion.optionallyConfigured(Mark)
  val latestMarkOnArgs: ConfiguredUnionProperty<Mark, Assignment.DatesArgs> by QSchemaType.QUnion.configured(Mark)

  val marks: UnionListProperty<Mark> by QSchemaType.QUnion.List.stub(Mark)
  val marksArgs: OptionallyConfiguredUnionListProperty<Mark, Assignment.DatesArgs> by QSchemaType.QUnion.List.optionallyConfigured(Mark)
  val marksOnArgs: ConfiguredUnionListProperty<Mark, Assignment.DatesArgs> by QSchemaType.QUnion.List.configured(Mark)
}



enum class Numbers : QEnumType { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE }


private
object CounterObject : QType {

  val numbers by QEnum.stub<Numbers>()
  val numbersOpt by QEnum.optionallyConfigured<Numbers, Blah>()
  val numbersReq by QEnum.configured<Numbers, Blah>()

  val nullNumbers: Property<Numbers?> by QEnum.stub<Numbers>().asNullable()
  val nullNumbersOpt: OptionallyConfiguredProperty<Numbers?, Blah> by QEnum.optionallyConfigured<Numbers, Blah>().asNullable()
  val nullNumbersReq: ConfiguredProperty<Numbers?, Blah> by QEnum.configured<Numbers, Blah>().asNullable()

  val numbersList: Property<List<Numbers>> by QEnum.List.stub()
  val numbersOptList: OptionallyConfiguredProperty<List<Numbers>, Blah> by QEnum.List.optionallyConfigured()
  val numbersReqList: ConfiguredProperty<List<Numbers>, Blah> by QEnum.List.configured()

  class Blah : ArgBuilder()
}











