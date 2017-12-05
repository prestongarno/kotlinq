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

package com.prestongarno.kotlinq.core.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.DelegateProvider
import com.prestongarno.kotlinq.core.QEnumType
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.SchemaStub
import com.prestongarno.kotlinq.core.adapters.EnumConfigStubImpl
import com.prestongarno.kotlinq.core.adapters.EnumNoArgStub
import com.prestongarno.kotlinq.core.adapters.EnumOptionalArgStubQuery
import com.prestongarno.kotlinq.core.adapters.QField
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

interface EnumStub<T, out A : ArgumentSpec> : DelegateProvider<T> where T : QEnumType, T : Enum<*> {
  var default: T?

  fun config(argumentScope: A.() -> Unit)

  companion object {

    internal
    fun <T> noArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): EnumNoArgStub<T>
        where T : QEnumType, T : Enum<*> =
        EnumNoArgStub(qproperty, enumClass)

    internal
    fun <T, A> argStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): ConfigurableQuery<T, A>
        where T : QEnumType,
              T : Enum<*>, A : ArgumentSpec =
        EnumConfigStubImpl(qproperty, enumClass)

    internal
    fun <T, A> optionalArgStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): OptionalConfigQuery<T, A>
        where T : QEnumType,
              T : Enum<*>,
              A : ArgumentSpec =
        EnumOptionalArgStubQuery(qproperty, enumClass)
  }

  interface Query<T> : SchemaStub where T : QEnumType, T : Enum<*> {

    operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
        invoke(scope = empty()).provideDelegate(inst, property)

    operator fun invoke(
        arguments: ArgumentSpec = ArgBuilder(),
        scope: EnumStub<T, ArgumentSpec>.() -> Unit = empty()
    ): EnumStub<T, ArgumentSpec>
  }

  interface OptionalConfigQuery<T, A> : SchemaStub where T : QEnumType, T : Enum<*>, A : ArgumentSpec {

    operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
        invoke(empty()).provideDelegate(inst, property)

    operator fun invoke(
        arguments: A,
        scope: EnumStub<T, A>.() -> Unit = empty()
    ): EnumStub<T, A>

    operator fun invoke(
        scope: EnumStub<T, ArgumentSpec>.() -> Unit
    ): EnumStub<T, ArgumentSpec>
  }


  interface ConfigurableQuery<T, A> : SchemaStub where T : QEnumType, T : Enum<*>, A : ArgumentSpec {

    operator fun invoke(
        arguments: A,
        scope: EnumStub<T, A>.() -> Unit = empty()
    ): EnumStub<T, A>

  }

}