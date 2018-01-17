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

typealias BooleanProperty = ScalarDelegate.NoArg<BooleanDelegate<ArgBuilder>, BooleanStub>
typealias OptionallyConfiguredBooleanProperty<A> = BooleanDelegate.OptionallyConfigured<A>
typealias ConfiguredBooleanProperty<A> = ScalarDelegate.Configured<BooleanDelegate<A>, BooleanStub, A>

typealias BooleanProvider = NullableStubProvider<ScalarDelegate.NoArg<BooleanDelegate<ArgBuilder>, BooleanStub>,
    ScalarDelegate.NoArg.Nullable<BooleanDelegate<ArgBuilder>, Boolean>>

typealias OptionallyConfiguredBooleanProvider<A> = NullableStubProvider<
    BooleanDelegate.OptionallyConfigured<A>,
    BooleanDelegate.OptionallyConfigured.Nullable<A>>

typealias ConfiguredBooleanProvider<A> = NullableStubProvider<
    ScalarDelegate.Configured<BooleanDelegate<A>, BooleanStub, A>,
    ScalarDelegate.Configured.Nullable<BooleanDelegate<A>, A, Boolean>>

interface BooleanDelegate<out A : ArgumentSpec> : GraphqlDslBuilder<A> {

  var default: Boolean

  interface OptionallyConfigured<A : ArgumentSpec>
    : ScalarDelegate<BooleanStub>,
      ScalarDelegate.Configured<BooleanDelegate<A>, BooleanStub, A> {
    operator fun invoke(block: BooleanDelegate<ArgBuilder>.() -> Unit): ScalarDelegate<BooleanStub>

    interface Nullable<A : ArgumentSpec>
      : DelegateProvider<Boolean?>,
        ScalarDelegate.Configured.Nullable<BooleanDelegate<A>, A, Boolean> {
      operator fun invoke(block: BooleanDelegate<ArgBuilder>.() -> Unit): DelegateProvider<Boolean?>
    }
  }

  companion object {

    fun stub(): BooleanProvider = object : BooleanProvider {
      override fun provideDelegate(inst: QSchemaType, property: KProperty<*>)
          : Stub<ScalarDelegate.NoArg<BooleanDelegate<ArgBuilder>, BooleanStub>> =
          Stub.stub(BooleanNoArgImpl(property.name))

      override fun asNullable(): StubProvider<ScalarDelegate.NoArg.Nullable<BooleanDelegate<ArgBuilder>, Boolean>> =
          StubProvider.provide { _, kProperty ->
            BooleanNoArgImpl(kProperty.name).asNullable()
          }
    }

    fun <A : ArgumentSpec> optionallyConfigured(): OptionallyConfiguredBooleanProvider<A> =
        object : OptionallyConfiguredBooleanProvider<A> {

          override fun asNullable() = provide { _, kProperty ->
            OptionalBooleanImpl<A>(kProperty.name).asNullable()
          }

          override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
              Stub.stub(OptionalBooleanImpl<A>(property.name))
        }

    fun <A : ArgumentSpec> configured(): ConfiguredBooleanProvider<A> =
        object : ConfiguredBooleanProvider<A> {
          override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
              Stub.stub(ConfiguredBooleanImpl<A>(property.name))

          override fun asNullable() = provide { _, kProperty ->
            ConfiguredBooleanImpl<A>(kProperty.name).asNullable()
          }
        }
  }
}


private class BooleanNoArgImpl(val propertyName: String) : ScalarDelegate.NoArg<BooleanDelegate<ArgBuilder>, BooleanStub> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanStub =
      ScalarPreDelegate.PreBoolean(ArgBuilder())
          .toDelegate(propertyName)
          .bindToContext(inst)

  override fun invoke(arguments: ArgBuilder, block: BooleanDelegate<ArgBuilder>.() -> Unit) =
      ScalarPreDelegate.PreBoolean(arguments)
          .apply(block)
          .toDelegate(propertyName)
          .asProvider()

  internal
  fun asNullable(): ScalarDelegate.NoArg.Nullable<BooleanDelegate<ArgBuilder>, Boolean> =

      object : ScalarDelegate.NoArg.Nullable<BooleanDelegate<ArgBuilder>, Boolean> {

        override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<Boolean?> =
            ScalarPreDelegate.PreBoolean(ArgBuilder())
                .toDelegate(propertyName)
                .wrapAsNullable()
                .bindingWith(inst)

        override fun invoke(arguments: ArgBuilder, block: BooleanDelegate<ArgBuilder>.() -> Unit) =
            ScalarPreDelegate.PreBoolean(arguments)
                .apply(block)
                .toDelegate(propertyName)
                .wrapAsNullable()
                .delegatingTo()
      }
}

private class OptionalBooleanImpl<A : ArgumentSpec>(val propertyName: String) : BooleanDelegate.OptionallyConfigured<A> {

  override fun invoke(arguments: A, block: BooleanDelegate<A>.() -> Unit) =
      new(propertyName, arguments, block)

  override fun invoke(block: BooleanDelegate<ArgBuilder>.() -> Unit) =
      new(propertyName, ArgBuilder(), block)

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
      ScalarPreDelegate
          .PreBoolean(ArgBuilder())
          .toDelegate(propertyName)
          .bindToContext(inst)

  fun asNullable() = object : BooleanDelegate.OptionallyConfigured.Nullable<A> {

    private val propertyName = this@OptionalBooleanImpl.propertyName

    override fun invoke(block: BooleanDelegate<ArgBuilder>.() -> Unit): DelegateProvider<Boolean?> =
        ScalarPreDelegate
            .PreBoolean(ArgBuilder())
            .apply(block)
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
        ScalarPreDelegate
            .PreBoolean(ArgBuilder())
            .toDelegate(propertyName)
            .bindToContext(inst)
            .wrapAsNullable()

    override fun invoke(arguments: A, block: BooleanDelegate<A>.() -> Unit) =
        ScalarPreDelegate
            .PreBoolean(ArgBuilder())
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()

  }
}

private class ConfiguredBooleanImpl<A : ArgumentSpec>(val propertyName: String) : ScalarDelegate.Configured<BooleanDelegate<A>, BooleanStub, A> {

  override fun invoke(arguments: A, block: BooleanDelegate<A>.() -> Unit): ScalarDelegate<BooleanStub> =
      ScalarPreDelegate.PreBoolean(arguments)
          .apply(block)
          .toDelegate(propertyName)
          .asProvider()

  fun asNullable(): ScalarDelegate.Configured.Nullable<BooleanDelegate<A>, A, Boolean> =
      object : ScalarDelegate.Configured.Nullable<BooleanDelegate<A>, A, Boolean> {

        override fun invoke(arguments: A, block: BooleanDelegate<A>.() -> Unit): DelegateProvider<Boolean?> =
            ScalarPreDelegate.PreBoolean(arguments)
                .apply(block)
                .toDelegate(propertyName)
                .wrapAsNullable()
                .delegatingTo()

      }
}

private fun <A : ArgumentSpec> new(
    propertyName: String,
    args: A,
    block: BooleanDelegate<A>.() -> Unit = empty()
) = ScalarPreDelegate.PreBoolean(args)
    .apply(block)
    .toDelegate(propertyName)
    .asProvider()

