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

package com.prestongarno.kotlinq.core

import com.prestongarno.kotlinq.core.api.NullableStubProvider
import com.prestongarno.kotlinq.core.api.NullableStubProvider.Companion.delegationContext
import com.prestongarno.kotlinq.core.schema.CustomScalar
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.prestongarno.kotlinq.core.schema.stubs.CustomScalarStub

/**
 * The root type of all generated schema objects. Nested objects provide
 * handles for field declarations to obtain a delegation provider for class declarations
 * @author prestongarno
 */
interface QSchemaType {

  object QScalar {

    object String

    object Int

    object Float

    object Boolean

    object List {

      object String

      object Int

      object Float

      object Boolean

    }
  }

  object QTypes {

    inline fun <reified T : QType> stub() =
        delegationContext.type.stub(T::class)

    inline fun <reified T : QType, A : ArgumentSpec> optionallyConfigured() =
        delegationContext.type.optionallyConfigured<T, A>(T::class)

    inline fun <reified T : QType, A : ArgumentSpec> configured() =
        delegationContext.type.configured<T, A>(T::class)


    object List {
      inline fun <reified T : QType> stub() =
          delegationContext.type.list.stub(T::class)

    }
  }

  object QInterfaces {

    inline fun <reified T> stub()
        where T : QType,
              T : QInterface =
        delegationContext.iface.stub(T::class)

    inline fun <reified T, A> optionallyConfigured()
        where T : QType,
              T : QInterface,
              A : ArgumentSpec =
        delegationContext.iface.optionallyConfigured<T, A>(T::class)


    inline fun <reified T, A> configured()
        where T : QType,
              T : QInterface,
              A : ArgumentSpec =
        delegationContext.iface.configured<T, A>(T::class)


    object List
  }

  object QEnum {

    inline fun <reified T> stub()
        where T : Enum<T>,
              T : QEnumType =
        delegationContext.enum.stub(T::class)

    inline fun <reified T, A : ArgumentSpec> optionallyConfigured()
        where T : Enum<T>,
              T : QEnumType =
        delegationContext.enum.optionallyConfigured<T, A>(T::class)


    inline fun <reified T, A : ArgumentSpec> configured()
        where T : Enum<T>,
              T : QEnumType =
        delegationContext.enum.configured<T, A>(T::class)


    object List

  }

  object QCustomScalar {

    inline fun <reified T : CustomScalar> stub()
        : NullableStubProvider<CustomScalarStub.NoArg<T>, CustomScalarStub.NoArg.Nullable<T>> =
        delegationContext.scalar.stub(T::class)

    inline fun <reified T : CustomScalar, A : ArgumentSpec> optionallyConfigured() =
        delegationContext.scalar.optionallyConfigured<T, A>(T::class)

    inline fun <reified T : CustomScalar, A : ArgumentSpec> configured() =
        delegationContext.scalar.configured<T, A>(T::class)

    object List
  }

  object QUnion {

    fun <T : QUnionType> stub(union: T) =
        delegationContext.union.stub(union)

    fun <T : QUnionType, A : ArgumentSpec> optionallyConfigured(union: T) =
        delegationContext.union.optionallyConfigured<T, A>(union)


    fun <T : QUnionType, A : ArgumentSpec> configured(union: T) =
        delegationContext.union.configured<T, A>(union)

    object List

  }
}

