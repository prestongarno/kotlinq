package org.kotlinq.adapters

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.JsonParser
import kotlin.reflect.KType

internal
class ModelPropertyImpl(
    override val name: String,
    override val type: KType,
    val init: () -> GraphQlInstance
) : ModelAdapter {

  private val instance by lazy(init)

  private var value: Sequence<Pair<String, String>>? = null

  override fun isResolved(): Boolean {
    return value == null
  }

  override fun take(value: String): Boolean {
    return resolve(JsonParser.parseToObject(value))
  }

  override fun resolve(value: Sequence<Pair<String, String>>): Boolean {
    require(this.value == null) {
      "GraphQL queries are non-reusable operation"
    }
    this.value = value
    return true
  }

  override fun getValue(): Any? = instance
}
