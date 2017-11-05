package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.input.QInput
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

interface Adapter {

  val qproperty: GraphQlProperty

  val args: Map<String, Any>

  fun accept(result: Any?): Boolean

  fun toRawPayload(): String
}

interface QField<out T> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
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
    else -> throw UnsupportedOperationException("Unsupported format for type: ${value::class}")
  }
}
