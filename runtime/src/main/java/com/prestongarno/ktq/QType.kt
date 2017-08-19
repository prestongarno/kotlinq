package com.prestongarno.ktq

import org.jetbrains.annotations.Nullable
import kotlin.reflect.*

@Suppress("UNCHECKED_CAST", "USELESS_CAST")
interface QType {

  fun <T> stub() = PrimitiveStubAdapter<T>(this) as Stub<T>

  fun <T> nullableStub() = NullablePrimitiveStubAdapter<@Nullable T>(this) as NullableStub<@Nullable T>

  fun <T : QType> stub(of: () -> T) = StubAdapter(of, this) as Stub<T>

  fun <T : QType> nullableStub(of: () -> T) = NullStubAdapter(of, this) as NullableStub<T>

  fun <T: QType, U> stub(name: String, mapper: KCallable<Stub<U>>) : Stub<U>
      = EmptyStubAdapter<U>(name, mapper, this) as Stub<U>

  fun <T: QType, U> nullableStub(name: String, mapper: KCallable<Stub<U>>) : NullableStub<U>
      = EmptyNullStubAdapter<U>(name, mapper, this) as NullableStub<U>
}

interface Stub<T> {
  operator fun getValue(inst: QType, property: KProperty<*>): T

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface NullableStub<T> {
  operator fun getValue(inst: QType, property: KProperty<*>): T?

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T>
}
