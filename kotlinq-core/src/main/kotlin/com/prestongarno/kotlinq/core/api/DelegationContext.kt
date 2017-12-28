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
import com.prestongarno.kotlinq.core.adapters.EnumAdapterImpl
import com.prestongarno.kotlinq.core.adapters.UnionStubImpl
import com.prestongarno.kotlinq.core.adapters.newUnionField
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.prestongarno.kotlinq.core.schema.stubs.EnumStub
import com.prestongarno.kotlinq.core.schema.stubs.UnionStub
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
    override val list: Lists.Type = Lists.Type()
  }

  class Interface : GraphQLDelegate() {
    override val list: Lists.Interface = Lists.Interface()
  }

  class Union : GraphQLDelegate() {

    fun <T> stub(schemaType: T)
        :
        BaseStubProvider<UnionStub<T, ArgBuilder>, QModel<*>?>
        where T : QUnionType = StubProvider.stub(schemaType::class.graphQlName()) { graphQlProperty, argumentSpec ->
      newUnionField(graphQlProperty, schemaType, argumentSpec ?: ArgBuilder())
    }

    fun <T, A> configuredStub(schemaType: T)
        :
        ConfiguredStubProvider<UnionStub<T, A>, A, QModel<*>?>
        where T : QUnionType,
              A : ArgumentSpec = StubProvider
        .configured<UnionStubImpl<T, A>, A, QModel<*>?>(schemaType::class.graphQlName()) { graphQlProperty, args ->
          newUnionField(graphQlProperty, schemaType, args)
        }

    override val list: Lists.Union = Lists.Union()

    private object Foo : QType
    private object Bar : QType

    private class SampleUnion : QUnionType by QUnionType.new() {
      fun onFoo(init: () -> QModel<Foo>) = on(init)
      fun onBar(init: () -> QModel<Bar>) = on(init)
    }

    private object SampleQuery : QType {
      val searchUnions by Union().configuredStub<SampleUnion, ArgBuilder>(SampleUnion())
      val optSearch by Union().stub(SampleUnion())
    }

    private class QuerySampleModel : QModel<SampleQuery>(SampleQuery) {

      val search: QModel<*>? by model.searchUnions(ArgBuilder()) {
        config { "Hello" with "World" }
        fragment {
          onFoo { TODO() }
          onBar { TODO() }
        }
      }

      val opt: QModel<*>? by model.optSearch {
        fragment {
          onFoo(::TODO)
          onBar(::TODO)
        }
      }
    }
  }

  class QlEnum : GraphQLDelegate() {

    /**
     * quick example TODO remove
     */
    private enum class FooEnum : QEnumType {
      ONE, TWO
    }

    private class FooArgs : ArgBuilder() {
      var isItGoodEnough: Boolean? by this.arguments
    }

    init {
      object : QModel<QType>(TODO()), QType {
        val ctor by stub<FooEnum, FooArgs>(FooEnum::class).asNullable()
        val ctor2 by configuredStub<FooEnum, FooArgs>(FooEnum::class)

        val foo: FooEnum? by ctor(FooArgs()) {
          config { isItGoodEnough = false }
          default = FooEnum.ONE
        }
        val by2 by ctor2(FooArgs())

        val foo3 by stub<FooEnum, FooArgs>(FooEnum::class)
        val foo6 by configuredStub<FooEnum, FooArgs>(FooEnum::class).asNullable()

        val foo4: FooEnum by foo3(FooArgs())
        val foo7: FooEnum? by foo6(FooArgs()) {
          config {
            isItGoodEnough = true && false
          }
        }
      }
    }

    fun <T, A> stub(clazz: KClass<T>)
        :
        StubProvider<EnumStub<T, A>, A, T>
        where T : Enum<*>,
              T : QEnumType,
              A : ArgBuilder = StubProvider.configurable<EnumAdapterImpl<T, A>, A, T>(clazz.graphQlName(), false) { graphQlProperty, args ->
      EnumAdapterImpl(graphQlProperty, clazz, args)
    }

    fun <T, A> configuredStub(clazz: KClass<T>)
        :
        ConfiguredStubProvider<EnumStub<T, A>, A, T>
        where T : Enum<*>,
              T : QEnumType,
              A : ArgBuilder = StubProvider.configured<EnumAdapterImpl<T, A>, A, T>(clazz.graphQlName(), false) { graphQlProperty, args ->
      EnumAdapterImpl(graphQlProperty, clazz, args)
    }

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

