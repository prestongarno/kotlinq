package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.ScalarStub
import com.prestongarno.ktq.adapters.TypeStubAdapter

interface ArgBuilder<T> {
  fun addArg(name: String, value: Any): ArgBuilder<*>
  fun build(): Stub<T>

  companion object {
    fun <T> create(): ArgBuilder<T> = ScalarPayload()
  }
}

interface TypeArgBuilder<T : QType, U : QModel<T>> {
  fun <U : QModel<T>> build(init: () -> U) : TypeStub<U, T>

  fun addArg(name: String, value: Any): TypeArgBuilder<T, U>

  companion object {
    fun <T: QType, A: TypeArgBuilder<T, QModel<T>>> create() : TypeArgBuilder<T, QModel<T>>
        = TypeStubAdapter<QModel<T>, T, A>()
  }
}

internal class ScalarPayload<T> : ArgBuilder<T> {
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
