package com.prestongarno.ktq

import kotlin.reflect.KProperty

interface ListConfig<out T, out A : ArgBuilder> {
  fun config(): A
}

interface ListConfigType<out T, out A> where T: QType, A: TypeArgBuilder{
  fun config(): A
}

interface ListStub<T> where T : List<T> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): List<T>
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface ListInitStub<T : QType> {
  fun <U : QModel<T>> init(of: () -> U): TypeListStub<U, T>
}

interface TypeListStub<U, T> where U : QModel<T>, T : QType {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): List<U>
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeListStub<U, T>
}
