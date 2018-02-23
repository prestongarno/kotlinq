package org.kotlinq.printer


private
fun formatArg(value: Any, quotationMark: String): String =
    when (value) {
      is Int, is Boolean -> "$value"
      is Float -> "${value}f"
      is String -> value.wrap(quotationMark)
      is Enum<*> -> value.name.wrap(quotationMark)
      is Iterable<*> -> value
          .map { formatArg(it ?: "", quotationMark) }
          .filter { it.isNotBlank() }
          .joinToString(",", "[ ", " ]")
      else -> value.toString().wrap(quotationMark)
    }

private fun String.wrap(value: String): String = value + String + value
