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

interface BooleanDelegate<out A : ArgBuilder> : ScalarDelegate<BooleanStub> {

  var default : Boolean

  fun config(scope: A.() -> Unit)

  companion object {

    internal fun noArgStub(
        qproperty: GraphQlProperty
    ): BooleanDelegate.Query = QueryImpl(qproperty)

    internal fun <A : ArgBuilder> optionalArgStub(
        qproperty: GraphQlProperty
    ): BooleanDelegate.OptionalConfigQuery<A> =
        OptionalConfigQueryImpl(qproperty)

    internal fun <A : ArgBuilder> argStub(
        qproperty: GraphQlProperty
    ): BooleanDelegate.ConfigurableQuery<A> =
        ConfigurableQueryImpl(qproperty)

  }

  interface Query : SchemaStub {

    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (BooleanDelegate<ArgBuilder>.() -> Unit)? = null
    ): BooleanDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): BooleanStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgBuilder> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (BooleanDelegate<A>.() -> Unit)?
    ): BooleanDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): BooleanStub
  }

  interface ConfigurableQuery<A : ArgBuilder> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (BooleanDelegate<A>.() -> Unit)? = null
    ): BooleanDelegate<A>
  }

  private class QueryImpl(val qproperty: GraphQlProperty) : BooleanDelegate.Query {
    override fun invoke(
        arguments: ArgBuilder?, scope: (BooleanDelegate<ArgBuilder>.() -> Unit)?
    ) = BooleanDelegateImpl(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private class OptionalConfigQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : BooleanDelegate.OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (BooleanDelegate<A>.() -> Unit)?): BooleanDelegate<A> =
        BooleanDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanStub = BooleanStub(qproperty).bind(inst)
  }

  private class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : BooleanDelegate.ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (BooleanDelegate<A>.() -> Unit)?): BooleanDelegate<A> =
        BooleanDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private class BooleanDelegateImpl<out A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    val argBuilder: A? = null
) : BooleanDelegate<A> {

  override var default: Boolean = false

  override fun config(scope: A.() -> Unit) {
    argBuilder?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanStub =
      BooleanStub(qproperty, argBuilder.toMap(), default).bind(inst)
}
