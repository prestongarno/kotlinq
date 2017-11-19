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

interface StringArrayDelegate<out A : ArgBuilder> : ScalarArrayDelegate<StringArrayStub> {

  var default: Array<String>?

  fun config(scope: A.() -> Unit)

  companion object {

    @PublishedApi internal fun noArgStub(
        qproperty: GraphQlProperty
    ): StringArrayDelegate.Query = QueryImpl(qproperty)

    @PublishedApi internal fun <A : ArgBuilder> optionalArgStub(
        qproperty: GraphQlProperty
    ): StringArrayDelegate.OptionalConfigQuery<A> =
        OptionalConfigQueryImpl(qproperty)

    @PublishedApi internal fun <A : ArgBuilder> argStub(
        qproperty: GraphQlProperty
    ): StringArrayDelegate.ConfigurableQuery<A> =
        ConfigurableQueryImpl(qproperty)

  }

  interface Query : SchemaStub {
    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (StringArrayDelegate<ArgBuilder>.() -> Unit)? = null
    ): StringArrayDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): StringArrayStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgBuilder> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (StringArrayDelegate<A>.() -> Unit)?
    ): StringArrayDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): StringArrayStub

  }

  interface ConfigurableQuery<A : ArgBuilder> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (StringArrayDelegate<A>.() -> Unit)? = null
    ): StringArrayDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : Query {
    override fun invoke(arguments: ArgBuilder?, scope: (StringArrayDelegate<ArgBuilder>.() -> Unit)?
    ) = StringArrayDelegateImpl<ArgBuilder>(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (StringArrayDelegate<A>.() -> Unit)?): StringArrayDelegate<A> =
        StringArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): StringArrayStub = StringArrayStub(qproperty).bind(inst)
  }

  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (StringArrayDelegate<A>.() -> Unit)?): StringArrayDelegate<A> =
        StringArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class StringArrayDelegateImpl<out A : ArgBuilder>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : StringArrayDelegate<A>  {

  override var default: Array<String>? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): StringArrayStub =
      StringArrayStub(qproperty, default, argBuilder.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) { argBuilder?.scope() }
}

