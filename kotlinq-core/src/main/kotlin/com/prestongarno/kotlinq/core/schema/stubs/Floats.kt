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

package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.NullableStubProvider
import com.prestongarno.kotlinq.core.api.Stub
import com.prestongarno.kotlinq.core.api.StubProvider
import com.prestongarno.kotlinq.core.api.StubProvider.Companion.provide
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import kotlin.reflect.KProperty

typealias FloatProvider = NullableStubProvider<ScalarDelegate.NoArg<FloatDelegate<ArgBuilder>, FloatStub>,
    ScalarDelegate.NoArg.Nullable<FloatDelegate<ArgBuilder>, Float>>

typealias OptionallyConfiguredFloatProvider<A> = NullableStubProvider<
    FloatDelegate.OptionallyConfigured<A>,
    FloatDelegate.OptionallyConfigured.Nullable<A>>

typealias ConfiguredFloatProvider<A> = NullableStubProvider<
    ScalarDelegate.Configured<FloatDelegate<A>, FloatStub, A>,
    ScalarDelegate.Configured.Nullable<FloatDelegate<A>, A, Float>>

interface FloatDelegate<out A : ArgumentSpec> : GraphqlDslBuilder<A> {

  var default: Float

  interface OptionallyConfigured<A : ArgumentSpec>
    : ScalarDelegate<FloatStub>,
      ScalarDelegate.Configured<FloatDelegate<A>, FloatStub, A> {
    operator fun invoke(block: FloatDelegate<ArgBuilder>.() -> Unit): ScalarDelegate<FloatStub>

    interface Nullable<A : ArgumentSpec>
      : DelegateProvider<Float?>,
        ScalarDelegate.Configured.Nullable<FloatDelegate<A>, A, Float> {
      operator fun invoke(block: FloatDelegate<ArgBuilder>.() -> Unit): DelegateProvider<Float?>
    }
  }

  companion object {

    fun stub(): FloatProvider = object : FloatProvider {
      override fun provideDelegate(inst: QSchemaType, property: KProperty<*>)
          : Stub<ScalarDelegate.NoArg<FloatDelegate<ArgBuilder>, FloatStub>> =
          Stub.stub(FloatNoArgImpl(property.name))

      override fun asNullable(): StubProvider<ScalarDelegate.NoArg.Nullable<FloatDelegate<ArgBuilder>, Float>> =
          StubProvider.provide { _, kProperty ->
            FloatNoArgImpl(kProperty.name).asNullable()
          }
    }

    fun <A : ArgumentSpec> optionallyConfigured(): OptionallyConfiguredFloatProvider<A> =
        object : OptionallyConfiguredFloatProvider<A> {

          override fun asNullable() = provide { _, kProperty ->
            OptionalFloatImpl<A>(kProperty.name).asNullable()
          }

          override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
              Stub.stub(OptionalFloatImpl<A>(property.name))
        }

    fun <A : ArgumentSpec> configured(): ConfiguredFloatProvider<A> =
        object : ConfiguredFloatProvider<A> {
          override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
              Stub.stub(ConfiguredFloatImpl<A>(property.name))

          override fun asNullable() = provide { _, kProperty ->
            ConfiguredFloatImpl<A>(kProperty.name).asNullable()
          }
        }
  }
}


private class FloatNoArgImpl(val propertyName: String) : ScalarDelegate.NoArg<FloatDelegate<ArgBuilder>, FloatStub> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): FloatStub =
      ScalarPreDelegate.PreFloat(ArgBuilder())
          .toDelegate(propertyName)
          .bindToContext(inst)

  override fun invoke(arguments: ArgBuilder, block: FloatDelegate<ArgBuilder>.() -> Unit) =
      ScalarPreDelegate.PreFloat(arguments)
          .apply(block)
          .toDelegate(propertyName)
          .asProvider()

  internal
  fun asNullable(): ScalarDelegate.NoArg.Nullable<FloatDelegate<ArgBuilder>, Float> =

      object : ScalarDelegate.NoArg.Nullable<FloatDelegate<ArgBuilder>, Float> {

        override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<Float?> =
            ScalarPreDelegate.PreFloat(ArgBuilder())
                .toDelegate(propertyName)
                .wrapAsNullable()
                .bindingWith(inst)

        override fun invoke(arguments: ArgBuilder, block: FloatDelegate<ArgBuilder>.() -> Unit) =
            ScalarPreDelegate.PreFloat(arguments)
                .apply(block)
                .toDelegate(propertyName)
                .wrapAsNullable()
                .delegatingTo()
      }
}

private class OptionalFloatImpl<A : ArgumentSpec>(val propertyName: String) : FloatDelegate.OptionallyConfigured<A> {

  override fun invoke(arguments: A, block: FloatDelegate<A>.() -> Unit) =
      new(propertyName, arguments, block)

  override fun invoke(block: FloatDelegate<ArgBuilder>.() -> Unit) =
      new(propertyName, ArgBuilder(), block)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
      ScalarPreDelegate
          .PreFloat(ArgBuilder())
          .toDelegate(propertyName)
          .bindToContext(inst)

  fun asNullable() = object : FloatDelegate.OptionallyConfigured.Nullable<A> {

    private val propertyName = this@OptionalFloatImpl.propertyName

    override fun invoke(block: FloatDelegate<ArgBuilder>.() -> Unit): DelegateProvider<Float?> =
        ScalarPreDelegate
            .PreFloat(ArgBuilder())
            .apply(block)
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
        ScalarPreDelegate
            .PreFloat(ArgBuilder())
            .toDelegate(propertyName)
            .bindToContext(inst)
            .wrapAsNullable()

    override fun invoke(arguments: A, block: FloatDelegate<A>.() -> Unit) =
        ScalarPreDelegate
            .PreFloat(ArgBuilder())
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()

  }
}

private class ConfiguredFloatImpl<A : ArgumentSpec>(val propertyName: String) : ScalarDelegate.Configured<FloatDelegate<A>, FloatStub, A> {

  override fun invoke(arguments: A, block: FloatDelegate<A>.() -> Unit): ScalarDelegate<FloatStub> =
      ScalarPreDelegate.PreFloat(arguments)
          .apply(block)
          .toDelegate(propertyName)
          .asProvider()

  fun asNullable(): ScalarDelegate.Configured.Nullable<FloatDelegate<A>, A, Float> =
      object : ScalarDelegate.Configured.Nullable<FloatDelegate<A>, A, Float> {

        override fun invoke(arguments: A, block: FloatDelegate<A>.() -> Unit): DelegateProvider<Float?> =
            ScalarPreDelegate.PreFloat(arguments)
                .apply(block)
                .toDelegate(propertyName)
                .wrapAsNullable()
                .delegatingTo()

      }
}

private fun <A : ArgumentSpec> new(
    propertyName: String,
    args: A,
    block: FloatDelegate<A>.() -> Unit = empty()
) = ScalarPreDelegate.PreFloat(args)
    .apply(block)
    .toDelegate(propertyName)
    .asProvider()

