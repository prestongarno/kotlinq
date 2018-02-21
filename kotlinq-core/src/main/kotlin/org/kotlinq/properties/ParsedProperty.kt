package org.kotlinq.properties

import org.kotlinq.api.Adapter
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.ParsingAdapter


internal
class ParsedProperty(
    override val propertyInfo: PropertyInfo,
    override val initializer: (String) -> Any?
) : ParsingAdapter {

  private var result: Any? = null

  override fun getValue(): Any? = result

  override fun setValue(value: String?): Boolean {
    result = this.initializer(value ?: "")
    return isResolved()
  }

  override fun isResolved() =
      result != null || propertyInfo.isNullable

  override fun equals(other: Any?) =
      Adapter.adapterEquals(this, other as? Adapter)

  override fun hashCode() =
      Adapter.adapterHashcode(this)
}