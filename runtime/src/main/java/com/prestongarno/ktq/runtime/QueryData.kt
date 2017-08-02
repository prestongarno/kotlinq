package com.prestongarno.ktq.runtime

import kotlin.reflect.KProperty

/**
 * The root class for all types, trying to simplify the delegates process
 */

open class QueryData(protected var map: Map<in String, Any>) {
	protected val mapper: PropertyMapper = PropertyMapper(map)
}

class PropertyMapper(val map: Map<in String, Any>) {
	@Suppress("UNCHECKED_CAST")
	operator fun <R: Any> R.getValue(inst: QueryData, prop: KProperty<*>): R = (map.get(prop.name)?: throw UnsupportedOperationException("")) as R

	operator fun <R: Any> getValue(inst: QueryData, prop: KProperty<*>): R = (map.get(prop.name)?: throw UnsupportedOperationException("")) as R
}

