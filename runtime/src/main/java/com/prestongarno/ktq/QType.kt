package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import kotlin.reflect.*

interface QType {

  fun <T> stub(): Stub<T, ArgBuilder<T>>
      = PrimitiveStubAdapter(this)

  fun <T> nullableStub(): NullableStub<T, ArgBuilder<T>>
      = NullablePrimitiveStubAdapter(this)

  fun <T : QType> stub(of: () -> T): Stub<T, ArgBuilder<T>>
      = StubAdapter(of, this)

  fun <T : QType> nullableStub(of: () -> T): NullableStub<T, ArgBuilder<T>>
      = NullStubAdapter(of, this)

  /****************************************/
  // ArgBuilders / Configurable fields
  /****************************************/

  fun <T, A : ArgBuilder<T>> configStub(argBuilder: A): Stub<T, A>
      = PrimitiveStubAdapter(this, argBuilder)

  fun <T, A : ArgBuilder<T>> configNullableStub(argBuilder: A): NullableStub<T, A>
      = NullablePrimitiveStubAdapter(this, argBuilder)

  fun <T : QType, A : ArgBuilder<T>> configStub(of: () -> T, argBuilder: A): Stub<T, A>
      = StubAdapter(of, this, argBuilder)

  fun <T : QType, A : ArgBuilder<T>> configNullableStub(of: () -> T, argBuilder: A): NullableStub<T, A>
      = NullStubAdapter(of, this, argBuilder)
}

interface Stub<T, A : ArgBuilder<T>> {

  fun config(): A

  operator fun getValue(inst: QType, property: KProperty<*>): T

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T, A>
}

interface NullableStub<T, A : ArgBuilder<T>> {

  fun config(): A

  operator fun getValue(inst: QType, property: KProperty<*>): T?

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T, A>
}

