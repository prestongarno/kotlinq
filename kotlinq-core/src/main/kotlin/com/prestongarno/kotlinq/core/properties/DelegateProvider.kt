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

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.PreDelegate
import com.prestongarno.kotlinq.core.adapters.QField
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.properties.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyDelegate.Companion.configuredBlock
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyDelegate.Companion.noArgBlock
import kotlin.reflect.KProperty

/**
 * Base interface for instance property delegation.
 *
 * TODO @prestongarno should maybe use type intersections with [GraphQlPropertyDelegate] to remove the [DelegateProvider] nested interfaces
 */
interface DelegateProvider<out T : Any?> {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>

  interface Configured<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T>
    : GraphQlPropertyDelegate.Configured<U, A, T>,
      DelegateProvider<T> {

    interface Nullable<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T> : Configured<U, A, T?>
  }

  interface ConfiguredBlock<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T>
    : GraphQlPropertyDelegate.ConfiguredBlock<U, A, T>,
      DelegateProvider<T>

  interface NoArg<out U : GraphqlDslBuilder<ArgBuilder>, T>
    : GraphQlPropertyDelegate.NoArg<U, T>,
      DelegateProvider<T>

  interface NoArgBlock<out U : GraphqlDslBuilder<ArgBuilder>, T>
    : GraphQlPropertyDelegate.NoArgBlock<U, T>,
      DelegateProvider<T>

  companion object {
    internal fun <T> delegateProvider(constructor: (QModel<*>, KProperty<*>) -> QField<T>) = object : DelegateProvider<T> {
      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = constructor(inst, property)
    }
  }
}

/**
 * Resulting static schema property base type. Sub-interfaces allow for different scenarios (i.e. if fragments should be given,
 * then use [GraphQlPropertyDelegate.Configured] to force a DSL block which is where fragments are defined)
 *
 * TODO @prestongarno maybe remove the "T" argument, and have that taken care of by [com.prestongarno.kotlinq.core.adapters.PreDelegate] argument
 */
interface GraphQlPropertyDelegate<out T : Any?> {

  interface Configured<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T> : GraphQlPropertyDelegate<T> {
    operator fun invoke(args: A, block: (U.() -> Unit)? = null): DelegateProvider<T>
  }

  interface ConfiguredBlock<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T> : GraphQlPropertyDelegate<T> {
    operator fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T>
  }

  interface NoArg<out U : GraphqlDslBuilder<ArgBuilder>, T> : GraphQlPropertyDelegate<T> {
    operator fun invoke(args: ArgBuilder = ArgBuilder(), block: (U.() -> Unit)? = null): DelegateProvider<T>
  }

  interface NoArgBlock<out U : GraphqlDslBuilder<ArgBuilder>, T> : GraphQlPropertyDelegate<T> {
    operator fun invoke(args: ArgBuilder = ArgBuilder(), block: U.() -> Unit): DelegateProvider<T>
  }

  companion object {

    internal
    fun <U : PreDelegate<T, ArgBuilder>, T> noArgBlock(
        qproperty: GraphQlProperty,
        constructor: (ArgBuilder) -> U,
        onDelegate: (ArgBuilder, U.() -> Unit) -> DelegateProvider<T> = { args, block ->
          DelegateProvider.delegateProvider { qModel, kProperty ->
            constructor(args).apply(block).toDelegate(qproperty).bindToContext(qModel)
          }
        }
    ): NoArgBlock<U, T> =
        NoArgImpl(qproperty, constructor, onDelegate)

    internal fun <U : PreDelegate<T, A>, A : ArgumentSpec, T> configuredBlock(qproperty: GraphQlProperty, constructor: (A) -> U,
        onDelegate: (A, U.() -> Unit) -> DelegateProvider<T> = { args, block ->
          delegateProvider { qmodel, _ -> constructor(args).apply(block).toDelegate(qproperty).bindToContext(qmodel) }
        }): ConfiguredBlock<U, A, T> = ConfiguredBlockImpl(qproperty, constructor, onDelegate)
  }
}

internal
class NoArgImpl<out U : PreDelegate<T, ArgBuilder>, T>(
    private val qproperty: GraphQlProperty,
    private val constructor: (ArgBuilder) -> U,
    private val onDelegate: (ArgBuilder, U.() -> Unit) -> DelegateProvider<T>
) : GraphQlPropertyDelegate.NoArgBlock<U, T> {

  fun asNullable(): GraphQlPropertyDelegate.NoArgBlock<U, T?> =
      noArgBlock(qproperty, constructor) { argBuilder, block ->
        DelegateProvider.delegateProvider { qModel, kProperty ->
          constructor(argBuilder).apply(block).toDelegate(qproperty).asNullable().bindToContext(qModel)
        }
      }

  override fun invoke(args: ArgBuilder, block: U.() -> Unit): DelegateProvider<T> = onDelegate(args, block)
}

internal class ConfiguredBlockImpl<out U : PreDelegate<T, A>, A : ArgumentSpec, T>(
    private val qproperty: GraphQlProperty,
    private val constructor: (A) -> U,
    private val onDelegate: (A, U.() -> Unit) -> DelegateProvider<T>
) : GraphQlPropertyDelegate.ConfiguredBlock<U, A, T> {

  override fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T> = onDelegate(args, block)

  fun asNullable(): GraphQlPropertyDelegate.ConfiguredBlock<U, A, T?> = configuredBlock(qproperty, constructor) { args, block ->
    delegateProvider { model, property -> constructor(args).apply(block).toDelegate(qproperty).asNullable().bindToContext(model) }
  }
}
