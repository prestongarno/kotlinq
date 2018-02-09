package org.kotlinq.adapters

import org.kotlinq.api.BooleanAdapter
import org.kotlinq.api.FloatAdapter
import org.kotlinq.api.IntAdapter
import org.kotlinq.api.Resolver
import org.kotlinq.api.StringAdapter
import kotlin.reflect.KType


class IntAdapterImpl(
    override val name: String,
    override val type: KType,
    override val initializer: (String) -> Int,
    override val arguments: Map<String, Any> = emptyMap()
) : IntAdapter {

  private var value = 0

  override fun getValue() = value

  override fun setValue(value: String?): Boolean {
    this.value = initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: Resolver) {
    resolver.visitScalar(this)
  }

  override fun isResolved() = true
}

class StringAdapterImpl(
    override val name: String,
    override val type: KType,
    override val initializer: (String) -> String,
    override val arguments: Map<String, Any> = emptyMap()
) : StringAdapter {

  private var value: String? = null

  override fun getValue() = value ?: ""

  override fun setValue(value: String?): Boolean {
    this.value = initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: Resolver) {
    resolver.visitScalar(this)
  }

  override fun isResolved() = true
}

class FloatAdapterImpl(
    override val name: String,
    override val type: KType,
    override val initializer: (String) -> Float,
    override val arguments: Map<String, Any> = emptyMap()
) : FloatAdapter {

  private var value = 0f

  override fun getValue() = value

  override fun setValue(value: String?): Boolean {
    this.value = initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: Resolver) {
    resolver.visitScalar(this)
  }

  override fun isResolved() = true
}

class BooleanAdapterImpl(
    override val name: String,
    override val type: KType,
    override val initializer: (String) -> Boolean,
    override val arguments: Map<String, Any> = emptyMap()
) : BooleanAdapter {

  private var value = false

  override fun getValue() = value

  override fun setValue(value: String?): Boolean {
    this.value = initializer(value ?: "")
    return isResolved()
  }

  override fun accept(resolver: Resolver) {
    resolver.visitScalar(this)
  }

  override fun isResolved() = true
}
