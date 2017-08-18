package com.prestongarno.ktq

import kotlin.reflect.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.jvm.javaType

@Suppress("UNCHECKED_CAST")
interface QType {
  fun <T> stub(): Stub<T> = dummy as Stub<T>

  fun <T> nullableStub(): NullableStub<T> = nullableDummy as NullableStub<T>

  fun <T : QType> stub(of: () -> T) = StubAdapter(of) as Stub<T>

  fun <T : QType> nullableStub(of: () -> T) = NullStubAdapter(of) as NullableStub<T>
}

val dummy: Stub<Any> = object : Stub<Any> { }
val nullableDummy: NullableStub<Any> = object : NullableStub<Any> {}

interface Stub<T> {
  operator fun getValue(inst: QType, property: KProperty<*>): T = throw UnsupportedOperationException()

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
    println("Providing stub: ${property.name} of type ${property.returnType}")
    val mapper = PMapper.create<T>(property)
    Tracker.putProperty<Any>(inst, mapper)
    return mapper
  }
}

interface NullableStub<T> {

  operator fun getValue(foo: QType, property: KProperty<*>): T? = throw UnsupportedOperationException()

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T> {
    println("Providing nullable stub: ${property.name} of type ${property.returnType}")
    val mapper = NullPMapper.create<T>(property)
    Tracker.putProperty<Any>(inst, mapper)
    return mapper as NullableStub<T>
  }
}
