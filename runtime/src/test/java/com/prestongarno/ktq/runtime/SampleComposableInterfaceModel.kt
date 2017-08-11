package com.prestongarno.ktq.runtime

import com.prestongarno.ktq.runtime.delegates.QDelegate
import java.util.*
import kotlin.collections.HashMap
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.*

// =========================================================== //
// =========================================================== //
// ================ Generated & user code  =================== //
// =========================================================== //
// =========================================================== //
interface UserImpl : QType<UserImpl> {
	fun username() : QType<String> = stub(this)
	fun <T: Runnable> since() : QType<T> = QType.stub<Runnable>(this) as QType<T>

	companion object {
		inline fun <reified T> stub(inst: QType<*>): QType<T> = inst as QType<T>
	}
}

fun main(args: Array<String>) {
	val result = QueryService.submitTask(::MFuckery)
	println(result.fieldery)
}
class MFuckery : UserImpl {
	val fieldery by username()
}


// =========================================================== //
// =========================================================== //
// ====================== Provided iface ===================== //
// =========================================================== //
// =========================================================== //

interface QType<T> {
	operator fun <R> provideDelegate(inst: R, property: KProperty<*>): QType<T> {
		println("providing delegate :: ${property.name}")
		QueryService.DELEGATE_UNKNOWN = property
		return this
	}
/*	operator fun T.getValue(foo: Any, property: KProperty<*>): T {
		println("T.getValue() for property :: ${property.name}")
		TODO()
	}*/
	operator fun getValue(foo: Any, property: KProperty<*>): (T) {
		println("getValue() for ${property.name}")
	    return (foo as QueryService.Values).swap<Any>(this, foo, property) as T
	}

	companion object {
		inline fun <reified T> stub(inst: QType<*>): QType<T> = inst as QType<T>
	}
}

object QueryService {
	//TODO("Network call, do some black magic on the object, and return obj")
	// Let's assume that any object implementing a QType subiface has a zero-arg constructor
	inline fun <T: QType<T>, reified K: QType<T>> submitTask(constructor: KFunction0<K>): K {
		val createdType = constructor.call()
		// Can we hijack the getValue() function, and return a result from the
		// map because we know which fields are which by providing the delegates?
		val hijinx = object : QType<T> by createdType, Values {

			// All we need to do is intercept all of the property/delegate
			// creation on the constructor invocation and then we can pass the pointer around
			// mapping each property to this map
			override val getValues: Map<String, Any?> = HashMap(mapOf(Pair("fieldery", "Haram")))

			override fun getValue(foo: Any, property: KProperty<*>): T {
				return createdType.getValue(apply {}, property)
			}
		}
		Thread.sleep(100) // network request...
		val sucess = hijinx.getValue(createdType, DELEGATE_UNKNOWN as KProperty<*>)
		println(sucess)
		return createdType
	}

	interface Values {
		val getValues : Map<String, Any?>

		fun <I: Any> swap(concrete: QType<*>, hijacker: Values, property: KProperty<*>): I
				= hijacker.getValues.get(property.name) as I
	}


	var DELEGATE_UNKNOWN : Any? = null
}

















