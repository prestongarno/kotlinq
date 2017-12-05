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
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.CustomScalar
import com.prestongarno.kotlinq.core.DelegateProvider
import com.prestongarno.kotlinq.core.SchemaStub
import com.prestongarno.kotlinq.core.adapters.custom.QScalarMapper
import com.prestongarno.kotlinq.core.adapters.newScalarDelegate
import com.prestongarno.kotlinq.core.api.ConfiguredQuery
import com.prestongarno.kotlinq.core.api.NoArgConfig
import com.prestongarno.kotlinq.core.api.OptionalConfiguration
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

interface CustomScalarStub<T : CustomScalar, V, out A : ArgumentSpec> : DelegateProvider<V> {

  var default: V?

  fun config(scope: A.() -> Unit)

  companion object {

    internal
    fun <T : CustomScalar> noArgStub(
        qproperty: GraphQlProperty
    ): Query<T> = QueryImpl(qproperty)

    internal
    fun <T : CustomScalar, A : ArgumentSpec> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQuery<T, A> = OptionalConfigQueryImpl(qproperty)

    internal
    fun <T : CustomScalar, A : ArgumentSpec> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<T, A> = ConfigurableQueryImpl(qproperty)

  }

  interface Query<T : CustomScalar> : SchemaStub {
    fun <U : QScalarMapper<V>, V> map(adapter: U)
        : NoArgConfig<CustomScalarStub<T, V, ArgumentSpec>, V>
  }

  interface OptionalConfigQuery<T : CustomScalar, A : ArgumentSpec> : SchemaStub {
    fun <U : QScalarMapper<V>, V> map(
        adapter: U
    ): OptionalConfiguration<CustomScalarStub<T, V, ArgumentSpec>, V, A>
  }

  interface ConfigurableQuery<T : CustomScalar, A : ArgumentSpec> : SchemaStub {
    fun <V> map(
        adapter: QScalarMapper<V>
    ): ConfiguredQuery<CustomScalarStub<T, V, ArgumentSpec>, A>
  }

  /*********************************************************************************
   * Private default implementations
   */
  private
  class QueryImpl<T : CustomScalar>(val qproperty: GraphQlProperty) : Query<T> {

    override fun <U : QScalarMapper<V>, V> map(adapter: U): NoArgConfig<CustomScalarStub<T, V, ArgumentSpec>, V> =
        NoArgConfig.new { args -> newScalarDelegate<T, U, V, ArgumentSpec>(qproperty, adapter, args ?: ArgBuilder()) }

  }

  private
  class OptionalConfigQueryImpl<T : CustomScalar, A : ArgumentSpec>(
      val qproperty: GraphQlProperty
  ) : OptionalConfigQuery<T, A> {

    override fun <U : QScalarMapper<V>, V> map(adapter: U): OptionalConfiguration<CustomScalarStub<T, V, ArgumentSpec>, V, A> =
        OptionalConfiguration.new { args -> newScalarDelegate(qproperty, adapter, args) }

  }

  private
  class ConfigurableQueryImpl<T : CustomScalar, A : ArgumentSpec>(
      val qproperty: GraphQlProperty
  ) : ConfigurableQuery<T, A> {

    override fun <V> map(adapter: QScalarMapper<V>): ConfiguredQuery<CustomScalarStub<T, V, ArgumentSpec>, A> =
        ConfiguredQuery.new { args -> newScalarDelegate(qproperty, adapter, args) }

  }
}