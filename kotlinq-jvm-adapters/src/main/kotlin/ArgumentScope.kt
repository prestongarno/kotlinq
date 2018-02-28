package org.kotlinq.jvm


class ArgumentScope internal constructor(
    private val block: ArgumentScope.() -> Unit) {

  private val arguments = mutableMapOf<String, Any>()

  init { this.block() }

  operator fun String.invoke(other: Any) { arguments[this] = other }

  fun toMap() = arguments.toMap()
}