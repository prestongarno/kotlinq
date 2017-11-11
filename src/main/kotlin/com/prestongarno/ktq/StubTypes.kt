package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.QField
import kotlin.reflect.KProperty

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to
 *
 * TODO(preston) make this same as [DelegateProvider]
 * with operator [DelegateProvider.provideDelegate]
 */
interface SchemaStub

interface QInterface

interface DelegateProvider<out T> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

/**
 * TODO make an easier way to declare any type as nullable
 * (i.e. outside the regular stub inheritance hierarchy)
 */
interface NullableStub<out T> : DelegateProvider<T?>

interface InterfaceStub<T> : DelegateProvider<QModel<T>?> where T : QType, T : QInterface

interface AbstractCollectionStub<T> : DelegateProvider<List<QModel<T>>> where T : QType, T : QInterface

interface TypeStub<T, U> : DelegateProvider<T> where  T : QModel<U>, U : QType

interface UnionStub : DelegateProvider<QModel<*>?>

interface EnumStub<out T> : DelegateProvider<T> where T: QEnumType, T: Enum<*>