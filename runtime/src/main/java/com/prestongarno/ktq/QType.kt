package com.prestongarno.ktq

import kotlin.reflect.*

@Suppress("UNCHECKED_CAST")
interface QType {
	fun <T> stub(): Stub<T> = dummy as Stub<T>

	fun <T> nullableStub(): NullableStub<T> = nullableDummy as NullableStub<T>

	fun <T: QType> stub(of: () -> T) = StubAdapter(this, of) as Stub<T>

	fun <T: QType> nullableStub(of: () -> T) = NullStubAdapter(of) as NullableStub<T>
}

val dummy : Stub<Any> = object : Stub<Any> {
	override fun <T : QType, U> mapDirect(function: (T) -> Stub<U>): Stub<U> {
		throw UnsupportedOperationException("not supported")
	}
}
val nullableDummy : NullableStub<Any> = object : NullableStub<Any> {
	override fun <T : QType, U> mapDirect(function: (T) -> Stub<U>): Stub<U> {
		throw UnsupportedOperationException("not supported")
	}
}
/**
 * This interface represents a field to be provided by another implementation
 *  (for example, on another thread which fetches the data by a network call)
 *
 *  It's possible to do this by `lateinit` or `lazy` delegation, but we want to initialize fields by
 *  map of properties -> values transparently (e.g. firebase, your own repositories or existing modules, etc.)
 *  and provide a delegate which transparently fetches the field from the map
 */
interface Stub<T> {
	operator fun getValue(inst: QType, property: KProperty<*>): T = throw UnsupportedOperationException()

	operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
		println("Providing stub: ${property.name} of type ${property.returnType}")
		val mapper = PMapper.create<T>(property)
		Tracker.putProperty<Any>(inst, mapper)
		return mapper
	}

	fun <T: QType, U> mapDirect(function: (T) -> Stub<U>): Stub<U>
}

interface NullableStub<T> {

	operator fun getValue(foo: QType, property: KProperty<*>): T? = throw UnsupportedOperationException()

	operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T> {
		println("Providing nullable stub: ${property.name} of type ${property.returnType}")
		val mapper = NullPMapper.create<T>(property)
		Tracker.putProperty<Any>(inst, mapper)
		return mapper as NullableStub<T>
	}
	fun <T: QType, U> mapDirect(function: (T) -> Stub<U>): Stub<U>
}

