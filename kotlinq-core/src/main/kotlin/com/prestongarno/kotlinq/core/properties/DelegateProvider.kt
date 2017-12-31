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
import kotlin.reflect.KProperty

/**
 * Base interface for instance property delegation.
 *
 * TODO @prestongarno should maybe use type intersections with [GraphQlDelegate] to remove the [DelegateProvider] nested interfaces
 */
interface DelegateProvider<out T : Any?> {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>

  interface Configurable<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T>
    : GraphQlDelegate.Configured<U, A, T>,
      DelegateProvider<T>

  interface ConfiguredBlock<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T>
    : GraphQlDelegate.ConfiguredBlock<U, A, T>,
      DelegateProvider<T>

  interface NoArg<out U : GraphqlDslBuilder<ArgBuilder>, T>
    : GraphQlDelegate.NoArg<U, T>,
      DelegateProvider<T>

  interface NoArgBlock<out U : GraphqlDslBuilder<ArgBuilder>, T>
    : GraphQlDelegate.NoArgBlock<U, T>,
      DelegateProvider<T>

  companion object {
    internal fun <T> delegateProvider(constructor: (QModel<*>, KProperty<*>) -> QField<T>) = object : DelegateProvider<T> {
      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> = constructor(inst, property)
    }
  }
}

/**
 * Resulting static schema property base type. Sub-interfaces allow for different scenarios (i.e. if fragments should be given,
 * then use [GraphQlDelegate.Configured] to force a DSL block which is where fragments are defined)
 *
 * TODO @prestongarno maybe remove the "T" argument, and have that taken care of by [com.prestongarno.kotlinq.core.adapters.PreDelegate] argument
 */
interface GraphQlDelegate<T> {

  fun asNullable(): GraphQlDelegate<T?>

  interface Configured<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T> : GraphQlDelegate<T> {
    operator fun invoke(args: A, block: (U.() -> Unit)? = null): DelegateProvider<T>

    override fun asNullable(): GraphQlDelegate.Configured<U, A, T?>
  }

  interface ConfiguredBlock<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, T> : GraphQlDelegate<T> {
    operator fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T>
    override fun asNullable(): GraphQlDelegate.ConfiguredBlock<U, A, T?>
  }

  interface NoArg<out U : GraphqlDslBuilder<ArgBuilder>, T> : GraphQlDelegate<T> {
    operator fun invoke(args: ArgBuilder = ArgBuilder(), block: (U.() -> Unit)? = null): DelegateProvider<T>
    override fun asNullable(): GraphQlDelegate.NoArg<U, T?>
  }

  interface NoArgBlock<out U : GraphqlDslBuilder<ArgBuilder>, T> : GraphQlDelegate<T> {
    operator fun invoke(args: ArgBuilder = ArgBuilder(), block: U.() -> Unit): DelegateProvider<T>
    override fun asNullable(): GraphQlDelegate.NoArgBlock<U, T?>
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
        object : NoArgBlock<U, T> {
          override fun asNullable(): NoArgBlock<U, T?> =
              noArgBlock(qproperty, constructor) { argBuilder, block ->
                DelegateProvider.delegateProvider { qModel, kProperty ->
                  constructor(argBuilder).apply(block).toDelegate(qproperty).asNullable().bindToContext(qModel)
                }
              }

          override fun invoke(args: ArgBuilder, block: U.() -> Unit): DelegateProvider<T> = onDelegate(args, block)
        }

    internal fun <U : PreDelegate<T, A>, A : ArgumentSpec, T> configuredBlock(qproperty: GraphQlProperty, constructor: (A) -> U,
        onDelegate: (A, U.() -> Unit) -> DelegateProvider<T> = { args, block ->
          delegateProvider { qmodel, _ ->
            constructor(args).apply(block).toDelegate(qproperty).bindToContext(qmodel)
          }
        }): ConfiguredBlock<U, A, T> =
        object : ConfiguredBlock<U, A, T> {

          override fun invoke(args: A, block: U.() -> Unit): DelegateProvider<T> = onDelegate(args, block)

          override fun asNullable(): ConfiguredBlock<U, A, T?> =
              configuredBlock(qproperty, constructor) { args, block ->
                delegateProvider { model, property ->
                  constructor(args).apply(block).toDelegate(qproperty).asNullable().bindToContext(model)
                }
              }

        }
  }
}
