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

package com.prestongarno.kotlinq.core

import com.prestongarno.kotlinq.core.api.StubProvider
import com.prestongarno.kotlinq.core.api.StubProvider.Companion.delegationContext
import com.prestongarno.kotlinq.core.stubs.BooleanArrayDelegate
import com.prestongarno.kotlinq.core.stubs.BooleanDelegate
import com.prestongarno.kotlinq.core.stubs.CustomScalarListStub
import com.prestongarno.kotlinq.core.stubs.CustomScalarStub
import com.prestongarno.kotlinq.core.stubs.EnumListStub
import com.prestongarno.kotlinq.core.stubs.EnumStub
import com.prestongarno.kotlinq.core.stubs.FloatArrayDelegate
import com.prestongarno.kotlinq.core.stubs.FloatDelegate
import com.prestongarno.kotlinq.core.stubs.IntArrayDelegate
import com.prestongarno.kotlinq.core.stubs.IntDelegate
import com.prestongarno.kotlinq.core.stubs.InterfaceListStub
import com.prestongarno.kotlinq.core.stubs.InterfaceStub
import com.prestongarno.kotlinq.core.stubs.StringArrayDelegate
import com.prestongarno.kotlinq.core.stubs.StringDelegate
import com.prestongarno.kotlinq.core.stubs.TypeListStub
import com.prestongarno.kotlinq.core.stubs.TypeStub
import com.prestongarno.kotlinq.core.stubs.UnionListStub
import com.prestongarno.kotlinq.core.stubs.UnionStub

/**
 * The root type of all generated schema objects. Nested objects provide
 * handles for field declarations to obtain a delegation provider for class declarations
 * @author prestongarno
 */
interface QSchemaType {

  object QScalar {

    object String {

      fun stub()
          :
          StubProvider<StringDelegate.Query> =
          delegationContext.stringScalar.stub()

      fun <A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<StringDelegate.OptionalConfigQuery<A>> =
          delegationContext.stringScalar.optionalConfigStub()

      fun <A : ArgumentSpec> configStub()
          :
          StubProvider<StringDelegate.ConfigurableQuery<A>> =
          delegationContext.stringScalar.configStub()

    }

    object Int {

      fun stub()
          :
          StubProvider<IntDelegate.Query> =
          delegationContext.intScalar.stub()


      fun <A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<IntDelegate.OptionalConfigQuery<A>> =
          delegationContext.intScalar.optionalConfigStub()

      fun <A : ArgumentSpec> configStub()
          :
          StubProvider<IntDelegate.ConfigurableQuery<A>> =
          delegationContext.intScalar.configStub()

    }

    object Float {

      fun stub()
          :
          StubProvider<FloatDelegate.Query> =
          delegationContext.floatScalar.stub()


      fun <A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<FloatDelegate.OptionalConfigQuery<A>> =
          delegationContext.floatScalar.optionalConfigStub()

      fun <A : ArgumentSpec> configStub()
          :
          StubProvider<FloatDelegate.ConfigurableQuery<A>> =
          delegationContext.floatScalar.configStub()

    }

    object Boolean {

      fun stub()
          :
          StubProvider<BooleanDelegate.Query> =
          delegationContext.booleanScalar.stub()

      fun <A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<BooleanDelegate.OptionalConfigQuery<A>> =
          delegationContext.booleanScalar.optionalConfigStub()

      fun <A : ArgumentSpec> configStub()
          :
          StubProvider<BooleanDelegate.ConfigurableQuery<A>> =
          delegationContext.booleanScalar.configStub()

    }

    object List {

      object String {

        fun stub()
            :
            StubProvider<StringArrayDelegate.Query> =
            delegationContext.stringScalar.list.stub()

        fun <A : ArgumentSpec> optionalConfigStub()
            :
            StubProvider<StringArrayDelegate.OptionalConfigQuery<A>> =
            delegationContext.stringScalar.list.optionalConfigStub()

        fun <A : ArgumentSpec> configStub()
            :
            StubProvider<StringArrayDelegate.ConfigurableQuery<A>> =
            delegationContext.stringScalar.list.configStub()

      }

      object Int {

        fun stub()
            :
            StubProvider<IntArrayDelegate.Query> =
            delegationContext.intScalar.list.stub()

        fun <A : ArgumentSpec> optionalConfigStub()
            :
            StubProvider<IntArrayDelegate.OptionalConfigQuery<A>> =
            delegationContext.intScalar.list.optionalConfigStub()

        fun <A : ArgumentSpec> configStub()
            :
            StubProvider<IntArrayDelegate.ConfigurableQuery<A>> =
            delegationContext.intScalar.list.configStub()

      }

      object Float {

        fun stub()
            :
            StubProvider<FloatArrayDelegate.Query> =
            delegationContext.floatScalar.list.stub()

        fun <A : ArgumentSpec> optionalConfigStub()
            :
            StubProvider<FloatArrayDelegate.OptionalConfigQuery<A>> =
            delegationContext.floatScalar.list.optionalConfigStub()

        fun <A : ArgumentSpec> configStub()
            :
            StubProvider<FloatArrayDelegate.ConfigurableQuery<A>> =
            delegationContext.floatScalar.list.configStub()

      }

      object Boolean {

        fun stub()
            :
            StubProvider<BooleanArrayDelegate.Query> =
            delegationContext.booleanScalar.list.stub()

        fun <A : ArgumentSpec> optionalConfigStub()
            :
            StubProvider<BooleanArrayDelegate.OptionalConfigQuery<A>> =
            delegationContext.booleanScalar.list.optionalConfigStub()

        fun <A : ArgumentSpec> configStub()
            :
            StubProvider<BooleanArrayDelegate.ConfigurableQuery<A>> =
            delegationContext.booleanScalar.list.configStub()

      }
    }
  }

  object QTypes {

    inline fun <reified T : QType> stub()
        :
        StubProvider<TypeStub.Query<T>> =
        delegationContext.type.query(T::class)

    inline fun <reified T : QType, A : ArgumentSpec> optionalConfigStub()
        :
        StubProvider<TypeStub.OptionalConfigQuery<T, A>> =
        delegationContext.type.optionalConfigStub(T::class)

    inline fun <reified T : QType, A : ArgumentSpec> configStub()
        :
        StubProvider<TypeStub.ConfigurableQuery<T, A>> =
        delegationContext.type.configStub(T::class)


    object List {

      inline fun <reified T : QType> stub()
          :
          StubProvider<TypeListStub.Query<T>> =
          delegationContext.type.list.query(T::class)

      inline fun <reified T : QType, A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<TypeListStub.OptionalConfigQuery<T, out A>> =
          delegationContext.type.list.optionalConfigStub(T::class)

      inline fun <reified T : QType, A : ArgumentSpec> configStub()
          :
          StubProvider<TypeListStub.ConfigurableQuery<T, out A>> =
          delegationContext.type.list.configStub(T::class)
    }
  }

  object QInterfaces {

    inline fun <reified T> stub()
        :
        StubProvider<InterfaceStub.Query<T>>
        where T : QType,
              T : QInterface =
        delegationContext.iface.stub(T::class)

    inline fun <reified T, A> optionalConfigStub()
        :
        StubProvider<InterfaceStub.OptionalConfigQuery<T, out A>>
        where T : QType,
              T : QInterface,
              A : ArgumentSpec =
        delegationContext.iface.optionalConfigStub(T::class)


    inline fun <reified T, A> configStub()
        :
        StubProvider<InterfaceStub.ConfigurableQuery<T, out A>>
        where T : QType,
              T : QInterface,
              A : ArgumentSpec =
        delegationContext.iface.configStub(T::class)


    object List {

      inline fun <reified T> stub()
          :
          StubProvider<InterfaceListStub.Query<T>>
          where T : QType,
                T : QInterface =
          delegationContext.iface.list.stub(T::class)

      inline fun <reified T, A> optionalConfigStub()
          :
          StubProvider<InterfaceListStub.OptionalConfigQuery<T, out A>>
          where T : QType,
                T : QInterface,
                A : ArgumentSpec =
          delegationContext.iface.list.optionalConfigStub(T::class)

      inline fun <reified T, A> configStub()
          :
          StubProvider<InterfaceListStub.ConfigurableQuery<T, out A>>
          where T : QType,
                T : QInterface,
                A : ArgumentSpec =
          delegationContext.iface.list.configStub(T::class)
    }
  }

  object QEnum {

    inline fun <reified T> stub()
        :
        StubProvider<EnumStub.Query<T>>
        where T : Enum<*>,
              T : QEnumType =
        delegationContext.enum.stub(T::class)

    inline fun <reified T, A : ArgumentSpec> optionalConfigStub()
        :
        StubProvider<EnumStub.OptionalConfigQuery<T, A>>
        where T : Enum<*>,
              T : QEnumType =
        delegationContext.enum.optionalConfigStub(T::class)


    inline fun <reified T, A : ArgumentSpec> configStub()
        :
        StubProvider<EnumStub.ConfigurableQuery<T, A>>
        where T : Enum<*>,
              T : QEnumType =
        delegationContext.enum.configStub(T::class)


    object List {

      inline fun <reified T> stub()
          :
          StubProvider<EnumListStub.Query<T>>
          where T : Enum<*>,
                T : QEnumType =
          delegationContext.enum.list.stub(T::class)

      inline fun <reified T, A> optionalConfigStub()
          :
          StubProvider<EnumListStub.OptionalConfigQuery<T, A>>
          where T : Enum<*>,
                T : QEnumType,
                A : ArgumentSpec =
          delegationContext.enum.list.optionalConfigStub(T::class)

      inline fun <reified T, A> configStub()
          :
          StubProvider<EnumListStub.ConfigurableQuery<T, A>>
          where T : Enum<*>,
                T : QEnumType,
                A : ArgumentSpec =
          delegationContext.enum.list.configStub(T::class)

    }

  }

  object QCustomScalar {

    inline fun <reified T : CustomScalar> stub()
        :
        StubProvider<CustomScalarStub.Query<T>> =
        delegationContext.scalar.stub(T::class)

    inline fun <reified T : CustomScalar, A : ArgumentSpec> optionalConfigStub()
        :
        StubProvider<CustomScalarStub.OptionalConfigQuery<T, A>> =
        delegationContext.scalar.optionalConfigStub(T::class)

    inline fun <reified T : CustomScalar, A : ArgumentSpec> configStub()
        :
        StubProvider<CustomScalarStub.ConfigurableQuery<T, A>> =
        delegationContext.scalar.configStub(T::class)


    object List {

      inline fun <reified T : CustomScalar> stub()
          :
          StubProvider<CustomScalarListStub.Query<T>> =
          delegationContext.scalar.list.stub(T::class)

      inline fun <reified T : CustomScalar, A : ArgumentSpec> optionalConfigStub()
          :
          StubProvider<CustomScalarListStub.OptionalConfigQuery<T, A>> =
          delegationContext.scalar.list.optionalConfigStub(T::class)

      inline fun <reified T : CustomScalar, A : ArgumentSpec> configStub()
          :
          StubProvider<CustomScalarListStub.ConfigurableQuery<T, A>> =
          delegationContext.scalar.list.configStub(T::class)
    }
  }

  object QUnion {

    fun <T : QUnionType> stub(union: T)
        :
        StubProvider<UnionStub.Query<T>> =
        delegationContext.union.stub(union)

    fun <T : QUnionType, A : ArgumentSpec> optionalConfigStub(union: T)
        :
        StubProvider<UnionStub.OptionalConfigQuery<T, A>> =
        delegationContext.union.optionalConfigStub(union)


    fun <T : QUnionType, A : ArgumentSpec> configStub(union: T)
        :
        StubProvider<UnionStub.ConfigurableQuery<T, A>> =
        delegationContext.union.configStub(union)

    object List {

      fun <T : QUnionType> stub(union: T)
          :
          StubProvider<UnionListStub.Query<T>> =
          delegationContext.union.list.stub(union)

      fun <T : QUnionType, A : ArgumentSpec> optionalConfigStub(union: T)
          :
          StubProvider<UnionListStub.OptionalConfigQuery<T, A>> =
          delegationContext.union.list.optionalConfigStub(union)

      fun <T : QUnionType, A : ArgumentSpec> configStub(union: T)
          :
          StubProvider<UnionListStub.ConfigurableQuery<T, A>> =
          delegationContext.union.list.configStub(union)

    }

  }
}

