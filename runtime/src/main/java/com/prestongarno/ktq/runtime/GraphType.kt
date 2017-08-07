package com.prestongarno.ktq.runtime

import kotlin.collections.HashMap
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * The root class for all types, trying to simplify the delegates process
 */

open class GraphType() {

	internal var values: Map<String, Any?>? = null
	internal var payLoads: MutableMap<KProperty<*>, Payload> = HashMap<KProperty<*>, Payload>()

	fun <T : Any> field() = PropertyMapper<T>()

	fun <T : Any> collection() = ListMapper<T>()

	class SchemaStub : Throwable("not declared")

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

class ListMapper<out T : Any> internal constructor() {
	@Suppress("UNCHECKED_CAST") operator fun <R : GraphType> getValue(inst: R, prop: KProperty<*>): List<T> {
		if (ArgBuilder.last != null) {
			inst.payLoads.put(prop, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return inst.values?.get(prop.name) as List<T>? ?: emptyList<T>()
	}
}

class PropertyMapper<out T : Any> internal constructor() {
	operator fun provideDelegate(inst: GraphType, prop: KProperty<*>): ReadOnlyProperty<GraphType, T> {
        println("PropertyMapper :: provideDelegate")
        return GraphReadOnlyProperty<T>()
    }
}

class GraphReadOnlyProperty<T>: ReadOnlyProperty<GraphType, T> {
	init {
		println("Initialized ${this::class.simpleName}")
	}
	override fun getValue(thisRef: GraphType, property: KProperty<*>): T {
		throw UnsupportedOperationException("Not Supported")
	}
}

