package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class JsonArrayResolve {

  @Test fun simpleScalarArrayResolves() {

    class Clazz(result: GraphQlResult) : Data by result.toData() {
      val integers: List<Int> by result.integer().asList()
    }

    val values = json { "integers"(0, 1, 2, 3, 4, 5, 6, 7, 8) }

    val shouldBeTrue = fragment(::Clazz)
        .resolveFrom(values)!!
        .integers
        .withIndex()
        .all { (index, value) -> index == value }

    assertThat(shouldBeTrue)
        .isTrue()
  }
}