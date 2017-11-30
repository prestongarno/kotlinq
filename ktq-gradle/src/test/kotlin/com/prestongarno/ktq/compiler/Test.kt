package com.prestongarno.ktq.compiler

import com.google.common.truth.ThrowableSubject
import com.google.common.truth.Truth.assertThat
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import java.time.Instant
import java.util.*
import kotlin.reflect.KCallable

/** F# expression redirect for single exp. return types */
fun Any?.ignore() = Unit

@Suppress("NOTHING_TO_INLINE") inline infix fun Any?.eq(other: Any?) = assertThat(this).isEqualTo(other)
@Suppress("NOTHING_TO_INLINE") inline infix fun Any?.notEq(other: Any?) = assertThat(this).isNotEqualTo(other)

inline fun <reified T> assertThrows(block: () -> Unit): ThrowableSubject {
  try {
    block()
  } catch (e: Throwable) {
    if (e is T) {
      return assertThat(e)
    } else {
      throw e
    }
  }
  throw AssertionError("Expected ${T::class.simpleName}")
}

fun compileOut(schema: String, block: (GraphQLCompiler.() -> Unit) = emptyBlock()): String =
    GraphQLCompiler(StringSchema(schema))
        .apply(block)
        .toKotlinApi()

// Not a test case without a functional println
fun Any?.println() = println(this)

fun <T> emptyBlock(): T.() -> Unit = Block.empty<T>()

private object Block {

  private val value: Any.() -> Unit = { /* Nothing */ }

  @Suppress("UNCHECKED_CAST") fun <T> empty(): T.() -> Unit = value as T.() -> Unit
}
