package org.kotlinq.entities

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.kotlinq.introspection.Kind
import org.kotlinq.api.PropertyInfo

class PropertyInfoTests {

  @Test fun simplePropertyEquals() {
    val arguments = mapOf("hello" to "World", "first" to 10)
    val info0 = PropertyInfo.propertyNamed("value")
        .arguments(arguments)
        .typeKind(Kind.string)
        .build()
    info0.copy()
        .let(assertThat(info0)::isEqualTo)

    info0.copy(arguments = arguments.toMutableMap()
        .also { it["foo"] = "BAR" }
        .toMap())
        .let(assertThat(info0)::isNotEqualTo)
  }

  @Test fun complexKindEquals() {

    Kind.typeNamed("Foo")
        .asList()
        .asList()
        .asNullable()
        .asList()
        .let { it.copy() to it }
        .also { (one, two) ->
          assertThat(one).isEqualTo(two)
          assertThat(one.rootKind())
              .isEqualTo(two.rootKind())
          assertThat(one.toString())
              .isEqualTo(two.toString())
        }.also { (one, two) ->
          // change and compare
          assertThat(one.asList())
              .isNotEqualTo(one)
          assertThat(one.asNullable())
              .isNotEqualTo(one)
          assertThat(two.asList())
              .isNotEqualTo(two)
          assertThat(two.asNullable())
              .isNotEqualTo(two)
        }.also { (one, two) ->
          assertThat(one.asList())
              .isEqualTo(two.asList())
          assertThat(one.asNullable())
              .isEqualTo(two.asNullable())
          assertThat(two.rootKind())
              .isEqualTo(one.rootKind())
        }.let { (one, two) ->
          PropertyInfo.propertyNamed("foobar").typeKind(one).build() to PropertyInfo.propertyNamed("foobar").typeKind(two).build()
        }.also { (prop1, prop2) ->
          assertThat(prop1.copy(arguments = mapOf("number" to 1000)))
              .isNotEqualTo(prop1)
        }
  }
}

