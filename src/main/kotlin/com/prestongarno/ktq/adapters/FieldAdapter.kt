package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.Payload
import com.prestongarno.ktq.QInput
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.internal.ModelProvider
import kotlin.reflect.KProperty

internal abstract class FieldAdapter(override val graphqlName: String) : Adapter, Payload {

  /**
   * A map of arguments for the field (for graphql) */
  override val args by lazy { mutableMapOf<String, Any>() }

  /**
   * The backing kotlin property */
  lateinit var property: KProperty<*>

  /**
   * Notified when this object is being provided as an adapter for a field */
  override fun onProvideDelegate(inst: QModel<*>) { inst.fields.add(this) }

  /**
   * Accept the result of the query */
  abstract override fun accept(result: Any?): Boolean

  /**
   * I try to make my code as unreadable as possible
   * ... this still works though somehow. Expression-based languages will be the end of me */
  override fun toRawPayload(): String = graphqlName + when {
    args.isNotEmpty() -> this.args.entries
        .joinToString(separator = ",", prefix = "(", postfix = ")") { (key, value) ->
          "$key: ${formatAs(value)}"
        }
    this is ModelProvider -> getModel().toGraphql(false)
    else -> ""
  }

  fun prettyPrinted(): String = graphqlName +
      (when {
        args.isNotEmpty() -> args.entries
            .joinToString(separator = ",", prefix = "(", postfix = ")") {
              "${it.key}: ${formatAs(it.value)}" }
        this is ModelProvider -> getModel().toGraphql()
        else -> ""
      }).replace("\\s*([(,])".toRegex(), "$1").trim()

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
        .joinToString(", ", "[ ", " ]")
    else -> throw UnsupportedOperationException("Unsupported type: $value")
  }
}

