package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Types {

  private object Vars {
    val foo: String = ""
    val bar: String? = null
    val baz: List<String> = emptyList()
  }

  @Test fun compatibilityChecks() {
    assertThat(Vars::foo.returnType.isCompatibleWith(null))
        .isFalse()
    assertThat(Vars::bar.returnType.isCompatibleWith(null))
        .isTrue()
    assertThat(Vars::baz.returnType.isCompatibleWith(emptyList<String>()))
        .isTrue()
    assertThat(Vars::baz.returnType.isCompatibleWith(emptyList<Int>()))
        .isTrue()
  }
}