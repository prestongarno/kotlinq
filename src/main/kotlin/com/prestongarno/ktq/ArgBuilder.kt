package com.prestongarno.ktq

import kotlin.reflect.KProperty

/**
 * The supertype of all classes which are used for arguments on GraphQL types.
 * Constructor parameters are arguments which are required by the GraphQL schema,
 * while properties are GraphQL optional arguments
 */
open class ArgBuilder {
  /*protected*/ val arguments = PropertyMapper()

  infix fun String.with(value: Any) {
    arguments.put(this, value)
  }

  fun addArgument(key: String, value: Any) = apply { arguments.put(key, value) }
}

class PropertyMapper {

  private val values = mutableMapOf<String, Any?>()

  operator fun <T> getValue(inst: Any, property: KProperty<*>): T? =
      values[property.name]?.let {
        @Suppress("UNCHECKED_CAST")
        it as? T
      }

  operator fun <T> setValue(inst: Any, property: KProperty<*>, value: T) {
    values[property.name] = value
  }

  internal operator fun invoke(): Map<String, Any> = values.mapNotNull { (key, value) ->
    value?.let { key to it }
  }.toMap()

  internal fun put(key: String, value: Any) {
    values[key] = value
  }
}

