package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import kotlin.reflect.KProperty

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface DelegateProvider<out T> : SchemaStub {
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Adapter<T>
}

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

interface Stub<T> : DelegateProvider<T> {
  fun withDefault(value: T): Stub<T>
}

interface CustomStub<U: QScalarMapper<T>, T> : DelegateProvider<T>

interface NullableStub<T> : DelegateProvider<T?>

interface TypeStub<T, U> : DelegateProvider<T> where  T : QModel<U>, U : QSchemaType

interface UnionStub : DelegateProvider<QModel<*>?>
