package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.QInput
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QProperty
import kotlin.reflect.KProperty

interface QField<out T> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
}

interface Adapter<out T> : QField<T> {

  val graphqlProperty: QProperty

  val args: Map<String, Any>

  fun accept(result: Any?): Boolean

  fun toRawPayload(): String
}


internal fun formatAs(value: Any): String {
  return when (value) {
    is Int, is Boolean, Float -> "$value"
    is String -> "\\\"$value\\\""
    is QInput -> value.toPayloadString()
    is Enum<*> -> value.name
    is List<*> -> value
        .map { formatAs(it ?: "") }
        .filter { it.isNotBlank() }
        .joinToString(",", "[ ", " ]")
    else -> throw UnsupportedOperationException("Unsupported type: $value")
  }
}
