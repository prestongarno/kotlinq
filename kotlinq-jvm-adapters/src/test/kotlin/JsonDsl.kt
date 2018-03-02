package org.kotlinq.jvm


fun json(block: JsonScope.() -> Unit): Map<String, Any> = JsonScope(block).toMap()


class JsonScope(block: JsonScope.() -> Unit) {


  init { this.block() }


  private val map = mutableMapOf<String, Any>()


  operator fun String.invoke(value: Any) {
    map[this] = value
  }


  operator fun String.invoke(block: JsonScope.() -> Unit) {
    map[this] = JsonScope(block).toMap()
  }

  internal fun toMap() = map.toMap()
}