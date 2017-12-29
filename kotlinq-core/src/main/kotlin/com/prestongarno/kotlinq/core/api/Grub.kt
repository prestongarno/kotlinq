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
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.PreDelegate
import com.prestongarno.kotlinq.core.properties.BasicDelegateProvider
import com.prestongarno.kotlinq.core.properties.ConfigurableDelegateProvider
import com.prestongarno.kotlinq.core.properties.GraphQlDelegateProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.basicDelegate
import com.prestongarno.kotlinq.core.properties.configurableDelegate
import com.prestongarno.kotlinq.core.properties.configuredDelegate
import com.prestongarno.kotlinq.core.properties.graphQlProperty
import kotlin.reflect.KProperty

interface BaseStubProvider<out U : GraphqlDslBuilder<ArgBuilder>, out T> : StubProvider<U, ArgBuilder, T> {
  override fun provideDelegate(inst: QSchemaType, property: KProperty<*>): Stub<BasicDelegateProvider<U, T>>
}

interface StubProvider<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, out T> : ConfiguredStubProvider<U, A, T> {

  override operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): Stub<ConfigurableDelegateProvider<U, A, T>>

  override fun asNullable(): StubProvider<U, A, T?>

  companion object {

    internal fun <T, V> stub(
        typeName: String,
        isList: Boolean = false,
        onInit: (GraphQlProperty, ArgBuilder?) -> T
    ): BaseStubProvider<T, V>
        where T : GraphqlDslBuilder<ArgBuilder>,
              T : PreDelegate<GraphqlPropertyDelegate<V>, V> =
        BasicGrub(typeName, isList, onInit)


    internal fun <T, A, Z> configurable(
        typeName: String,
        isList: Boolean = false,
        onInit: (GraphQlProperty, A?) -> T
    ): StubProvider<T, A, Z>
        where T : GraphqlDslBuilder<A>,
              T : PreDelegate<GraphqlPropertyDelegate<Z>, Z>,
              A : ArgumentSpec =
        ConfigurableGrub(typeName, isList, onInit)

    internal
    fun <T, A, Z> configured(
        typeName: String,
        isList: Boolean = false,
        onInit: (GraphQlProperty, A) -> T
    ): ConfiguredStubProvider<T, A, Z>
        where T : GraphqlDslBuilder<A>,
              T : PreDelegate<GraphqlPropertyDelegate<Z>, Z>,
              A : ArgumentSpec =
        ConfiguredGrub(typeName, isList, onInit)

    internal
    @PublishedApi val delegationContext: DelegationContext = DefaultDelegationContext()
  }
}

interface ConfiguredStubProvider<out U : GraphqlDslBuilder<A>, A : ArgumentSpec, out T> {
  operator fun provideDelegate(inst: QSchemaType, property: KProperty<*>): Stub<GraphQlDelegateProvider<U, A, T>>
  fun asNullable(): ConfiguredStubProvider<U, A, T?>
}


interface Stub<out T> {
  operator fun getValue(inst: QSchemaType, property: KProperty<*>): T
}

/**
 * Grand Unified Bootloader for SchemaType definitions/stubs.
 * solves the problem of not knowing the field's GraphQL property name from the generated type hierarchy
 * when generating queries/payloads
 *
 * Delegation inception : this property delegate is delegated to in order to get the property name and type of the
 * schema field, then passes it off to the backing field, which is another delegate in order for the
 * actual delegation to happen fragment a schema model implementation
 *
 * This class does minimal work in order to reduce added complexity - it simply gets passed a function
 * which, when applied a string, produces the correct schemastub.  When delegated to by schemastub types,
 * fragment `getValue` for the schemastub it simply invokes the function with the prop of the graphqlName that it's
 * delegating to. This way, the delegate property can be passed to the delegate/schemastub type without having
 * to resort to hard-wired  &/or needlessly complex metadata methods such as (god forbid) annotations */
internal sealed class Grub<out U, in A : ArgumentSpec, out T>(val typeName: String, val isList: Boolean = false)

private class ConfigurableGrub<out U, A : ArgumentSpec, out T>(
    typeName: String,
    isList: Boolean = false,
    val schemaPropertyInit: (GraphQlProperty, A?) -> U
) : Grub<U, A, T>(typeName, isList), StubProvider<U, A, T>
    where U : GraphqlDslBuilder<A>,
          U : PreDelegate<GraphqlPropertyDelegate<T>, T> {

  override fun asNullable(): StubProvider<U, A, T?> = ConfigurableGrub(typeName, isList) { graphQlProperty, args ->
    schemaPropertyInit(graphQlProperty, args).apply {
      flagNullable(true)
    }
  }

  override fun provideDelegate(inst: QSchemaType, property: KProperty<*>): Stub<ConfigurableDelegateProvider<U, A, T>> =

      object : Stub<ConfigurableDelegateProvider<U, A, T>> {

        private val qproperty = graphQlProperty(typeName, isList, property.name)

        private val value by lazy {
          configurableDelegate<U, A, T> { schemaPropertyInit(qproperty, it) }
        }

        override fun getValue(inst: QSchemaType, property: KProperty<*>): ConfigurableDelegateProvider<U, A, T> = value
      }
}

private class ConfiguredGrub<out U, A, out T>(
    typeName: String,
    isList: Boolean,
    private val schemaPropertyInit: (GraphQlProperty, A) -> U
) : Grub<U, A, T>(typeName, isList), ConfiguredStubProvider<U, A, T>
    where U : GraphqlDslBuilder<A>,
          U : PreDelegate<GraphqlPropertyDelegate<T>, T>,
          A : ArgumentSpec {

  override fun asNullable(): ConfiguredStubProvider<U, A, T?> = ConfiguredGrub(typeName, isList) { graphQlProperty, args ->
    schemaPropertyInit(graphQlProperty, args).apply {
      flagNullable(true)
    }
  }

  private lateinit var qproperty: GraphQlProperty
  val lazyInitializer = lazy { configuredDelegate<U, A, T> { schemaPropertyInit(qproperty, it) } }

  override fun provideDelegate(inst: QSchemaType, property: KProperty<*>): Stub<GraphQlDelegateProvider<U, A, T>> {
    qproperty = graphQlProperty(typeName, isList, property.name)
    return object : Stub<GraphQlDelegateProvider<U, A, T>> {
      private val value by lazyInitializer
      override fun getValue(inst: QSchemaType, property: KProperty<*>): GraphQlDelegateProvider<U, A, T> = value
    }
  }
}

private class BasicGrub<out T, out V>(
    typeName: String,
    isList: Boolean,
    private val schemaPropertyInit: (GraphQlProperty, ArgBuilder?) -> T
) : Grub<T, ArgBuilder, V>(typeName, isList), BaseStubProvider<T, V>
    where T : GraphqlDslBuilder<ArgBuilder>,
          T : PreDelegate<GraphqlPropertyDelegate<V>, V> {

  override fun provideDelegate(inst: QSchemaType, property: KProperty<*>): Stub<BasicDelegateProvider<T, V>> =
      object : Stub<BasicDelegateProvider<T, V>> {
        private val qproperty = graphQlProperty(typeName, isList, property.name)
        private val value = basicDelegate<T, V> { schemaPropertyInit(qproperty, it as? ArgBuilder ?: ArgBuilder()) }
        override fun getValue(inst: QSchemaType, property: KProperty<*>): BasicDelegateProvider<T, V> = value
      }

  override fun asNullable(): StubProvider<T, ArgBuilder, V?> =
      BasicGrub(typeName, isList) { graphQlProperty, argumentSpec ->
        schemaPropertyInit(graphQlProperty, argumentSpec).apply {
          flagNullable(true)
        }
      }
}

