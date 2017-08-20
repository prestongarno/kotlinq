package com.prestongarno.ktq

interface ArgBuilder<T> {

  fun addArg(name: String, value: Any): ArgBuilder<*>

  fun build(): Stub<T, *>

  companion object {
    fun <T, A: ArgBuilder<T>> create() : A = TODO()
  }

}

internal class Payload<T>(val stub: Stub<*, *>) : ArgBuilder<T> {
  override fun build(): Stub<T, *> {
    @Suppress("UNCHECKED_CAST")
    return stub as Stub<T, *>
  }

  val values: MutableMap<in String, Any> = HashMap()

	override fun addArg(name: String, value: Any): ArgBuilder<T> {
		values.put(name, value)
    println(values)
    @Suppress("UNCHECKED_CAST")
    return this
  }

	override fun toString(): String = TODO()

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
}

