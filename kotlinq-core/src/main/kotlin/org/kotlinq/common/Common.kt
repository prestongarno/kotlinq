package org.kotlinq.common

@Suppress("UNCHECKED_CAST")
private object Block {
  private val empty: Any?.() -> Unit = { /* nothing */ }

  fun <T : Any?> emptyBlock(): T.() -> Unit = empty
}

internal
fun <T : Any?> empty(): T.() -> Unit = Block.emptyBlock()

internal
fun Map<String, Any>.stringify(): String = if (entries.isEmpty()) "" else
  entries.joinToString(prefix = "(", postfix = ")", separator = ",") { (k, v) -> "$k: ${formatAs(v)}" }

internal
fun String.bracket(): String = "[$this]"

internal
fun String.parenthesize(): String = "($this)"

internal
fun formatAs(value: Any): String {
  return when (value) {
    is Int, is Boolean -> "$value"
    is Float -> "${value}f"
    is String -> "\"$value\""
    //is QInputType -> value.input.stringify().bracket()
    is Enum<*> -> value.name
    is List<*> -> value
        .map { formatAs(it ?: "") }
        .filter { it.isNotBlank() }
        .joinToString(",", "[ ", " ]")
    else -> throw UnsupportedOperationException("Unsupported format for type: ${value::class}")
  }
}
