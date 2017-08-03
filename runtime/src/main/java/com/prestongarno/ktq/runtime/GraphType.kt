package com.prestongarno.ktq.runtime

import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * The root class for all types, trying to simplify the delegates process
 */

open class GraphType {

	internal var values: Map<String, Any?>? = null
	internal var payLoads: MutableList<Pair<KClass<*>, Payload>> = LinkedList()

	/** instance for delegating simple primitive properties to getFromMap top-level method */
	fun primitives(): PropertyMapper {
		println(this::class)
		return PropertyMapper()
	}

	/** Restricts fields in subtypes using QueryData delegates to be of the lower bounds `T` */
	fun <T : GraphType> nested() = ObjectsMapper<T>()

	fun <T : Any> collection() = ListMapper<T>()

	companion object haxor {

/*		@Suppress("UNCHECKED_CAST") operator fun <R : GraphType, T> R.provideDelegate(inst: R, prop: KProperty<*>): T {
			println("Fuck that was easy") // custom delegates move to an inner class/property
										  // (internal) might even be able to support nested queries in a single anonymous closure!
			println(inst)
			println(prop)
			return {p -> inst.values.get(prop.name)}
		}*/

/*		operator fun getValue(g: GraphType, property: KProperty<*>): Map<String, Any?>? {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		operator fun setValue(graphType: GraphType, property: KProperty<*>, map: Map<String, Any?>?) {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}*/
	}

}

/**
 * Represents all field parameterization/mutation payload data
 *
 * Only needed by codegen -> type implementation:
 * 1. each method/ctor arg adds pair of <NameOfArg, ValueOfArg> to list
 * 2. returns list as Payload#get() method override
 *
 * actually make it a wrapper class
 */
internal class Payload(val clazz: KClass<*>) : ArgBuilder {

	val payloadMap: MutableMap<String, Any> = HashMap()

	override fun addArg(name: String, value: Any): ArgBuilder {
		payloadMap.put(name, value)
		return this
	}

	override fun buildFor(queryType: GraphType): GraphType {
		queryType.payLoads.add(Pair(clazz, this))
		return queryType
	}

	override fun toString(): String {
		return "Payload(payloadMap=$payloadMap)" // TODO -> make the toString generate a JSON-compatible format for adding payloads
	}
}

fun anon(clazz: KClass<*>): ArgBuilder = Payload(clazz)

@Suppress("NOTHING_TO_INLINE")
interface ArgBuilder {
	fun addArg(name: String, value: Any): ArgBuilder

	fun buildFor(queryType: GraphType): GraphType

	companion object {
		inline fun create(): ArgBuilder {
			// probably want to work with delegates (which are single-thread access/create)
			// by default in order to map payloads to correct fields
			println(this::class)
			val foo = anon(this::class)
			println(foo::class)
			return foo
		}
	}
}

fun set(obj: GraphType, map: Map<String, Any?>) {
	obj.values = map
}

class ListMapper<T : Any> internal constructor() {
	@Suppress("UNCHECKED_CAST") operator fun <R : GraphType> getValue(inst: R, prop: KProperty<*>): List<T> {
		return getFromMap(prop.name, inst) as List<T>
	}
}

class ObjectsMapper<out T : GraphType> internal constructor() {
	@Suppress("UNCHECKED_CAST") operator fun <R : GraphType> getValue(inst: R, prop: KProperty<*>): T {
		return getFromMap(prop.name, inst) as T
	}
}

class PropertyMapper internal constructor() {
	@Suppress("UNCHECKED_CAST") operator fun <R : Any> getValue(inst: GraphType, prop: KProperty<*>): R {
		return getFromMap(prop.name, inst) as R
	}
	/**
	 * <b>Provided<b> delegate functions are the place to perform checks as to the property types, etc. for the query.
	 * As stated in the wiki: "The provideDelegate method is called for each property during the creation of the ...
	 * instance, and it performs the necessary validation right away.
	 */
	//@Suppress("UNCHECKED_CAST") operator fun <R : GraphType, T: Int> provideDelegate(inst: R, prop: KProperty<*>): ReadOnlyProperty<R, Int> {
	//	println("Hello, mf!")
	//}

}

class SchemaStub() : Throwable("not declared")

fun getFromMap(key: String, inst: GraphType): Any = (inst as GraphType).values?.get(key) ?: throw NullPointerException("No such $key in type ${inst::class}")
