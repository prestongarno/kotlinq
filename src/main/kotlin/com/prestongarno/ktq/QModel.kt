package com.prestongarno.ktq

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.adapters.FieldAdapter
import com.prestongarno.ktq.internal.FragmentProvider
import java.io.InputStream
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.jvmErasure

open class QModel<out T : QSchemaType>(val model: T) {

  internal val fields by lazy { mutableListOf<FieldAdapter>() }

  internal var resolved = false

  fun isResolved(): Boolean = resolved

  internal fun onResponse(input: InputStream) {
    (Parser().parse(input) as? JsonObject)?.run {
      fields.forEach { it.accept(this[it.fieldName]) }
    }
  }

  internal fun onResponse(input: String) = onResponse(input.byteInputStream())

  internal open fun accept(input: JsonObject): Boolean {
    resolved = fields
        .filterNot { it.accept(input[it.fieldName]) }
        .isEmpty()
    return resolved
  }

  fun toGraphql(pretty: Boolean = true): String {
    return when {
      pretty -> prettyPrinted(0)
      this is QSchemaUnion -> toPayload()
      else -> fields.joinToString(",", "{", "}") { it.toRawPayload() }
    }
  }

  private fun unionToGraphql(): String =
      fields.joinToString(separator = ",", prefix = "{", postfix = "}") {
        it.toRawPayload().prepend("... on ")
      }

  override fun toString() = "${this::class.simpleName}<${model::class.simpleName}>" +
      fields.joinToString(",", "[", "]") { it.toRawPayload() }

  companion object {
    internal val NONE: QModel<*> = QModel<QSchemaType>(object : QSchemaType {
      override fun equals(other: Any?): Boolean {
        return other === this
      }
    })
  }
}

private fun QModel<*>.prettyPrinted(indentation: Int): String =
    if (model is QSchemaUnion) prettyPrintUnion(indentation) else
      ((fields.joinToString(separator = ",\n") { it.prettyPrinted() }
          .indent(1)) + "\n}").prepend("{\n").indent(indentation)
          .replace("\\s*([(,])".toRegex(), "$1").trim()

private fun QModel<*>.prettyPrintUnion(indentation: Int) =
    (fields.joinToString(separator = ",\n", prefix = "{\n".indent(indentation)) {
      it.prettyPrinted().prepend("... on ")
    }.indent(1)
        .plus("\n}")
        .indent(indentation))
        .replace("\\s*([(,])".toRegex(), "$1").trim()

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
    // TODO Take out generic type arguments from the stubs for primitives -> these are BOXED AND UNBOXED every time they're accessed!
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

