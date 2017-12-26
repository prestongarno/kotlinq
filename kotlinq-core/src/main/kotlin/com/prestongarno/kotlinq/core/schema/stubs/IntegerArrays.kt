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

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.properties.GraphQLPropertyContext
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import com.prestongarno.kotlinq.core.adapters.bind
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface IntArrayDelegate<out A : ArgumentSpec> : ScalarArrayDelegate<IntArrayStub> {

  var default: IntArray?

  fun config(scope: A.() -> Unit)

  companion object {

    internal
    fun noArgStub(
        qproperty: GraphQlProperty
    ): Query = QueryImpl(qproperty)

    internal
    fun <A : ArgumentSpec> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQuery<A> =
        OptionalConfigQueryImpl(qproperty)

    internal
    fun <A : ArgumentSpec> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<A> =
        ConfigurableQueryImpl(qproperty)
  }

  interface Query : GraphQLPropertyContext<Any?> {
    operator fun invoke(
        arguments: ArgumentSpec? = null,
        scope: (IntArrayDelegate<ArgumentSpec>.() -> Unit)? = null
    ): IntArrayDelegate<ArgumentSpec>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntArrayStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgumentSpec> : GraphQLPropertyContext<Any?> {

    operator fun invoke(
        arguments: A,
        scope: (IntArrayDelegate<A>.() -> Unit)?
    ): IntArrayDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntArrayStub

  }

  interface ConfigurableQuery<A : ArgumentSpec> : GraphQLPropertyContext<Any?> {

    operator fun invoke(
        arguments: A,
        scope: (IntArrayDelegate<A>.() -> Unit)? = null
    ): IntArrayDelegate<A>
  }

  /*********************************************************************************
   * Private default implementations
   */
  private
  class QueryImpl(val qproperty: GraphQlProperty) : Query {
    override fun invoke(arguments: ArgumentSpec?, scope: (IntArrayDelegate<ArgumentSpec>.() -> Unit)?
    ) = IntArrayDelegateImpl(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private
  class OptionalConfigQueryImpl<A : ArgumentSpec>(val qproperty: GraphQlProperty) : OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (IntArrayDelegate<A>.() -> Unit)?): IntArrayDelegate<A> =
        IntArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntArrayStub = IntArrayStub(qproperty).bind(inst)
  }


  private
  class ConfigurableQueryImpl<A : ArgumentSpec>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (IntArrayDelegate<A>.() -> Unit)?): IntArrayDelegate<A> =
        IntArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private
class IntArrayDelegateImpl<out A : ArgumentSpec>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : IntArrayDelegate<A> {

  override var default: IntArray? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntArrayStub =
      IntArrayStub(qproperty, default, argBuilder.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) {
    argBuilder?.scope()
  }
}

