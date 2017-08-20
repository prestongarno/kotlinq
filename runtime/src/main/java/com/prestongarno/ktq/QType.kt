package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import kotlin.reflect.*

interface QType {

  fun <T> stub(): Stub<T, ArgBuilder>
      = PrimitiveStubAdapter(this)

  fun <T> nullableStub(): NullableStub<T, ArgBuilder>
      = NullablePrimitiveStubAdapter(this)

  fun <T : QType> stub(of: () -> T): Stub<T, ArgBuilder>
      = StubAdapter(of, this)

  fun <T : QType> nullableStub(of: () -> T): NullableStub<T, ArgBuilder>
      = NullStubAdapter(of, this)

  @Suppress("unused") fun <T : QType, U> stub(name: String, mapper: KCallable<Stub<U, ArgBuilder>>): Stub<U, ArgBuilder>
      = EmptyStubAdapter(name, mapper, this)

  @Suppress("unused") fun <T : QType, U> nullableStub(name: String,
      mapper: KCallable<Stub<U, ArgBuilder>>): NullableStub<U, ArgBuilder>
      = EmptyNullStubAdapter(name, mapper, this)

  /****************************************/
  // ArgBuilders / Configurable fields
  /****************************************/

  fun <T, A : ArgBuilder> configStub(argBuilder: A): Stub<T, A>
      = PrimitiveStubAdapter(this, argBuilder)

  fun <T, A : ArgBuilder> configNullableStub(argBuilder: A): NullableStub<T, A>
      = NullablePrimitiveStubAdapter(this, argBuilder)

  fun <T : QType, A : ArgBuilder> configStub(of: () -> T, argBuilder: A): Stub<T, A>
      = StubAdapter(of, this, argBuilder)

  fun <T : QType, A : ArgBuilder> configNullableStub(of: () -> T, argBuilder: A): NullableStub<T, A>
      = NullStubAdapter(of, this, argBuilder)

  @Suppress("unused") fun <T : QType, U, A : ArgBuilder> configStub(name: String,
      mapper: KCallable<Stub<U, A>>,
      argBuilder: A): Stub<U, A>
      = EmptyStubAdapter(name, mapper, this, argBuilder)

  @Suppress("unused") fun <T : QType, U, A : ArgBuilder> configNullableStub(name: String,
      mapper: KCallable<Stub<U, A>>,
      argBuilder: A): NullableStub<U, A>
      = EmptyNullStubAdapter(name, mapper, this, argBuilder)
}

interface Stub<T, A : ArgBuilder> {
  operator fun getValue(inst: QType, property: KProperty<*>): T

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T, A>
}

interface NullableStub<T, A : ArgBuilder> {
  operator fun getValue(inst: QType, property: KProperty<*>): T?

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T, A>
}

