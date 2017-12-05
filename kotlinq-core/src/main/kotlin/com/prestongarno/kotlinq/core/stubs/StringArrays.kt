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
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.SchemaStub
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import com.prestongarno.kotlinq.core.adapters.bind
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface StringArrayDelegate<out A : ArgumentSpec> : ScalarArrayDelegate<StringArrayStub> {

  var default: Array<String>?

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

  interface Query : SchemaStub {
    operator fun invoke(
        arguments: ArgumentSpec? = null,
        scope: (StringArrayDelegate<ArgumentSpec>.() -> Unit)? = null
    ): StringArrayDelegate<ArgumentSpec>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): StringArrayStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgumentSpec> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (StringArrayDelegate<A>.() -> Unit)?
    ): StringArrayDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): StringArrayStub

  }

  interface ConfigurableQuery<A : ArgumentSpec> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (StringArrayDelegate<A>.() -> Unit)? = null
    ): StringArrayDelegate<A>
  }

  /*********************************************************************************
   * Private default implementations
   */
  private
  class QueryImpl(val qproperty: GraphQlProperty) : Query {
    override fun invoke(arguments: ArgumentSpec?, scope: (StringArrayDelegate<ArgumentSpec>.() -> Unit)?
    ) = StringArrayDelegateImpl<ArgumentSpec>(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private
  class OptionalConfigQueryImpl<A : ArgumentSpec>(val qproperty: GraphQlProperty) : OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (StringArrayDelegate<A>.() -> Unit)?): StringArrayDelegate<A> =
        StringArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): StringArrayStub = StringArrayStub(qproperty).bind(inst)
  }

  private
  class ConfigurableQueryImpl<A : ArgumentSpec>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (StringArrayDelegate<A>.() -> Unit)?): StringArrayDelegate<A> =
        StringArrayDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private
class StringArrayDelegateImpl<out A : ArgumentSpec>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : StringArrayDelegate<A> {

  override var default: Array<String>? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): StringArrayStub =
      StringArrayStub(qproperty, default, argBuilder.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) {
    argBuilder?.scope()
  }
}

