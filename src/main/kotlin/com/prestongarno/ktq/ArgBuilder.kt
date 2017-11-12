package com.prestongarno.ktq

import kotlin.reflect.KProperty

open class ArgBuilder {
  /*protected*/ val arguments = PropertyMapper()
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

  internal fun getAll(): Sequence<Pair<String, Any>> = values.entries
      .mapNotNull { (key, value) -> value?.let { key to it } }
      .asSequence()
}

// interface CustomScalarArgBuilder : ArgBuilder { fun <U: QScalarMapper<T>, T> build(init: U): CustomStub<U, T> }
//interface CustomScalarListArgBuilder : ArgBuilder { fun <U: QScalarListMapper<T>, T> build(init: U): CustomScalarListStub<U, T> }
