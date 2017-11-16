package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.TypeListAdapter
import com.prestongarno.ktq.hooks.ConfiguredQuery
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfiguration
import com.prestongarno.ktq.properties.GraphQlProperty

interface TypeListStub<out T : QModel<U>, U : QType, out A : ArgBuilder> : DelegateProvider<List<T>> {

  fun config(scope: A.() -> Unit)

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
    fun <T : QModel<U>> query(init: () -> T): NoArgConfig<TypeListStub<T, U, ArgBuilder>, List<T>>

    companion object {
      @PublishedApi internal fun <U : QType> create(
          qproperty: GraphQlProperty
      ): Query<U> = QueryImpl(qproperty)
    }

    private class QueryImpl<U : QType>(val qproperty: GraphQlProperty) : TypeListStub.Query<U> {

      override fun <T : QModel<U>> query(
          init: () -> T
      ): NoArgConfig<TypeListStub<T, U, ArgBuilder>, List<T>> =
          NoArgConfig.new { args -> TypeListAdapter(qproperty, init, args) }

    }

  }

  interface OptionalConfigQuery<U : QType, A : ArgBuilder> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): OptionalConfiguration<TypeListStub<T, U, A>, List<T>, A>

    companion object {
      @PublishedApi internal fun <U : QType, A : ArgBuilder> create(
          qproperty: GraphQlProperty
      ): OptionalConfigQuery<U, A> = OptionalConfigQueryImpl(qproperty)
    }

    private class OptionalConfigQueryImpl<U : QType, A : ArgBuilder>(
        val qproperty: GraphQlProperty
    ) : TypeListStub.OptionalConfigQuery<U, A> {

      override fun <T : QModel<U>> query(
          init: () -> T
      ): OptionalConfiguration<TypeListStub<T, U, A>, List<T>, A> =
          OptionalConfiguration.new { args -> TypeListAdapter(qproperty, init, args) }

    }
  }

  interface ConfigurableQuery<U : QType, A : ArgBuilder> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): ConfiguredQuery<TypeListStub<T, U, A>, A>

    companion object {
      @PublishedApi internal fun <U : QType, A : ArgBuilder> create(
          qproperty: GraphQlProperty
      ): ConfigurableQuery<U, A> = ConfigurableQueryImpl(qproperty)
    }

    private class ConfigurableQueryImpl<U : QType, A : ArgBuilder>(
        val qproperty: GraphQlProperty
    ) : TypeListStub.ConfigurableQuery<U, A> {

      override fun <T : QModel<U>> query(
          init: () -> T
      ): ConfiguredQuery<TypeListStub<T, U, A>, A> =
          ConfiguredQuery.new { args -> TypeListAdapter(qproperty, init, args) }

    }
  }
}


