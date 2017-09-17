package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.custom.ScalarAdapter
import kotlin.reflect.KProperty

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface InitStub<T : QSchemaType> : SchemaStub {
  fun <U : QModel<T>> init(of: () -> U): TypeStub<U, T>
}

interface CustomInitStub<T: CustomScalar> : SchemaStub {
  fun <U: ScalarAdapter<A>, A> init(of: U): CustomStub<U, A>
}

interface CustomStub<U: ScalarAdapter<T>, T> : SchemaStub {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): CustomStub<U, T>
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

interface NullableStub<T> : SchemaStub {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T?
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T>
}

interface TypeStub<U, T> : SchemaStub where  U : QModel<T>, T : QSchemaType {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): U
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<U, T>
}
