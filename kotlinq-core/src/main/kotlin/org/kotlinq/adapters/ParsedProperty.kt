package org.kotlinq.adapters

import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.Resolver
import kotlin.reflect.KType


internal
class ParsedProperty(
    override val name: String,
    override val type: KType,
    override val initializer: (String) -> Any?,
    override val arguments: Map<String, String>
) : ParsingAdapter {

  lateinit var textResult: String

  private val result: Any? by lazy {
    if (!this::textResult.isInitialized) initializer("") else initializer(textResult)
  }

  override fun getValue(): Any? = result

  override fun setValue(value: String?): Boolean {
    this.textResult = value ?: ""
    return result != null || type.isMarkedNullable
  }

  override fun accept(resolver: Resolver) {
    resolver.visitScalar(name, this)
  }

  override fun isResolved() = this::textResult.isInitialized || type.isMarkedNullable

}