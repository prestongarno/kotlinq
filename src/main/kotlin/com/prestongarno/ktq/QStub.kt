package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import kotlin.reflect.KProperty

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface DelegateProvider<out T> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

interface InitStub<T : QSchemaType> : SchemaStub {
  fun <U : QModel<T>> init(init: () -> U): TypeStub<U, T>
}

interface CustomScalarInitStub<T: CustomScalar> : SchemaStub {
  fun <U: QScalarMapper<A>, A> init(init: U): CustomStub<U, A>
}

interface Config<out T, out A: ArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): DelegateProvider<T>
}

interface OptionalConfig<out T> : Config<T, ArgBuilder> {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

interface TypeConfiguration<T: QSchemaType, out A: ArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): InitStub<T>
}

interface CustomScalarConfiguration<T: CustomScalar, out A: CustomScalarArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): CustomScalarInitStub<T>
}

interface QTypeConfigStub<T : QSchemaType, out A : ArgBuilder> : TypeConfiguration<T, A>

interface CustomScalarConfigStub<T: CustomScalar, out A: CustomScalarArgBuilder> : CustomScalarConfiguration<T, A>

interface CustomStub<U: QScalarMapper<T>, T> : DelegateProvider<T> {
  fun withDefault(value: T): CustomStub<U, T>
}

interface NullableStub<T> : DelegateProvider<T?>

interface TypeStub<T, U> : DelegateProvider<T> where  T : QModel<U>, U : QSchemaType

interface UnionStub : DelegateProvider<QModel<*>?>
