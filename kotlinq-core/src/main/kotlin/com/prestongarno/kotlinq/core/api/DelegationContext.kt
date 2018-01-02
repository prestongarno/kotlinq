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

package com.prestongarno.kotlinq.core.api

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.InterfaceStubImpl
import com.prestongarno.kotlinq.core.adapters.UnionStubImpl
import com.prestongarno.kotlinq.core.properties.delegates.Configured
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.ConfiguredBlock
import com.prestongarno.kotlinq.core.properties.delegates.NoArgBlock
import com.prestongarno.kotlinq.core.properties.delegates.configuredBlock
import com.prestongarno.kotlinq.core.properties.contextBuilder
import com.prestongarno.kotlinq.core.properties.delegates.noArgBlock
import com.prestongarno.kotlinq.core.schema.CustomScalar
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.prestongarno.kotlinq.core.schema.stubs.CustomScalarStub
import com.prestongarno.kotlinq.core.schema.stubs.EnumStub
import com.prestongarno.kotlinq.core.schema.stubs.InterfaceStub
import com.prestongarno.kotlinq.core.schema.stubs.TypeStub
import com.prestongarno.kotlinq.core.schema.stubs.UnionStub
import kotlin.reflect.KClass

@PublishedApi
internal
interface DelegationContext {
  val type: GraphQLDelegate.Type

  val iface: GraphQLDelegate.Interface

  val enum: GraphQLDelegate.QlEnum

  val union: GraphQLDelegate.Union

  val scalar: GraphQLDelegate.Scalar

  val stringScalar: GraphQLDelegate.QlString

  val intScalar: GraphQLDelegate.QlInt

  val floatScalar: GraphQLDelegate.QlFloat

  val booleanScalar: GraphQLDelegate.QlBoolean

}

/**
 * The interface structure is a bit bloated with 3 different delegation interfaces
 * to support the 3 scenarios a graphql field can require: zero arguments, all nullable arguments,
 * or 1 or more non-null arguments. This isn't easily put into an interface and abstracted, since
 * each type has different requirements for the parameters and/or the DSL flow.
 *
 * However, the GraphQL type hierarchy is finite, so a sealed class hierarchy will make it easy
 * to cut down on the repetitiveness of the $TYPE$.$ARGUMENT_STRATEGY$ way to currently get a delegate
 *
 * DelegationContext is a single entry point for the API now, and takes a single argument for the schema
 * stub delegate provider type. This is restricted by the sealed class [GraphQLDelegate]
 */

@PublishedApi
internal
class DefaultDelegationContext : DelegationContext {

  override val type = GraphQLDelegate.Type()

  override val iface = GraphQLDelegate.Interface()

  override val enum = GraphQLDelegate.QlEnum()

  override val union = GraphQLDelegate.Union()

  override val scalar = GraphQLDelegate.Scalar()

  override val stringScalar = GraphQLDelegate.QlString()

  override val intScalar = GraphQLDelegate.QlInt()

  override val floatScalar = GraphQLDelegate.QlFloat()

  override val booleanScalar = GraphQLDelegate.QlBoolean()

}


/**
 * Call to create a stub will be like:
 *
 *    StubProvider.delegationContext.type.query<T>()
 *
 * This is better because it's no longer inlining internal code into client class
 * files and also enables mocked delegate providers for testing
 */
@PublishedApi
internal
sealed class GraphQLDelegate {

  abstract val list: Lists.GraphQLListDelegate

  class Type : GraphQLDelegate() {

    fun <T : QType> stub(clazz: KClass<T>)
        : NullStubProvider<TypeStub.NoArg<T>, TypeStub.NoArg.Nullable<T>> =
        Grub(clazz.graphQlName(), false,
            builder = contextBuilder { TypeStub.noArg<T>(it) },
            nullableBuilder = contextBuilder { TypeStub.noArg<T>(it).asNullable() })

    fun <T : QType, A : ArgumentSpec> optionallyConfigured(clazz: KClass<T>)
        : NullStubProvider<TypeStub.OptionalConfigured<T, A>, TypeStub.OptionalConfigured.Nullable<T, A>> =
        Grub(clazz.graphQlName(), false,
            builder = contextBuilder { TypeStub.optionallyConfigured<T, A>(it) },
            nullableBuilder = contextBuilder { TypeStub.optionallyConfigured<T, A>(it).asNullable() })

    fun <T : QType, A : ArgumentSpec> configured(clazz: KClass<T>)
        : NullStubProvider<TypeStub.Configured<T, A>, TypeStub.Configured.Nullable<T, A>> =
        optionallyConfigured(clazz)

    override val list: Lists.Type = Lists.Type()
  }

  class Interface : GraphQLDelegate() {

    fun <I> stub(clazz: KClass<I>)
        : StubProvider<NoArgBlock<InterfaceStub<I, ArgBuilder>, QModel<I>?>>
        where I : QInterface, I : QType =
        contextBuilder { qproperty ->
          noArgBlock(qproperty, { InterfaceStubImpl<I, ArgBuilder>(it) })
        }.let {
          Grub(clazz.graphQlName(), false, builder = it, nullableBuilder = it).asNullable()
        }

    fun <I, A> optionallyConfigured(clazz: KClass<I>)
        : StubProvider<InterfaceStub.OptionallyConfigured<I, A>>
        where I : QInterface, I : QType, A : ArgumentSpec =
        contextBuilder { property ->
          InterfaceStub.optionallyConfigured<I, A>(property)
        }.let { context ->
          Grub(clazz.graphQlName(), false, builder = context, nullableBuilder = context).asNullable()
        }

    fun <I, A> configured(clazz: KClass<I>)
        : StubProvider<ConfiguredBlock<InterfaceStub<I, A>, A, QModel<I>?>>
        where I : QInterface, I : QType, A : ArgumentSpec =
        contextBuilder { qproperty ->
          configuredBlock<InterfaceStubImpl<I, A>, A, QModel<I>?>(qproperty, { InterfaceStubImpl(it) })
        }.let {
          Grub(clazz.graphQlName(), false, builder = it, nullableBuilder = it).asNullable()
        }

    override val list: Lists.Interface = Lists.Interface()
  }

  class Union : GraphQLDelegate() {

    fun <T : QUnionType> stub(obj: T)
        : StubProvider<NoArgBlock<UnionStub<T, ArgBuilder>, QModel<*>?>> =
        contextBuilder {
          noArgBlock(it, { args -> UnionStubImpl(obj, args) })
        }.let { context ->
          Grub(obj::class.graphQlName(), false, builder = context, nullableBuilder = context).asNullable()
        }

    fun <T : QUnionType, A : ArgumentSpec> optionallyConfigured(obj: T)
        : StubProvider<UnionStub.OptionallyConfigured<T, A>> =
        contextBuilder {
          UnionStub.optionallyConfigured<T, A>(it, obj)
        }.let { context ->
          Grub(obj::class.graphQlName(), false, builder = context, nullableBuilder = context).asNullable()
        }

    fun <T : QUnionType, A : ArgumentSpec> configured(obj: T)
        : StubProvider<ConfiguredBlock<UnionStub<T, A>, A, QModel<*>?>> =
        contextBuilder {
          configuredBlock<UnionStubImpl<T, A>, A, QModel<*>?>(it, { UnionStubImpl(obj, it) })
        }.let { context ->
          Grub(obj::class.graphQlName(), false, builder = context, nullableBuilder = context).asNullable()
        }

    override val list: Lists.Union = Lists.Union()

  }

  class QlEnum : GraphQLDelegate() {

    fun <T> stub(clazz: KClass<T>)
        : NullStubProvider<DelegateProvider.NoArgDelegate<EnumStub<T, ArgBuilder>, T>, DelegateProvider.NoArgDelegate<EnumStub<T, ArgBuilder>, T?>>
        where T : Enum<T>, T : QEnumType =
        Grub(clazz.graphQlName(), false,
            builder = contextBuilder { EnumStub.noArg(clazz, it) },
            nullableBuilder = contextBuilder { EnumStub.noArg(clazz, it).asNullable() })

    fun <T, A> optionallyConfigured(clazz: KClass<T>)
        : NullStubProvider<EnumStub.OptionallyConfigured<T, A>, EnumStub.OptionallyConfigured.Nullable<T, A>>
        where T : Enum<T>, T : QEnumType, A : ArgumentSpec = Grub(clazz.graphQlName(), false,
        builder = contextBuilder { EnumStub.optionallyConfigured<T, A>(clazz, it) },
        nullableBuilder = contextBuilder { EnumStub.optionallyConfigured<T, A>(clazz, it).asNullable() })

    fun <T, A> configured(clazz: KClass<T>)
        : NullStubProvider<Configured<EnumStub<T, A>, A, T>, Configured<EnumStub<T, A>, A, T?>>
        where T : Enum<T>, T : QEnumType, A : ArgumentSpec =
        Grub(clazz.graphQlName(), false,
            builder = contextBuilder { EnumStub.configured<T, A>(clazz, it) },
            nullableBuilder = contextBuilder { EnumStub.configured<T, A>(clazz, it).asNullable() })


    override val list: Lists.QlEnum = Lists.QlEnum()
  }

  class QlString : GraphQLDelegate() {
    override val list: Lists.QlString = Lists.QlString()
  }

  class QlInt : GraphQLDelegate() {
    override val list: Lists.QlInt = Lists.QlInt()
  }

  class QlFloat : GraphQLDelegate() {
    override val list: Lists.QlFloat = Lists.QlFloat()
  }

  class QlBoolean : GraphQLDelegate() {
    override val list: Lists.QlBoolean = Lists.QlBoolean()
  }

  class Scalar : GraphQLDelegate() {

    fun <T : CustomScalar> stub(clazz: KClass<T>)
        : NullStubProvider<CustomScalarStub.NoArg<T>, CustomScalarStub.NoArg.Nullable<T>> =
        Grub(clazz.graphQlName(), false,
            builder = CustomScalarStub.noArg(),
            nullableBuilder = CustomScalarStub.Companion.Nullables.noArg())

    fun <T : CustomScalar, A : ArgumentSpec> optionallyConfigured(clazz: KClass<T>)
        : NullStubProvider<CustomScalarStub.OptionallyConfigured<T, A>, CustomScalarStub.OptionallyConfigured.Nullable<T, A>> =
        Grub(clazz.graphQlName(), false,
            builder = CustomScalarStub.optionallyConfigured(),
            nullableBuilder = CustomScalarStub.Companion.Nullables.optionallyConfigured())

    fun <T : CustomScalar, A : ArgumentSpec> configured(clazz: KClass<T>)
        : NullStubProvider<CustomScalarStub.Configured<T, A>, CustomScalarStub.Configured.Nullable<T, A>> =
        Grub(clazz.graphQlName(), false,
            builder = CustomScalarStub.configured(),
            nullableBuilder = CustomScalarStub.Companion.Nullables.configured())

    override val list: Lists.Scalar = Lists.Scalar()
  }

  /**
   * Trying to find out how to do lists/collections without adding a set of interfaces
   * on top of the already somewhat complicated type->argument config->delegate structure already...
   *
   *    delegationContext.type.stub<Actual>().asList()
   *
   * is a little complicated because for every interface
   * in the delegation hierarchy there is also a delegate provider
   *
   *    delegateContext.type.stub<Actual>().asNullable()
   *
   * will definitely have to be the way to support nullables
   */
  object Lists {

    abstract class GraphQLListDelegate : GraphQLDelegate() {
      override val list: GraphQLListDelegate get() = this
    }

    class Type : GraphQLListDelegate()

    class Interface : GraphQLListDelegate()

    class Union : GraphQLListDelegate()

    class QlEnum : GraphQLListDelegate()

    class Scalar : GraphQLListDelegate()

    class QlString : GraphQLListDelegate()

    class QlInt : GraphQLListDelegate()

    class QlFloat : GraphQLListDelegate()

    class QlBoolean : GraphQLListDelegate()
  }

  internal
  fun <T : Any> KClass<T>.graphQlName() = "${this.simpleName}"

}


