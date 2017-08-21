package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import kotlin.reflect.*

interface QType {

  fun <T> stub(): Stub<T> = ScalarStub()

  fun <T : QType> stub(of: () -> T): TypeStub<QModel<T>, T> = TypeStubAdapter()

  fun <T, A : ArgBuilder<T>> configStub(argBuilder: A): Config<T, A> = ScalarStub(argBuilder)
  /****************************************
   *         Nested types fields          *
   ****************************************/
  fun <T : QType> typeStub(): InitStub<T> = TypeStubAdapter()

  fun <A : TypeArgBuilder<T, QModel<T>>, T : QType> typeConfigStub(argBuilder: A)
      : ConfigType<A, T> = TypeStubAdapter(argBuilder)
}

interface InitStub<T : QType> {
  fun <U: QModel<T>> init(of: () -> U): TypeStub<U, T>
}

interface Config<T, A : ArgBuilder<T>> {
  fun config(): A
}

interface ConfigType<out A : TypeArgBuilder<T, QModel<T>>, T : QType> {
  fun config(): A
}

interface Stub<T> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T

  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface TypeStub<U : QModel<T>, T : QType> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): U

  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<U, T>
}












