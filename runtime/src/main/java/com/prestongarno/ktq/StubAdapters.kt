package com.prestongarno.ktq

import kotlin.reflect.KProperty
import kotlin.reflect.jvm.reflect

internal class StubAdapter<T: QType> internal constructor(private val init: () -> T) : Stub<T> {

  init {
		println("Creating adapter for : ${init.reflect()?.returnType}")
	}

	override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
		println("Providing adapter stub: ${property.name} of type ${property.returnType}")
		val provided = init.invoke()
		val mapper = PMapper.create<T>(provided, property)
		Tracker.putProperty(inst, mapper, provided)
		return mapper
	}
}

internal class NullStubAdapter<T> internal constructor(private val init: () -> T) : NullableStub<T> {

	override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T> {
		println("Providing adapter stub: ${property.name} of type ${property.returnType}")
		val provided = init.invoke()
		val mapper = NullPMapper.create<T>(provided, property)
		Tracker.putProperty(inst, mapper, provided)
		return mapper as NullableStub<T>
	}
}

