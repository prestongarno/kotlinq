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

package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.EnumConfigStubImpl
import com.prestongarno.ktq.adapters.EnumNoArgStub
import com.prestongarno.ktq.adapters.EnumOptionalArgStubQuery
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.internal.empty
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

interface EnumStub<T, out A : ArgBuilder> : DelegateProvider<T> where T : QEnumType, T : Enum<*> {
  var default: T?

  fun config(argumentScope: A.() -> Unit)

  companion object {

    internal fun <T> noArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): EnumNoArgStub<T>
        where T : QEnumType, T : Enum<*> =
        EnumNoArgStub(qproperty, enumClass)

    internal fun <T, A> argStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): EnumStub.ConfigurableQuery<T, A>
        where T : QEnumType,
              T : Enum<*>, A : ArgBuilder =
        EnumConfigStubImpl(qproperty, enumClass)

    internal fun <T, A> optionalArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): EnumStub.OptionalConfigQuery<T, A>
        where T : QEnumType,
              T : Enum<*>, A : ArgBuilder =
        EnumOptionalArgStubQuery(qproperty, enumClass)
  }

  interface Query<T> : SchemaStub where T : QEnumType, T : Enum<*> {

    operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
        invoke(scope = empty()).provideDelegate(inst, property)

    operator fun invoke(
        arguments: ArgBuilder = ArgBuilder(),
        scope: EnumStub<T, ArgBuilder>.() -> Unit = empty()
    ): EnumStub<T, ArgBuilder>
  }

  interface OptionalConfigQuery<T, A> : SchemaStub where T : QEnumType, T : Enum<*>, A : ArgBuilder {

    operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
        invoke(empty()).provideDelegate(inst, property)

    operator fun invoke(
        arguments: A,
        scope: EnumStub<T, A>.() -> Unit = empty()
    ): EnumStub<T, A>

    operator fun invoke(
        scope: EnumStub<T, ArgBuilder>.() -> Unit
    ): EnumStub<T, ArgBuilder>
  }


  interface ConfigurableQuery<T, A> : SchemaStub where T : QEnumType, T : Enum<*>, A : ArgBuilder {

    operator fun invoke(
        arguments: A,
        scope: EnumStub<T, A>.() -> Unit = empty()
    ): EnumStub<T, A>

  }

}