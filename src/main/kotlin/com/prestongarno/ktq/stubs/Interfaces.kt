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
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QInterface
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.InterfaceAdapterImpl
import com.prestongarno.ktq.properties.GraphQlProperty

/**
 * Remember -> compile generate all interface types to be *both* [QType] ***and*** [QInterface]
 */
interface InterfaceStub<I, out A : ArgBuilder> : FragmentStub<I>, DelegateProvider<QModel<I>?>
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
              A : ArgBuilder =
        OptionalConfigQueryImpl(qproperty)

    internal
    fun <I, A> argStub(qproperty: GraphQlProperty)
        :
        ConfigurableQuery<I, A>
        where I : QInterface,
              I : QType,
              A : ArgBuilder =
        ConfigurableQueryImpl(qproperty)
  }

  interface Query<I> : SchemaStub where I : QInterface, I : QType {

    operator fun invoke(arguments: ArgBuilder? = ArgBuilder(),
          scope: InterfaceStub<I, ArgBuilder>.() -> Unit)
        :
        InterfaceStub<I, ArgBuilder>
  }

  interface OptionalConfigQuery<I, A> : SchemaStub where I : QInterface, I : QType, A : ArgBuilder {

    operator fun invoke(scope: FragmentStub<I>.() -> Unit)
        :
        FragmentStub<I>

    operator fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit)
        :
        InterfaceStub<I, A>

  }

  interface ConfigurableQuery<I, A> : SchemaStub where I : QInterface, I : QType, A : ArgBuilder {

    operator fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit)
        :
        InterfaceStub<I, A>

  }


  /*********************************************************************************
   * Private default implementations
   */
  private
  class QueryImpl<I>(val qproperty: GraphQlProperty) : Query<I> where I : QInterface, I : QType {

    override fun invoke(arguments: ArgBuilder?, scope: InterfaceStub<I, ArgBuilder>.() -> Unit)
        :
        InterfaceStub<I, ArgBuilder>
        = InterfaceAdapterImpl<I, ArgBuilder>(qproperty, arguments ?: ArgBuilder()).apply(scope)

  }

  private
  class OptionalConfigQueryImpl<I, A>(val qproperty: GraphQlProperty) : OptionalConfigQuery<I, A>
      where I : QInterface, I : QType, A : ArgBuilder {

    override fun invoke(scope: FragmentStub<I>.() -> Unit): FragmentStub<I> =
        InterfaceAdapterImpl<I, A>(qproperty, null).apply(scope)

    override fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).apply(scope)

  }

  private
  class ConfigurableQueryImpl<I, A>(val qproperty: GraphQlProperty) : ConfigurableQuery<I, A>
      where I : QInterface, I : QType, A : ArgBuilder {

    override fun invoke(arguments: A, scope: InterfaceStub<I, A>.() -> Unit): InterfaceStub<I, A> =
        InterfaceAdapterImpl<I, A>(qproperty, arguments).apply(scope)

  }

}