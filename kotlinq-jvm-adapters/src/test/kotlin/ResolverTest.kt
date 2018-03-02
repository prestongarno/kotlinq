package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.Kind
import org.kotlinq.jvm.ClassFragment.Companion.fragment


class ResolverTest {

  @Test fun validatorValuesTest() {
    assertThat(Validator.isValidValue(Kind.string, "")).isTrue()
    assertThat(Validator.isValidValue(Kind.integer, 1)).isTrue()
    assertThat(Validator.isValidValue(Kind.float, 1.0f)).isTrue()
    assertThat(Validator.isValidValue(Kind.bool, 1.0f)).isTrue() // todo fixme
    assertThat(Validator.isValidValue(Kind.typeNamed("Any"), emptyMap<String, Any?>())).isTrue()
    assertThat(Validator.isValidValue(Kind.typeNamed("Foo").asNullable(), null)).isTrue()
    assertThat(Validator.isValidValue(Kind.typeNamed("Foo").asNullable().asList(), null)).isFalse()
    assertThat(Validator.isValidValue(Kind.typeNamed("Foo").asList(), null)).isFalse()
    assertThat(Validator.isValidValue(Kind.typeNamed("Foo").asList(), listOf(emptyMap<String, Any?>()))).isTrue()

    val tripleKind =
        Kind.typeNamed("Foo")
            .asList()
            .asList()
            .asList()

    val tripleList = listOf(listOf(listOf(emptyMap<String, Any?>())))

    assertThat(Validator.isValidValue(tripleKind, tripleList)).isTrue()

    val tripleKind2 =
        Kind.typeNamed("...")
            .asNullable()
            .asList()
            .asList()

    val tripleNull = listOf(listOf(null))

    assertThat(
        Validator.isValidValue(tripleKind2, tripleNull)
    ).isTrue()

    // failing
    assertThat(
        Validator.isValidValue(tripleKind2.asList(), tripleNull)
    ).isFalse()

  }

  @Test fun singleNestedFragmentResolves() {
    val frag = fragment(::Root)
    assertThat(frag.toGraphQl())
        .isEqualTo("{id,__typename,bar,foo}")

    val data = mapOf(
        "foo" to 1000,
        "bar" to "is this thing on")

    assertThat(frag.resolveFrom(data))
        .isNotNull()
  }

}

class Root(value: GraphQlResult) : GraphQlData(value) {
  val foo by value.integer()
  val bar by value.string()
}