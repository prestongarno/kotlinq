package org.kotlinq.adapters

import org.kotlinq.api.JsonParser
import org.kotlinq.api.TypeContext
import kotlin.reflect.KType

internal
class ModelPropertyImpl(
    override val name: String,
    override val type: KType,
    override val arguments: Map<String, String>,
    override val initializer: () -> TypeContext
) : ModelAdapter {

  private var instance: TypeContext? = null

  override val prototype: TypeContext by lazy { initializer() }

  override fun isResolved(): Boolean {
    return instance?.graphQlInstance?.isResolved() == true
  }

  override fun take(value: String): Boolean {
    return resolve(JsonParser.parseToObject(value))
  }

  override fun resolve(value: Sequence<Pair<String, String>>): Boolean {
    require(this.instance == null) { "GraphQL queries are non-reusable operation" }

    instance = initializer().also { context ->
      value.forEach { (name, value) -> context.graphQlInstance.properties[name]?.take(value) }
    }

    return instance?.graphQlInstance?.isResolved() == true
  }

  override fun getValue(): Any? = instance
}
