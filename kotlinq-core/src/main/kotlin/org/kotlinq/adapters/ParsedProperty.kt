package org.kotlinq.adapters

import org.kotlinq.api.Adapter
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

  override fun equals(other: Any?): Boolean {
    other as? Adapter ?: return false

    if (name != other.name) return false
    if (type != other.type) return false
    if (arguments != other.arguments) return false

    return true
  }

  override fun hashCode(): Int {
    var result1 = name.hashCode()
    result1 = 31 * result1 + type.hashCode()
    result1 = 31 * result1 + arguments.hashCode()
    result1 *= 31
    return result1
  }


}