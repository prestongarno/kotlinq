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
import com.prestongarno.kotlinq.core.adapters.PreDelegate
import com.prestongarno.kotlinq.core.adapters.QField
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.Stub
import kotlin.reflect.KProperty

internal
fun <D, N, RET : Any?>
    newConfigurableGraphqlProperty(
    constructor: () -> D,
    nullableCtor: () -> N,
    onDelegate: D.(QModel<*>) -> QField<RET>
): Stub<D>
    where D : GraphqlDslBuilder<*>,
          D : PreDelegate<*, RET>,
          N : D =

    object : GraphQLPropertyContext<D, RET>, Stub<D> {

      override val constructor: () -> D get() = constructor

      private val value by lazy { constructor() }

      override fun getValue(inst: QSchemaType, property: KProperty<*>): D = value

      private val nullable by lazy { Nullable(nullableCtor) }

      override fun asNullable(): Nullable = nullable

      private inner class Nullable(private val ctor: () -> N)
        : GraphQLPropertyContext<N, RET?> {
        override val constructor: () -> N get() = ctor
        override fun asNullable() = this
      }
    }

interface GraphQLPropertyContext<out D : GraphqlDslBuilder<*>, out RET : Any?> {
  // TODO overriders should be more restrictive of the type so DSL is still enabled
  fun asNullable(): GraphQLPropertyContext<D, RET?>

  val constructor: () -> D
}


interface DelegateProvider<out T : Any?> {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>
}

interface ConfiguredDelegateProvider<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, out T> {
  fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T>
}

interface ConfigurableDelegateProvider<
    out U : GraphqlDslBuilder<A>,
    A : ArgumentSpec,
    out T>
  : DelegateProvider<T> {
  fun invoke(args: A, block: (U.() -> Unit)?): DelegateProvider<T>
}

private
fun <T> delegateProvider(onDelegate: (QModel<*>) -> QField<T>): DelegateProvider<T> = object : DelegateProvider<T> {
  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = onDelegate(inst)
}

internal
fun <U, A, T> configurableDelegate(
    constructor: () -> U,
    onDsl: U.(A?) -> Unit,
    onDelegate: U.(QModel<*>) -> QField<T>
) where U : GraphqlDslBuilder<A>, A : ArgumentSpec =

    object : ConfigurableDelegateProvider<U, A, T> {
      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = constructor().onDelegate(inst)
      override fun invoke(args: A, block: (U.() -> Unit)?): DelegateProvider<T> = delegateProvider {
        constructor().apply { this@apply.onDsl(args) }.applyNotNull(block).onDelegate(it)
      }
    }

internal
fun <U, A, T> configuredDelegate(
    constructor: () -> U,
    onDsl: U.(A) -> Unit,
    onDelegate: U.(QModel<*>) -> QField<T>
) where U : GraphqlDslBuilder<A>, A : ArgumentSpec, T : Any? =

    object : ConfiguredDelegateProvider<U, A, T> {
      override fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T> = delegateProvider {
        constructor().apply { this@apply.onDsl(args) }.apply(block).onDelegate(it)
      }
    }
