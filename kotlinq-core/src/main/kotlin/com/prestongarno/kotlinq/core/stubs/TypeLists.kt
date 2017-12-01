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
import com.prestongarno.kotlinq.core.adapters.TypeListAdapter
import com.prestongarno.kotlinq.core.hooks.ConfiguredQuery
import com.prestongarno.kotlinq.core.hooks.NoArgConfig
import com.prestongarno.kotlinq.core.hooks.OptionalConfiguration
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

interface TypeListStub<out T : QModel<U>, out U : QType, out A : ArgBuilder> : DelegateProvider<List<T>> {

  fun config(scope: A.() -> Unit)

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
    fun <T : QModel<U>> query(init: () -> T): NoArgConfig<TypeListStub<T, U, ArgBuilder>, List<T>>

    companion object {
      internal
      fun <U : QType> create(
          qproperty: GraphQlProperty
      ): Query<U> = QueryImpl(qproperty)
    }

    private
    class QueryImpl<U : QType>(val qproperty: GraphQlProperty) : Query<U> {

      override fun <T : QModel<U>> query(
          init: () -> T
      ): NoArgConfig<TypeListStub<T, U, ArgBuilder>, List<T>> =
          NoArgConfig.new { args -> TypeListAdapter(qproperty, init, args) }

    }

  }

  interface OptionalConfigQuery<U : QType, A : ArgBuilder> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): OptionalConfiguration<TypeListStub<T, U, A>, List<T>, A>

    companion object {
      internal
      fun <U : QType, A : ArgBuilder> create(
          qproperty: GraphQlProperty
      ): OptionalConfigQuery<U, A> = OptionalConfigQueryImpl(qproperty)
    }

    private
    class OptionalConfigQueryImpl<U : QType, A : ArgBuilder>(
        val qproperty: GraphQlProperty
    ) : OptionalConfigQuery<U, A> {

      override fun <T : QModel<U>> query(
          init: () -> T
      ): OptionalConfiguration<TypeListStub<T, U, A>, List<T>, A> =
          OptionalConfiguration.new { args -> TypeListAdapter(qproperty, init, args) }

    }
  }

  interface ConfigurableQuery<U : QType, A : ArgBuilder> : SchemaStub {
    fun <T : QModel<U>> query(init: () -> T): ConfiguredQuery<TypeListStub<T, U, A>, A>

    companion object {
      internal
      fun <U : QType, A : ArgBuilder> create(
          qproperty: GraphQlProperty
      ): ConfigurableQuery<U, A> = ConfigurableQueryImpl(qproperty)
    }

    private
    class ConfigurableQueryImpl<U : QType, A : ArgBuilder>(
        val qproperty: GraphQlProperty
    ) : ConfigurableQuery<U, A> {

      override fun <T : QModel<U>> query(
          init: () -> T
      ): ConfiguredQuery<TypeListStub<T, U, A>, A> =
          ConfiguredQuery.new { args -> TypeListAdapter(qproperty, init, args) }

    }
  }
}


