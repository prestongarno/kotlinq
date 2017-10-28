package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.QField
import kotlin.reflect.KProperty

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to */
interface SchemaStub

interface DelegateProvider<out T> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

interface NullableStub<T> : DelegateProvider<T?>

interface TypeStub<T, U> : DelegateProvider<T> where  T : QModel<U>, U : QSchemaType

interface UnionStub : DelegateProvider<QModel<*>?>
