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
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.api.GraphQlDelegateContext
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.Stub
import com.prestongarno.kotlinq.core.api.StubProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlProperty.Companion.from
import com.prestongarno.kotlinq.core.properties.PropertyType
import com.prestongarno.kotlinq.core.schema.QEnumType
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal
class EnumDelegateStub<out X, T, A> private constructor(val qproperty: GraphQlProperty, val clazz: KClass<T>, val provider: X)
  : Stub<X>
    where X : GraphqlDslBuilder.Context<T>,
          X : GraphQlDelegateContext<T, A>,
          T : QEnumType,
          T : Enum<T>,
          A : ArgumentSpec {

  override fun getValue(inst: QSchemaType, property: KProperty<*>): X = provider


  companion object {

    internal
    fun <T> newBuilder(clazz: KClass<T>)
        where T : QEnumType,
              T : Enum<T> = Builder.create(clazz)
  }

  internal
  class Builder<T, A> private constructor(private val clazz: KClass<T>) where T : QEnumType, T : Enum<T>, A : ArgumentSpec {

    fun <A : ArgumentSpec> withArgs(): Builder<T, A> = Builder(clazz)

    fun <X> build(provider: X): StubProvider<X>
        where X : GraphqlDslBuilder.Context<T>,
              X : GraphQlDelegateContext<T, A> = object : StubProvider<X> {
      override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) =
          EnumDelegateStub(from("${clazz.simpleName}", false, property.name, PropertyType.ENUM), clazz, provider)
    }

    companion object {
      internal
      fun <T> create(clazz: KClass<T>)
          where T : QEnumType,
                T : Enum<T> = Builder<T, ArgBuilder>(clazz)
    }
  }

}
