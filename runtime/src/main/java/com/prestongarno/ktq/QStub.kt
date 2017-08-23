package com.prestongarno.ktq

import kotlin.reflect.KProperty

interface InitStub<T : QType> {
  fun <U : QModel<T>> init(of: () -> U): TypeStub<U, T>
}

interface Config<T, A : ArgBuilder> {
  fun config(): A
}

interface ConfigType<T : QType, out A : TypeArgBuilder> {
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

interface TypeStub<U, T> where  U : QModel<T>, T : QType {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): U

  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<U, T>
}
