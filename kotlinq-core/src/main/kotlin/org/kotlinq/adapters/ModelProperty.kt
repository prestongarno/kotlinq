package org.kotlinq.adapters

import org.kotlinq.api.JsonParser
import org.kotlinq.api.TypeContext
import kotlin.reflect.KType

internal
class ModelPropertyImpl(
    override val name: String,
    override val type: KType,
    val init: () -> TypeContext
) : ModelAdapter {

  private var instance: TypeContext? = null

  override fun isResolved(): Boolean {
    return instance?.graphQlInstance?.isResolved() == true
  }

  override fun take(value: String): Boolean {
    return resolve(JsonParser.parseToObject(value))
  }

  override fun resolve(value: Sequence<Pair<String, String>>): Boolean {
    require(this.instance == null) { "GraphQL queries are non-reusable operation" }

    instance = init().also { context ->
      value.forEach { (name, value) -> context.graphQlInstance.properties[name]?.take(value) }
    }

    return instance?.graphQlInstance?.isResolved() == true
  }

  override fun getValue(): Any? = instance
}
