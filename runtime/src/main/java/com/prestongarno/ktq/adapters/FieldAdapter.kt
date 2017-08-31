package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.QInput
import com.prestongarno.ktq.QModel
import kotlin.reflect.KProperty

internal abstract class FieldAdapter {
  val args: MutableMap<String, Any> = HashMap(4, 0.75f)

  lateinit var property: KProperty<*>

  fun onProvideDelegate(inst: QModel<*>) = inst.fields.add(this)

  fun toRawPayload(): String = property.name +
      if (args.isNotEmpty()) {
        args.map {
          "${it.key}: ${formatAs(it.value)}"
        }.joinToString(separator = ", ", prefix = "(", postfix = ")")
      } else ""

  override fun toString(): String = toRawPayload()
}

internal fun formatAs(value: Any): String {
  return when (value) {
    is Int, is Boolean, Float -> "$value"
    is String -> "\"$value\""
    is QInput -> value.toPayloadString()
    is Enum<*> -> value.name
    is List<*> -> value
        .map { formatAs(it ?: "") }
        .filter { it.isNotBlank() }
        .joinToString(", ", "[ ", " ]")
    else -> throw UnsupportedOperationException()
  }
}
