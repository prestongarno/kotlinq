package org.kotlinq.adapters

import org.kotlinq.api.GraphQlType
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.Resolver
import kotlin.reflect.KType


internal
class ParsedProperty(
    override val name: String,
    override val type: GraphQlType,
    override val initializer: (String) -> Any?,
    override val arguments: Map<String, Any>
) : ParsingAdapter {

  private var result: Any? = null

  override fun getValue(): Any? = result

  override fun setValue(value: String?): Boolean {
    result = this.initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visitScalar(this)
  }

  override fun isResolved() =
      result != null || type.isNullable

}