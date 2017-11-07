package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.input.QInput
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Reflective object which provides the information needed
 * to generate the correct representation of the GraphQL field query
 *
 * Base type of all delegates - this is the internal side of a property.
 * This class (internal API) and [com.prestongarno.ktq.adapters.QField] (public API delegate)
 * generally are both implemented on a property delegate */
interface Adapter {

  val qproperty: GraphQlProperty

  val args: Map<String, Any>

  fun accept(result: Any?): Boolean

  fun toRawPayload(): String
}

/**
 * Public API delegate representing an object which holds
 * the backing field information about a GraphQL property
 * @param T : The type of object or value which this provides */
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
