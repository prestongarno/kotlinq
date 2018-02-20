package org.kotlinq

import org.kotlinq.api.GraphQlPropertyInfo
import kotlin.reflect.KClass
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

infix fun Any.eq(expect: Any?) = require(this == expect) {
  errNotMatching(this, expect)
}

infix fun Any.notEq(expect: Any?) = require(this != expect) {
  "Expected <'$this'> not to but was equal to <'$expect'>"
}

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
    graphQlTypeName: String = GraphQlPropertyInfo.STRING,
    arguments: Map<String, Any> = emptyMap(),
    clazz: KClass<*> = String::class
) =
    GraphQlPropertyInfo(graphQlName, graphQlTypeName, mockType(clazz), arguments)
