package com.prestongarno.ktq.runtime

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*
import kotlin.collections.HashMap
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * The root type of all GraphQL objects
 *
 * Each field should be delegated to the appropriate method in this class.
 * Primitive or custom scalar fields have their own methods, while nested GraphType objects
 * should use the method GraphType#field for a single field, or GraphType#list for a list of objects:
 * providing as a parameter a function which returns an instance of that type or subtype
 *
 * Example BasicUser model of type `User`
 *
 *
 * ```
 *     class BasicUser : User() {
 *       public override val username by string()
 *     }
 * ```
 *
 * where User is a GraphQL type defined in the schema i.e. an auto-generated subclass GraphType.
 */
open class GraphType {

	/** This is the result of the query : Holds the values */
	internal var values: Map<String, Any?>? = null

	/** This map holds information about payloads (arguments) which are mapped to a specific field */
	internal var payLoads: MutableMap<KProperty<*>, Payload> = HashMap<KProperty<*>, Payload>()

	/** This list holds info about which fields to include in the GraphQL query */
	internal val primitiveFields: MutableList<String> = LinkedList()

	/** This list holds info about which GraphTypes (i.e. nested objects) to include in the GraphQL query */
	internal val objectFields: MutableList<GraphMapper<GraphType>> = LinkedList()

	/** Maps this field to a nested GraphType object
	 * @param of : A function which returns an instance of type of which this field is
	 */
	fun <T : GraphType> field(of: () -> T): PropertyMapper<T> = GraphPropertyMapper<T>(of)

	/** Maps this field to a list of nested GraphType object
	 * @param of : A function which returns an instance of type of which this field is
	 */
	/** Maps this field to an integer value */
	fun <T : GraphType> list(of: () -> T): ListMapper<T> = GraphListMapper<T>(of)

	/** Maps this field to an integer value */
	fun int() = PropertyMapper.intMapper

	/** Maps this field to an float value */
	fun float() = PropertyMapper.floatMapper

	/** Maps this field to an boolean value */
	fun bool() = PropertyMapper.boolMapper

	/** Maps this field to an string value */
	fun string() = PropertyMapper.stringMapper

	/** Maps this field to an ID type
	 * This cooresponds to the `ID` Scalar type defined in the GraphQL specification
	 */
	fun ID() = PropertyMapper.idMapper

	fun scalar() = PropertyMapper.stringMapper

	fun <T : Any> scalarMapper(converter: (String) -> T): PropertyMapper<T> {
		val scalarMapper = ScalarMapper<T>(converter)
		return scalarMapper
	}

	fun <T : Any> scalarListMapper(converter: (String) -> T): ListMapper<T> = TODO()

	/** Maps this field to a list of integer values */
	fun intList() = ListMapper.intListMapper

	/** Maps this field to a list of float values */
	fun floatList() = ListMapper.floatListMapper

	/** Maps this field to a list of boolean values */
	fun boolList() = ListMapper.boolListMapper

	/** Maps this field to a list of string values */
	fun stringList() = ListMapper.stringListMapper

	override fun toString(): String {
		return "GraphType(values=$values\n, payLoads=$payLoads\n, primitiveFields=$primitiveFields\n, objectFields=$objectFields)"
	}

	/** Throwable set as default delegate value for all fields on
	 * auto-generated GraphQL schema types, ensures that models explicitly declare their fields & types
	 */
	class SchemaStub : Throwable("not declared")

}

/** Interface for all custom scalar types. Scalars defined in the schema
 * are generated as interfaces, allowing an as-needed implementation of the "getValue"
 *
 * Fields of this type should can be either a concrete class, or an inline function/override
 *
 * An example of this is a Date/Time field
 */
interface Scalar<T> {
	val rawValue: String

	fun toType(): T
}

class ID(override val rawValue: String) : Scalar<String> {
	override fun toType(): String = rawValue
}

internal class Payload : ArgBuilder {

	val payloadMap: MutableMap<String, Any> = HashMap()

	override fun addArg(name: String, value: Any): ArgBuilder {
		payloadMap.put(name, value)
		return this
	}

	override fun build(queryType: GraphType): GraphType = queryType

	override fun toString(): String {// TODO -> make the toString generate a JSON-compatible format for adding payloads
		return "Payload(payloadMap=$payloadMap)"
	}

}

interface ArgBuilder {

	fun addArg(name: String, value: Any): ArgBuilder

	fun build(queryType: GraphType): GraphType

	companion object {
		fun create(): ArgBuilder {
			val payload = Payload()
			last = payload
			return payload
		}

		/** Nullable field needed for delegates to link a payload with a property*/
		internal var last: Payload? = null
	}
}

interface GraphMapper<out T : GraphType> {
	fun create(): T
	fun isList(): Boolean
	fun getName(): String
}

internal class ScalarMapper<T : Any>(val converter: (String) -> T) : PropertyMapper<T>() {
	override fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T> {
		return object : ReadOnlyProperty<GraphType, T> {
			override fun getValue(thisRef: GraphType, prop: KProperty<*>): T {
				val raw = thisRef.values?.get(prop.name) as String
				return converter.invoke(raw)
			}
		}
	}
}

internal class ScalarListMapper<T : Any>(val converter: (String) -> T) : ListMapper<T>() {
	override fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, List<T>> {
		return object : ReadOnlyProperty<GraphType, List<T>> {
			@Suppress("UNCHECKED_CAST") override fun getValue(thisRef: GraphType, prop: KProperty<*>): List<T> {
				val rawValues = thisRef.values?.get(prop.name) as List<String>
				return rawValues.map { converter.invoke(it) }
			}
		}
	}
}

internal class GraphListMapper<T : GraphType>(val of: () -> T) : ListMapper<T>(), GraphMapper<T> {
	override fun create(): T = of.invoke()
	override fun isList(): Boolean = true
	override fun getName(): String = fieldName!! // delegate will be created before property access

	private var fieldName: String? = null

	override operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, List<T>> {
		inst.objectFields.add(this)
		fieldName = property.name
		if (ArgBuilder.last != null) {
			inst.payLoads.put(property, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return GraphListProperty<T>()
	}

	override fun toString(): String {
		return "GraphListMapper(of=$of, fieldName=$fieldName)"
	}
}

internal class GraphPropertyMapper<out T : GraphType>(val of: () -> T) : PropertyMapper<T>(), GraphMapper<T> {
	override fun create(): T = of.invoke()
	override fun isList(): Boolean = true
	override fun getName(): String = fieldName!! // delegate will be created before property access

	private var fieldName: String? = null

	override operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T> {
		fieldName = property.name
		inst.objectFields.add(this)
		if (ArgBuilder.last != null) {
			inst.payLoads.put(property, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return GraphReadOnlyProperty<T>()
	}

	override fun toString(): String {
		return "GraphPropertyMapper(of=$of, fieldName=$fieldName)"
	}
}

open class ListMapper<out T : Any> internal constructor() {

	open operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, List<T>> {
		inst.primitiveFields.add(property.name) // on delegate initialization, adds the property name to list of fields
		if (ArgBuilder.last != null) {
			inst.payLoads.put(property, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return GraphListProperty<T>()
	}

	internal class GraphListProperty<out T : Any> : ReadOnlyProperty<GraphType, List<T>> {
		@Suppress("UNCHECKED_CAST") override fun getValue(thisRef: GraphType, property: KProperty<*>): List<T> {
			if (ArgBuilder.last != null) {
				thisRef.payLoads.put(property, ArgBuilder.last!!)
				ArgBuilder.last = null
			}
			return thisRef.values?.get(property.name) as List<T>? ?: emptyList<T>()
		}
	}

	companion object {
		internal val intListMapper = ListMapper<Int>()
		internal val floatListMapper = ListMapper<Float>()
		internal val boolListMapper = ListMapper<Boolean>()
		internal val stringListMapper = ListMapper<String>()
	}
}

open class PropertyMapper<out T : Any> internal constructor() {

	open operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T> {
		inst.primitiveFields.add(property.name) // on delegate initialization, adds the property name to list of fields
		if (ArgBuilder.last != null) {
			inst.payLoads.put(property, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return GraphReadOnlyProperty<T>()
	}

	internal class GraphReadOnlyProperty<T> : ReadOnlyProperty<GraphType, T> {
		@Suppress("UNCHECKED_CAST") override fun getValue(thisRef: GraphType, property: KProperty<*>): T {
			return thisRef.values?.get(property.name) as T? ?: throw NullPointerException("No such ${property.name} in ${thisRef::class.qualifiedName}")
		}
	}

	companion object {
		internal val intMapper = PropertyMapper<Int>()
		internal val floatMapper = PropertyMapper<Float>()
		internal val boolMapper = PropertyMapper<Boolean>()
		internal val stringMapper = PropertyMapper<String>()
		internal val idMapper = PropertyMapper<ID>()
	}
}
