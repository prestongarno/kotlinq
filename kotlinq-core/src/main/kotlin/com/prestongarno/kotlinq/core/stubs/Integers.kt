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
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.SchemaStub
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import com.prestongarno.kotlinq.core.adapters.bind
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface IntDelegate<out A : ArgBuilder> : ScalarDelegate<IntStub> {

  var default: Int

  fun config(scope: A.() -> Unit)

  companion object {

    internal
    fun noArgStub(
        qproperty: GraphQlProperty
    ): Query = QueryImpl(qproperty)

    internal
    fun <A : ArgBuilder> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQuery<A> =
        OptionalConfigQueryImpl(qproperty)

    internal
    fun <A : ArgBuilder> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<A> =
        ConfigurableQueryImpl(qproperty)

  }

  interface Query : SchemaStub {

    operator fun invoke(
        arguments: ArgBuilder? = null,
        scope: (IntDelegate<ArgBuilder>.() -> Unit)? = null
    ): IntDelegate<ArgBuilder>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntStub = invoke().provideDelegate(inst, property)
  }

  interface OptionalConfigQuery<A : ArgBuilder> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (IntDelegate<A>.() -> Unit)?
    ): IntDelegate<A>

    operator fun provideDelegate(
        inst: QModel<*>,
        property: KProperty<*>
    ): IntStub
  }

  interface ConfigurableQuery<A : ArgBuilder> : SchemaStub {

    operator fun invoke(
        arguments: A,
        scope: (IntDelegate<A>.() -> Unit)? = null
    ): IntDelegate<A>
  }

  /*********************************************************************************
   * Private default implementations
   */
  private
  class QueryImpl(val qproperty: GraphQlProperty) : Query {
    override fun invoke(
        arguments: ArgBuilder?, scope: (IntDelegate<ArgBuilder>.() -> Unit)?
    ) = IntDelegateImpl(qproperty, arguments ?: ArgBuilder()).applyNotNull(scope)
  }

  private
  class OptionalConfigQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : OptionalConfigQuery<A> {

    override fun invoke(arguments: A, scope: (IntDelegate<A>.() -> Unit)?): IntDelegate<A> =
        IntDelegateImpl(qproperty, arguments).applyNotNull(scope)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntStub = IntStub(qproperty).bind(inst)
  }

  private
  class ConfigurableQueryImpl<A : ArgBuilder>(val qproperty: GraphQlProperty) : ConfigurableQuery<A> {

    override fun invoke(arguments: A, scope: (IntDelegate<A>.() -> Unit)?): IntDelegate<A> =
        IntDelegateImpl(qproperty, arguments).applyNotNull(scope)
  }
}

private
class IntDelegateImpl<out A : ArgBuilder>(
    val qproperty: GraphQlProperty,
    val argBuilder: A? = null
) : IntDelegate<A> {

  override var default: Int = 0

  override fun config(scope: A.() -> Unit) {
    argBuilder?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntStub =
      IntStub(qproperty, argBuilder.toMap(), default).bind(inst)
}
