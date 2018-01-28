package org.kotlinq.adapters

import org.kotlinq.Model
import kotlin.reflect.KType

internal
class ModelPropertyImpl(
    override val type: KType,
    private val init: () -> Model<*>
) : ModelAdapter {

  private var value: Model<*>? = null

  override fun resolve(value: Map<String, Any?>): Boolean {
    TODO()
  }

  override fun getValue(): Model<*>? = value

}
