package org.kotlinq.jvm


fun json(block: JsonScope.() -> Unit): Map<String, Any?> =
    JsonScope(block).toMap()


class JsonScope(block: JsonScope.() -> Unit) {


  private val map = mutableMapOf<String, Any?>()

  init { this.block() }


  operator fun String.invoke(value: String) = putValue(this, value)
  operator fun String.invoke(value: Int) = putValue(this, value)
  operator fun String.invoke(value: Boolean) = putValue(this, value)
  operator fun String.invoke(value: Double) = putValue(this, value)
  operator fun String.invoke(list: List<*>) = putValue(this, list)

  operator fun String.invoke(vararg values: Any) =
      putValue(this, values.toList())

  infix fun String.list(block: ArrayScope.() -> Unit) =
      putValue(this, ArrayScope(block).toList())

  private fun putValue(key: String, value: Any) { map[key] = value }

  operator fun String.invoke(block: JsonScope.() -> Unit) {
    map[this] = JsonScope(block).toMap()
  }

  internal fun toMap() = map.toMap()
}

class ArrayScope(block: ArrayScope.() -> Unit) {


  private val objects = mutableListOf<Map<String, Any?>>()

  init { this.block() }

  fun add(block: JsonScope.() -> Unit) {
    objects.add(JsonScope(block).toMap())
  }

  internal fun toList() = objects.toList()
}