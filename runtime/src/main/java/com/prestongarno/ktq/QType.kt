package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import kotlin.reflect.*

interface QType {

  fun <T> stub(): Stub<T> = ScalarStub()

  fun <T> nullableStub(): Stub<T> = ScalarStub()

  fun <T : QType> stub(of: () -> T): TypeStub<QModel<T>, T> = TypeStubAdapter()

  /****************************************
   *         Nested types fields          *
   ****************************************/

  /** Stub with no input for nested types, requiring only an initializer
   */
  fun <T : QType> typeStub(): InitStub<T> = TypeStubAdapter()

  /** Configurable stub for primitives
   */
  fun <A : ArgBuilder<T>, T> configStub(argBuilder: A): Config<A, T> = ScalarStub(argBuilder)

  /** Configurable stubs for types
   */
  fun <A : TypeArgBuilder<T, QModel<T>>, T : QType> typeConfigStub(argBuilder: A)
      : ConfigType<A, T> = TypeStubAdapter(argBuilder)
}

interface InitStub<T : QType> {
  fun <U : QModel<T>> init(of: () -> U): TypeStub<U, T>
}

interface Config<out A : ArgBuilder<T>, T> {
  fun config(): A
}

interface ConfigType<out A : TypeArgBuilder<T, QModel<T>>, T : QType> {
  fun config(): A
}

interface Stub<T> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T

  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface NullableStub<T> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T?

  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface TypeStub<U : QModel<T>, T : QType> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): U

  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<U, T>
}












