package com.prestongarno.ktq

import kotlin.reflect.KProperty

open class ArgBuilder {
  /*protected*/ val arguments = PropertyMapper()

  infix fun String.with(value: Any) {
    arguments.put(this, value)
  }

  fun addArgument(key: String, value: Any) = apply { arguments.put(key, value) }
}

@Suppress("UNCHECKED_CAST")
internal fun <A : ArgBuilder> toArgumentMap(
    args: A?,
    scope: (A.() -> Unit)?
): Map<String, Any> =
    args?.apply { scope?.invoke(this) }?.arguments?.invoke()
        ?: (ArgBuilder() as? A)?.apply { scope?.invoke(this) }?.arguments?.invoke()
        ?: emptyMap()

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

// interface CustomScalarArgBuilder : ArgBuilder { fun <U: QScalarMapper<T>, T> build(init: U): CustomStub<U, T> }
//interface CustomScalarListArgBuilder : ArgBuilder { fun <U: QScalarListMapper<T>, T> build(init: U): CustomScalarListStub<U, T> }
