package org.kotlinq.common

@Suppress("UNCHECKED_CAST")
private object Block {
  private val empty: Any?.() -> Unit = { /* nothing */ }

  fun <T : Any?> emptyBlock(): T.() -> Unit = empty
}

fun <T : Any?> empty(): T.() -> Unit = Block.emptyBlock()

internal
fun Map<String, Any>.stringify(): String = if (entries.isEmpty()) "" else
  entries.joinToString(prefix = "(", postfix = ")", separator = ",") { (k, v) -> "$k: ${formatArg(v)}" }

internal
fun String.bracket(): String = "[$this]"

internal
fun String.parenthesize(): String = "($this)"

internal fun String.quote() = """"$this""""

internal
fun formatArg(value: Any): String = when (value) {
  is Int, is Boolean -> "$value"
  is Float -> "${value}f"
  is String -> value.quote()
  is Enum<*> -> value.name.quote()
  is List<*> -> value
      .map { formatArg(it ?: "") }
      .filter { it.isNotBlank() }
      .joinToString(",", "[ ", " ]")
  else -> value.toString().quote()
}

internal
fun <T: Any> T.unit(block: T.() -> Any?) {
  block()
}

internal
fun Any?.ignore() = Unit

internal
fun <E> MutableList<E>.addFirst(element: E) =
    add(0, element).ignore()

internal
fun <E> MutableList<E>.addLast(element: E) =
    add(element).ignore()

