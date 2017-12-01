/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.core.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.DelegateProvider
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.SchemaStub
import com.prestongarno.kotlinq.core.adapters.TypeStubAdapter
import com.prestongarno.kotlinq.core.hooks.ConfiguredQuery
import com.prestongarno.kotlinq.core.hooks.NoArgConfig
import com.prestongarno.kotlinq.core.hooks.OptionalConfiguration
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

// TODO add something like enum class GraphQL.ResolutionStrategy
// (for something like one of 'STRICT' or 'PERMISSIVE' for how to handle invalid responses)
interface TypeStub<out T, out U, out A : ArgBuilder> : DelegateProvider<T> where  T : QModel<U>, U : QType {

  fun config(argumentScope: A.() -> Unit)

  companion object {
    internal
    fun <U : QType> noArgStub(
        qproperty: GraphQlProperty
    ): Query<U> = Query.create(qproperty)

    internal
    fun <U : QType, A : ArgBuilder> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQuery<U, A> = OptionalConfigQuery.create(qproperty)

    internal
    fun <U : QType, A : ArgBuilder> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<U, A> = ConfigurableQuery.create(qproperty)
  }

  interface Query<U : QType> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): NoArgConfig<TypeStub<T, U, ArgBuilder>, T>

    companion object {
      internal
      fun <U : QType> create(
          qproperty: GraphQlProperty
      ): Query<U> = QueryImpl(qproperty)
    }

    private
    class QueryImpl<U : QType>(val qproperty: GraphQlProperty) : Query<U> {
      override fun <T : QModel<U>> query(init: () -> T): NoArgConfig<TypeStub<T, U, ArgBuilder>, T> =
          NoArgConfig.new { TypeStubAdapter(qproperty, init, it) }
    }
  }

  interface OptionalConfigQuery<U : QType, A : ArgBuilder> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): OptionalConfiguration<TypeStub<T, U, A>, T, A>

    companion object {
      internal
      fun <U : QType, A : ArgBuilder> create(
          qproperty: GraphQlProperty
      ): OptionalConfigQuery<U, A> = OptionalConfigQueryImpl(qproperty)
    }

    private
    class OptionalConfigQueryImpl<U : QType, A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfigQuery<U, A> {
      override fun <T : QModel<U>> query(init: () -> T): OptionalConfiguration<TypeStub<T, U, A>, T, A> =
          OptionalConfiguration.new { TypeStubAdapter(qproperty, init, it) }
    }
  }

  interface ConfigurableQuery<U : QType, A : ArgBuilder> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): ConfiguredQuery<TypeStub<T, U, A>, A>

    companion object {
      internal
      fun <U : QType, A : ArgBuilder> create(
          qproperty: GraphQlProperty
      ): ConfigurableQuery<U, A> = ConfigurableTypeQueryImpl(qproperty)
    }

    private
    class ConfigurableTypeQueryImpl<U : QType, A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<U, A> {
      override fun <T : QModel<U>> query(init: () -> T): ConfiguredQuery<TypeStub<T, U, A>, A> =
          ConfiguredQuery.new { TypeStubAdapter(qproperty, init, it) }
    }
  }
}