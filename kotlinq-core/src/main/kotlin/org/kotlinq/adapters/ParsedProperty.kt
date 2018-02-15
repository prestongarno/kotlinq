package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.ParsingAdapter


internal
class ParsedProperty(
    override val propertyInfo: GraphQlPropertyInfo,
    override val initializer: (String) -> Any?
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
      result != null || propertyInfo.isNullable

  override fun equals(other: Any?) =
      Adapter.adapterEquals(this, other as? Adapter)

  override fun hashCode() =
      Adapter.adapterHashcode(this)
}