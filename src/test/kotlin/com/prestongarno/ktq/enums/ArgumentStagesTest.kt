package com.prestongarno.ktq.enums

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QType
import org.junit.Test

object Class : QType {
  val classLevel by QSchemaType.QEnum.stub<ClassLevel>()
}

enum class ClassLevel : QEnumType {
  ELEMENTARY,
  MIDDLE_SCHOOL,
  HIGH_SCHOOL,
  UNDERGRADUATE,
  GRADUATE
}

class ClassQuery : QModel<Class>(Class) {
  val level by model.classLevel { default = ClassLevel.UNDERGRADUATE }
}

class ArgumentStagesTest {
  @Test fun sampleConfiguration() {
    assertThat(ClassQuery().toGraphql(false)).isEqualTo("{classLevel}")
  }
}
