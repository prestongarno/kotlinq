/*
 * Copyright (C) 2018 Preston Garno
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

package com.prestongarno.kotlinq.core.properties.delegates

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.PreDelegate
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import kotlin.reflect.KProperty

/**
 * Base interface for instance property delegation.
 *
 * TODO @prestongarno should maybe use type intersections with GraphQlPropertyPreDelegate to remove the [DelegateProvider] nested interfaces
 */
interface DelegateProvider<out T : Any?> {

  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<T>

  interface NoArgDelegate<out U : GraphqlDslBuilder<ArgBuilder>, out T>
    : NoArg<U, T>,
      DelegateProvider<T>

  companion object {

    internal
    fun <T> delegateProvider(constructor: (QModel<*>, KProperty<*>) -> GraphqlPropertyDelegate<T>): InternalDelegateProvider<T> =
        object : InternalDelegateProvider<T> {
          override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) = constructor(inst, property)
        }
  }
}

internal
interface InternalDelegateProvider<out T : Any?> : DelegateProvider<T> {
  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphqlPropertyDelegate<T>

  fun asListDelegate(): InternalDelegateProvider<List<T>> =
      delegateProvider { qModel, kProperty -> provideDelegate(qModel, kProperty).asList() }

  fun asNullableDelegate(): InternalDelegateProvider<T?> =
      delegateProvider { model, prop -> provideDelegate(model, prop).asNullable() }
}

interface Configured<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, out T> {
  operator fun invoke(args: A, block: (U.() -> Unit)? = null): DelegateProvider<T>
}

interface ConfiguredBlock<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, out T> {
  operator fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T>
}

interface NoArg<out U : GraphqlDslBuilder<ArgBuilder>, out T> {
  operator fun invoke(args: ArgBuilder = ArgBuilder(), block: (U.() -> Unit)? = null): DelegateProvider<T>
}

interface NoArgBlock<out U : GraphqlDslBuilder<ArgBuilder>, out T> {
  operator fun invoke(args: ArgBuilder = ArgBuilder(), block: U.() -> Unit): DelegateProvider<T>
}

internal
fun <U : PreDelegate<T, ArgBuilder>, T> noArgBlock(
    qproperty: GraphQlProperty,
    constructor: (ArgBuilder) -> U,
    onDelegate: (ArgBuilder, U.() -> Unit) -> InternalDelegateProvider<T> = { args, block ->
      DelegateProvider.delegateProvider { qModel, _ ->
        constructor(args)
            .apply(block)
            .toDelegate(qproperty)
            .bindToContext(qModel)
      }
    }
): NoArgBlock<U, T> = NoArgImpl(qproperty, constructor, onDelegate)

internal fun <U : PreDelegate<T, A>, A : ArgumentSpec, T> configuredBlock(qproperty: GraphQlProperty, constructor: (A) -> U,
    onDelegate: (A, U.() -> Unit) -> DelegateProvider<T> = { args, block ->
      delegateProvider { qmodel, _ -> constructor(args).apply(block).toDelegate(qproperty).bindToContext(qmodel) }
    }): ConfiguredBlock<U, A, T> = ConfiguredBlockImpl(qproperty, constructor, onDelegate)

internal
class NoArgImpl<out U : PreDelegate<T, ArgBuilder>, out T>(
    private val qproperty: GraphQlProperty,
    private val constructor: (ArgBuilder) -> U,
    private val onDelegate: (ArgBuilder, U.() -> Unit) -> InternalDelegateProvider<T>
) : NoArgBlock<U, T> {

  fun asNullable(): NoArgBlock<U, T?> =
      noArgBlock(qproperty, constructor) { argBuilder, block ->
        DelegateProvider.delegateProvider { qModel, _ ->
          constructor(argBuilder).apply(block).toDelegate(qproperty).asNullable().bindToContext(qModel)
        }
      }

  override fun invoke(args: ArgBuilder, block: U.() -> Unit): DelegateProvider<T> = onDelegate(args, block)
}

internal class ConfiguredBlockImpl<out U : PreDelegate<T, A>, A : ArgumentSpec, out T>(
    private val qproperty: GraphQlProperty,
    private val constructor: (A) -> U,
    private val onDelegate: (A, U.() -> Unit) -> DelegateProvider<T>
) : ConfiguredBlock<U, A, T> {

  override fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T> = onDelegate(args, block)

  fun asNullable(): ConfiguredBlock<U, A, T?> = configuredBlock(qproperty, constructor) { args, block ->
    delegateProvider { model, _ -> constructor(args).apply(block).toDelegate(qproperty).asNullable().bindToContext(model) }
  }
}
