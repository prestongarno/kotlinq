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
import com.prestongarno.kotlinq.core.DelegateProvider
import com.prestongarno.kotlinq.core.QInterface
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.SchemaStub
import com.prestongarno.kotlinq.core.adapters.newInterfaceListStub
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

interface InterfaceListStub<I, out A> :
    FragmentStub<I>,
    DelegateProvider<List<QModel<I>>>
    where I : QType,
          I : QInterface,
          A : ArgumentSpec {

  fun config(argumentScope: A.() -> Unit)

  companion object {
    internal
    fun <I> noArgStub(
        qproperty: GraphQlProperty
    ): Query<I> where I : QInterface, I : QType =
        QueryImpl(qproperty)

    internal
    fun <I, A> optionalArgStub(
        qproperty: GraphQlProperty
    ): OptionalConfigQuery<I, out A>
        where I : QInterface, I : QType, A : ArgumentSpec =
        OptionalConfigQueryImpl(qproperty)

    internal
    fun <I, A> argStub(
        qproperty: GraphQlProperty
    ): ConfigurableQuery<I, out A>
        where I : QInterface, I : QType, A : ArgumentSpec =
        ConfigurableQueryImpl(qproperty)
  }

  interface Query<I> : SchemaStub where I : QInterface, I : QType {

    operator fun invoke(
        arguments: ArgumentSpec? = null,
        scope: InterfaceListStub<I, ArgumentSpec>.() -> Unit
    ): InterfaceListStub<I, ArgumentSpec>

  }

  interface OptionalConfigQuery<I, A> : ConfigurableQuery<I, A>
      where I : QInterface,
            I : QType,
            A : ArgumentSpec {

    /** Create stub for field without any arguments */
    operator fun invoke(
        scope: FragmentStub<I>.() -> Unit
    ): InterfaceListStub<I, A>

  }

  interface ConfigurableQuery<I, A> : SchemaStub
      where I : QInterface,
            I : QType,
            A : ArgumentSpec {

    operator fun invoke(
        arguments: A,
        scope: InterfaceListStub<I, out A>.() -> Unit
    ): InterfaceListStub<I, out A>

  }

  /*********************************************************************************
   * Private default implementations
   */
  private
  class QueryImpl<I>(val qproperty: GraphQlProperty) : Query<I> where I : QInterface, I : QType {
    override fun invoke(arguments: ArgumentSpec?, scope: InterfaceListStub<I, ArgumentSpec>.() -> Unit)
        : InterfaceListStub<I, ArgumentSpec> =
        newInterfaceListStub<I, ArgumentSpec>(qproperty, arguments ?: ArgBuilder()).apply(scope)
  }

  private
  class OptionalConfigQueryImpl<I, A>(
      val qproperty: GraphQlProperty
  ) : OptionalConfigQuery<I, A>
      where I : QInterface,
            I : QType,
            A : ArgumentSpec {

    override fun invoke(arguments: A, scope: InterfaceListStub<I, A>.() -> Unit): InterfaceListStub<I, A> =
        newInterfaceListStub<I, A>(qproperty, arguments).apply(scope)

    override fun invoke(scope: FragmentStub<I>.() -> Unit): InterfaceListStub<I, A> =
        newInterfaceListStub<I, A>(qproperty, null).apply(scope)
  }

  private
  class ConfigurableQueryImpl<I, A>(
      val qproperty: GraphQlProperty
  ) : ConfigurableQuery<I, A>
      where I : QInterface,
            I : QType,
            A : ArgumentSpec {

    override fun invoke(
        arguments: A, scope: InterfaceListStub<I, A>.() -> Unit
    ): InterfaceListStub<I, A> =
        newInterfaceListStub<I, A>(qproperty, arguments).apply(scope)
  }
}