package org.kotlinq

import com.google.common.truth.Truth.assertThat
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.Kind
import kotlin.reflect.full.isSubclassOf

infix fun Throwable.withMessageContaining(value: String) =
    require((message ?: "").contains(value)) {
      "Expected <'$this'> to contain <'$value'>"
    }

infix fun Throwable.messageMatchingExactly(value: Regex) =
    require((message ?: "").matches(value)) {
      errNotMatching(this, value)
    }

infix fun Throwable.messageMatchingExactly(value: String) =
    message!! eq value

infix fun Throwable.withMessageContaining(value: Regex) =
    require((message ?: "").contains(value)) {
      "Expected <'$this'> to contain <'$value'>"
    }

infix fun Any?.matchesNotNull(expect: Any?) = require(this!! == expect!!) {
  errNotMatching(this, expect)
}

infix fun Any.eq(expect: Any?) =
    assertThat(this).isEqualTo(expect)

infix fun Any.notEq(expect: Any?) =
    assertThat(this).isNotEqualTo(expect)

fun Any?.println() = println(this)

inline fun <reified T> assertThrows(noinline block: () -> Unit): T {
  try {
    block()
  } catch (ex: Throwable) {
    if (!ex::class.isSubclassOf(T::class)) {
      throw java.lang.AssertionError("Expected exception '${T::class.qualifiedName}' " +
          "but was '${ex::class.qualifiedName}'", ex)
    } else return ex as T
  }
  throw AssertionError("No exception was thrown (Expected: '${T::class.qualifiedName}'")
}

private fun errNotMatching(expect: Any?, actual: Any?) =
    "Expected <'$expect'> was not equal to <'$actual'>"

fun info(
    graphQlName: String,
    kind: Kind = Kind.Scalar._String,
    arguments: Map<String, Any> = emptyMap()
) = PropertyInfo(graphQlName, kind, arguments)
