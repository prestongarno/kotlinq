package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.introspection.Kind
import org.kotlinq.jvm.Validation.isValidValue

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

  @Test fun validatorValuesTest() {
    assertThat(isValidValue(Kind.string, "")).isTrue()
    assertThat(isValidValue(Kind.integer, 1)).isTrue()
    assertThat(isValidValue(Kind.float, 1.0f)).isTrue()
    assertThat(isValidValue(Kind.bool, 1.0f)).isFalse()
    assertThat(isValidValue(Kind.bool, false)).isTrue()
    assertThat(isValidValue(Kind.typeNamed("Any"), emptyMap<String, Any?>())).isTrue()
    assertThat(isValidValue(Kind.typeNamed("Foo").asNullable(), null)).isTrue()
    assertThat(isValidValue(Kind.typeNamed("Foo").asNullable().asList(), null)).isFalse()
    assertThat(isValidValue(Kind.typeNamed("Foo").asList(), null)).isFalse()
    assertThat(isValidValue(Kind.typeNamed("Foo").asList(), listOf(emptyMap<String, Any?>()))).isTrue()

    val tripleKind =
        Kind.typeNamed("Foo")
            .asList()
            .asList()
            .asList()

    val tripleList = listOf(listOf(listOf(emptyMap<String, Any?>())))

    assertThat(isValidValue(tripleKind, tripleList)).isTrue()

    val tripleKind2 =
        Kind.typeNamed("...")
            .asNullable()
            .asList()
            .asList()

    val tripleNull = listOf(listOf(null))

    assertThat(
        isValidValue(tripleKind2, tripleNull)
    ).isTrue()

    assertThat(isValidValue(Kind.bool.asNullable(), false)).isFalse()
    assertThat(isValidValue(Kind.bool.asNullable(), null)).isTrue()
    assertThat(isValidValue(Kind.bool.asList().asList().asList(), emptyList<Any?>())).isTrue()

    assertThat(
        isValidValue(tripleKind2.asList(), tripleNull)
    ).isFalse()

  }

}