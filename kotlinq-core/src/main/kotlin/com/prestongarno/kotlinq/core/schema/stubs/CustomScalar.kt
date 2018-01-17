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

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.CustomScalarField
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.api.DslEvaluationResult
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Always
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Never
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Sometimes
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Companion.newBuilder
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.schemaProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.PropertyType
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.schema.CustomScalar
import java.io.InputStream
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


internal sealed
class Mapper<in T, out U>(val init: (T) -> U?) {

  fun transform(raw: T): U? = init(raw)

  class StringMapper<out U>(init: (String) -> U?) : Mapper<String, U>(init)
  class StreamMapper<out U>(init: (InputStream) -> U?) : Mapper<InputStream, U>(init)
}


interface CustomScalarStub<X : CustomScalar> {

  fun <T : Any> fromString(deserializer: (String) -> T?): GraphqlDslBuilder.Context<T>

  fun <T : Any> fromStream(deserializer: (InputStream) -> T?): GraphqlDslBuilder.Context<T>


  interface NoArg<X : CustomScalar> : CustomScalarStub<X>, DelegateProvider<String> {
    override fun <T : Any> fromString(deserializer: (String) -> T?): GraphqlDslBuilder.NoArgContext<T>
    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?): GraphqlDslBuilder.NoArgContext<T>
  }


  interface Configured<X : CustomScalar, A : ArgumentSpec> : CustomScalarStub<X> {
    override fun <T : Any> fromString(deserializer: (String) -> T?): GraphqlDslBuilder.ConfiguredContext<T, A>
    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?): GraphqlDslBuilder.ConfiguredContext<T, A>
  }


  interface OptionallyConfigured<X : CustomScalar, A : ArgumentSpec> : CustomScalarStub<X>, DelegateProvider<String> {
    override fun <T : Any> fromString(deserializer: (String) -> T?): GraphqlDslBuilder.OptionallyConfiguredContext<T, A>
    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?): GraphqlDslBuilder.OptionallyConfiguredContext<T, A>
  }

}

internal
sealed class CustomScalarPreDelegate<X, T : Any>(
    val graphqlProperty: GraphQlProperty,
    val deserializer: Mapper<*, T>
) where X : GraphqlDslBuilder.Context<T>,
        X : GraphQlDelegateContext<T, *> {

  fun toDelegate(result: DslEvaluationResult<T>) = CustomScalarField(
      graphqlProperty,
      result.first.toMap(),
      deserializer,
      result.second.default
  )

  abstract fun newContext(): X


  class ConfiguredDelegate<T : Any, A : ArgumentSpec>(qproperty: GraphQlProperty, deserializer: Mapper<*, T>)
    : CustomScalarPreDelegate<GraphQlDelegateContext.Configured<T, A>, T>(qproperty, deserializer) {

    override fun newContext() =
        newBuilder<T>().withArgs<A>() takingArguments ::Always resultingIn this::toDelegate
  }


  class OptionallyConfiguredDelegate<T : Any, A : ArgumentSpec>(qproperty: GraphQlProperty, deserializer: Mapper<*, T>)
    : CustomScalarPreDelegate<GraphQlDelegateContext.OptionallyConfigured<T, A>, T>(qproperty, deserializer) {

    override fun newContext() =
        newBuilder<T>().withArgs<A>() takingArguments ::Sometimes resultingIn this::toDelegate
  }


  class NoArgDelegate<T : Any>(qproperty: GraphQlProperty, deserializer: Mapper<*, T>)
    : CustomScalarPreDelegate<GraphQlDelegateContext.NoArg<T>, T>(qproperty, deserializer) {

    override fun newContext() =
        newBuilder<T>() takingArguments ::Never resultingIn this::toDelegate

  }


}


internal
sealed class CustomStubHandle<out E : CustomScalar>(qpropertyName: String, typeName: String) {

  val qproperty = GraphQlProperty.from(typeName, false, qpropertyName, PropertyType.CUSTOM_SCALAR)


  class NoArgImpl<E : CustomScalar>(qpropertyName: String, typeName: String)
    : CustomStubHandle<E>(qpropertyName, typeName), CustomScalarStub.NoArg<E> {

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) = CustomScalarPreDelegate
        .NoArgDelegate(qproperty, Mapper.StringMapper { it })
        .newContext()
        .provideDelegate(inst, property)
        .bindToContext(inst)

    override fun <T : Any> fromString(deserializer: (String) -> T?) = CustomScalarPreDelegate
        .NoArgDelegate(qproperty, Mapper.StringMapper(deserializer))
        .newContext()

    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?) = CustomScalarPreDelegate
        .NoArgDelegate(qproperty, Mapper.StreamMapper(deserializer))
        .newContext()
  }


  class ConfiguredImpl<E : CustomScalar, A : ArgumentSpec>(qpropertyName: String, typeName: String)
    : CustomStubHandle<E>(qpropertyName, typeName), CustomScalarStub.Configured<E, A> {

    override fun <T : Any> fromString(deserializer: (String) -> T?) = CustomScalarPreDelegate
        .ConfiguredDelegate<T, A>(qproperty, Mapper.StringMapper(deserializer))
        .newContext()

    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?) = CustomScalarPreDelegate
        .ConfiguredDelegate<T, A>(qproperty, Mapper.StreamMapper(deserializer))
        .newContext()
  }


  class OptionallyConfiguredImpl<E : CustomScalar, A : ArgumentSpec>(qpropertyName: String, typeName: String)
    : CustomStubHandle<E>(qpropertyName, typeName), CustomScalarStub.OptionallyConfigured<E, A> {

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<String> {
      return CustomScalarPreDelegate
          .OptionallyConfiguredDelegate<String, A>(qproperty, Mapper.StringMapper { it })
          .newContext()
          .provideDelegate(inst, property)
          .bindToContext(inst)
    }

    override fun <T : Any> fromString(deserializer: (String) -> T?) = CustomScalarPreDelegate
        .OptionallyConfiguredDelegate<T, A>(qproperty, Mapper.StringMapper(deserializer))
        .newContext()

    override fun <T : Any> fromStream(deserializer: (InputStream) -> T?) = CustomScalarPreDelegate
        .OptionallyConfiguredDelegate<T, A>(qproperty, Mapper.StreamMapper(deserializer))
        .newContext()
  }

  companion object {

    internal
    fun <E : CustomScalar> stub(clazz: KClass<E>) = schemaProvider {
      create<CustomStubHandle.NoArgImpl<E>>("${clazz.simpleName}", it.second.name)
    }

    internal
    fun <E : CustomScalar, A : ArgumentSpec> optionallyConfiguredStub(clazz: KClass<E>) = schemaProvider {
      create<CustomStubHandle.OptionallyConfiguredImpl<E, A>>("${clazz.simpleName}", it.second.name)
    }

    internal
    fun <E : CustomScalar, A : ArgumentSpec> configuredStub(clazz: KClass<E>) = schemaProvider {
      create<CustomStubHandle.ConfiguredImpl<E, A>>("${clazz.simpleName}", it.second.name)
    }

    private
    inline fun <reified T : CustomStubHandle<CustomScalar>> create(typeName: String, propertyName: String): T = when (T::class) {
      CustomStubHandle.NoArgImpl::class -> CustomStubHandle.NoArgImpl<CustomScalar>(propertyName, typeName) as T
      CustomStubHandle.OptionallyConfiguredImpl::class -> CustomStubHandle.OptionallyConfiguredImpl<CustomScalar, ArgumentSpec>(propertyName, typeName) as T
      CustomStubHandle.ConfiguredImpl::class -> CustomStubHandle.ConfiguredImpl<CustomScalar, ArgumentSpec>(propertyName, typeName) as T
      else -> TODO("learn me some algebraic data types in kotlin")
    }

  }

}

