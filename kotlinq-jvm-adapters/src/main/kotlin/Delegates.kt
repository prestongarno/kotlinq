package org.kotlinq.jvm

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.InstanceAdapter
import java.util.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf


/**
 * Delegate for a [Data] instance properties.
 *
 * TODO decouple the type checking and the
 * mapping/initialization from the platform type
 */
@PublishedApi internal
class GraphQlFieldDelegate<out T> internal constructor(
    values: Map<String, Any?>,
    inst: GraphQlInstance
) : ReadOnlyProperty<Any?, T> {

  private val accessRecord: MutableSet<String> =
      Collections.synchronizedSet(mutableSetOf())

  /**
   * Maps names of properties to the types/fragments that it can resolve to
   */
  private var instanceResolvers: Map<String, Set<ClassFragment<*>>> =
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

  @Suppress("UNCHECKED_CAST")
  override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {

    if (!wasAccessed(property.name)) synchronized(this) {
      val value = run {
        val raw = mutableRawValues[property.name]
        if (property.returnType.rootType.isSubtypeOf(dataType)) {
          createInstanceFromClassFragment(
              instanceResolvers[property.name] ?: emptySet(),
              property.returnType,
              raw)
        } else raw
      }
      checkCast(property, value)
      values[property.name] = value
      mutableRawValues[property.name] = null
      // release all fragments after resolving all, since those can be large objects
      if (mutableRawValues.isEmpty())
        instanceResolvers = emptyMap()
    }

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

    /** @throws NullPointerException if type is incorrect */
    fun checkCast(property: KProperty<*>, value: Any?) {
      if (!property.returnType.isCompatibleWith(value))
        throw NullPointerException("On $property, got wrong type ${value?.let { it::class }
            ?: Nothing::class} ($value)")
    }


    // TODO remove recursion
    fun createInstanceFromClassFragment(fragments: Set<ClassFragment<*>>, returnType: KType, value: Any?): Any? {

      if (returnType.isMarkedNullable && value == null) return null

      if (returnType.isList) return run {
        (value as? Iterable<*>)?.map {
          createInstanceFromClassFragment(
              fragments,
              returnType.arguments.first().type!!,
              it)
        } ?: emptyList()
      }.let {
        if (!returnType.arguments.first().type!!.isMarkedNullable) it.filterNotNull() else it
      }

      return value.asStringMap()?.let { map ->
        if (fragments.size == 1)
          fragments.first().resolveFrom(map)
        else
          fragments.find { it.typeName == map["__typename"] }
              ?.resolveFrom(map)
      }
    }

    private val dataType = Data::class.createType(nullable = true)
  }
}



