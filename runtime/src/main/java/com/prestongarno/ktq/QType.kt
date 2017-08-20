package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import kotlin.reflect.*

interface QType {

  fun <T> stub(): Stub<T> = PrimitiveStubAdapter(this)

  fun <T> nullableStub(): NullableStub<T> = NullablePrimitiveStubAdapter(this)

  fun <T : QType> stub(of: () -> T): Stub<T> = StubAdapter(of, this)

  fun <T : QType> nullableStub(of: () -> T): NullableStub<T> = NullStubAdapter(of, this)

  @Suppress("unused") fun <T : QType, U> stub(name: String, mapper: KCallable<Stub<U>>): Stub<U>
      = EmptyStubAdapter(name, mapper, this)

  @Suppress("unused") fun <T : QType, U> nullableStub(name: String, mapper: KCallable<Stub<U>>): NullableStub<U>
      = EmptyNullStubAdapter(name, mapper, this)
}

interface Stub<T> {
  operator fun getValue(inst: QType, property: KProperty<*>): T

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface NullableStub<T> {
  operator fun getValue(inst: QType, property: KProperty<*>): T?

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T>
}

