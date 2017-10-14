package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.ModelProvider

internal abstract class FieldAdapter(override val property: Property) : Adapter, Payload {

  /**
   * A map of arguments for the field (for graphql) */
  override val args by lazy { mutableMapOf<String, Any>() }

  /**
   * Notified when this object is being provided as an adapter for a field */
  override fun onProvideDelegate(inst: QModel<*>) { inst.fields.add(this); }

  /**
   * Accept the result of the query */
  abstract override fun accept(result: Any?): Boolean

  /**
   * I try to make my code as unreadable as possible
   * ... this still works though somehow. Expression-based languages will be the end of me */
  override fun toRawPayload(): String = property.fieldName + when {
    args.isNotEmpty() -> this.args.entries
        .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
          "$key: ${formatAs(value)}"
        }
    this is ModelProvider -> getModel().toGraphql(false)
    else -> ""
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as FieldAdapter

    if (property != other.property) return false

    return true
  }

  override fun hashCode(): Int {
    return property.hashCode()
  }

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

