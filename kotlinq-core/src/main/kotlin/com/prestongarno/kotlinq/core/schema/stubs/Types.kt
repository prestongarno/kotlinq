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
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.TypeStubAdapter
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.DelegateProvider
import com.prestongarno.kotlinq.core.properties.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyPreDelegate
import com.prestongarno.kotlinq.core.schema.QType

interface TypeStub<out A : ArgumentSpec> : GraphqlDslBuilder<A> {

  interface NoArg<in T : QType> : GraphQlPropertyPreDelegate {

    operator fun <U : QModel<T>> invoke(
        constructor: () -> U,
        arguments: ArgBuilder = ArgBuilder(),
        block: TypeStub<ArgBuilder>.() -> Unit = empty()
    ): DelegateProvider<U>

    interface Nullable<in T : QType> : GraphQlPropertyPreDelegate {
      operator fun <U : QModel<T>> invoke(
          constructor: () -> U,
          arguments: ArgBuilder = ArgBuilder(),
          block: TypeStub<ArgBuilder>.() -> Unit = empty()
      ): DelegateProvider<U?>
    }
  }

  interface Configured<in T : QType, A : ArgumentSpec> : GraphQlPropertyPreDelegate {
    operator fun <U : QModel<T>> invoke(
        constructor: () -> U,
        arguments: A,
        block: TypeStub<A>.() -> Unit = empty()
    ): DelegateProvider<U>

    interface Nullable<in T : QType, A : ArgumentSpec> : GraphQlPropertyPreDelegate {
      operator fun <U : QModel<T>> invoke(
          constructor: () -> U,
          arguments: A,
          block: TypeStub<A>.() -> Unit = empty()
      ): DelegateProvider<U?>
    }

  }

  interface OptionalConfigured<in T : QType, A : ArgumentSpec> : Configured<T, A> {
    operator fun <U : QModel<T>> invoke(
        constructor: () -> U,
        block: TypeStub<ArgBuilder>.() -> Unit = empty()
    ): DelegateProvider<U>

    interface Nullable<in T : QType, A : ArgumentSpec> : Configured.Nullable<T, A> {
      operator fun <U : QModel<T>> invoke(
          constructor: () -> U,
          block: TypeStub<ArgBuilder>.() -> Unit = empty()
      ): DelegateProvider<U?>
    }
  }

  companion object {
    internal
    fun <T : QType> noArg(qproperty: GraphQlProperty): NoArgImpl<T> =
        NoArgImpl(qproperty)

    internal
    fun <T : QType, A : ArgumentSpec> optionallyConfigured(qproperty: GraphQlProperty): OptionalConfiguredImpl<T, A> =
        OptionalConfiguredImpl(qproperty)
  }
}

internal
class NoArgImpl<in T : QType>(val qproperty: GraphQlProperty) : TypeStub.NoArg<T> {
  override fun <U : QModel<T>> invoke(
      constructor: () -> U,
      arguments: ArgBuilder,
      block: TypeStub<ArgBuilder>.() -> Unit
  ): DelegateProvider<U> = createTypeDelegate(qproperty, constructor, arguments, block)

  fun asNullable(): TypeStub.NoArg.Nullable<T> = NullableNoArgImpl(qproperty)
}

private
class NullableNoArgImpl<in T : QType>(val qproperty: GraphQlProperty) : TypeStub.NoArg.Nullable<T> {

  override fun <U : QModel<T>> invoke(
      constructor: () -> U,
      arguments: ArgBuilder,
      block: TypeStub<ArgBuilder>.() -> Unit
  ): DelegateProvider<U?> = createNullableTypeDelegate(qproperty, constructor, arguments, block)
}

internal
class OptionalConfiguredImpl<in T : QType, A : ArgumentSpec>(val qproperty: GraphQlProperty) : TypeStub.OptionalConfigured<T, A> {

  override fun <U : QModel<T>> invoke(constructor: () -> U, block: TypeStub<ArgBuilder>.() -> Unit): DelegateProvider<U> =
      createTypeDelegate(qproperty, constructor, null, block)

  override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: A, block: TypeStub<A>.() -> Unit): DelegateProvider<U> =
      createTypeDelegate(qproperty, constructor, arguments, block)

  fun asNullable(): TypeStub.OptionalConfigured.Nullable<T, A> = NullableOptionalConfigImpl(qproperty)
}

private
class NullableOptionalConfigImpl<in T : QType, A : ArgumentSpec>(val qproperty: GraphQlProperty) : TypeStub.OptionalConfigured.Nullable<T, A> {
  override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: A, block: TypeStub<A>.() -> Unit): DelegateProvider<U?> =
      createNullableTypeDelegate(qproperty, constructor, arguments, block)

  override fun <U : QModel<T>> invoke(constructor: () -> U, block: TypeStub<ArgBuilder>.() -> Unit): DelegateProvider<U?> =
    createNullableTypeDelegate(qproperty, constructor, ArgBuilder(), block)
}

private fun <U : QModel<T>, T : QType, A : ArgumentSpec> createTypeDelegate(
    property: GraphQlProperty,
    ctor: () -> U,
    args: A? = null,
    block: TypeStub<A>.() -> Unit = empty()
): DelegateProvider<U> = delegateProvider { model, _ ->
  TypeStubAdapter(ctor, args)
      .apply(block)
      .toDelegate(property)
      .bindToContext(model)
}

private fun <U : QModel<T>, T : QType, A : ArgumentSpec> createNullableTypeDelegate(
    property: GraphQlProperty,
    ctor: () -> U,
    args: A? = null,
    block: TypeStub<A>.() -> Unit = empty()
): DelegateProvider<U?> = delegateProvider { model, _ ->
  TypeStubAdapter(ctor, args)
      .apply(block)
      .toDelegate(property)
      .asNullable()
      .bindToContext(model)
}
