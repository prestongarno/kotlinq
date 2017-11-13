package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.EnumConfigStubImpl
import com.prestongarno.ktq.adapters.EnumNoArgStub
import com.prestongarno.ktq.adapters.EnumOptionalArgStub
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.hooks.Configurable
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfig
import com.prestongarno.ktq.hooks.StubProvider
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Simply a marker interface to group together the different
 * stubbable types for the StubMapper delegate to restrict delegation to
 *
 * TODO(preston) make this same as [DelegateProvider]
 * with operator [DelegateProvider.provideDelegate]
 */
interface SchemaStub

interface DelegateProvider<out T> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

interface InterfaceStub<out T> : DelegateProvider<QModel<T>?> where T : QType, T : QInterface

interface AbstractCollectionStub<out T> : DelegateProvider<List<QModel<T>>> where T : QType, T : QInterface

interface TypeStub<out T, out U> : DelegateProvider<T> where  T : QModel<U>, U : QType

interface UnionStub : DelegateProvider<QModel<*>?>

interface EnumStub<T, out A : ArgBuilder> : DelegateProvider<T> where T : QEnumType, T : Enum<*> {
  var default: T?

  fun config(argumentScope: A.() -> Unit)

  companion object {

    @PublishedApi internal fun <T> noArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): NoArgConfig<EnumStub<T, ArgBuilder>, T>
        where T : QEnumType, T : Enum<*> =
        EnumNoArgStub(qproperty, enumClass)

    @PublishedApi internal fun <T, A> argStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): Configurable<EnumStub<T, A>, A>
        where T : QEnumType, T : Enum<*>, A : ArgBuilder =
        EnumConfigStubImpl(qproperty, enumClass)

    @PublishedApi internal fun <T, A> optionalArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): OptionalConfig<EnumStub<T, A>, T, A>
        where T : QEnumType, T : Enum<*>, A : ArgBuilder =
        EnumOptionalArgStub(qproperty, enumClass)
  }
}

