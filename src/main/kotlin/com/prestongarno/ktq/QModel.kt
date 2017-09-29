package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.adapters.FieldAdapter
import java.io.InputStream
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.jvmErasure

open class QModel<out T : QSchemaType>(val model: T) {

  internal val fields = mutableListOf<FieldAdapter>()

  internal var resolved = false

  fun isResolved(): Boolean = resolved

  override fun toString() = this.toGraphql()

  fun toGraphql(indentation: Int = 0): String {
    return if (model is QSchemaUnion) unionToGraphql(indentation) else
      ((fields.joinToString(separator = ",\n") { it.toRawPayload() }
          .indent(1)) + "\n}").prepend("{\n").indent(indentation)
          .replace("\\s*([(,])".toRegex(), "$1").trim()
  }

  private fun unionToGraphql(indentation: Int): String =
      (fields.joinToString(separator = ",\n", prefix = "{\n".indent(indentation)) {
        it.toRawPayload().prepend("... on ")
      }.indent(1)
          .plus("\n}")
          .indent(indentation))
          .replace("\\s*([(,])".toRegex(), "$1").trim()

  internal fun onResponse(input: InputStream) {
    (Parser().parse(input) as JsonObject).run {
      fields.forEach {
        it.accept(this[it.fieldName])
      }
    }
  }

  internal fun onResponse(input: String) = onResponse(input.byteInputStream())

  internal fun accept(input: JsonObject): Boolean = this.fields.run {
    var resolved = true
    forEach { if (!it.accept(input[it.fieldName]))
        resolved = false }
    resolved
  }
}

fun String.indent(times: Int = 1): String =
    replace("^".toRegex(), Jsonify.INDENT.repeat(times))
        .replace("\\n".toRegex(), ("\n${Jsonify.INDENT.repeat(times)}"))

fun String.prepend(of: String): String = of + this

fun KProperty<*>.typedValueFrom(value: Any): Any? {
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

fun KProperty<*>.typedListValueFrom(value: Any): List<Any> {
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
