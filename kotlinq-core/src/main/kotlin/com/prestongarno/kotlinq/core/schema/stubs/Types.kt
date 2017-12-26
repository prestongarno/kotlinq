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

package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.TypeStubAdapter
import com.prestongarno.kotlinq.core.api.ConfiguredQuery
import com.prestongarno.kotlinq.core.api.OptionalConfiguration
import com.prestongarno.kotlinq.core.properties.ConfigurableDelegateProvider
import com.prestongarno.kotlinq.core.properties.DelegateProvider
import com.prestongarno.kotlinq.core.properties.GraphQLPropertyContext
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.configurableDelegate
import com.prestongarno.kotlinq.core.schema.QType

// TODO add something like enum class GraphQL.ResolutionStrategy
// (for something like one of 'STRICT' or 'PERMISSIVE' for how to handle invalid responses)
interface TypeStub<out T, out U, out A : ArgumentSpec> where  T : QModel<U>, U : QType {

  fun config(argumentScope: A.() -> Unit)

  companion object {
    internal
    fun <U : QType> noArgStub(
        qproperty: GraphQlProperty
    ): Query<U> = Query.create(qproperty)

    internal
    fun <U : QType, A : ArgumentSpec> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQuery<U, A> = OptionalConfigQuery.create(qproperty)

    internal
    fun <U : QType, A : ArgumentSpec> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<U, A> = ConfigurableQuery.create(qproperty)
  }

  interface Query<U : QType> : GraphQLPropertyContext<QModel<U>> {

    fun <T : QModel<U>> query(init: () -> T): ConfigurableDelegateProvider<Query<U>, QModel<U>>

    companion object {
      internal
      fun <U : QType> create(
          qproperty: GraphQlProperty
      ): Query<U> = QueryImpl(qproperty)
    }

    private
    class QueryImpl<U : QType>(val qproperty: GraphQlProperty) : Query<U> {

      override fun asNullable(): GraphQLPropertyContext<QModel<U>?> {
        TODO("not implemented")
      }

      /**
       * Oh, damn, this is actually something promising 12/25/2017C19:01:00
       */
      override fun <T : QModel<U>> query(init: () -> T) = configurableDelegate(this, { qModel: QModel<*> ->
        TypeStubAdapter<T, U, ArgumentSpec>(qproperty, init, null).bindingTo(qModel)
      })
      //NoArgConfig.new { TypeStubAdapter(qproperty, init, it) }
    }
  }

  interface OptionalConfigQuery<U : QType, A : ArgumentSpec> : GraphQLPropertyContext<Any?> {
    fun <T : QModel<U>> query(init: () -> T): OptionalConfiguration<ConfigurableDelegateProvider<TypeStub<T, U, A>, QModel<T>>, T, A>

    companion object {
      internal
      fun <U : QType, A : ArgumentSpec> create(
          qproperty: GraphQlProperty
      ): OptionalConfigQuery<U, A> = OptionalConfigQueryImpl(qproperty)
    }

    private
    class OptionalConfigQueryImpl<U : QType, A : ArgumentSpec>(val qproperty: GraphQlProperty) : OptionalConfigQuery<U, A> {
      override fun <T : QModel<U>> query(init: () -> T): OptionalConfiguration<ConfigurableDelegateProvider<TypeStub<T, U, A>, QModel<T>>, T, A> =
          OptionalConfiguration.new { TypeStubAdapter(qproperty, init, it) }
    }
  }

  interface ConfigurableQuery<U : QType, A : ArgumentSpec> : GraphQLPropertyContext<Any?> {
    fun <T : QModel<U>> query(init: () -> T): ConfiguredQuery<TypeStub<T, U, A>, A>

    companion object {
      internal
      fun <U : QType, A : ArgumentSpec> create(
          qproperty: GraphQlProperty
      ): ConfigurableQuery<U, A> = ConfigurableTypeQueryImpl(qproperty)
    }

    private
    class ConfigurableTypeQueryImpl<U : QType, A : ArgumentSpec>(val qproperty: GraphQlProperty) : ConfigurableQuery<U, A> {
      override fun <T : QModel<U>> query(init: () -> T): ConfiguredQuery<TypeStub<T, U, A>, A> =
          ConfiguredQuery.new { TypeStubAdapter(qproperty, init, it) }
    }
  }
}