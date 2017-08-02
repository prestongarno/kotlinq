package com.prestongarno.ktq.runtime

import kotlin.reflect.KProperty

/**
 * The root class for all types, trying to simplify the delegates process
 */

open class QueryData constructor(internal val map: Map<String, Any?> = HashMap(10)) {

	protected val primitives: PropertyMapper = PropertyMapper(map)

	protected fun notDeclaredErr(property: String = ""): Throwable =
			UnsupportedOperationException("Property $property not declared/not declared correctly in concrete query/mutation type")

	protected inline fun <reified T : Any> get(key: String): T = get(key, this) as T

	@Suppress("UNCHECKED_CAST") operator fun <R : Any> List<R>.getValue(inst: QueryData, prop: KProperty<*>): List<R> = get<List<R>>(prop.name)

	@Suppress("UNCHECKED_CAST") operator fun <X : QueryData> X.getValue(inst: QueryData, prop: KProperty<*>): X = get<Any>(prop.name) as X

	companion object {

	}
}

fun get(key: String, inst: QueryData): Any = inst.map.get(key) ?: throw NullPointerException("No such $key")

class PropertyMapper internal constructor(protected val map: Map<String, Any?>) {
	@Suppress("UNCHECKED_CAST") operator fun <R : Any> getValue(inst: QueryData, prop: KProperty<*>): R
			= (map.get(prop.name) ?: throw UnsupportedOperationException("")) as R

}

