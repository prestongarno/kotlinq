package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.adapters.FieldAdapter
import java.io.InputStream
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

open class QModel<out T : QSchemaType>(val model: T) {

  internal val fields = mutableListOf<FieldAdapter>()

  internal var resolved = false

  fun isResolved(): Boolean = resolved

  internal fun onResponse(input: InputStream) {
    (Parser().parse(input) as JsonObject).run {
      fields.forEach {
        it.accept(this[it.fieldName])
      }
    }
  }

  internal fun onResponse(input: String) = onResponse(input.byteInputStream())

  internal fun accept(input: JsonObject): Boolean {
    resolved = fields
        .filterNot { it.accept(input[it.fieldName]) }
        .isEmpty()
    return resolved
  }

  override fun toString() = this.toGraphql()

  fun toGraphql(pretty: Boolean = true): String = if (pretty) {
    prettyPrinted(0)
  } else if (model is QSchemaUnion) {
    unionToGraphql()
  } else {
    fields.joinToString(",", "{", "}") { it.toRawPayload() }
  }

  private fun unionToGraphql(): String = TODO()

  internal fun QModel<*>.prettyPrinted(indentation: Int): String =
      if (model is QSchemaUnion) prettyPrintUnion(indentation) else
        ((fields.joinToString(separator = ",\n") { it.prettyPrinted(indentation) }
            .indent(1)) + "\n}").prepend("{\n").indent(indentation)
            .replace("\\s*([(,])".toRegex(), "$1").trim()

  internal fun prettyPrintUnion(indentation: Int) =
      (fields.joinToString(separator = ",\n", prefix = "{\n".indent(indentation)) {
        it.prettyPrinted(indentation).prepend("... on ")
      }.indent(1)
          .plus("\n}")
          .indent(indentation))
          .replace("\\s*([(,])".toRegex(), "$1").trim()
}

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

