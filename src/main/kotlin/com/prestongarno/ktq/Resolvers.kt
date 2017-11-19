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

package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.adapters.formatAs
import com.prestongarno.ktq.hooks.FragmentContext
import com.prestongarno.ktq.hooks.ModelProvider
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure


internal fun KProperty<*>.typedValueFrom(value: Any): Any? {
  return if (this.returnType.jvmErasure == value::class)
    value
  else when (this.returnType.jvmErasure) {
    Int::class -> "$value".toIntOrNull()
    Boolean::class -> "$value".toBoolean()
    Float::class -> "$value".toFloatOrNull()
    String::class -> "$value"
    else -> {
      if (returnType.jvmErasure.java.isEnum)
        returnType.jvmErasure.javaObjectType.enumConstants.find {
          (it as Enum<*>).name == "$value"
        }
      else null
    }
  }
}

internal fun KProperty<*>.typedListValueFrom(value: Any): List<Any> {
  val type: KClass<*>? = returnType.arguments[0].type?.classifier as KClass<*>
  val values = (value as? List<*>)?.filterNotNull() ?: listOf(value)
  val responseType: KClass<*> = if (values.isNotEmpty()) values[0]::class else Any::class

  return when (type) {
    null -> emptyList()
    responseType -> values
    Int::class -> values.mapNotNull { "$it".toIntOrNull() }
    Boolean::class -> values.map { "$it".toBoolean() }
    Float::class -> values.mapNotNull { "$it".toFloatOrNull() }
    String::class -> values.map { "$it" }
    else -> {
      if (type.java.isEnum) {
        values.mapNotNull {
          type.java.enumConstants.find { (it as Enum<*>).name == "$it" }
        }
      } else {
        emptyList()
      }
    }
  }
}


fun String.indent(times: Int = 1): String =
    replace("^".toRegex(), Jsonify.INDENT.repeat(times))
        .replace("\\n".toRegex(), ("\n${Jsonify.INDENT.repeat(times)}"))

fun String.prepend(of: String): String = of + this

internal fun QModel<*>.prettyPrinted(indentation: Int): String =
    ((getFields().joinToString(separator = ",\n") { it.prettyPrinted() }
          .indent(1)) + "\n}").prepend("{\n").indent(indentation)
          .replace("\\s*([(,])".toRegex(), "$1").trim()

internal fun QModel<*>.prettyPrintUnion(indentation: Int) =
    (getFields().joinToString(separator = ",\n", prefix = "{\n".indent(indentation)) {
      it.prettyPrinted().prepend("... on ")
    }.indent(1)
        .plus("\n}")
        .indent(indentation))
        .replace("\\s*([(,])".toRegex(), "$1").trim()

internal fun Adapter.prettyPrinted(): String = qproperty.graphqlName +
    (when {
      args.isNotEmpty() -> args.entries
          .joinToString(separator = ",", prefix = "(", postfix = ")") {
            "${it.key}: ${formatAs(it.value)}"
          }
      this is ModelProvider -> value.toGraphql()
      this is FragmentContext -> fragments.joinToString("\n") {
        "... on ${qproperty.graphqlType} ${it.model.toGraphql(true)}"
      }
      else -> ""
    }).replace("\\s*([(,])".toRegex(), "$1").trim()

internal object Jsonify {
  val INDENT = "  "
}