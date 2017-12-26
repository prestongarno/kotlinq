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
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.prestongarno.kotlinq.core.properties.GraphQLPropertyContext
import com.prestongarno.kotlinq.core.adapters.newUnionField
import com.prestongarno.kotlinq.core.properties.GraphQlProperty

interface UnionStub<out T : QUnionType, out A : ArgumentSpec> {

  fun config(scope: A.() -> Unit)

  fun fragment(scope: T.() -> Unit)

  companion object {

    internal
    fun <T : QUnionType> noArgStub(qproperty: GraphQlProperty, unionObject: T)
        :
        Query<T> =
        QueryImpl(qproperty, unionObject)

    internal
    fun <T : QUnionType, A : ArgumentSpec> optionalArgStub(qproperty: GraphQlProperty, unionObject: T)
        :
        OptionalConfigQuery<T, A> =
        OptionalConfigQueryImpl(qproperty, unionObject)

    internal
    fun <T : QUnionType, A : ArgumentSpec> argStub(qproperty: GraphQlProperty, unionObject: T)
        :
        ConfigurableQuery<T, A> =
        ConfigurableQueryImpl(qproperty, unionObject)
  }

  interface Query<out T : QUnionType> : GraphQLPropertyContext<Any?> {

    operator fun invoke(arguments: ArgumentSpec? = null, scope: UnionStub<T, ArgumentSpec>.() -> Unit)
        :
        UnionStub<T, ArgumentSpec>

  }

  interface OptionalConfigQuery<out T : QUnionType, A : ArgumentSpec> : ConfigurableQuery<T, A> {

    operator fun invoke(scope: UnionStub<T, ArgumentSpec>.() -> Unit)
        :
        UnionStub<T, ArgumentSpec>

  }

  interface ConfigurableQuery<out T : QUnionType, A : ArgumentSpec> : GraphQLPropertyContext<Any?> {

    operator fun invoke(arguments: A, scope: UnionStub<T, A>.() -> Unit)
        :
        UnionStub<T, A>

  }

  /*********************************************************************************
   * Private default implementations
   */
  private
  class QueryImpl<out T : QUnionType>(
      val qproperty: GraphQlProperty,
      val unionObject: T
  ) : Query<T> {

    override fun invoke(
        arguments: ArgumentSpec?,
        scope: UnionStub<T, ArgumentSpec>.() -> Unit
    ): UnionStub<T, ArgumentSpec> =
        newUnionField(qproperty, unionObject, arguments ?: ArgBuilder()).apply(scope)

  }

  private
  class OptionalConfigQueryImpl<out T : QUnionType, A : ArgumentSpec>(
      val qproperty: GraphQlProperty,
      val unionObject: T
  ) : OptionalConfigQuery<T, A> {

    override fun invoke(arguments: A, scope: UnionStub<T, A>.() -> Unit) =
        newUnionField(qproperty, unionObject, arguments).apply(scope)

    override fun invoke(scope: UnionStub<T, ArgumentSpec>.() -> Unit) =
        newUnionField(qproperty, unionObject, ArgBuilder()).apply(scope)

  }

  private
  class ConfigurableQueryImpl<out T : QUnionType, A : ArgumentSpec>(
      val qproperty: GraphQlProperty,
      val unionObject: T
  ) : ConfigurableQuery<T, A> {

    override fun invoke(arguments: A, scope: UnionStub<T, A>.() -> Unit)
        :
        UnionStub<T, A> =
        newUnionField(qproperty, unionObject, arguments).apply(scope)

  }

}