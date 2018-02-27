package org.kotlinq.jvm

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


fun <T> GraphQlResult.nullable(): ReadOnlyProperty<Any?, T> = NotNullDelegate(this)

operator fun <T> GraphQlResult.not(): ReadOnlyProperty<Any?, T> = NotNullDelegate(this)

@PublishedApi internal
class NotNullDelegate<out T>(val result: GraphQlResult) : ReadOnlyProperty<Any?, T> {
  @Suppress("UNCHECKED_CAST")
  override operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
      result.map[property.name] as? T ?: throw NullPointerException()
}

@PublishedApi internal
class NullableDelegate<out T>(val result: GraphQlResult): ReadOnlyProperty<Any?, T> {

  private var mapValueReflectionCheck: Boolean = false

  @Suppress("UNCHECKED_CAST")
  override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {

    if (!mapValueReflectionCheck) {
       result.map[property.name]?.let {
         checkCast<T>(property, it)
         mapValueReflectionCheck = true
       } ?: if (!property.returnType.isMarkedNullable) throw NullPointerException(property.toString())
    }

    return result.map[property.name] as? T ?: throw NullPointerException(property.toString())
  }

  internal companion object {

    /**
     * @throws IllegalStateException if type is incorrect
     */
    fun <T> checkCast(property: KProperty<*>, value: Any) {
      if (property.returnType.clazz?.isInstance(value) == false)
        throw IllegalStateException(
            "Expected $property, got wrong type ${value::class} ($value)")
    }
  }
}



