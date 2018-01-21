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
import com.prestongarno.kotlinq.core.Model
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.NullableStubProvider
import com.prestongarno.kotlinq.core.api.Stub
import com.prestongarno.kotlinq.core.api.StubProvider
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import kotlin.reflect.KProperty

typealias StringProvider = NullableStubProvider<StringDelegate.NoArg, StringDelegate.NoArg.Nullable>

typealias ConfiguredStringProvider<A> = NullableStubProvider<
    ScalarDelegate.Configured<StringDelegate<A>, StringStub, A>,
    ScalarDelegate.Configured.Nullable<StringDelegate<A>, A, String>>

typealias OptionallyConfiguredStringProvider<A> = NullableStubProvider<
    StringDelegate.OptionallyConfigured<A>,
    StringDelegate.OptionallyConfigured.Nullable<A>>

interface StringDelegate<out A : ArgumentSpec> : GraphqlDslBuilder<A> {

  var default: String?

  interface NoArg : ScalarDelegate.NoArg<StringDelegate<ArgBuilder>, StringStub> {
    interface Nullable : ScalarDelegate.NoArg.Nullable<StringDelegate<ArgBuilder>, String>
  }

  interface OptionallyConfigured<A : ArgumentSpec>
    : ScalarDelegate<StringStub>,
      ScalarDelegate.Configured<StringDelegate<A>, StringStub, A> {
    operator fun invoke(block: StringDelegate<ArgBuilder>.() -> Unit): ScalarDelegate<StringStub>

    interface Nullable<A : ArgumentSpec>
      : DelegateProvider<String?>,
        ScalarDelegate.Configured.Nullable<StringDelegate<A>, A, String> {
      operator fun invoke(block: StringDelegate<ArgBuilder>.() -> Unit): DelegateProvider<String?>
    }
  }

  companion object {
    internal
    fun noArg(): StringProvider = object : StringProvider {

      override fun asNullable(): StubProvider<NoArg.Nullable> =
          StubProvider.provide { _, kProperty -> StringNoArgImpl(kProperty.name).asNullable() }

      override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
          Stub.stub(StringNoArgImpl(property.name))
    }

    internal
    fun <A : ArgumentSpec> configured() = object : ConfiguredStringProvider<A> {
      override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
          Stub.stub(ConfiguredStringImpl<A>(property.name))

      override fun asNullable() = StubProvider.provide { _, kProperty ->
        ConfiguredStringImpl<A>(kProperty.name).asNullable()
      }
    }

    internal
    fun <A : ArgumentSpec> optionallyConfigured(): OptionallyConfiguredStringProvider<A> =

        object : OptionallyConfiguredStringProvider<A> {

          override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
              Stub.stub(OptionalStringImpl<A>(property.name))

          override fun asNullable() = StubProvider.provide { _, prop ->
            OptionalStringImpl<A>(prop.name).asNullable()
          }

        }
  }

}

internal
fun <T : PrimitiveStub> T.asProvider() = object : ScalarDelegate<T> {
  override fun provideDelegate(inst: Model<*>, property: KProperty<*>): T =
      inst.register(this@asProvider)
}

private class StringNoArgImpl(val propertyName: String) : StringDelegate.NoArg {

  override fun provideDelegate(inst: Model<*>, property: KProperty<*>): StringStub =
      ScalarPreDelegate
          .PreString(ArgBuilder())
          .toDelegate(propertyName)
          .bindToContext(inst)

  override fun invoke(arguments: ArgBuilder, block: StringDelegate<ArgBuilder>.() -> Unit) =
      new(propertyName, arguments, block)

  internal
  fun asNullable(): StringDelegate.NoArg.Nullable = object : StringDelegate.NoArg.Nullable {
    override fun provideDelegate(inst: Model<*>, property: KProperty<*>): GraphQlField<String?> =
        ScalarPreDelegate.PreString(ArgBuilder())
            .toDelegate(propertyName)
            .wrapAsNullable()
            .bindingWith(inst)

    override fun invoke(arguments: ArgBuilder, block: StringDelegate<ArgBuilder>.() -> Unit) =
        ScalarPreDelegate.PreString(arguments)
            .apply(block)
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()
  }
}

private class ConfiguredStringImpl<A : ArgumentSpec>(val propertyName: String) : ScalarDelegate.Configured<StringDelegate<A>, StringStub, A> {
  override fun invoke(arguments: A, block: StringDelegate<A>.() -> Unit): ScalarDelegate<StringStub> =
      ScalarPreDelegate.PreString(arguments)
          .apply(block)
          .toDelegate(propertyName)
          .asProvider()

  fun asNullable(): ScalarDelegate.Configured.Nullable<StringDelegate<A>, A, String> =
      object : ScalarDelegate.Configured.Nullable<StringDelegate<A>, A, String> {

        override fun invoke(arguments: A, block: StringDelegate<A>.() -> Unit): DelegateProvider<String?> =
            ScalarPreDelegate.PreString(arguments)
                .apply(block)
                .toDelegate(propertyName)
                .wrapAsNullable()
                .delegatingTo()

      }
}

private class OptionalStringImpl<A : ArgumentSpec>(val propertyName: String) : StringDelegate.OptionallyConfigured<A> {

  override fun invoke(arguments: A, block: StringDelegate<A>.() -> Unit) =
      new(propertyName, arguments, block)

  override fun invoke(block: StringDelegate<ArgBuilder>.() -> Unit) =
      new(propertyName, ArgBuilder(), block)

  override fun provideDelegate(inst: Model<*>, property: KProperty<*>) =
      ScalarPreDelegate
          .PreString(ArgBuilder())
          .toDelegate(propertyName)
          .bindToContext(inst)

  fun asNullable() = object : StringDelegate.OptionallyConfigured.Nullable<A> {

    private val propertyName = this@OptionalStringImpl.propertyName

    override fun invoke(block: StringDelegate<ArgBuilder>.() -> Unit): DelegateProvider<String?> =
        ScalarPreDelegate
            .PreString(ArgBuilder())
            .apply(block)
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()

    override fun provideDelegate(inst: Model<*>, property: KProperty<*>) =
        ScalarPreDelegate
            .PreString(ArgBuilder())
            .toDelegate(propertyName)
            .bindToContext(inst)
            .wrapAsNullable()

    override fun invoke(arguments: A, block: StringDelegate<A>.() -> Unit) =
        ScalarPreDelegate
            .PreString(ArgBuilder())
            .toDelegate(propertyName)
            .wrapAsNullable()
            .delegatingTo()

  }
}


private fun <A : ArgumentSpec> new(propertyName: String, args: A, block: StringDelegate<A>.() -> Unit = empty()): ScalarDelegate<StringStub> {
  return ScalarPreDelegate.PreString(args)
      .apply(block)
      .toDelegate(propertyName)
      .asProvider()
}

