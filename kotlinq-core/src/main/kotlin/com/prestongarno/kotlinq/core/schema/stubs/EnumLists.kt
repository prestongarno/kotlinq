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
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.properties.GraphQLPropertyContext
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import com.prestongarno.kotlinq.core.adapters.newEnumListDelegate
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import kotlin.reflect.KClass

interface EnumListStub<T, out A>
    where T : Enum<*>,
          T : QEnumType,
          A : ArgumentSpec {

  var default: T?

  fun config(scope: A.() -> Unit)

  companion object {

    internal
    fun <T> noArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): Query<T> where T : Enum<*>, T : QEnumType =
        QueryImpl(qproperty, enumClass)

    internal
    fun <T, A> optionalArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): OptionalConfigQuery<T, A>
        where T : Enum<*>, T : QEnumType, A : ArgumentSpec =
        OptionalConfigQueryImpl(qproperty, enumClass)

    internal
    fun <T, A> argStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): ConfigurableQuery<T, A>
        where T : Enum<*>, T : QEnumType, A : ArgumentSpec =
        ConfigurableQueryImpl(qproperty, enumClass)
  }

  interface Query<T>
      where T : Enum<*>,
            T : QEnumType {

    operator fun invoke(
        arguments: ArgumentSpec? = null,
        scope: (EnumListStub<T, ArgumentSpec>.() -> Unit)? = null
    ): EnumListStub<T, ArgumentSpec>

  }

  private
  class QueryImpl<T>(
      val qproperty: GraphQlProperty,
      val enumClass: KClass<T>
  ) : Query<T> where T : Enum<*>, T : QEnumType {

    override fun invoke(
        arguments: ArgumentSpec?,
        scope: (EnumListStub<T, ArgumentSpec>.() -> Unit)?
    ): EnumListStub<T, ArgumentSpec> =
        newEnumListDelegate(
            qproperty,
            arguments ?: ArgBuilder(),
            enumClass
        ).applyNotNull(scope)
  }

  interface OptionalConfigQuery<T, A>
      where T : Enum<*>,
            T : QEnumType,
            A : ArgumentSpec {

    operator fun invoke(
        arguments: A,
        scope: (EnumListStub<T, A>.() -> Unit)? = null
    ): EnumListStub<T, ArgumentSpec>

  }

  /*********************************************************************************
   * Private default implementations
   */
  private
  class OptionalConfigQueryImpl<T, A>(
      val qproperty: GraphQlProperty,
      val enumClass: KClass<T>
  ) : OptionalConfigQuery<T, A> where T : Enum<*>, T : QEnumType, A : ArgumentSpec {

    override fun invoke(
        arguments: A,
        scope: (EnumListStub<T, A>.() -> Unit)?
    ): EnumListStub<T, ArgumentSpec> =
        newEnumListDelegate(qproperty, arguments, enumClass).applyNotNull(scope)

  }

  interface ConfigurableQuery<T, A> : GraphQLPropertyContext<Any?>
      where T : Enum<*>,
            T : QEnumType,
            A : ArgumentSpec {

    operator fun invoke(
        arguments: A,
        scope: (EnumListStub<T, A>.() -> Unit)? = null
    ): EnumListStub<T, ArgumentSpec>

  }

  private
  class ConfigurableQueryImpl<T, A>(
      val qproperty: GraphQlProperty,
      val enumClass: KClass<T>
  ) : ConfigurableQuery<T, A> where T : Enum<*>, T : QEnumType, A : ArgumentSpec {

    override fun invoke(
        arguments: A,
        scope: (EnumListStub<T, A>.() -> Unit)?
    ): EnumListStub<T, ArgumentSpec> =
        newEnumListDelegate(qproperty, arguments, enumClass).applyNotNull(scope)

  }

}











