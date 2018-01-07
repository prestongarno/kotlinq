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

@file:Suppress("unused")

package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.CustomScalarStubImpl
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyContext
import com.prestongarno.kotlinq.core.properties.contextBuilder
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.schema.CustomScalar
import java.io.InputStream
import kotlin.reflect.KProperty

interface CustomScalarStub<T : CustomScalar, V, A : ArgumentSpec> : GraphqlDslBuilder<A> {

  var default: V?

  interface CustomScalarDelegate<T : CustomScalar>
    : DelegateProvider<String> {

    interface Nullable<T : CustomScalar> : DelegateProvider<String?>
  }

  interface NoArg<T : CustomScalar> : CustomScalarDelegate<T> {
    operator fun <V : Any> invoke(
        arguments: ArgBuilder = ArgBuilder(),
        mapper: Mapper<V>,
        block: CustomScalarStub<T, V, ArgBuilder>.() -> Unit = empty()
    ): DelegateProvider<V>

    interface Nullable<T : CustomScalar> : CustomScalarDelegate.Nullable<T> {
      operator fun <V : Any> invoke(arguments: ArgBuilder = ArgBuilder(), mapper: Mapper<V>, block: CustomScalarStub<T, V, ArgBuilder>.() -> Unit = empty()): DelegateProvider<V?>
    }
  }

  interface OptionallyConfigured<T : CustomScalar, A : ArgumentSpec>
    : CustomScalarDelegate<T>,
      NoArg<T>,
      Configured<T, A> {

    interface Nullable<T : CustomScalar, A : ArgumentSpec> : CustomScalarDelegate.Nullable<T>, NoArg.Nullable<T>, Configured.Nullable<T, A>
  }

  interface Configured<T : CustomScalar, A : ArgumentSpec> {
    operator fun <V : Any> invoke(
        arguments: A,
        mapper: Mapper<V>,
        block: CustomScalarStub<T, V, A>.() -> Unit = empty()
    ): DelegateProvider<V>

    operator fun invoke(
        arguments: A,
        block: CustomScalarStub<T, String, A>.() -> Unit
    ): DelegateProvider<String> =
        invoke(arguments, Mapper.IDENTITY, block)

    interface Nullable<T : CustomScalar, A : ArgumentSpec> {
      operator fun <V : Any> invoke(arguments: A, mapper: Mapper<V>, block: CustomScalarStub<T, V, A>.() -> Unit = empty()): DelegateProvider<V?>
      operator fun invoke(arguments: A, block: CustomScalarStub<T, String, A>.() -> Unit): DelegateProvider<String?> = invoke(arguments, Mapper.IDENTITY, block)
    }
  }

  sealed class Mapper<T : Any?> {
    class StringMapper<T>(impl: (String) -> T) : Mapper<T>(), (String) -> T by impl
    class InputStreamMapper<T>(impl: (InputStream) -> T) : Mapper<T>(), (InputStream) -> T by impl

    companion object {
      val IDENTITY: StringMapper<String> = StringMapper { it }
      fun <T> fromString(impl: (String) -> T) = StringMapper(impl)
      fun <T> fromInputStream(impl: (InputStream) -> T) = InputStreamMapper(impl)
    }
  }

  companion object {

    internal
    fun <T : CustomScalar> noArg(): GraphQlPropertyContext.Companion.Builder<NoArg<T>> =
        new<T, ArgBuilder>()

    internal
    fun <T : CustomScalar, A : ArgumentSpec> optionallyConfigured()
        : GraphQlPropertyContext.Companion.Builder<OptionallyConfigured<T, A>> =
        new()

    internal
    fun <T : CustomScalar, A : ArgumentSpec> configured()
        : GraphQlPropertyContext.Companion.Builder<Configured<T, A>> =
        new()

    private fun <T : CustomScalar, A : ArgumentSpec> new() =
        contextBuilder { prop -> CustomScalarPreDelegateImpl<T, A>(prop) }

    internal
    object Nullables {

      internal
      fun <T : CustomScalar> noArg()
          : GraphQlPropertyContext.Companion.Builder<NoArg.Nullable<T>> =
          new<T, ArgBuilder>()

      internal
      fun <T : CustomScalar, A : ArgumentSpec> optionallyConfigured()
          : GraphQlPropertyContext.Companion.Builder<OptionallyConfigured.Nullable<T, A>> =
          new()

      internal
      fun <T : CustomScalar, A : ArgumentSpec> configured()
          : GraphQlPropertyContext.Companion.Builder<Configured.Nullable<T, A>> =
          new()

      private fun <T : CustomScalar, A : ArgumentSpec> new() =
          contextBuilder { prop -> NullableCustomScalarPreDelegateImpl<T, A>(prop) }

    }
  }
}

private class CustomScalarPreDelegateImpl<T : CustomScalar, A : ArgumentSpec>(
    val qproperty: GraphQlProperty
) : CustomScalarStub.NoArg<T>, CustomScalarStub.OptionallyConfigured<T, A> {

  override fun <V : Any> invoke(
      arguments: A,
      mapper: CustomScalarStub.Mapper<V>,
      block: CustomScalarStub<T, V, A>.() -> Unit
  ): DelegateProvider<V> = customProvider(mapper, arguments, qproperty, block)

  override fun <V : Any> invoke(
      arguments: ArgBuilder,
      mapper: CustomScalarStub.Mapper<V>,
      block: CustomScalarStub<T, V, ArgBuilder>.() -> Unit
  ): DelegateProvider<V> = customProvider(mapper, arguments, qproperty, block)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<String> =
      CustomScalarStubImpl<T, String, ArgBuilder>(CustomScalarStub.Mapper.IDENTITY, ArgBuilder())
          .toDelegate(qproperty)
          .bindToContext(inst)

}

private class NullableCustomScalarPreDelegateImpl<T : CustomScalar, A : ArgumentSpec>(
    val qproperty: GraphQlProperty
) : CustomScalarStub.NoArg.Nullable<T>, CustomScalarStub.OptionallyConfigured.Nullable<T, A> {

  override fun <V : Any> invoke(
      arguments: A,
      mapper: CustomScalarStub.Mapper<V>,
      block: CustomScalarStub<T, V, A>.() -> Unit
  ): DelegateProvider<V?> =
      nullableCustomProvider(mapper, arguments, qproperty, block)


  override fun <V : Any> invoke(
      arguments: ArgBuilder,
      mapper: CustomScalarStub.Mapper<V>,
      block: CustomScalarStub<T, V, ArgBuilder>.() -> Unit
  ): DelegateProvider<V?> =
      nullableCustomProvider(mapper, arguments, qproperty, block)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<String?> =
      CustomScalarStubImpl<T, String, ArgBuilder>(CustomScalarStub.Mapper.IDENTITY, ArgBuilder())
          .toDelegate(qproperty)
          .asNullable()
          .bindToContext(inst)

}

private
fun <T : CustomScalar, A : ArgumentSpec, V : Any> customProvider(
    mapper: CustomScalarStub.Mapper<V>,
    arguments: A?,
    qproperty: GraphQlProperty,
    block: CustomScalarStub<T, V, A>.() -> Unit
): DelegateProvider<V> = delegateProvider { qmodel, _ ->
  CustomScalarStubImpl<T, V, A>(mapper, arguments)
      .apply(block)
      .toDelegate(qproperty)
      .bindToContext(qmodel)
}

private
fun <T : CustomScalar, A : ArgumentSpec, V : Any> nullableCustomProvider(
    mapper: CustomScalarStub.Mapper<V>,
    arguments: A?,
    qproperty: GraphQlProperty,
    block: CustomScalarStub<T, V, A>.() -> Unit
): DelegateProvider<V?> = delegateProvider { qmodel, _ ->
  CustomScalarStubImpl<T, V, A>(mapper, arguments)
      .apply(block)
      .toDelegate(qproperty)
      .asNullable()
      .bindToContext(qmodel)
}
