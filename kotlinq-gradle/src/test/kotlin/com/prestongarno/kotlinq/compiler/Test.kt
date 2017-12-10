/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.compiler

import com.google.common.truth.ThrowableSubject
import com.google.common.truth.Truth.assertThat

@Suppress("unused")
    /** F# expression redirect for single exp. return types */
fun Any?.ignore() = Unit

@Suppress("NOTHING_TO_INLINE")
inline infix fun Any?.eq(other: Any?) = assertThat(this).isEqualTo(other)
@Suppress("NOTHING_TO_INLINE")
inline infix fun Any?.notEq(other: Any?) = assertThat(this).isNotEqualTo(other)

inline fun <reified T> assertThrows(block: () -> Unit): ThrowableSubject {
  try {
    block()
  } catch (e: Throwable) {
    return if (e is T) {
      assertThat(e)
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

fun <T> emptyBlock(): T.() -> Unit = Block.empty()

private object Block {

  private val value: Any.() -> Unit = { /* Nothing */ }

  @Suppress("UNCHECKED_CAST") fun <T> empty(): T.() -> Unit = value as T.() -> Unit
}
