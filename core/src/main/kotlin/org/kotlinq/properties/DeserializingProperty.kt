package org.kotlinq.properties

import org.kotlinq.api.Adapter
import org.kotlinq.api.DeserializingAdapter
import org.kotlinq.api.PropertyInfo
import java.io.InputStream


internal
class DeserializingProperty(
    override val propertyInfo: PropertyInfo,
    override val initializer: (java.io.InputStream) -> Any?
) : DeserializingAdapter {

  private var value: Any? = null

  override fun getValue() = value

  override fun isResolved() = value != null || propertyInfo.isNullable

  override fun setValue(value: InputStream?): Boolean {
    this.value = value?.let(initializer)
    return isResolved()
  }

  override fun equals(other: Any?) =
      Adapter.adapterEquals(this, other as? Adapter)

  override fun hashCode() =
      Adapter.adapterHashcode(this)

}