package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.api.BooleanAdapter
import org.kotlinq.api.FloatAdapter
import org.kotlinq.api.GraphQlType
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
    override val name: String,
    override val type: GraphQlType,
    override val initializer: (String) -> Int,
    override val arguments: Map<String, Any> = emptyMap()
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
    override val name: String,
    override val type: GraphQlType,
    override val initializer: (String) -> String,
    override val arguments: Map<String, Any> = emptyMap()
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
    override val name: String,
    override val type: GraphQlType,
    override val initializer: (String) -> Float,
    override val arguments: Map<String, Any> = emptyMap()
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
    override val name: String,
    override val type: GraphQlType,
    override val initializer: (String) -> Boolean,
    override val arguments: Map<String, Any> = emptyMap()
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
