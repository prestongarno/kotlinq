package org.kotlinq.jvm

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.api.Kind
import org.kotlinq.jvm.ClassFragment.Companion.fragment
import org.kotlinq.jvm.Validator.isValidValue
import org.kotlinq.jvm.annotations.Ignore


class ResolverTest {

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

  @Test fun singleNestedFragmentResolves() {

    class RootSub(value: GraphQlResult) : Root(value) {
      @Ignore
      override val nest = null
    }

    val frag = fragment(::RootSub)


    assertThat(frag.toGraphQl())
        .isEqualTo("{id,__typename,bar,foo}")

    val data = mapOf(
        "foo" to 1000,
        "bar" to "is this thing on")

    assertThat(frag.resolveFrom(data))
        .isNotNull()

    assertThat(frag.resolveFrom(data)!!.bar)
        .isEqualTo("is this thing on")

    assertThat(frag.resolveFrom(data)!!.foo)
        .isEqualTo(1000)
  }

  @Test fun resolveSubFragment() {

    val frag = fragment(::Root) {
      Root::nest on ::Nest
    }

    val result = mapOf(
        "foo" to 100,
        "bar" to "Hello",
        "nest" to mapOf(
            "baz" to "World",
            "__typename" to "Nest"))

    assertThat(Validator.canResolve(result, frag)).isTrue()

    val reified = frag.resolveFrom(result)

    assertThat(reified).isNotNull()

    assertThat(reified!!.nest).isNotNull()

    assertThat(reified.nest!!.baz).isEqualTo("World")
  }

}

open class Root(value: GraphQlResult) : GraphQlData(value) {
  val foo by value.integer()
  val bar by value.string()
  open val nest by value<Nest>()
}

open class Nest(value: GraphQlResult) : GraphQlData(value) {
  val baz by value.string()
}
