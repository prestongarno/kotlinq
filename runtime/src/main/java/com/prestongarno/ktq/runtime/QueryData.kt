package com.prestongarno.ktq.runtime

import kotlin.reflect.KProperty

/**
 * The root class for all types, trying to simplify the delegates process
 */
open class QueryData constructor(internal var map: Map<String, Any?> = HashMap(10)) {

	/**
	 * TODO -> use Provided Delegates to check type info + property name information as explained
	 * todo     @Link https://kotlinlang.org/docs/reference/delegated-properties.html#providing-a-delegate-since-11
	 */
	companion object {
		/** global/static instance for delegating simple primitive properties to getFromMap top-level method */
		val primitives: PropertyMapper = PropertyMapper()

		/** Restricts fields in subtypes using QueryData delegates to be of the lower bounds `T` */
		fun <T: QueryData> nested() = ObjectsMapper<T>()

		fun <T: Any> collection() = ListMapper<T>()

		val stub: SchemaStub = SchemaStub()

		//fun <T: Any> stub(): SchemaStub = stub
	}
}

/** TODO: remove or restructure for testing manually */
fun set(obj: QueryData, map: Map<String, Any?>) {
	obj.map = map
}

class SchemaStub internal constructor() {
	@Suppress("UNCHECKED_CAST") inline operator fun <R: Any> getValue(inst: QueryData, property: KProperty<*>): R =
			throw UnsupportedOperationException("not implemented")
}

class ListMapper<T: Any> internal constructor() {
	@Suppress("UNCHECKED_CAST") operator fun <R: QueryData> getValue(inst: R, property: KProperty<*>): List<T> = getFromMap(property.name, inst) as List<T>
}

class ObjectsMapper<out T: QueryData> internal constructor(){
	@Suppress("UNCHECKED_CAST") operator fun <R : QueryData> getValue(inst: R, prop: KProperty<*>): T = getFromMap(prop.name, inst) as T
}

class PropertyMapper internal constructor() {
	@Suppress("UNCHECKED_CAST") operator fun <R : Any> getValue(inst: QueryData, prop: KProperty<*>): R = getFromMap(prop.name, inst) as R
}

fun getFromMap(key: String, inst: QueryData): Any = inst.map.get(key) ?: throw NullPointerException("No such $key in type ${inst::class}")
