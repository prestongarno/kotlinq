package com.prestongarno.ktq.runtime

import kotlin.collections.HashMap
import kotlin.reflect.*

// =========================================================== //
// =========================================================== //
// ================ Generated & user code  =================== //
// =========================================================== //
// =========================================================== //
interface Person : QType {
	fun username() = stub<String>()
}

fun main(args: Array<String>) {
	val result = QueryService.submitTask(::MFuckery)
	println(result.fieldery)
}

class MFuckery : Person {
	val fieldery by username()
}

// =========================================================== //
// =========================================================== //
// ====================== Provided iface ===================== //
// =========================================================== //
// =========================================================== //

interface QType {

	operator fun <T> getValue(inst: Stub<*>, property: KProperty<*>): T {
		println("getValue() for ${property.name}")
		return (inst as Values).swap(this, inst, property) as T
	}

	fun <T> stub(): Stub<T> = dummy as Stub<T>

	companion object {
		private val dummy = object : Stub<Any> {}
	}

}

interface Stub<T> {

	operator fun getValue(foo: QType, property: KProperty<*>): (T) {
		throw UnsupportedOperationException()
	}

	operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
		QueryService.UNKNOWN = property
		return this
	}
}

object QueryService {

	fun <K : QType> submitTask(constructor: KFunction0<K>): K {

		val createdType = constructor.call()

		val hijinx = object : Stub<Any>, Values, QType by createdType {

			override val values: Map<String, Any?> = HashMap(mapOf(Pair("fieldery", "success")))

			override fun getValue(foo: QType, property: KProperty<*>): Any = createdType.getValue(this, property)
		}

		hijinx.getValue(createdType, UNKNOWN as KProperty<*>)
		return createdType
	}

	// Lets get these bitches up on the stack and see where we go from here

	var UNKNOWN: Any? = null
}

internal interface Values {
	val values: Map<String, Any?>

	fun swap(concrete: QType, hijacker: Values, property: KProperty<*>): Any =
			hijacker.values.get(property.name) ?: throw NullPointerException("Something went wrong")
}














