package com.prestongarno.ktq.runtime

import com.prestongarno.ktq.runtime.FieldType.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class ListMapper<out T : Any> internal constructor(val type: FieldType) {

	open operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, List<T>> {
		val mapper = GraphListProperty<T>(type, inst, property.name)
		inst.primitiveListFields.add(mapper) // on delegate initialization, adds the property name to list of fields
		if (ArgBuilder.last != null) {
			inst.payLoads.put(property.name, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return mapper
	}

	companion object {
		internal val intListMapper = ListMapper<Int>(INT)
		internal val floatListMapper = ListMapper<Float>(FLOAT)
		internal val boolListMapper = ListMapper<Boolean>(BOOLEAN)
		internal val stringListMapper = ListMapper<String>(STRING)
	}
}

open class PropertyMapper<out T : Any> internal constructor(val type: FieldType) {

	open operator fun provideDelegate(inst: GraphType, property: KProperty<*>): ReadOnlyProperty<GraphType, T> {
		val mapper = GraphFieldProperty<T>(type, inst, property.name)
		inst.primitiveFields.add(mapper) // on delegate initialization, adds the property name to list of fields
		if (ArgBuilder.last != null) {
			inst.payLoads.put(property.name, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return mapper
	}


	companion object {
		internal val intMapper = PropertyMapper<Int>(INT)
		internal val floatMapper = PropertyMapper<Float>(FLOAT)
		internal val boolMapper = PropertyMapper<Boolean>(BOOLEAN)
		internal val stringMapper = PropertyMapper<String>(STRING)
	}
}

internal enum class FieldType {
	INT,
	FLOAT,
	BOOLEAN,
	STRING,
	OBJECT;
}

internal class GraphListProperty<out T : Any>(val type: FieldType, val thisRef: GraphType, val name: String) : ReadOnlyProperty<GraphType, List<T>> {

	@Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY") private val value by lazy {
		(thisRef.values?.get(name) as List<String>).map {
			when (type) { // TODO :: get rid of hacky enum and make this actually type safe
				INT -> it.toInt()
				FLOAT -> it.toFloat()
				STRING -> it
				BOOLEAN -> if (it == "true") true else if (it == "false") false else throw IllegalArgumentException("Type boolean, but unknown value '$it'")
				else -> throw UnsupportedOperationException("Unexpected: $name was expected to be a list of primitives, but was of type Object")
			}
		} as List<T>
	}

	override fun getValue(thisRef: GraphType, property: KProperty<*>): List<T> = value

	override fun toString(): String {
		return "Property(type=$type, name='$name')"
	}
}

internal open class GraphFieldProperty<T>(val type: FieldType, val thisRef: GraphType, val name: String) : ReadOnlyProperty<GraphType, T> {

	@Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY") private val value by lazy {
		val raw = (thisRef.values?.get(name) as String)
		when (type) { // TODO :: get rid of hacky enum and make this actually type safe
			INT -> raw.toInt()
			FLOAT -> raw.toFloat()
			STRING -> raw
			BOOLEAN -> if (raw == "true") true else if (raw == "false") false else throw IllegalArgumentException("Type boolean, but unknown value '$raw'")
			else -> throw UnsupportedOperationException("Unexpected: $name was expected to be a list of primitives, but was of type Object")
		} as T
	}

	override fun getValue(thisRef: GraphType, property: KProperty<*>): T = value

	override fun toString(): String {
		return "Property(type=$type, name='$name')"
	}
}
