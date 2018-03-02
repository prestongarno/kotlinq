package org.kotlinq.jvm

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.InstanceAdapter
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.full.isSubclassOf


@PublishedApi
internal
class GraphQlFieldDelegate<out T>(
    values: Map<String, Any?>,
    inst: GraphQlInstance
) : ReadOnlyProperty<Any?, T> {

  private val accessRecord: MutableSet<String> =
      Collections.synchronizedSet(mutableSetOf())

  /**
   * Maps names of properties to the types/fragments that it can resolve to
   */
  private val instanceResolvers: Map<String, Set<ClassFragment<*>>> =
      inst.properties.entries.mapNotNull { (key, value) ->
        key with when (value) {
          is FragmentSpread<*> -> value.classFragments
          is InstanceAdapter -> (value.fragment as? ClassFragment<*>)?.let(::setOf) ?: emptySet()
          else -> null
        }
      }.toMap()

  private val mutableRawValues = values.toMutableMap()

  private val values = Collections.synchronizedMap(mutableMapOf<String, Any?>())!!

  /**
   * For single instance per GraphQlResult,
   * just as type safe as individual ones per-property
   */
  @Suppress("UNCHECKED_CAST")
  internal fun <X> withReturnType() = this as ReadOnlyProperty<Any?, X>

  override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {

    if (!wasAccessed(property.name)) {
      val value = mutableRawValues[property.name]?.let {
        if (property.returnType.rootType.clazz?.isSubclassOf(Data::class) == true) {
          createInstanceFromClassFragment(
              instanceResolvers[property.name] ?: emptySet(),
              property.returnType, it)
        } else it
      }
      checkCast(property, value)
      values[property.name] = value
      mutableRawValues[property.name] = null
    }

    @Suppress("UNCHECKED_CAST")
    return values[property.name] as? T
        ?: if (property.returnType.isMarkedNullable) null as T else removeAndThrowNullPointer(property)
  }


  private
  fun wasAccessed(propertyName: String) = accessRecord.contains(propertyName)
      .also { if (!it) accessRecord.add(propertyName) }

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


    fun createInstanceFromClassFragment(fragments: Set<ClassFragment<*>>, returnType: KType, value: Any?): Any? {

      if (returnType.isMarkedNullable && value == null) return null

      if (returnType.isList) return value.asList<Any?>(returnType.clazz ?: Any::class)?.map {
        createInstanceFromClassFragment(fragments, returnType.arguments.first().type!!, it)
      }?.let {
        if (!returnType.arguments.first().type!!.isMarkedNullable) it.filterNotNull() else it
      }

      return value.asStringMap()?.let { map ->
        if (fragments.size == 1)
          fragments.first().resolveFrom(map)
        else
          fragments.find { it.typeName == map["__typename"] }?.resolveFrom(map)
      }
    }
  }
}



