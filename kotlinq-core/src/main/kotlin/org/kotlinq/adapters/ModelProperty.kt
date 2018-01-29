package org.kotlinq.adapters

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlJsonParser
import org.kotlinq.context.ContextAware
import kotlin.reflect.KType

internal
class ModelPropertyImpl(
    override val name: String,
    override val type: KType,
    val init: () -> GraphQlInstance
) : ModelAdapter {

  private lateinit var lazyParsingSequence: Sequence<Pair<String, String>>

  private val instance by lazy(init)

  override fun take(value: String): Boolean {
    return onQuery(GraphQlJsonParser.parseToObject(value))
  }

  private var value: Any? = null

  override fun parseField(adapter: ContextAware): Boolean {
    // Make sure that the property is calling from within the correct context
    require(adapter.context.get() === this && instance.properties[adapter.name] != null)
    // parse the values in this instance until a result is found
    return parseUntil(adapter.name)
  }

  override fun onQuery(value: Sequence<Pair<String, String>>): Boolean {
    require(!this::lazyParsingSequence.isInitialized) {
      "GraphQL queries must be an immutable, non-reusable operation"
    }
    lazyParsingSequence = value
    return true
  }

  private
  fun parseUntil(propertyName: String): Boolean {

    // this should never happen
    if (!this::lazyParsingSequence.isInitialized)
      throw IllegalStateException("GraphQL query was not resolved!")

    // parse until the value is found
    return lazyParsingSequence.firstOrNull { (name, value) ->
      name == propertyName
          && instance.properties[propertyName]
          ?.take(value) == true
    } != null
  }

  override fun getValue(): Any? = value

}
