package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.adapters.formatAs
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
    if (model is QUnionType) prettyPrintUnion(indentation) else
      ((fields.joinToString(separator = ",\n") { it.prettyPrinted() }
          .indent(1)) + "\n}").prepend("{\n").indent(indentation)
          .replace("\\s*([(,])".toRegex(), "$1").trim()

internal fun QModel<*>.prettyPrintUnion(indentation: Int) =
    (fields.joinToString(separator = ",\n", prefix = "{\n".indent(indentation)) {
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
      else -> ""
    }).replace("\\s*([(,])".toRegex(), "$1").trim()

internal object Jsonify {
  val INDENT = "  "
}