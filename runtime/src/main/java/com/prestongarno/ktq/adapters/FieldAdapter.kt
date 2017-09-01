package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.QInput
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.indent
import com.prestongarno.ktq.internal.ModelProvider
import kotlin.reflect.KProperty

internal abstract class FieldAdapter(val fieldName: String) {
  val args: MutableMap<String, Any> = HashMap(4, 0.75f)

  lateinit var property: KProperty<*>

  fun onProvideDelegate(inst: QModel<*>) = inst.fields.add(this)

  /**
   * I try to make my code as unreadable as possible
   * ... this still works though somehow. Expression-based languages will be the end of me
   */
  fun toRawPayload(indentation: Int = 1): String =
      property.name +
          (if (args.isNotEmpty()) {
            args.map {
              "${it.key}: ${formatAs(it.value, indentation + 1)}"
            }.joinToString(separator = ",\n\t", prefix = "(", postfix = ")")
          } else "")
              .concat(
                  if (this is ModelProvider)
                    this.getModel().toJson()
                  else "")
              .indent(indentation).replace("\\s+([(,])".toRegex(), "$1")


  override fun toString(): String = toRawPayload()
}

internal fun formatAs(value: Any, indentation: Int = 1): String {
  return when (value) {
    is Int, is Boolean, Float -> "$value"
    is String -> "\"$value\""
    is QInput -> value.toPayloadString()
    is Enum<*> -> value.name
    is List<*> -> value
        .map { formatAs(it ?: "") }
        .filter { it.isNotBlank() }
        .joinToString(", ", "[ ", " ]")
    is FieldAdapter -> throw IllegalStateException("how did this get here")//value.toRawPayload(indentation + 1)
    else -> throw UnsupportedOperationException()
  }
}

private fun String.concat(to: String): String = this + to
