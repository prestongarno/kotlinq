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
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.properties.GraphQLPropertyContext
import com.prestongarno.kotlinq.core.adapters.InterfaceAdapterImpl
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

/**
 * Remember -> compile generate all interface types to be *both* [QType] ***and*** [QInterface]
 */
interface InterfaceStub<in I, out A : ArgumentSpec> : FragmentStub<I>
    where I : QInterface,
          I : QType {

  fun config(argumentScope: A.() -> Unit)

  companion object {

    internal
    fun <I> noArgStub(qproperty: GraphQlProperty)
        :
        Query<I> where I : QInterface, I : QType =
        QueryImpl(qproperty)

    internal
    fun <I, A> optionalArgStub(qproperty: GraphQlProperty)
        :
        OptionalConfigQuery<I, A>
        where I : QInterface,
              I : QType,
              A : ArgumentSpec =
        OptionalConfigQueryImpl(qproperty)

    internal
    fun <I, A> argStub(qproperty: GraphQlProperty)
        :
        ConfigurableQuery<I, A>
        where I : QInterface,
              I : QType,
              A : ArgumentSpec =
        ConfigurableQueryImpl(qproperty)
  }

  interface Query<I> : GraphQLPropertyContext<Any?> where I : QInterface, I : QType {

    operator fun invoke(arguments: ArgumentSpec? = ArgBuilder(),
          scope: InterfaceStub<I, ArgumentSpec>.() -> Unit)
        :
        InterfaceStub<I, ArgumentSpec>
  }

  interface OptionalConfigQuery<I, A> : GraphQLPropertyContext<Any?> where I : QInterface, I : QType, A : ArgumentSpec {

    operator fun invoke(scope: FragmentStub<I>.() -> Unit)
        :
        FragmentStub<I>

    operator fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit)
        :
        InterfaceStub<I, A>

  }

  interface ConfigurableQuery<I, A> : GraphQLPropertyContext<Any?> where I : QInterface, I : QType, A : ArgumentSpec {

    operator fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit)
        :
        InterfaceStub<I, A>

  }


  /*********************************************************************************
   * Private default implementations
   */
  private
  class QueryImpl<I>(val qproperty: GraphQlProperty) : Query<I> where I : QInterface, I : QType {

    override fun invoke(arguments: ArgumentSpec?, scope: InterfaceStub<I, ArgumentSpec>.() -> Unit)
        :
        InterfaceStub<I, ArgumentSpec>
        = InterfaceAdapterImpl<I, ArgumentSpec>(qproperty, arguments ?: ArgBuilder()).apply(scope)

  }

  private
  class OptionalConfigQueryImpl<I, A>(val qproperty: GraphQlProperty) : OptionalConfigQuery<I, A>
      where I : QInterface, I : QType, A : ArgumentSpec {

    override fun invoke(scope: FragmentStub<I>.() -> Unit): FragmentStub<I> =
        InterfaceAdapterImpl<I, A>(qproperty, null).apply(scope)

    override fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).apply(scope)

  }

  private
  class ConfigurableQueryImpl<I, A>(val qproperty: GraphQlProperty) : ConfigurableQuery<I, A>
      where I : QInterface, I : QType, A : ArgumentSpec {

    override fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).apply(scope)

  }

}