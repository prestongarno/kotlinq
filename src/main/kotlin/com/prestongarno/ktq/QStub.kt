package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.custom.QScalarMapper
import kotlin.reflect.KProperty

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface InitStub<T : QSchemaType> : SchemaStub {
  fun <U : QModel<T>> init(of: () -> U): TypeStub<U, T>
}
interface CustomScalarInitStub<T: CustomScalar> : SchemaStub {
  fun <U: QScalarMapper<A>, A> init(of: U): CustomStub<U, A>
}


interface QConfigStub<T, out A : ArgBuilder> : SchemaStub {
  fun config(): A
}
interface QTypeConfigStub<T : QSchemaType, out A : TypeArgBuilder> : SchemaStub {
  fun config(): A
}

interface CustomScalarConfigStub<T: CustomScalar, out A: CustomScalarArgBuilder> : SchemaStub {
  fun config(): A
}

interface Stub<T> : SchemaStub {
  fun withDefault(value: T): Stub<T>
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}
interface CustomStub<U: QScalarMapper<T>, T> : SchemaStub {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): CustomStub<U, T>
}

interface NullableStub<T> : SchemaStub {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T?
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T>
}

interface TypeStub<T, U> : SchemaStub where  T : QModel<U>, U : QSchemaType {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<T, U>
}

interface UnionStub : SchemaStub {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): QModel<*>
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): UnionStub
}
