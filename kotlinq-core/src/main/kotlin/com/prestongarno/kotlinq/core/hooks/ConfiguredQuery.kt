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

package com.prestongarno.kotlinq.core.hooks

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.DelegateProvider
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.SchemaStub
import com.prestongarno.kotlinq.core.adapters.QField
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import kotlin.reflect.KProperty

/**
 * Represents an intermediate object that enforces passing of an [ArgBuilder]
 * instance to the object. This is for GraphQL fields with one or more non-null
 * arguments specified in the schema
 *
 *
 * This interface enforces passing of arguments by not having the standard operator
 * function like [OptionalConfiguration.provideDelegate]
 *
 *
 * @param D : The type of SchemaStub which will provide a delegate type <T>
 * @param A : The type of ArgBuilder which configures on this field
 */
interface ConfiguredQuery<out D : DelegateProvider<*>, in A : ArgBuilder> : SchemaStub {

  operator fun invoke(arguments: A, scope: (D.() -> Unit)? = null): D

  companion object {
    fun <T : DelegateProvider<*>, A : ArgBuilder> new(
        constructor: (A) -> T
    ): ConfiguredQuery<T, A> = DefaultConfigurableQuery(constructor)
  }
}

/**
 * Represents an intermediate object that optionally allows invoking by passing
 * an [ArgBuilder] instance to the object. This is for GraphQL fields with
 * exactly zero non-null arguments specified for this field, but with one or
 * more arguments/parameters specified
 * @param D : The type of [DelegateProvider] which this field supplies
 * @param A : The type of ArgBuilder which configures on this field
 */
interface OptionalConfiguration<out D : DelegateProvider<T>, out T : Any?, in A : ArgBuilder> : SchemaStub {

  operator fun invoke(arguments: A, scope: (D.() -> Unit)? = null): D

  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>

  companion object {
    fun <D : DelegateProvider<T>, T : Any?, A : ArgBuilder> new(
        constructor: (A?) -> D
    ): OptionalConfiguration<D, T, A> = DefaultOptionalConfiguration(constructor)
  }
}

/**
 * Represents an intermediate object that optionally allows invoking by passing
 * an [ArgBuilder] instance to the object. This is for GraphQL fields with
 * exactly zero non-null arguments specified for this field, and zero arguments/parameters specified.
 * This interface exists to support adding arguments arbitrarily to GraphQL queries/mutations
 * @param D : The type of [DelegateProvider] which this field supplies
 */
interface NoArgConfig<out D : DelegateProvider<T>, out T : Any?> : SchemaStub {

  operator fun invoke(arguments: ArgBuilder? = ArgBuilder(), scope: (D.() -> Unit)? = null): D

  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      invoke().provideDelegate(inst, property)

  companion object {
    fun <D : DelegateProvider<T>, T : Any?> new(
        constructor: (ArgBuilder?) -> D
    ): NoArgConfig<D, T> = DefaultNoArgConfig(constructor)
  }
}

/**
 * Default [ConfiguredQuery] class for dynamic class-level delegation by schema stub types
 * @param T : The type of [DelegateProvider] that this function returns
 * @param A : The type of [ArgBuilder] that this DelegateProvider takes
 * @param constructor a function that constructs a new [DelegateProvider]
 */
private
class DefaultConfigurableQuery<out T : DelegateProvider<*>, in A : ArgBuilder>(
    private val constructor: (A) -> T
) : ConfiguredQuery<T, A> {

  override operator fun invoke(arguments: A, scope: (T.() -> Unit)?): T =
      constructor.invoke(arguments).applyNotNull(scope)
}

/**
 * Default [OptionalConfiguration] class for dynamic class-level delegation by schema stub types
 * @param T : The type of [DelegateProvider] that this function returns
 * @param A : The type of [ArgBuilder] that this DelegateProvider takes
 * @param constructor a function that constructs a new [DelegateProvider]
 */
private
class DefaultOptionalConfiguration<out D : DelegateProvider<T>, out T : Any?, in A : ArgBuilder>(
    private val constructor: (A?) -> D
) : OptionalConfiguration<D, T, A> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      constructor(null).provideDelegate(inst, property)

  override fun invoke(arguments: A, scope: (D.() -> Unit)?): D =
      constructor(arguments).applyNotNull(scope)
}

private
class DefaultNoArgConfig<out D : DelegateProvider<T>, out T : Any?>(
    private val constructor: (ArgBuilder?) -> D
) : NoArgConfig<D, T> {

  override fun invoke(arguments: ArgBuilder?, scope: (D.() -> Unit)?): D =
      constructor(arguments).applyNotNull(scope)
}
