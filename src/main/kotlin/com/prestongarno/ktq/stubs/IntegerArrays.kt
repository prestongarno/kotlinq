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
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.applyNotNull
import com.prestongarno.ktq.adapters.bind
import com.prestongarno.ktq.adapters.toMap
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface IntArrayDelegate<out A : ArgBuilder> : ScalarArrayDelegate<IntArrayStub> {

  var default: IntArray?

  fun config(scope: A.() -> Unit)

  companion object {

    internal fun noArgStub(
        qproperty: GraphQlProperty
    ): IntArrayDelegate.Query = QueryImpl(qproperty)

    internal fun <A : ArgBuilder> optionalArgStub(
        qproperty: GraphQlProperty
    ): IntArrayDelegate.OptionalConfigQuery<A> =
        OptionalConfigQueryImpl(qproperty)

    internal fun <A : ArgBuilder> argStub(
        qproperty: GraphQlProperty
    ): IntArrayDelegate.ConfigurableQuery<A> =
        ConfigurableQueryImpl(qproperty)
  }

  interface Query : SchemaStub {
    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (IntArrayDelegate<ArgBuilder>.() -> Unit)? = null
    ): IntArrayDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntArrayStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgBuilder> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (IntArrayDelegate<A>.() -> Unit)?
    ): IntArrayDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntArrayStub

  }

  interface ConfigurableQuery<A : ArgBuilder> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (IntArrayDelegate<A>.() -> Unit)? = null
    ): IntArrayDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : Query {
    override fun invoke(arguments: ArgBuilder?, scope: (IntArrayDelegate<ArgBuilder>.() -> Unit)?
    ) = IntArrayDelegateImpl(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (IntArrayDelegate<A>.() -> Unit)?): IntArrayDelegate<A> =
        IntArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntArrayStub = IntArrayStub(qproperty).bind(inst)
  }


  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (IntArrayDelegate<A>.() -> Unit)?): IntArrayDelegate<A> =
        IntArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class IntArrayDelegateImpl<out A : ArgBuilder>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : IntArrayDelegate<A>  {

  override var default: IntArray? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntArrayStub =
      IntArrayStub(qproperty, default, argBuilder.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) { argBuilder?.scope() }
}

