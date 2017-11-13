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
    ): Query<U> = Query.create(qproperty)

    @PublishedApi internal fun <U : QType, A : ArgBuilder> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQuery<U, A> = OptionalConfigQuery.create(qproperty)

    @PublishedApi internal fun <U : QType, A : ArgBuilder> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<U, A> = ConfigurableQuery.create(qproperty)
  }

  interface Query<U : QType> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): NoArgConfig<TypeStub<T, U, ArgBuilder>, T>

    companion object {
      @PublishedApi internal fun <U : QType> create(
          qproperty: GraphQlProperty
      ): Query<U> = QueryImpl(qproperty)
    }

    private class QueryImpl<U : QType>(val qproperty: GraphQlProperty) : Query<U> {
      override fun <T : QModel<U>> query(init: () -> T): NoArgConfig<TypeStub<T, U, ArgBuilder>, T> =
          NoArgConfig.new { TypeStubAdapter(qproperty, init, it) }
    }
  }

  interface OptionalConfigQuery<U : QType, A : ArgBuilder> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): OptionalConfig<TypeStub<T, U, A>, T, A>

    companion object {
      @PublishedApi internal fun <U : QType, A : ArgBuilder> create(
          qproperty: GraphQlProperty
      ): OptionalConfigQuery<U, A> = OptionalConfigQueryImpl(qproperty)
    }

    private class OptionalConfigQueryImpl<U : QType, A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfigQuery<U, A> {
      override fun <T : QModel<U>> query(init: () -> T): OptionalConfig<TypeStub<T, U, A>, T, A> =
          OptionalConfig.new { TypeStubAdapter(qproperty, init, it) }
    }
  }

  interface ConfigurableQuery<U : QType, A : ArgBuilder> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): Configurable<TypeStub<T, U, A>, A>

    companion object {
      @PublishedApi internal fun <U : QType, A : ArgBuilder> create(
          qproperty: GraphQlProperty
      ): ConfigurableQuery<U, A> = ConfigurableTypeQueryImpl(qproperty)
    }

    private class ConfigurableTypeQueryImpl<U : QType, A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<U, A> {
      override fun <T : QModel<U>> query(init: () -> T): Configurable<TypeStub<T, U, A>, A> {
        return Configurable.new { TypeStubAdapter(qproperty, init, it) }
      }
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


