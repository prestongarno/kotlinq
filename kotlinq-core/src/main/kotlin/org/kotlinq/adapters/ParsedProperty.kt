package org.kotlinq.adapters

import kotlin.reflect.KType


internal
class ParsedProperty(
    override val name: String,
    override val type: KType,
    override val initializer: (String) -> Any?
) : ParsingAdapter {

  lateinit var textResult: String

  private val result: Any? by lazy {
    if (!this::textResult.isInitialized) initializer("") else initializer(textResult)
  }

  override fun getValue(): Any? = result

  override fun take(value: String): Boolean {
    textResult = value
    return true
  }

  override fun isResolved() = this::textResult.isInitialized

}