package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

internal class ScalarStub<T, A: ArgBuilder<T>>(val argBuilder: A? = null) :
    Stub<T>,
    Config<A, T>,
    ArgBuilder<T> {

  @Suppress("UNCHECKED_CAST") override fun config(): A = argBuilder ?: ScalarStub<T, A>() as A

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T> = this

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T {
    throw UnsupportedOperationException()
  }

  val values: MutableMap<in String, Any> = HashMap()

  @Suppress("UNCHECKED_CAST") override fun build() : Stub<T> = ScalarStub()

  override fun addArg(name: String, value: Any): ArgBuilder<T> = apply { values.put(name, value)  }

  override fun toString(): String = values.map { "${it.key} : ${formatAs(it.value)}" }.joinToString { "\n" }

}

private fun formatAs(value: Any): String {
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
