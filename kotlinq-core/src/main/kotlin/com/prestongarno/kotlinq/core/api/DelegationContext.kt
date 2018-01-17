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

package com.prestongarno.kotlinq.core.api

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.BooleanArrayStub
import com.prestongarno.kotlinq.core.adapters.EnumAdapterImpl
import com.prestongarno.kotlinq.core.adapters.FloatArrayStub
import com.prestongarno.kotlinq.core.adapters.IntArrayStub
import com.prestongarno.kotlinq.core.adapters.InterfaceStubImpl
import com.prestongarno.kotlinq.core.adapters.StringArrayStub
import com.prestongarno.kotlinq.core.adapters.UnionStubImpl
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Always
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Never
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Sometimes
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Companion.newBuilder
import com.prestongarno.kotlinq.core.api.Grub.Companion.singleBuilder
import com.prestongarno.kotlinq.core.properties.contextBuilder
import com.prestongarno.kotlinq.core.properties.delegates.ConfiguredBlock
import com.prestongarno.kotlinq.core.properties.delegates.NoArgBlock
import com.prestongarno.kotlinq.core.properties.delegates.configuredBlock
import com.prestongarno.kotlinq.core.properties.delegates.noArgBlock
import com.prestongarno.kotlinq.core.schema.CustomScalar
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.prestongarno.kotlinq.core.schema.stubs.BooleanDelegate
import com.prestongarno.kotlinq.core.schema.stubs.BooleanProvider
import com.prestongarno.kotlinq.core.schema.stubs.ConfiguredBooleanProvider
import com.prestongarno.kotlinq.core.schema.stubs.ConfiguredFloatProvider
import com.prestongarno.kotlinq.core.schema.stubs.ConfiguredIntProvider
import com.prestongarno.kotlinq.core.schema.stubs.ConfiguredStringProvider
import com.prestongarno.kotlinq.core.schema.stubs.CustomListStubHandle
import com.prestongarno.kotlinq.core.schema.stubs.CustomScalarListStub
import com.prestongarno.kotlinq.core.schema.stubs.CustomScalarStub
import com.prestongarno.kotlinq.core.schema.stubs.CustomStubHandle
import com.prestongarno.kotlinq.core.schema.stubs.FloatDelegate
import com.prestongarno.kotlinq.core.schema.stubs.FloatProvider
import com.prestongarno.kotlinq.core.schema.stubs.IntDelegate
import com.prestongarno.kotlinq.core.schema.stubs.IntProvider
import com.prestongarno.kotlinq.core.schema.stubs.InterfaceListStub
import com.prestongarno.kotlinq.core.schema.stubs.InterfaceListStubImpl
import com.prestongarno.kotlinq.core.schema.stubs.InterfaceStub
import com.prestongarno.kotlinq.core.schema.stubs.OptionallyConfiguredBooleanProvider
import com.prestongarno.kotlinq.core.schema.stubs.OptionallyConfiguredFloatProvider
import com.prestongarno.kotlinq.core.schema.stubs.OptionallyConfiguredIntProvider
import com.prestongarno.kotlinq.core.schema.stubs.OptionallyConfiguredStringProvider
import com.prestongarno.kotlinq.core.schema.stubs.StringDelegate
import com.prestongarno.kotlinq.core.schema.stubs.StringProvider
import com.prestongarno.kotlinq.core.schema.stubs.TypeListStub
import com.prestongarno.kotlinq.core.schema.stubs.TypeListStubImpl
import com.prestongarno.kotlinq.core.schema.stubs.TypeStub
import com.prestongarno.kotlinq.core.schema.stubs.TypeStubImpl
import com.prestongarno.kotlinq.core.schema.stubs.UnionListStub
import com.prestongarno.kotlinq.core.schema.stubs.UnionListStubImpl
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

    fun <T : QType> stub(clazz: KClass<T>): NullableStubProvider<TypeStub.NoArg.NotNull<T>, TypeStub.NoArg<T>> =
        schemaProvider {
          TypeStubImpl.NoArg.NotNull(clazz, it.second.name)
        }.withAlternate { it.asNullable() }

    fun <T : QType, A : ArgumentSpec> optionallyConfigured(clazz: KClass<T>)
        : NullableStubProvider<TypeStub.OptionallyConfigured.NotNull<T, A>, TypeStub.OptionallyConfigured<T, A>> =
        schemaProvider { TypeStubImpl.OptionallyConfigured.NotNull<T, A>(clazz, it.second.name) }
            .withAlternate { it.asNullable() }

    fun <T : QType, A : ArgumentSpec> configured(clazz: KClass<T>)
        : NullableStubProvider<TypeStub.Configured.NotNull<T, A>, TypeStub.Configured<T, A>> =
        schemaProvider { TypeStubImpl.Configured.NotNull<T, A>(clazz, it.second.name) }.withAlternate { it.asNullable() }


    override val list: Lists.Type = Lists.Type()
  }


  class Interface : GraphQLDelegate() {

    fun <I> stub(clazz: KClass<I>)
        : StubProvider<NoArgBlock<InterfaceStub<I, ArgBuilder>, QModel<I>?>>
        where I : QInterface, I : QType =
        contextBuilder { qproperty ->
          noArgBlock(qproperty, { InterfaceStubImpl<I, ArgBuilder>(it) })
        }.let { singleBuilder(clazz.graphQlName(), false, builder = it) }

    fun <I, A> optionallyConfigured(clazz: KClass<I>)
        : StubProvider<InterfaceStub.OptionallyConfigured<I, A>>
        where I : QInterface, I : QType, A : ArgumentSpec =
        contextBuilder { property ->
          InterfaceStub.optionallyConfigured<I, A>(property)
        }.let { context ->
          singleBuilder(clazz.graphQlName(), false, builder = context)
        }

    fun <I, A> configured(clazz: KClass<I>)
        : StubProvider<ConfiguredBlock<InterfaceStub<I, A>, A, QModel<I>?>>
        where I : QInterface, I : QType, A : ArgumentSpec =
        contextBuilder { qproperty ->
          configuredBlock<InterfaceStubImpl<I, A>, A, QModel<I>?>(qproperty, { InterfaceStubImpl(it) })
        }.let { singleBuilder(clazz.graphQlName(), false, builder = it) }

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
        : NoArgProvider<T>
        where T : QEnumType,
              T : Enum<T> = schemaProvider { kontext ->

      newBuilder<T>() takingArguments ::Never resultingIn {
        EnumAdapterImpl(clazz, it.first, it.second.default).toDelegate(kontext)
      }

    }.withAlternate { it allowing Nothing::class }

    fun <T, A> optionallyConfigured(clazz: KClass<T>)
        : OptionallyConfiguredProvider<T, A>
        where T : QEnumType,
              T : Enum<T>,
              A : ArgumentSpec =
        schemaProvider { kdelegateContext ->
          newBuilder<T>().withArgs<A>() takingArguments ::Sometimes resultingIn { (args, builder) ->
            EnumAdapterImpl(clazz, args, builder.default).toDelegate(kdelegateContext)
          }
        }.withAlternate { it allowing Nothing::class }

    fun <T, A> configured(clazz: KClass<T>)
        : ConfiguredProvider<T, A>
        where T : QEnumType,
              T : Enum<T>,
              A : ArgumentSpec = schemaProvider { kdelegateContext ->
      newBuilder<T>().withArgs<A>() takingArguments ::Always resultingIn { (args, builder) ->
        EnumAdapterImpl(clazz, args, builder.default).toDelegate(kdelegateContext)
      }
    }.withAlternate { it allowing Nothing::class }


    override val list: Lists.QlEnum = Lists.QlEnum()
  }


  class QlString : GraphQLDelegate() {

    fun stub(): StringProvider =
        StringDelegate.noArg()

    fun <A : ArgumentSpec> optionallyConfigured()
        : OptionallyConfiguredStringProvider<A> =
        StringDelegate.optionallyConfigured()

    fun <A : ArgumentSpec> configured()
        : ConfiguredStringProvider<A> =
        StringDelegate.configured()

    override val list: Lists.QlString = Lists.QlString()
  }


  class QlInt : GraphQLDelegate() {

    fun stub()
        : IntProvider =
        IntDelegate.stub()

    fun <A : ArgumentSpec> optionallyConfigured()
        : OptionallyConfiguredIntProvider<A> =
        IntDelegate.optionallyConfigured()

    fun <A : ArgumentSpec> configured()
        : ConfiguredIntProvider<A> =
        IntDelegate.configured()

    override val list: Lists.QlInt = Lists.QlInt()
  }


  class QlFloat : GraphQLDelegate() {

    fun stub()
        : FloatProvider =
        FloatDelegate.stub()

    fun <A : ArgumentSpec> optionallyConfigured()
        : OptionallyConfiguredFloatProvider<A> =
        FloatDelegate.optionallyConfigured()

    fun <A : ArgumentSpec> configured()
        : ConfiguredFloatProvider<A> =
        FloatDelegate.configured()

    override val list: Lists.QlFloat = Lists.QlFloat()
  }


  class QlBoolean : GraphQLDelegate() {

    fun stub()
        : BooleanProvider =
        BooleanDelegate.stub()

    fun <A : ArgumentSpec> optionallyConfigured()
        : OptionallyConfiguredBooleanProvider<A> =
        BooleanDelegate.optionallyConfigured()

    fun <A : ArgumentSpec> configured()
        : ConfiguredBooleanProvider<A> =
        BooleanDelegate.configured()

    override val list: Lists.QlBoolean = Lists.QlBoolean()
  }


  class Scalar : GraphQLDelegate() {

    fun <T : CustomScalar> stub(clazz: KClass<T>)
        : StubProvider<CustomScalarStub.NoArg<T>> =
        CustomStubHandle.stub(clazz)

    fun <T : CustomScalar, A : ArgumentSpec> optionallyConfigured(clazz: KClass<T>)
        : StubProvider<CustomScalarStub.OptionallyConfigured<T, A>> =
        CustomStubHandle.optionallyConfiguredStub(clazz)

    fun <T : CustomScalar, A : ArgumentSpec> configured(clazz: KClass<T>)
        : StubProvider<CustomScalarStub.Configured<T, A>> =
        CustomStubHandle.configuredStub(clazz)

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

      fun <T : QType> stub(clazz: KClass<T>): StubProvider<TypeListStub.NoArg<T>> =
          schemaProvider { TypeListStubImpl.NoArg(clazz, it.second.name) }

      fun <T : QType, A : ArgumentSpec> optionallyConfigured(clazz: KClass<T>)
          : StubProvider<TypeListStub.OptionallyConfigured<T, A>> =
          schemaProvider { TypeListStubImpl.OptionallyConfigured<T, A>(clazz, it.second.name) }

      fun <T : QType, A : ArgumentSpec> configured(clazz: KClass<T>)
          : StubProvider<TypeListStub.Configured<T, A>> =
          schemaProvider { TypeListStubImpl.Configured<T, A>(clazz, it.second.name) }

      override val list: Lists.Type = this
    }


    class Interface : GraphQLListDelegate() {

      fun <T> stub(clazz: KClass<T>)
          : NullableStubProvider<InterfaceListStub.NoArg.NotNull<T>, InterfaceListStub.NoArg<T>>
          where T : QType, T : QInterface =
          Grub(clazz.graphQlName(), true,
              builder = InterfaceListStubImpl.newStub<InterfaceListStubImpl.NoArg.NotNull<T>, T, ArgBuilder>(),
              nullableBuilder = InterfaceListStubImpl.newStub<InterfaceListStubImpl.NoArg<T>, T, ArgBuilder>())

      fun <T, A> optionallyConfigured(clazz: KClass<T>)
          : NullableStubProvider<InterfaceListStub.OptionallyConfigured.NotNull<T, A>, InterfaceListStub.OptionallyConfigured<T, A>>
          where T : QType, T : QInterface, A : ArgumentSpec =
          Grub(clazz.graphQlName(), true,
              builder = InterfaceListStubImpl.newStub<InterfaceListStubImpl.OptionallyConfigured.NotNull<T, A>, T, A>(),
              nullableBuilder = InterfaceListStubImpl.newStub<InterfaceListStubImpl.OptionallyConfigured<T, A>, T, A>())

      fun <T, A> configured(clazz: KClass<T>)
          : NullableStubProvider<InterfaceListStub.Configured.NotNull<T, A>, InterfaceListStub.Configured<T, A>>
          where T : QType, T : QInterface, A : ArgumentSpec =
          Grub(clazz.graphQlName(), true,
              builder = InterfaceListStubImpl.newStub<InterfaceListStubImpl.Configured.NotNull<T, A>, T, A>(),
              nullableBuilder = InterfaceListStubImpl.newStub<InterfaceListStubImpl.Configured<T, A>, T, A>())

    }


    class Union : GraphQLListDelegate() {

      fun <T : QUnionType> stub(unionObject: T)
          : NullableStubProvider<UnionListStub.NoArg.NotNull<T>, UnionListStub.NoArg<T>> =
          Grub(unionObject::class.graphQlName(), true,
              builder = UnionListStubImpl.newStub<UnionListStubImpl.NoArg.NotNull<T>, T, ArgBuilder>(unionObject),
              nullableBuilder = UnionListStubImpl.newStub<UnionListStubImpl.NoArg<T>, T, ArgBuilder>(unionObject))

      fun <T : QUnionType, A : ArgumentSpec> optionallyConfigured(unionObject: T)
          : NullableStubProvider<UnionListStub.OptionallyConfigured.NotNull<T, A>, UnionListStub.OptionallyConfigured<T, A>> =
          Grub(unionObject::class.graphQlName(), true,
              builder = UnionListStubImpl.newStub<UnionListStubImpl.OptionallyConfigured.NotNull<T, A>, T, A>(unionObject),
              nullableBuilder = UnionListStubImpl.newStub<UnionListStubImpl.OptionallyConfigured<T, A>, T, A>(unionObject))

      fun <T : QUnionType, A : ArgumentSpec> configured(unionObject: T)
          : NullableStubProvider<UnionListStub.Configured.NotNull<T, A>, UnionListStub.Configured<T, A>> =
          Grub(unionObject::class.graphQlName(), true,
              builder = UnionListStubImpl.newStub<UnionListStubImpl.Configured.NotNull<T, A>, T, A>(unionObject),
              nullableBuilder = UnionListStubImpl.newStub<UnionListStubImpl.Configured<T, A>, T, A>(unionObject))
    }


    class QlEnum : GraphQLListDelegate() {

      fun <T> stub(clazz: KClass<T>)
          : NoArgListProvider<T>
          where T : QEnumType,
                T : Enum<T> = schemaProvider { kontext ->
        newBuilder<T>() takingArguments ::Never resultingIn {
          EnumAdapterImpl(clazz, it.first, it.second.default).toDelegate(kontext)
        } providingInstead List::class
      }

      fun <T, A> optionallyConfigured(clazz: KClass<T>)
          : OptionallyConfiguredListProvider<T, A>
          where T : QEnumType,
                T : Enum<T>,
                A : ArgumentSpec =
          schemaProvider { kdelegateContext ->
            newBuilder<T>().withArgs<A>() takingArguments ::Sometimes resultingIn { (args, builder) ->
              EnumAdapterImpl(clazz, args, builder.default).toDelegate(kdelegateContext)
            } providingInstead List::class
          }

      fun <T, A> configured(clazz: KClass<T>)
          : ConfiguredListProvider<T, A>
          where T : QEnumType,
                T : Enum<T>,
                A : ArgumentSpec = schemaProvider { kdelegateContext ->

        newBuilder<T>().withArgs<A>() takingArguments ::Always resultingIn { (args, builder) ->
          EnumAdapterImpl(clazz, args, builder.default).toDelegate(kdelegateContext)
        } providingInstead List::class

      }

    }


    class Scalar : GraphQLListDelegate() {

      fun <T : CustomScalar> stub(clazz: KClass<T>)
          : StubProvider<CustomScalarListStub.NoArg<T>> =
          CustomListStubHandle.stub(clazz)

      fun <T : CustomScalar, A : ArgumentSpec> optionallyConfigured(clazz: KClass<T>)
          : StubProvider<CustomScalarListStub.OptionallyConfigured<T, A>> =
          CustomListStubHandle.optionallyConfiguredStub(clazz)

      fun <T : CustomScalar, A : ArgumentSpec> configured(clazz: KClass<T>)
          : StubProvider<CustomScalarListStub.Configured<T, A>> =
          CustomListStubHandle.configuredStub(clazz)
    }


    class QlString : GraphQLListDelegate() {

      fun stub(): NoArgProvider<Array<String>> = schemaProvider { (_, prop) ->
        newBuilder<Array<String>>() takingArguments ::Never resultingIn { (args, builder) ->
          StringArrayStub.create(prop.name, args, builder.default)
        }
      }.withAlternate { it.asNullable() }

      fun <A : ArgumentSpec> optionallyConfigured()
          : OptionallyConfiguredProvider<Array<String>, A> = schemaProvider { (_, prop) ->
        newBuilder<Array<String>>().withArgs<A>() takingArguments ::Sometimes resultingIn { (args, builder) ->
          StringArrayStub.create(prop.name, args, builder.default)
        }
      }.withAlternate { it allowing Nothing::class }

      fun <A : ArgumentSpec> configured()
          : ConfiguredProvider<Array<String>, A> = schemaProvider { (_, prop) ->
        newBuilder<Array<String>>().withArgs<A>() takingArguments ::Always resultingIn { (args, builder) ->
          StringArrayStub.create(prop.name, args, builder.default)
        }
      }.withAlternate { it allowing Nothing::class }
    }


    class QlInt : GraphQLListDelegate() {

      fun stub()
          : NoArgProvider<IntArray> = schemaProvider { kontext ->

        newBuilder(IntArray(0)) takingArguments ::Never resultingIn { (args, builder) ->
          IntArrayStub.create(kontext.second.name, args, builder.default)
        }
      }.withAlternate { it allowing Nothing::class }

      fun <A : ArgumentSpec> optionallyConfigured()
          : OptionallyConfiguredProvider<IntArray, A> = schemaProvider { kontext ->

        newBuilder(IntArray(0)).withArgs<A>() takingArguments ::Sometimes resultingIn { (args, builder) ->
          IntArrayStub.create(kontext.second.name, args, builder.default)
        }
      }.withAlternate { it allowing Nothing::class }


      fun <A : ArgumentSpec> configured()
          : ConfiguredProvider<IntArray, A> = schemaProvider { kontext ->

        newBuilder<IntArray>().withArgs<A>() takingArguments ::Always resultingIn {
          IntArrayStub.create(kontext.second.name, it.first, it.second.default)
        }
      }.withAlternate { it allowing Nothing::class }
    }


    class QlFloat : GraphQLListDelegate() {

      fun stub()
          : NoArgProvider<FloatArray> = schemaProvider { kontext ->

        newBuilder(FloatArray(0)) takingArguments ::Never resultingIn { (args, builder) ->
          FloatArrayStub.create(kontext.second.name, args, builder.default)
        }
      }.withAlternate { it allowing Nothing::class }

      fun <A : ArgumentSpec> optionallyConfigured()
          : OptionallyConfiguredProvider<FloatArray, A> = schemaProvider { kontext ->

        newBuilder(FloatArray(0)).withArgs<A>() takingArguments ::Sometimes resultingIn { (args, builder) ->
          FloatArrayStub.create(kontext.second.name, args, builder.default)
        }
      }.withAlternate { it allowing Nothing::class }


      fun <A : ArgumentSpec> configured()
          : ConfiguredProvider<FloatArray, A> = schemaProvider { kontext ->

        newBuilder<FloatArray>().withArgs<A>() takingArguments ::Always resultingIn {
          FloatArrayStub.create(kontext.second.name, it.first, it.second.default)
        }
      }.withAlternate { it allowing Nothing::class }
    }


    class QlBoolean : GraphQLListDelegate() {

      fun stub()
          : NoArgProvider<BooleanArray> = schemaProvider { kontext ->

        newBuilder(BooleanArray(0)) takingArguments ::Never resultingIn { (args, builder) ->
          BooleanArrayStub.create(kontext.second.name, args, builder.default)
        }
      }.withAlternate { it allowing Nothing::class }

      fun <A : ArgumentSpec> optionallyConfigured()
          : OptionallyConfiguredProvider<BooleanArray, A> = schemaProvider { kontext ->

        newBuilder(BooleanArray(0)).withArgs<A>() takingArguments ::Sometimes resultingIn { (args, builder) ->
          BooleanArrayStub.create(kontext.second.name, args, builder.default)
        }
      }.withAlternate { it allowing Nothing::class }


      fun <A : ArgumentSpec> configured()
          : ConfiguredProvider<BooleanArray, A> = schemaProvider { kontext ->

        newBuilder<BooleanArray>().withArgs<A>() takingArguments ::Always resultingIn {
          BooleanArrayStub.create(kontext.second.name, it.first, it.second.default)
        }
      }.withAlternate { it allowing Nothing::class }
    }
  }

  internal
  fun <T : Any> KClass<T>.graphQlName() = "${this.simpleName}"

}

