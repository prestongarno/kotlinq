package org.kotlinq.adapters

import java.io.InputStream
import kotlin.reflect.KType


class DeserializingProperty(
    override val name: String,
    override val type: KType,
    val init: (java.io.InputStream) -> Any?,
    override val arguments: Map<String, String>
) : DeserializingAdapter {

  private var value: Any? = null

  override fun resolve(stream: InputStream): Boolean {
    value = init(stream)
    return isResolved()
  }

  override fun getValue() = value

  override fun take(value: String): Boolean = resolve(value.byteInputStream())

  override fun isResolved() = value != null || type.isMarkedNullable
}