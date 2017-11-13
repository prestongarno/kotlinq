package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.EnumConfigStubImpl
import com.prestongarno.ktq.adapters.EnumNoArgStub
import com.prestongarno.ktq.adapters.EnumOptionalArgStub
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.adapters.TypeStubAdapter
import com.prestongarno.ktq.hooks.Configurable
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfig
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

interface UnionStub : DelegateProvider<QModel<*>?>

// TODO add something like enum class GraphQL.ResolutionStrategy
// (for something like one of 'STRICT' or 'PERMISSIVE' for how to handle invalid responses)
interface TypeStub<out T, out U, out A : ArgBuilder> : DelegateProvider<T> where  T : QModel<U>, U : QType {

  fun config(argumentScope: A.() -> Unit)

  companion object {
    @PublishedApi internal fun <U : QType> noArgStub(
        qproperty: GraphQlProperty
    ): TypeQuery<U> = TypeQuery.create(qproperty)

    @PublishedApi internal fun <U : QType, A : ArgBuilder> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalTypeQuery<U, A> = OptionalTypeQuery.create(qproperty)

    @PublishedApi internal fun <U : QType, A : ArgBuilder> configArgStub(
        qproperty: GraphQlProperty
    ): ConfigTypeQuery<U, A> = ConfigTypeQuery.create(qproperty)
  }
}

interface TypeQuery<U : QType> : SchemaStub {
  fun <T : QModel<U>> query(init: () -> T): NoArgConfig<TypeStub<T, U, ArgBuilder>, T>

  companion object {
    @PublishedApi internal fun <U : QType> create(
        qproperty: GraphQlProperty
    ): TypeQuery<U> = TypeQueryImpl(qproperty)
  }

  private class TypeQueryImpl<U : QType>(val qproperty: GraphQlProperty) : TypeQuery<U> {
    override fun <T : QModel<U>> query(init: () -> T): NoArgConfig<TypeStub<T, U, ArgBuilder>, T> =
        NoArgConfig.new { TypeStubAdapter(qproperty, init, it) }
  }
}

interface OptionalTypeQuery<U : QType, in A : ArgBuilder> : SchemaStub {
  fun <T : QModel<U>> query(init: () -> T): OptionalConfig<TypeStub<T, U, ArgBuilder>, T, A>

  companion object {
    @PublishedApi internal fun <U : QType, A : ArgBuilder> create(
        qproperty: GraphQlProperty
    ): OptionalTypeQuery<U, A> = OptionalTypeQueryImpl(qproperty)
  }

  private class OptionalTypeQueryImpl<U : QType, in A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalTypeQuery<U, A> {
    override fun <T : QModel<U>> query(init: () -> T): OptionalConfig<TypeStub<T, U, ArgBuilder>, T, A> =
        OptionalConfig.new { TypeStubAdapter(qproperty, init, it) }
  }
}

interface ConfigTypeQuery<U : QType, in A : ArgBuilder> : SchemaStub {
  fun <T : QModel<U>> query(init: () -> T): Configurable<TypeStub<T, U, ArgBuilder>, A>

  companion object {
    @PublishedApi internal fun <U : QType, A : ArgBuilder> create(
        qproperty: GraphQlProperty
    ): ConfigTypeQuery<U, A> = ConfigurableTypeQueryImpl(qproperty)
  }

  private class ConfigurableTypeQueryImpl<U : QType, in A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigTypeQuery<U, A> {
    override fun <T : QModel<U>> query(init: () -> T): Configurable<TypeStub<T, U, ArgBuilder>, A> {
      return Configurable.new { TypeStubAdapter(qproperty, init, it) }
    }
  }
}

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
        where T : QEnumType,
              T : Enum<*>, A : ArgBuilder =
        EnumConfigStubImpl(qproperty, enumClass)

    @PublishedApi internal fun <T, A> optionalArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): OptionalConfig<EnumStub<T, A>, T, A>
        where T : QEnumType,
              T : Enum<*>, A : ArgBuilder =
        EnumOptionalArgStub(qproperty, enumClass)
  }
}


