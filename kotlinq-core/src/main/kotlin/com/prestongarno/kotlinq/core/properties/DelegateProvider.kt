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

package com.prestongarno.kotlinq.core.properties

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.QField
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.Stub
import kotlin.reflect.KProperty

internal
fun <D, A : ArgumentSpec, RET : Any?> configurableGraphQlProperty(
    constructor: () -> D,
    onProvideDelegate: D.(QModel<*>) -> GraphqlPropertyDelegate<RET>
): GraphQLPropertyContext<D, A, RET>
    where D : GraphqlDslBuilder<A> = object : GraphQLPropertyContext<D, A, RET> {
  private val value by lazy { configurableDelegate(constructor, onDelegate) }

  override val constructor: () -> D get() = constructor

  override val onDelegate: D.(QModel<*>) -> GraphqlPropertyDelegate<RET> get() = onProvideDelegate

  override fun getValue(
      inst: QSchemaType,
      property: KProperty<*>
  ): ConfigurableDelegateProvider<D, A, RET> = value

}

internal
interface GraphQLPropertyContext<
    U : GraphqlDslBuilder<A>,
    A : ArgumentSpec,
    out RET : Any?
    >
  : Stub<ConfigurableDelegateProvider<U, A, RET>> {

  val constructor: () -> U

  val onDelegate: U.(QModel<*>) -> QField<RET>
}

interface DelegateProvider<out T : Any?> {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

interface ConfiguredDelegateProvider<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, out T> {
  operator fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T>
}

interface ConfigurableDelegateProvider<
    out U : GraphqlDslBuilder<A>,
    A : ArgumentSpec,
    out T : Any?>
  : DelegateProvider<T> {
  operator fun invoke(args: A, block: (U.() -> Unit)?): DelegateProvider<T>
  operator fun invoke(block: U.() -> Unit): DelegateProvider<T>
}

private
fun <T> delegateProvider(onDelegate: (QModel<*>) -> QField<T>): DelegateProvider<T> = object : DelegateProvider<T> {
  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = onDelegate(inst)
}

internal
fun <U, A, T> configurableDelegate(
    constructor: () -> U,
    onDelegate: U.(QModel<*>) -> QField<T>
) where U : GraphqlDslBuilder<A>, A : ArgumentSpec =
    object : ConfigurableDelegateProvider<U, A, T> {
      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = constructor().onDelegate(inst)
      override fun invoke(args: A, block: (U.() -> Unit)?): DelegateProvider<T> =
          delegateProvider { constructor().applyNotNull(block).onDelegate(it) }
      override fun invoke(block: U.() -> Unit): DelegateProvider<T> =
          delegateProvider { constructor().apply(block).onDelegate(it) }
    }

internal
fun <U, A, T> configuredDelegate(
    constructor: () -> U,
    onDelegate: U.(QModel<*>) -> QField<T>
) where U : GraphqlDslBuilder<A>, A : ArgumentSpec, T : Any? =

    object : ConfiguredDelegateProvider<U, A, T> {
      override fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T> = delegateProvider {
        constructor().apply(block).onDelegate(it)
      }
    }
