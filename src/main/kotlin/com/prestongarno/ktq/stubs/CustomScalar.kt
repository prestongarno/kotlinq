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

package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.adapters.newScalarDelegate
import com.prestongarno.ktq.hooks.ConfiguredQuery
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfiguration
import com.prestongarno.ktq.properties.GraphQlProperty

interface CustomScalarStub<T : CustomScalar, V, out A : ArgBuilder> : DelegateProvider<V> {

  var default: V?

  fun config(scope: A.() -> Unit)

  companion object {

    internal fun <T : CustomScalar> noArgStub(
        qproperty: GraphQlProperty
    ): Query<T> = QueryImpl(qproperty)

    internal fun <T : CustomScalar, A : ArgBuilder> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQuery<T, A> = OptionalConfigQueryImpl(qproperty)

    internal fun <T : CustomScalar, A : ArgBuilder> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<T, A> = ConfigurableQueryImpl(qproperty)

  }

  interface Query<T : CustomScalar> : SchemaStub {
    fun <U : QScalarMapper<V>, V> map(adapter: U)
        : NoArgConfig<CustomScalarStub<T, V, ArgBuilder>, V>
  }

  interface OptionalConfigQuery<T : CustomScalar, in A : ArgBuilder> : SchemaStub {
    fun <U : QScalarMapper<V>, V> map(
        adapter: U
    ): OptionalConfiguration<CustomScalarStub<T, V, ArgBuilder>, V, A>
  }

  interface ConfigurableQuery<T : CustomScalar, in A : ArgBuilder> : SchemaStub {
    fun <V> map(
        adapter: QScalarMapper<V>
    ): ConfiguredQuery<CustomScalarStub<T, V, ArgBuilder>, A>
  }

  /*********************************************************************************
   * Private default implementations
   */
  private class QueryImpl<T : CustomScalar>(val qproperty: GraphQlProperty) : Query<T> {

    override fun <U : QScalarMapper<V>, V> map(adapter: U): NoArgConfig<CustomScalarStub<T, V, ArgBuilder>, V> =
        NoArgConfig.new { args -> newScalarDelegate<T, U, V, ArgBuilder>(qproperty, adapter, args ?: ArgBuilder()) }

  }

  private class OptionalConfigQueryImpl<T : CustomScalar, A : ArgBuilder>(
      val qproperty: GraphQlProperty
  ) : OptionalConfigQuery<T, A> {

    override fun <U : QScalarMapper<V>, V> map(adapter: U): OptionalConfiguration<CustomScalarStub<T, V, ArgBuilder>, V, A> =
        OptionalConfiguration.new { args -> newScalarDelegate(qproperty, adapter, args) }

  }

  private class ConfigurableQueryImpl<T : CustomScalar, A : ArgBuilder>(
      val qproperty: GraphQlProperty
  ) : ConfigurableQuery<T, A> {

    override fun <V> map(adapter: QScalarMapper<V>): ConfiguredQuery<CustomScalarStub<T, V, ArgBuilder>, A> =
        ConfiguredQuery.new { args -> newScalarDelegate(qproperty, adapter, args) }

  }
}