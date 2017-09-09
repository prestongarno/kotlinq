package com.prestongarno.ktq

import kotlin.reflect.KProperty

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface InitStub<T : QSchemaType> : SchemaStub {
  fun <U : QModel<T>> init(of: () -> U): TypeStub<U, T>
}

interface QConfigStub<T, out A : ArgBuilder> : SchemaStub {
  fun config(): A
}

interface QTypeConfigStub<T : QSchemaType, out A : TypeArgBuilder> : SchemaStub {
  fun config(): A
}

interface Stub<T> : SchemaStub {
  fun withDefault(value: T): Stub<T>
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface TypeStub<U, T> : SchemaStub where  U : QModel<T>, T : QSchemaType {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): U
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<U, T>
}
