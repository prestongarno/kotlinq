package org.kotlinq.adapters

import org.kotlinq.api.DeserializingAdapter
import org.kotlinq.api.Resolver
import java.io.InputStream
import kotlin.reflect.KType


internal
class DeserializingProperty(
    override val name: String,
    override val type: KType,
    override val initializer: (java.io.InputStream) -> Any?,
    override val arguments: Map<String, Any>
) : DeserializingAdapter {
  private var value: Any? = null

  override fun getValue() = value

  override fun isResolved() = value != null || type.isMarkedNullable

  override fun setValue(value: InputStream?): Boolean {
    this.value = value?.let(initializer)
    return isResolved()
  }

  override fun accept(resolver: Resolver) {
    resolver.visitDeserializer(this)
  }

}