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

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.CustomScalar
import com.prestongarno.kotlinq.core.QEnumType
import com.prestongarno.kotlinq.core.QInterface
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.QUnionType
import com.prestongarno.kotlinq.core.stubs.BooleanArrayDelegate
import com.prestongarno.kotlinq.core.stubs.BooleanDelegate
import com.prestongarno.kotlinq.core.stubs.CustomScalarListStub
import com.prestongarno.kotlinq.core.stubs.CustomScalarStub
import com.prestongarno.kotlinq.core.stubs.EnumListStub
import com.prestongarno.kotlinq.core.stubs.EnumStub
import com.prestongarno.kotlinq.core.stubs.FloatArrayDelegate
import com.prestongarno.kotlinq.core.stubs.FloatDelegate
import com.prestongarno.kotlinq.core.stubs.IntArrayDelegate
import com.prestongarno.kotlinq.core.stubs.IntDelegate
import com.prestongarno.kotlinq.core.stubs.InterfaceListStub
import com.prestongarno.kotlinq.core.stubs.InterfaceStub
import com.prestongarno.kotlinq.core.stubs.StringArrayDelegate
import com.prestongarno.kotlinq.core.stubs.StringDelegate
import com.prestongarno.kotlinq.core.stubs.TypeListStub
import com.prestongarno.kotlinq.core.stubs.TypeStub
import com.prestongarno.kotlinq.core.stubs.UnionListStub
import com.prestongarno.kotlinq.core.stubs.UnionStub
import kotlin.reflect.KClass

@PublishedApi internal interface DelegationContext {
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
@PublishedApi internal sealed class GraphQLDelegate {

  abstract val list: Lists.GraphQLListDelegate


  // TODO make typealias for ctors and allow passing in of functions
  // which make the StubProvider for the delegate creation on schema fields
  class Type : GraphQLDelegate() {

    fun <T : QType> query(typeClazz: KClass<T>)
        :
        StubProvider<TypeStub.Query<T>> =
        Grub(typeClazz.graphQlName()) { TypeStub.noArgStub<T>(it) }

    fun <T : QType, A : ArgumentSpec> optionalConfigStub(typeClazz: KClass<T>)
        :
        StubProvider<TypeStub.OptionalConfigQuery<T, A>> =
        Grub(typeClazz.graphQlName()) { TypeStub.optionalArgStub<T, A>(it) }

    fun <T : QType, A : ArgumentSpec> configStub(typeClazz: KClass<T>)
        :
        StubProvider<TypeStub.ConfigurableQuery<T, A>> =
        Grub(typeClazz.graphQlName()) { TypeStub.argStub<T, A>(it) }

    override val list: Lists.Type = Lists.Type()
  }

  class Interface : GraphQLDelegate() {

    fun <T> stub(clazz: KClass<T>)
        :
        StubProvider<InterfaceStub.Query<T>>
        where T : QType,
              T : QInterface =
        Grub(clazz.graphQlName()) { InterfaceStub.noArgStub<T>(it) }

    fun <T, A> optionalConfigStub(clazz: KClass<T>)
        :
        StubProvider<InterfaceStub.OptionalConfigQuery<T, A>>
        where T : QType,
              T : QInterface,
              A : ArgumentSpec =
        Grub(clazz.graphQlName()) { InterfaceStub.optionalArgStub<T, A>(it) }

    fun <T, A> configStub(clazz: KClass<T>)
        :
        StubProvider<InterfaceStub.ConfigurableQuery<T, A>>
        where T : QType,
              T : QInterface,
              A : ArgumentSpec =
        Grub(clazz.graphQlName()) { InterfaceStub.argStub<T, A>(it) }

    override val list: Lists.Interface = Lists.Interface()
  }

  class Union : GraphQLDelegate() {

    fun <T : QUnionType> stub(union: T)
        :
        StubProvider<UnionStub.Query<T>> =
        Grub(union::class.graphQlName()) { UnionStub.noArgStub(it, union) }

    fun <T : QUnionType, A : ArgumentSpec> optionalConfigStub(union: T)
        :
        StubProvider<UnionStub.OptionalConfigQuery<T, A>> =
        Grub(union::class.graphQlName()) { UnionStub.optionalArgStub<T, A>(it, union) }

    fun <T : QUnionType, A : ArgumentSpec> configStub(union: T)
        :
        StubProvider<UnionStub.ConfigurableQuery<T, A>> =
        Grub(union::class.graphQlName()) { UnionStub.argStub<T, A>(it, union) }

    override val list: Lists.Union = Lists.Union()

  }

  class QlEnum : GraphQLDelegate() {

    fun <T> stub(clazz: KClass<T>)
        :
        StubProvider<EnumStub.Query<T>>
        where T : kotlin.Enum<*>,
              T : QEnumType
        = Grub(clazz.graphQlName()) { EnumStub.noArgStub(it, clazz) }

    fun <T, A : ArgumentSpec> optionalConfigStub(clazz: KClass<T>)
        :
        StubProvider<EnumStub.OptionalConfigQuery<T, A>>
        where T : kotlin.Enum<*>,
              T : QEnumType =
        Grub(clazz.graphQlName()) { EnumStub.optionalArgStub<T, A>(it, clazz) }

    fun <T, A : ArgumentSpec> configStub(clazz: KClass<T>)
        :
        StubProvider<EnumStub.ConfigurableQuery<T, A>>
        where T : kotlin.Enum<*>,
              T : QEnumType
        = Grub(clazz.graphQlName()) { EnumStub.argStub<T, A>(it, clazz) }

    override val list: Lists.QlEnum = Lists.QlEnum()

  }

  class QlString : GraphQLDelegate() {

    fun stub()
        :
        StubProvider<StringDelegate.Query> =
        Grub("String") { StringDelegate.noArgStub(it) }

    fun <A : ArgumentSpec> optionalConfigStub()
        :
        StubProvider<StringDelegate.OptionalConfigQuery<A>> =
        Grub("String") { StringDelegate.optionalArgStub<A>(it) }

    fun <A : ArgumentSpec> configStub()
        :
        StubProvider<StringDelegate.ConfigurableQuery<A>> =
        Grub("String") { StringDelegate.argStub<A>(it) }

    override val list: Lists.QlString = Lists.QlString()

  }

  class QlInt : GraphQLDelegate() {

    fun stub()
        :
        StubProvider<IntDelegate.Query> =
        Grub("Int") { IntDelegate.noArgStub(it) }

    fun <A : ArgumentSpec> optionalConfigStub()
        :
        StubProvider<IntDelegate.OptionalConfigQuery<A>> =
        Grub("Int") { IntDelegate.optionalArgStub<A>(it) }

    fun <A : ArgumentSpec> configStub()
        :
        StubProvider<IntDelegate.ConfigurableQuery<A>> =
        Grub("Int") { IntDelegate.argStub<A>(it) }

    override val list: Lists.QlInt = Lists.QlInt()

  }

  class QlFloat : GraphQLDelegate() {

    fun stub()
        :
        StubProvider<FloatDelegate.Query> =
        Grub("Float") { FloatDelegate.noArgStub(it) }

    fun <A : ArgumentSpec> optionalConfigStub()
        :
        StubProvider<FloatDelegate.OptionalConfigQuery<A>> =
        Grub("Float") { FloatDelegate.optionalArgStub<A>(it) }

    fun <A : ArgumentSpec> configStub()
        :
        StubProvider<FloatDelegate.ConfigurableQuery<A>> =
        Grub("Float") { FloatDelegate.argStub<A>(it) }

    override val list: Lists.QlFloat = Lists.QlFloat()
  }

  class QlBoolean : GraphQLDelegate() {

    fun stub()
        : StubProvider<BooleanDelegate.Query> =
        Grub("Boolean") { BooleanDelegate.noArgStub(it) }

    fun <A : ArgumentSpec> optionalConfigStub()
        :
        StubProvider<BooleanDelegate.OptionalConfigQuery<A>> =
        Grub("Boolean") { BooleanDelegate.optionalArgStub<A>(it) }

    fun <A : ArgumentSpec> configStub()
        :
        StubProvider<BooleanDelegate.ConfigurableQuery<A>> =
        Grub("Boolean") { BooleanDelegate.argStub<A>(it) }

    override val list: Lists.QlBoolean = Lists.QlBoolean()
  }

  class Scalar : GraphQLDelegate() {

    fun <T : CustomScalar> stub(clazz: KClass<T>)
        :
        StubProvider<CustomScalarStub.Query<T>> =
        Grub(clazz.graphQlName()) { CustomScalarStub.noArgStub<T>(it) }

    fun <T : CustomScalar, A : ArgumentSpec> optionalConfigStub(clazz: KClass<T>)
        :
        StubProvider<CustomScalarStub.OptionalConfigQuery<T, A>> =
        Grub(clazz.graphQlName()) { CustomScalarStub.optionalArgStub<T, A>(it) }

    fun <T : CustomScalar, A : ArgumentSpec> configStub(clazz: KClass<T>)
        :
        StubProvider<CustomScalarStub.ConfigurableQuery<T, A>> =
        Grub(clazz.graphQlName()) { CustomScalarStub.argStub<T, A>(it) }

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

    class Type : GraphQLListDelegate() {

      fun <T : QType> query(typeClazz: KClass<T>)
          :
          StubProvider<TypeListStub.Query<T>> =
          Grub(typeClazz.graphQlName(), true) { TypeListStub.noArgStub<T>(it) }

      fun <T : QType, A : ArgumentSpec> optionalConfigStub(typeClazz: KClass<T>)
          :
          StubProvider<TypeListStub.OptionalConfigQuery<T, A>> =
          Grub(typeClazz.graphQlName(), true) { TypeListStub.optionalArgStub<T, A>(it) }

      fun <T : QType, A : ArgumentSpec> configStub(typeClazz: KClass<T>)
          :
          StubProvider<TypeListStub.ConfigurableQuery<T, A>> =
          Grub(typeClazz.graphQlName(), true) { TypeListStub.argStub<T, A>(it) }
    }

    class Interface : GraphQLListDelegate() {

      fun <T> stub(clazz: KClass<T>)
          :
          StubProvider<InterfaceListStub.Query<T>>
          where T : QType,
                T : QInterface =
          Grub(clazz.graphQlName(), true) { InterfaceListStub.noArgStub<T>(it) }

      fun <T, A> optionalConfigStub(clazz: KClass<T>)
          :
          StubProvider<InterfaceListStub.OptionalConfigQuery<T, out A>>
          where T : QType,
                T : QInterface,
                A : ArgumentSpec =
          Grub(clazz.graphQlName(), true) { InterfaceListStub.optionalArgStub<T, A>(it) }

      fun <T, A> configStub(clazz: KClass<T>)
          :
          StubProvider<InterfaceListStub.ConfigurableQuery<T, out A>>
          where T : QType,
                T : QInterface,
                A : ArgumentSpec =
          Grub(clazz.graphQlName(), true) { InterfaceListStub.argStub<T, A>(it) }
    }

    class Union : GraphQLListDelegate() {

      fun <T : QUnionType> stub(union: T)
          :
          StubProvider<UnionListStub.Query<T>> =
          Grub(union::class.graphQlName(), true) { UnionListStub.noArgStub(it, union) }

      fun <T : QUnionType, A : ArgumentSpec> optionalConfigStub(union: T)
          :
          StubProvider<UnionListStub.OptionalConfigQuery<T, A>> =
          Grub(union::class.graphQlName(), true) { UnionListStub.optionalArgStub<T, A>(it, union) }

      fun <T : QUnionType, A : ArgumentSpec> configStub(union: T)
          :
          StubProvider<UnionListStub.ConfigurableQuery<T, A>> =
          Grub(union::class.graphQlName(), true) { UnionListStub.argStub<T, A>(it, union) }
    }

    class QlEnum : GraphQLListDelegate() {

      fun <T> stub(clazz: KClass<T>)
          :
          StubProvider<EnumListStub.Query<T>>
          where T : kotlin.Enum<*>,
                T : QEnumType
          = Grub(clazz.graphQlName(), true) { EnumListStub.noArgStub(it, clazz) }

      fun <T, A : ArgumentSpec> optionalConfigStub(clazz: KClass<T>)
          :
          StubProvider<EnumListStub.OptionalConfigQuery<T, A>>
          where T : kotlin.Enum<*>,
                T : QEnumType =
          Grub(clazz.graphQlName(), true) { EnumListStub.optionalArgStub<T, A>(it, clazz) }

      fun <T, A : ArgumentSpec> configStub(clazz: KClass<T>)
          :
          StubProvider<EnumListStub.ConfigurableQuery<T, A>>
          where T : kotlin.Enum<*>,
                T : QEnumType
          = Grub(clazz.graphQlName(), true) { EnumListStub.argStub<T, A>(it, clazz) }

    }

    class Scalar : GraphQLListDelegate() {

      fun <T : CustomScalar> stub(clazz: KClass<T>)
          :
          StubProvider<CustomScalarListStub.Query<T>> =
          Grub(clazz.graphQlName()) { CustomScalarListStub.noArgStub<T>(it) }

      fun <T : CustomScalar, A : ArgumentSpec> optionalConfigStub(clazz: KClass<T>)
          :
          StubProvider<CustomScalarListStub.OptionalConfigQuery<T, A>> =
          Grub(clazz.graphQlName()) { CustomScalarListStub.optionalArgStub<T, A>(it) }

      fun <T : CustomScalar, A : ArgumentSpec> configStub(clazz: KClass<T>)
          :
          StubProvider<CustomScalarListStub.ConfigurableQuery<T, A>> =
          Grub(clazz.graphQlName()) { CustomScalarListStub.argStub<T, A>(it) }
    }

    class QlString : GraphQLListDelegate() {

      fun stub()
          :
          StubProvider<StringArrayDelegate.Query> =
          Grub("String", true) { StringArrayDelegate.noArgStub(it) }

      fun <A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<StringArrayDelegate.OptionalConfigQuery<A>> =
          Grub("String", true) { StringArrayDelegate.optionalArgStub<A>(it) }

      fun <A : ArgumentSpec> configStub()
          :
          StubProvider<StringArrayDelegate.ConfigurableQuery<A>> =
          Grub("String", true) { StringArrayDelegate.argStub<A>(it) }
    }

    class QlInt : GraphQLListDelegate() {

      fun stub()
          :
          StubProvider<IntArrayDelegate.Query> =
          Grub("Int", true) { IntArrayDelegate.noArgStub(it) }

      fun <A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<IntArrayDelegate.OptionalConfigQuery<A>> =
          Grub("Int", true) { IntArrayDelegate.optionalArgStub<A>(it) }

      fun <A : ArgumentSpec> configStub()
          :
          StubProvider<IntArrayDelegate.ConfigurableQuery<A>> =
          Grub("Int", true) { IntArrayDelegate.argStub<A>(it) }
    }

    class QlFloat : GraphQLListDelegate() {

      fun stub()
          :
          StubProvider<FloatArrayDelegate.Query> =
          Grub("Float", true) { FloatArrayDelegate.noArgStub(it) }

      fun <A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<FloatArrayDelegate.OptionalConfigQuery<A>> =
          Grub("Float", true) { FloatArrayDelegate.optionalArgStub<A>(it) }

      fun <A : ArgumentSpec> configStub()
          :
          StubProvider<FloatArrayDelegate.ConfigurableQuery<A>> =
          Grub("Float", true) { FloatArrayDelegate.argStub<A>(it) }
    }

    class QlBoolean : GraphQLListDelegate() {

      fun stub()
          :
          StubProvider<BooleanArrayDelegate.Query> =
          Grub("Boolean", true) { BooleanArrayDelegate.noArgStub(it) }

      fun <A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<BooleanArrayDelegate.OptionalConfigQuery<A>> =
          Grub("Boolean", true) { BooleanArrayDelegate.optionalArgStub<A>(it) }

      fun <A : ArgumentSpec> configStub()
          :
          StubProvider<BooleanArrayDelegate.ConfigurableQuery<A>> =
          Grub("Boolean", true) { BooleanArrayDelegate.argStub<A>(it) }
    }
  }

  internal
  fun <T : Any> KClass<T>.graphQlName() = "${this.simpleName}"

}

