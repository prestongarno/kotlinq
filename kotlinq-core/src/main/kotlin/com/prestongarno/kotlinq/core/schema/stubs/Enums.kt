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
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.EnumAdapterImpl
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.adapters.applyNotNull
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.delegates.Configured
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.schema.QEnumType
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

interface EnumStub<T, A : ArgumentSpec> : GraphqlDslBuilder<A>
    where T : QEnumType?,
          T : Enum<*>? {
  var default: T?

  interface OptionallyConfigured<T, A : ArgumentSpec> : Configured<EnumStub<T, A>, A, T>, DelegateProvider<T>
      where T : QEnumType,
            T : Enum<T> {
    operator fun invoke(block: EnumStub<T, ArgBuilder>.() -> Unit): DelegateProvider<T>

    interface Nullable<T, A> : Configured<EnumStub<T, A>, A, T?>
        where T : QEnumType,
              T : Enum<*>,
              A : ArgumentSpec {
      operator fun invoke(block: EnumStub<T, ArgBuilder>.() -> Unit): DelegateProvider<T?>
    }
  }

  companion object {

    internal fun <T> noArg(
        clazz: KClass<T>,
        qproperty: GraphQlProperty
    ) where T : QEnumType, T : Enum<T> = EnumNoArgImpl(clazz, qproperty)

    internal fun <T, A> optionallyConfigured(
        clazz: KClass<T>,
        qproperty: GraphQlProperty
    ) where T : QEnumType, T : Enum<T>, A : ArgumentSpec = OptionallyConfiguredImpl<T, A>(clazz, qproperty)

    internal
    fun <T, A> configured(
        clazz: KClass<T>,
        qproperty: GraphQlProperty
    ) where T : QEnumType, T : Enum<T>, A : ArgumentSpec = EnumConfigImpl<T, A>(clazz, qproperty)
  }

}

internal
class EnumNoArgImpl<T>(
    val clazz: KClass<T>,
    val qproperty: GraphQlProperty
) : DelegateProvider.NoArgDelegate<EnumStub<T, ArgBuilder>, T>
    where T : Enum<T>,
          T : QEnumType {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
      EnumAdapterImpl(clazz, ArgBuilder())
          .toDelegate(qproperty)
          .bindToContext(inst)

  override fun invoke(args: ArgBuilder, block: (EnumStub<T, ArgBuilder>.() -> Unit)?): DelegateProvider<T> =
      DelegateProvider.delegateProvider { qModel, _ ->
        EnumAdapterImpl(clazz, args)
            .applyNotNull(block)
            .toDelegate(qproperty)
            .bindToContext(qModel)
      }

  fun asNullable() = object : DelegateProvider.NoArgDelegate<EnumStub<T, ArgBuilder>, T?> {

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<T?> =
        invoke().provideDelegate(inst, property)

    val clazz = this@EnumNoArgImpl.clazz
    val prop = this@EnumNoArgImpl.qproperty

    override fun invoke(args: ArgBuilder, block: (EnumStub<T, ArgBuilder>.() -> Unit)?): DelegateProvider<T?> =
        DelegateProvider.delegateProvider { qModel, _ ->
          EnumAdapterImpl(clazz, args)
              .applyNotNull(block)
              .toDelegate(prop)
              .asNullable()
              .bindToContext(qModel)
        }
  }
}

internal
class EnumConfigImpl<T, A>(
    val clazz: KClass<T>, val qproperty: GraphQlProperty
) : Configured<EnumStub<T, A>, A, T>
    where T : Enum<T>,
          T : QEnumType,
          A : ArgumentSpec {

  override fun invoke(args: A, block: (EnumStub<T, A>.() -> Unit)?): DelegateProvider<T> =
      DelegateProvider.delegateProvider { qModel, _ ->
        EnumAdapterImpl(clazz, null)
            .toDelegate(qproperty)
            .bindToContext(qModel)

      }

  fun asNullable(): Nullable<T, A> = Nullable(clazz, qproperty)

  internal class Nullable<T, A : ArgumentSpec>(
      val clazz: KClass<T>,
      val qproperty: GraphQlProperty
  ) : EnumStub.OptionallyConfigured.Nullable<T, A> where T : QEnumType, T : Enum<T> {

    override fun invoke(block: EnumStub<T, ArgBuilder>.() -> Unit): DelegateProvider<T?> =
        delegateProvider { inst, _ ->
          EnumAdapterImpl(clazz, ArgBuilder())
              .apply(block)
              .toDelegate(qproperty)
              .asNullable()
              .bindToContext(inst)
        }

    override fun invoke(args: A, block: (EnumStub<T, A>.() -> Unit)?): DelegateProvider<T?> =
        DelegateProvider.delegateProvider { qModel, _ ->
          EnumAdapterImpl(clazz, args)
              .toDelegate(qproperty)
              .asNullable()
              .bindToContext(qModel)
        }
  }
}

internal
class OptionallyConfiguredImpl<T, A>(
    val clazz: KClass<T>,
    val qproperty: GraphQlProperty
) : EnumStub.OptionallyConfigured<T, A>
    where T : Enum<T>,
          T : QEnumType,
          A : ArgumentSpec {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
      EnumAdapterImpl(clazz, null)
          .toDelegate(qproperty)
          .bindToContext(inst)

  override fun invoke(args: A, block: (EnumStub<T, A>.() -> Unit)?): DelegateProvider<T> =
      DelegateProvider.delegateProvider { qModel, _ ->
        EnumAdapterImpl(clazz, args)
            .applyNotNull(block)
            .toDelegate(qproperty)
            .bindToContext(qModel)
      }

  override fun invoke(block: EnumStub<T, ArgBuilder>.() -> Unit): DelegateProvider<T> =
      DelegateProvider.delegateProvider { qModel, _ ->
        EnumAdapterImpl(clazz, ArgBuilder())
            .toDelegate(qproperty)
            .bindToContext(qModel)
      }

  fun asNullable(): EnumStub.OptionallyConfigured.Nullable<T, A> = EnumConfigImpl.Nullable(clazz, qproperty)

}
