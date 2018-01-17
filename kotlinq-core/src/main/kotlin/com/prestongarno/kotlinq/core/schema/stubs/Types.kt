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

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.typeAdapter
import com.prestongarno.kotlinq.core.api.DefaultBuilderImpl
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Always
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Never
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Builder.ArgumentPolicy.Sometimes
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext.Companion.newBuilder
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.allowing
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlProperty.Companion.from
import com.prestongarno.kotlinq.core.properties.PropertyType
import com.prestongarno.kotlinq.core.schema.QType
import kotlin.reflect.KClass

interface TypeStub<in E : QType> {

  operator fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.Context<U?>


  interface NoArg<in E : QType> : TypeStub<E> {
    override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.NoArgContext<U?>

    interface NotNull<in E : QType> : TypeStub<E> {
      override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.NoArgContext<U>
    }
  }


  interface OptionallyConfigured<in E : QType, A : ArgumentSpec> : TypeStub<E> {
    override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.OptionallyConfiguredContext<U?, A>

    interface NotNull<in E : QType, A : ArgumentSpec> : TypeStub<E> {
      override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.OptionallyConfiguredContext<U, A>
    }
  }


  interface Configured<in E : QType, A : ArgumentSpec> : TypeStub<E> {
    override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.ConfiguredContext<U?, A>

    interface NotNull<in E : QType, A : ArgumentSpec> : TypeStub<E> {
      override fun <U : QModel<E>> invoke(init: () -> U): GraphqlDslBuilder.ConfiguredContext<U, A>
    }
  }
}


internal
sealed class TypeStubImpl<in E : QType>(val typeName: String, propertyName: String) {

  abstract fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.Context<U?>

  val qproperty: GraphQlProperty = from(typeName, false, propertyName, PropertyType.OBJECT)

  abstract
  class NotNullStub<in E : QType, out T : TypeStubImpl<E>>(typeName: String, propertyName: String) : TypeStubImpl<E>(typeName, propertyName) {
    abstract fun asNullable(): T
  }


  class Configured<in E : QType, A : ArgumentSpec>(
      clazz: KClass<E>,
      propertyName: String
  ) : TypeStubImpl<E>(clazz.graphQlName(), propertyName), TypeStub.Configured<E, A> {

    override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

    override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.ConfiguredContext<U?, A> =
        newBuilder<U>().withArgs<A>() takingArguments ::Always resultingIn adapter(init) allowing Nothing::class


    class NotNull<in E : QType, A : ArgumentSpec>(
        private val clazz: KClass<E>,
        propertyName: String
    ) : NotNullStub<E, Configured<E, A>>(clazz.graphQlName(), propertyName), TypeStub.Configured.NotNull<E, A> {

      override fun asNullable(): Configured<E, A> = Configured(clazz, qproperty.graphqlName)

      override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

      override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.ConfiguredContext<U, A> =
          newBuilder<U>().withArgs<A>() takingArguments ::Always resultingIn adapter(init)
    }

  }


  class OptionallyConfigured<in E : QType, A : ArgumentSpec>(
      clazz: KClass<E>,
      propertyName: String
  ) : TypeStubImpl<E>(clazz.graphQlName(), propertyName), TypeStub.OptionallyConfigured<E, A> {

    override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

    override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.OptionallyConfiguredContext<U?, A> =
        newBuilder<U>().withArgs<A>() takingArguments ::Sometimes resultingIn adapter(init) allowing Nothing::class


    class NotNull<in E : QType, A : ArgumentSpec>(
        private val clazz: KClass<E>,
        propertyName: String
    ) : NotNullStub<E, OptionallyConfigured<E, A>>(clazz.graphQlName(), propertyName), TypeStub.OptionallyConfigured.NotNull<E, A> {

      override fun asNullable() = OptionallyConfigured<E, A>(clazz, qproperty.graphqlName)

      override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

      override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.OptionallyConfiguredContext<U, A> =
          newBuilder<U>().withArgs<A>() takingArguments ::Sometimes resultingIn adapter(init)
    }

  }


  class NoArg<in E : QType>(
      clazz: KClass<E>,
      propertyName: String
  ) : TypeStubImpl<E>(clazz.graphQlName(), propertyName), TypeStub.NoArg<E> {

    override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

    override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.NoArgContext<U?> =
        newBuilder<U>() takingArguments ::Never resultingIn adapter(init) allowing Nothing::class


    class NotNull<in E : QType>(
        private val clazz: KClass<E>,
        propertyName: String
    ) : NotNullStub<E, NoArg<E>>(clazz.graphQlName(), propertyName), TypeStub.NoArg.NotNull<E> {

      override fun asNullable() = NoArg(clazz, qproperty.graphqlName)

      override fun <U : QModel<E>> invoke(init: () -> U) = build(init)

      override fun <U : QModel<E>> build(init: () -> U): GraphqlDslBuilder.NoArgContext<U> =
          newBuilder<U>() takingArguments ::Never resultingIn adapter(init)
    }

  }
}

internal
fun KClass<out QSchemaType>.graphQlName() = "$simpleName"


private
fun <U : QModel<E>, E : QType> TypeStubImpl<E>.adapter(init: () -> U)
    : (Pair<ArgumentSpec?, DefaultBuilderImpl<U, ArgumentSpec>>) -> GraphqlPropertyDelegate<U> =
    { typeAdapter(qproperty, init, it) }
