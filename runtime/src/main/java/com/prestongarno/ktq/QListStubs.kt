package com.prestongarno.ktq

import kotlin.reflect.KProperty

interface ListInitStub<T : QSchemaType> {
  fun <U : QModel<T>> init(of: () -> U): TypeListStub<U, T>
}

interface ListConfig<out T, out A : ListArgBuilder> {
  fun config(): A
}

interface ListConfigType<out T, out A> where T: QSchemaType, A: TypeListArgBuilder{
  fun config(): A
}

interface ListStub<T> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): List<T>
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): ListStub<T>
}

interface TypeListStub<U, T> where U : QModel<T>, T : QSchemaType {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): List<U>
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeListStub<U, T>
}
