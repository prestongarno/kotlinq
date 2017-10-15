package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.ModelProvider
import kotlin.reflect.KProperty

internal abstract class FieldAdapter(override val graphqlProperty: QProperty) : Adapter, Payload {

  /**
   * A map of arguments for the field (for graphql) */
  override val args by lazy { mutableMapOf<String, Any>() }

  /**
   * The actual graphqlName for the delegation set value. does not have to be the same name and/or the same return type
   */
  lateinit var targetProperty: KProperty<*>

  /**
   * Notified when this object is being provided as an adapter for a field */
  override fun onDelegate(inst: QModel<*>, property: KProperty<*>) {
    inst.fields.add(this)
    targetProperty = property
  }

  /**
   * Accept the result of the query */
  abstract override fun accept(result: Any?): Boolean

  override fun toRawPayload(): String = graphqlProperty.graphqlName + when {
    args.isNotEmpty() -> this.args.entries
        .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
          "$key: ${formatAs(value)}"
        }
    this is ModelProvider -> value.toGraphql(false)
    else -> ""
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as FieldAdapter

    if (graphqlProperty != other.graphqlProperty) return false

    return true
  }

  override fun hashCode(): Int {
    return graphqlProperty.hashCode()
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

