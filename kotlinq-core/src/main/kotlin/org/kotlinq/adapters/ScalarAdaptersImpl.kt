package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.api.BooleanAdapter
import org.kotlinq.api.FloatAdapter
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.GraphVisitor
import org.kotlinq.api.IntAdapter
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.StringAdapter


sealed class PrimitiveAdapter : ParsingAdapter {

  override fun equals(other: Any?) =
      Adapter.adapterEquals(this, other as? Adapter)

  override fun hashCode() =
      Adapter.adapterHashcode(this)
}


class IntAdapterImpl(
    override val propertyInfo: GraphQlPropertyInfo,
    override val initializer: (String) -> Int
) : PrimitiveAdapter(), IntAdapter {

  private var value = 0

  override fun getValue() = value

  override fun setValue(value: String?): Boolean {
    this.value = initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visitScalar(this)
  }

  override fun isResolved() = true
}

class StringAdapterImpl(
    override val propertyInfo: GraphQlPropertyInfo,
    override val initializer: (String) -> String
) : PrimitiveAdapter(), StringAdapter {

  private var value: String? = null

  override fun getValue() = value ?: ""

  override fun setValue(value: String?): Boolean {
    this.value = initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visitScalar(this)
  }

  override fun isResolved() = true
}

class FloatAdapterImpl(
    override val propertyInfo: GraphQlPropertyInfo,
    override val initializer: (String) -> Float
) : PrimitiveAdapter(), FloatAdapter {

  private var value = 0f

  override fun getValue() = value

  override fun setValue(value: String?): Boolean {
    this.value = initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visitScalar(this)
  }

  override fun isResolved() = true
}

class BooleanAdapterImpl(
    override val propertyInfo: GraphQlPropertyInfo,
    override val initializer: (String) -> Boolean
) : PrimitiveAdapter(), BooleanAdapter {

  private var value = false

  override fun getValue() = value

  override fun setValue(value: String?): Boolean {
    this.value = initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: GraphVisitor) {
    resolver.visitScalar(this)
  }

  override fun isResolved() = true
}
