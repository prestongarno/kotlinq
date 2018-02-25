package org.kotlinq.api.schema

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


private
class NotNullDelegate<out T>(val map: Map<String, Any?>) : ReadOnlyProperty<Any?, T> {
  @Suppress("UNCHECKED_CAST") override
  operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
      map[property.name] as? T ?: throw NullPointerException()
}

class NullableDelegate<out T>(val map: Map<String, Any?>) : ReadOnlyProperty<Any?, T?> {
  @Suppress("UNCHECKED_CAST") override
  operator fun getValue(thisRef: Any?, property: KProperty<*>): T? = map[property.name] as? T
}


fun <T> Map<String, Any?>.notNull(): ReadOnlyProperty<Any?, T> = NotNullDelegate(this)

fun <T> Map<String, Any?>.nullable(): ReadOnlyProperty<Any?, T> = NotNullDelegate(this)

