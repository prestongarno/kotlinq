package com.prestongarno.ktq.runtime

import kotlin.collections.HashMap
import kotlin.reflect.KProperty

/**
 * The root class for all types, trying to simplify the delegates process
 */

open class GraphType() {

	internal var values: Map<String, Any?>? = null
	internal var payLoads: MutableMap<KProperty<*>, Payload> = HashMap()

	/** Restricts fields in subtypes using QueryData delegates to be of the lower bounds `T` */
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

fun set(obj: GraphType, map: Map<String, Any?>) {
	obj.values = map
}

class ListMapper<out T : Any> internal constructor() {
	@Suppress("UNCHECKED_CAST") operator fun <R : GraphType> getValue(inst: R, prop: KProperty<*>): List<T> {

		if(ArgBuilder.last != null) {
			inst.payLoads.put(prop, ArgBuilder.last!!)
			ArgBuilder.last = null
		}

		return inst.values?.get(prop.name) as List<T>? ?: emptyList<T>()
	}
}

class PropertyMapper<out T : Any> internal constructor() {
	@Suppress("UNCHECKED_CAST") operator fun getValue(inst: GraphType, prop: KProperty<*>): T {

		if(ArgBuilder.last != null) {
			inst.payLoads.put(prop, ArgBuilder.last!!)
			ArgBuilder.last = null
		}
		return inst.values?.get(prop.name) as T? ?: throw NullPointerException("No such ${prop.name} in type ${inst::class}")
	}
}














