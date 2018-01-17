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
import com.prestongarno.kotlinq.core.api.StubProvider.Companion.delegationContext
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

    object String {

      fun stub() = delegationContext.stringScalar.stub()

      fun <A : ArgumentSpec> optionallyConfigured() =
          delegationContext.stringScalar.optionallyConfigured<A>()

      fun <A : ArgumentSpec> configured() =
          delegationContext.stringScalar.configured<A>()
    }

    object Int {

      fun stub() = delegationContext.intScalar.stub()

      fun <A : ArgumentSpec> optionallyConfigured() =
          delegationContext.intScalar.optionallyConfigured<A>()

      fun <A : ArgumentSpec> configured() =
          delegationContext.intScalar.configured<A>()

    }

    object Float {

      fun stub() = delegationContext.floatScalar.stub()

      fun <A : ArgumentSpec> optionallyConfigured() =
          delegationContext.floatScalar.optionallyConfigured<A>()

      fun <A : ArgumentSpec> configured() =
          delegationContext.floatScalar.configured<A>()
    }

    object Boolean {

      fun stub() = delegationContext.booleanScalar.stub()

      fun <A : ArgumentSpec> optionallyConfigured() =
          delegationContext.booleanScalar.optionallyConfigured<A>()

      fun <A : ArgumentSpec> configured() =
          delegationContext.booleanScalar.configured<A>()
    }

    object List {

      object String {

        fun stub() = delegationContext.stringScalar.list.stub()

        fun <A : ArgumentSpec> optionallyConfigured() =
            delegationContext.stringScalar.list.optionallyConfigured<A>()

        fun <A : ArgumentSpec> configured() =
            delegationContext.stringScalar.list.configured<A>()
      }

      object Int {

        fun stub() = delegationContext.intScalar.list.stub()

        fun <A : ArgumentSpec> optionallyConfigured() =
            delegationContext.intScalar.list.optionallyConfigured<A>()

        fun <A : ArgumentSpec> configured() =
            delegationContext.intScalar.list.configured<A>()

      }

      object Float {

        fun stub() = delegationContext.floatScalar.list.stub()

        fun <A : ArgumentSpec> optionallyConfigured() =
            delegationContext.floatScalar.list.optionallyConfigured<A>()

        fun <A : ArgumentSpec> configured() =
            delegationContext.floatScalar.list.configured<A>()
      }

      object Boolean {

        fun stub() = delegationContext.booleanScalar.list.stub()

        fun <A : ArgumentSpec> optionallyConfigured() =
            delegationContext.booleanScalar.list.optionallyConfigured<A>()

        fun <A : ArgumentSpec> configured() =
            delegationContext.booleanScalar.list.configured<A>()
      }

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

      inline fun <reified T : QType, A : ArgumentSpec> optionallyConfigured() =
          delegationContext.type.list.optionallyConfigured<T, A>(T::class)

      inline fun <reified T : QType, A : ArgumentSpec> configured() =
          delegationContext.type.list.configured<T, A>(T::class)
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


    object List {
      inline fun <reified T> stub()
          where T : QType, T : QInterface =
          delegationContext.iface.list.stub(T::class)

      inline fun <reified T, A : ArgumentSpec> optionallyConfigured()
          where T : QType, T : QInterface =
          delegationContext.iface.list.optionallyConfigured<T, A>(T::class)

      inline fun <reified T, A : ArgumentSpec> configured()
          where T : QType, T : QInterface =
          delegationContext.iface.list.configured<T, A>(T::class)
    }
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


    object List {

    inline fun <reified T> stub()
        where T : Enum<T>,
              T : QEnumType =
        delegationContext.enum.list.stub(T::class)

    inline fun <reified T, A : ArgumentSpec> optionallyConfigured()
        where T : Enum<T>,
              T : QEnumType =
        delegationContext.enum.list.optionallyConfigured<T, A>(T::class)


    inline fun <reified T, A : ArgumentSpec> configured()
        where T : Enum<T>,
              T : QEnumType =
        delegationContext.enum.list.configured<T, A>(T::class)
    }

  }

  object QCustomScalar {

    inline fun <reified T : CustomScalar> stub() =
        delegationContext.scalar.stub(T::class)

    inline fun <reified T : CustomScalar, A : ArgumentSpec> optionallyConfigured() =
        delegationContext.scalar.optionallyConfigured<T, A>(T::class)

    inline fun <reified T : CustomScalar, A : ArgumentSpec> configured() =
        delegationContext.scalar.configured<T, A>(T::class)

    object List {

      inline fun <reified T : CustomScalar> stub() =
          delegationContext.scalar.list.stub(T::class)

      inline fun <reified T : CustomScalar, A : ArgumentSpec> optionallyConfigured() =
          delegationContext.scalar.list.optionallyConfigured<T, A>(T::class)

      inline fun <reified T : CustomScalar, A : ArgumentSpec> configured() =
          delegationContext.scalar.list.configured<T, A>(T::class)
    }
  }

  object QUnion {

    fun <T : QUnionType> stub(union: T) =
        delegationContext.union.stub(union)

    fun <T : QUnionType, A : ArgumentSpec> optionallyConfigured(union: T) =
        delegationContext.union.optionallyConfigured<T, A>(union)

    fun <T : QUnionType, A : ArgumentSpec> configured(union: T) =
        delegationContext.union.configured<T, A>(union)

    object List {

      fun <T : QUnionType> stub(union: T) =
          delegationContext.union.list.stub(union)

      fun <T : QUnionType, A : ArgumentSpec> optionallyConfigured(union: T) =
          delegationContext.union.list.optionallyConfigured<T, A>(union)

      fun <T : QUnionType, A : ArgumentSpec> configured(union: T) =
          delegationContext.union.list.configured<T, A>(union)
    }

  }
}

