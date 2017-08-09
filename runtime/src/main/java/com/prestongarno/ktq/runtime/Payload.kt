package com.prestongarno.ktq.runtime

internal class Payload() : ArgBuilder {

	constructor(vararg arguments: Pair<String, Any>) : this() {
		arguments.map { values.put(it.first, it.second) }
	}

	val values: MutableMap<in String, Any> = HashMap()

	override fun addArg(name: String, value: Any): ArgBuilder {
		values.put(name, value)
		return this
	}

	override fun build() = GraphType.Companion

	override fun toString(): String = TODO()

	private fun formatAs(value: Any): String {
		return when (value) {
			is Int, is Boolean, Float -> "$value"
			is String -> "\"$value\""
			is GraphType -> { TODO() }
			is Enum<*> -> value.name
			is List<*> -> value
					.map { formatAs(it ?: "") }
					.filter { it.isNotBlank() }
					.joinToString(", ", "[ ", " ]")
			else -> throw UnsupportedOperationException()
		}
	}
}

interface ArgBuilder {

	fun addArg(name: String, value: Any): ArgBuilder

	fun build(): GraphType.Companion

	companion object {
		fun create(): ArgBuilder {
			val payload = Payload()
			last = payload
			return payload
		}

		/** Nullable field needed for delegates to link a payload with a property*/
		internal var last: Payload? = null
		val empty: ArgBuilder by lazy { Payload() }
	}
}


