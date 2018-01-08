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

typealias IntProvider = NullableStubProvider<ScalarDelegate.NoArg<IntDelegate<ArgBuilder>, IntStub>,
    ScalarDelegate.NoArg.Nullable<IntDelegate<ArgBuilder>, Int>>

typealias OptionallyConfiguredIntProvider<A> = NullableStubProvider<
    IntDelegate.OptionallyConfigured<A>,
    IntDelegate.OptionallyConfigured.Nullable<A>>

typealias ConfiguredIntProvider<A> = NullableStubProvider<
    ScalarDelegate.Configured<IntDelegate<A>, IntStub, A>,
    ScalarDelegate.Configured.Nullable<IntDelegate<A>, A, Int>>

interface IntDelegate<A : ArgumentSpec> : GraphqlDslBuilder<A> {

  var default: Int

  interface OptionallyConfigured<A : ArgumentSpec>
    : ScalarDelegate<IntStub>,
      ScalarDelegate.Configured<IntDelegate<A>, IntStub, A> {
    operator fun invoke(block: IntDelegate<ArgBuilder>.() -> Unit): ScalarDelegate<IntStub>

    interface Nullable<A : ArgumentSpec>
      : DelegateProvider<Int?>,
        ScalarDelegate.Configured.Nullable<IntDelegate<A>, A, Int> {
      operator fun invoke(block: IntDelegate<ArgBuilder>.() -> Unit): DelegateProvider<Int?>
    }
  }

  companion object {

    fun stub(): IntProvider = object : IntProvider {
      override fun provideDelegate(inst: QSchemaType, property: KProperty<*>)
          : Stub<ScalarDelegate.NoArg<IntDelegate<ArgBuilder>, IntStub>> =
          Stub.stub(IntNoArgImpl(property.name))

      override fun asNullable(): StubProvider<ScalarDelegate.NoArg.Nullable<IntDelegate<ArgBuilder>, Int>> =
          StubProvider.provide { _, kProperty ->
            IntNoArgImpl(kProperty.name).asNullable()
          }
    }

    fun <A : ArgumentSpec> optionallyConfigured(): OptionallyConfiguredIntProvider<A> =
        object : OptionallyConfiguredIntProvider<A> {

          override fun asNullable() = provide { _, kProperty ->
            OptionalIntImpl<A>(kProperty.name).asNullable()
          }

          override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
              Stub.stub(OptionalIntImpl<A>(property.name))
        }

    fun <A : ArgumentSpec> configured(): ConfiguredIntProvider<A> =
        object : ConfiguredIntProvider<A> {
          override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
              Stub.stub(ConfiguredIntImpl<A>(property.name))

          override fun asNullable() = provide { _, kProperty ->
            ConfiguredIntImpl<A>(kProperty.name).asNullable()
          }
        }
  }
}


private class IntNoArgImpl(val propertyName: String) : ScalarDelegate.NoArg<IntDelegate<ArgBuilder>, IntStub> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntStub =
      ScalarPreDelegate.PreInt(ArgBuilder())
          .toDelegate(propertyName)
          .bindToContext(inst)

  override fun invoke(arguments: ArgBuilder, block: IntDelegate<ArgBuilder>.() -> Unit) =
      ScalarPreDelegate.PreInt(arguments)
          .apply(block)
          .toDelegate(propertyName)
          .asProvider()

  internal
  fun asNullable(): ScalarDelegate.NoArg.Nullable<IntDelegate<ArgBuilder>, Int> =

      object : ScalarDelegate.NoArg.Nullable<IntDelegate<ArgBuilder>, Int> {

        override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<Int?> =
            ScalarPreDelegate.PreInt(ArgBuilder())
                .toDelegate(propertyName)
                .wrapAsNullable()
                .bindingWith(inst)

        override fun invoke(arguments: ArgBuilder, block: IntDelegate<ArgBuilder>.() -> Unit) =
            ScalarPreDelegate.PreInt(arguments)
                .apply(block)
                .toDelegate(propertyName)
                .wrapAsNullable()
                .delegatingTo()
      }
}

private class OptionalIntImpl<A : ArgumentSpec>(val propertyName: String) : IntDelegate.OptionallyConfigured<A> {

  override fun invoke(arguments: A, block: IntDelegate<A>.() -> Unit) =
      new(propertyName, arguments, block)

  override fun invoke(block: IntDelegate<ArgBuilder>.() -> Unit) =
      new(propertyName, ArgBuilder(), block)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
      ScalarPreDelegate
          .PreInt(ArgBuilder())
          .toDelegate(propertyName)
          .bindToContext(inst)

  fun asNullable() = object : IntDelegate.OptionallyConfigured.Nullable<A> {

    private val propertyName = this@OptionalIntImpl.propertyName

    override fun invoke(block: IntDelegate<ArgBuilder>.() -> Unit): DelegateProvider<Int?> =
        ScalarPreDelegate
            .PreInt(ArgBuilder())
            .apply(block)
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
        ScalarPreDelegate
            .PreInt(ArgBuilder())
            .toDelegate(propertyName)
            .bindToContext(inst)
            .wrapAsNullable()

    override fun invoke(arguments: A, block: IntDelegate<A>.() -> Unit) =
        ScalarPreDelegate
            .PreInt(ArgBuilder())
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()

  }
}

private class ConfiguredIntImpl<A : ArgumentSpec>(val propertyName: String) : ScalarDelegate.Configured<IntDelegate<A>, IntStub, A> {

  override fun invoke(arguments: A, block: IntDelegate<A>.() -> Unit): ScalarDelegate<IntStub> =
      ScalarPreDelegate.PreInt(arguments)
          .apply(block)
          .toDelegate(propertyName)
          .asProvider()

  fun asNullable(): ScalarDelegate.Configured.Nullable<IntDelegate<A>, A, Int> =
      object : ScalarDelegate.Configured.Nullable<IntDelegate<A>, A, Int> {

        override fun invoke(arguments: A, block: IntDelegate<A>.() -> Unit): DelegateProvider<Int?> =
            ScalarPreDelegate.PreInt(arguments)
                .apply(block)
                .toDelegate(propertyName)
                .wrapAsNullable()
                .delegatingTo()

      }
}

private fun <A : ArgumentSpec> new(
    propertyName: String,
    args: A,
    block: IntDelegate<A>.() -> Unit = empty()
) = ScalarPreDelegate.PreInt(args)
    .apply(block)
    .toDelegate(propertyName)
    .asProvider()

