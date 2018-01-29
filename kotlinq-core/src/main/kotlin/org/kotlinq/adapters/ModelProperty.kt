package org.kotlinq.adapters

import org.kotlinq.api.GraphQlInstance
import kotlin.reflect.KType

internal
class ModelPropertyImpl(
    override val name: String,
    override val type: KType,
    // TODO this should be a function returning the state container
    private val init: () -> GraphQlInstance
) : ModelAdapter {

  private var value: Any? = null

  override fun resolve(value: Map<String, Any?>): Boolean {
    TODO()
  }

  override fun getValue(): Any? = value

}
