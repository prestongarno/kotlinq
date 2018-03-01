package org.kotlinq.jvm

import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


operator fun <T> GraphQlResult.not(): ReadOnlyProperty<Any?, T?> = TODO()//GraphQlFieldDelegate(this)

@PublishedApi
internal
class GraphQlFieldDelegate<out T>(
    private val values: Map<String, Any?>
) : ReadOnlyProperty<Any?, T> {

  private val accessRecord: MutableSet<String> =
      Collections.synchronizedSet(mutableSetOf())

  /**
   * For single instance per GraphQlResult,
   * just as type safe as individual ones per-property
   */
  @Suppress("UNCHECKED_CAST")
  internal fun <X> withReturnType() = this as ReadOnlyProperty<Any?, X>

  override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {

    if (!wasAccessed(property.name)) {
      checkCast(property, values[property.name])
    }

    @Suppress("UNCHECKED_CAST")
    return values[property.name] as? T ?: removeAndThrowNullPointer(property)
  }


  private
  fun wasAccessed(propertyName: String): Boolean {
    if (accessRecord.contains(propertyName))
      return false.also { accessRecord.add(propertyName) }
    return true
  }

  private
  fun <T> removeAndThrowNullPointer(property: KProperty<*>): T {
    accessRecord.remove(property.name)
    throw NullPointerException(property.toString())
  }

  internal companion object {

    /**
     * TODO should check for stdlib collections types, etc.
     * @throws NullPointerException if type is incorrect
     */
    fun checkCast(property: KProperty<*>, value: Any?) {
      if (!property.returnType.isCompatibleWith(value))
        throw NullPointerException("On $property, got wrong type ${value?.let { it::class }
            ?: Nothing::class} ($value)")
    }
  }
}



