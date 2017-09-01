package com.prestongarno.ktq

import kotlin.reflect.KProperty

interface InitStub<T : QSchemaType> {
  fun <U : QModel<T>> init(of: () -> U): TypeStub<U, T>
}

interface QConfigStub<T, out A : ArgBuilder> {
  fun config(): A
}

interface QTypeConfigStub<T : QSchemaType, out A : TypeArgBuilder> {
  fun config(): A
}

interface Stub<T> : Delegator {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
  companion object {
    fun <T> create() : Stub<T> = TODO("Need to create delegates for the delegates in order to track the names... delegate inception wtf")
  }
}

interface TypeStub<U, T> where  U : QModel<T>, T : QSchemaType {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): U
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<U, T>
}

interface Delegator