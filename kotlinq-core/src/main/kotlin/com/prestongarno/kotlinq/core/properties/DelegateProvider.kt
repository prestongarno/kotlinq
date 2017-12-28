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
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.PreDelegate
import com.prestongarno.kotlinq.core.adapters.QField
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import kotlin.reflect.KProperty

interface DelegateProvider<out T : Any?> {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

interface BasicDelegateProvider<out T : GraphqlDslBuilder<ArgumentSpec>, out V> : ConfigurableDelegateProvider<T, ArgumentSpec, V> {
  override operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<>
  operator fun invoke(block: T.() -> Unit): DelegateProvider<V>
}

interface ConfiguredDelegateProvider<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, out T> {
  operator fun invoke(args: A, block: (U.() -> Unit)? = null): DelegateProvider<T>
}

interface ConfigurableDelegateProvider<
    out U : GraphqlDslBuilder<A>,
    A : ArgumentSpec,
    out T : Any?>
  : DelegateProvider<T>, ConfiguredDelegateProvider<U, A, T>

private
fun <T> delegateProvider(onDelegate: (QModel<*>) -> GraphqlPropertyDelegate<T>): DelegateProvider<T> = object : DelegateProvider<T> {
  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = onDelegate(inst).bindToContext(inst)
}

internal
fun <U, A, T> configurableDelegate(
    constructor: (A?) -> U
) where U : GraphqlDslBuilder<A>, U : PreDelegate<GraphqlPropertyDelegate<T>, T>, A : ArgumentSpec =

    object : ConfigurableDelegateProvider<U, A, T> {

      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
          constructor(null).toDelegate().bindToContext(inst)

      override fun invoke(args: A, block: (U.() -> Unit)?): DelegateProvider<T> =
          delegateProvider { constructor(args).applyNotNull(block).toDelegate() }
    }

internal
fun <U, A, T> configuredDelegate(
    constructor: (A) -> U
) where U : GraphqlDslBuilder<A>, U : PreDelegate<GraphqlPropertyDelegate<T>, T>, A : ArgumentSpec =

    object : ConfiguredDelegateProvider<U, A, T> {

      override fun invoke(args: A, block: (U.() -> Unit)?): DelegateProvider<T> =
          delegateProvider { constructor(args).applyNotNull(block).toDelegate() }

    }

internal
fun <U : GraphqlDslBuilder<ArgumentSpec>, T> basicDelegate(
    constructor: (ArgumentSpec?) -> U
): BasicDelegateProvider<U, T> = object : BasicDelegateProvider<U, T> {

  override fun invoke(block: U.() -> Unit): DelegateProvider<T> {
    TODO("not implemented")
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> {
    TODO("not implemented")
  }

  override fun invoke(args: ArgumentSpec, block: (U.() -> Unit)?): DelegateProvider<T> {
    TODO("not implemented")
  }

}
