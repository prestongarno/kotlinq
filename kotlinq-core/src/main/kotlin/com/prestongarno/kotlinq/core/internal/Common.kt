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

package com.prestongarno.kotlinq.core.internal

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.PropertyMapper
import com.prestongarno.kotlinq.core.QInputType
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.Adapter
import com.prestongarno.kotlinq.core.hooks.FragmentContext
import com.prestongarno.kotlinq.core.hooks.ModelProvider


@Suppress("UNCHECKED_CAST")
private object Block {
  private val empty: Any?.() -> Unit = { /* nothing */ }

  fun <T : Any?> emptyBlock(): T.() -> Unit = empty
}

internal
fun <T : Any?> empty(): T.() -> Unit = Block.emptyBlock()

internal
fun Map<String, Any>.stringify(): String = if (entries.isEmpty()) "" else
  entries.joinToString(prefix = "(", postfix = ")", separator = ",") { (k, v) -> "$k: ${formatAs(v)}" }

internal
fun ArgBuilder?.stringify(): String = this?.let {
  this.getArguments().stringify().parenthesize()
} ?: ""

internal
fun PropertyMapper.stringify(): String =
    this().entries.joinToString(separator = ",") { (k, v) -> "$k: ${formatAs(v)}" }

internal
fun String.bracket(): String = "[$this]"

internal
fun String.parenthesize(): String = "($this)"

internal
fun formatAs(value: Any): String {
  return when (value) {
    is Int, is Boolean -> "$value"
    is Float -> "${value}f"
    is String -> "\\\"$value\\\""
    is QInputType -> value.toString()
    is Enum<*> -> value.name
    is List<*> -> value
        .map { formatAs(it ?: "") }
        .filter { it.isNotBlank() }
        .joinToString(",", "[ ", " ]")
    else -> throw UnsupportedOperationException("Unsupported format for type: ${value::class}")
  }
}

internal
fun String.indent(times: Int = 1): String =
    replace("^".toRegex(), Jsonify.INDENT.repeat(times))
        .replace("\\n".toRegex(), ("\n${Jsonify.INDENT.repeat(times)}"))

internal
fun String.prepend(of: String): String = of + this

internal
fun QModel<*>.prettyPrinted(indentation: Int): String =
    ((getFields().joinToString(separator = ",\n") { it.prettyPrinted() }
        .indent(1)) + "\n}").prepend("{\n").indent(indentation)
        .replace("\\s*([(,])".toRegex(), "$1").trim()

internal
fun QModel<*>.prettyPrintUnion(indentation: Int) =
    (getFields().joinToString(separator = ",\n", prefix = "{\n".indent(indentation)) {
      it.prettyPrinted().prepend("... on ")
    }.indent(1)
        .plus("\n}")
        .indent(indentation))
        .replace("\\s*([(,])".toRegex(), "$1").trim()

internal
fun Adapter.prettyPrinted(): String = qproperty.graphqlName +
    (when {
      args.isNotEmpty() -> args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") {
            "${it.key}: ${formatAs(it.value)}"
          }
      this is ModelProvider -> value.toGraphql()
      this is FragmentContext -> fragments.joinToString("\n") {
        "... on ${qproperty.graphqlType} ${it.model.toGraphql()}"
      }
      else -> ""
    }).replace("\\s*([(,])".toRegex(), "$1").trim()

internal object Jsonify {
  val INDENT = "  "

}
