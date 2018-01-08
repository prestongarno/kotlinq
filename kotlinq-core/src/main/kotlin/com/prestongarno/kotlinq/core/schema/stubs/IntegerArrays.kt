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
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.adapters.BooleanArrayStub
import com.prestongarno.kotlinq.core.adapters.FloatArrayStub
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.adapters.IntArrayStub
import com.prestongarno.kotlinq.core.adapters.StringArrayStub
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.api.Grub
import com.prestongarno.kotlinq.core.api.NullableStubProvider
import com.prestongarno.kotlinq.core.api.Stub
import com.prestongarno.kotlinq.core.api.StubProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty.Companion.from
import com.prestongarno.kotlinq.core.properties.contextBuilder
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import kotlin.reflect.KProperty

typealias IntArrayNoArgContext<A> = NullableStubProvider<DelegateProvider.NoArgDelegate<IntDelegate<A>, IntArray>, DelegateProvider.NoArgDelegate<IntDelegate<A>, IntArray?>>

interface IntArrayDelegate<A : ArgumentSpec> : GraphqlDslBuilder<A> {
  var default: IntArray?

  companion object {

    fun stub() = object : IntArrayNoArgContext<ArgBuilder> {
      override fun provideDelegate(inst: QSchemaType, property: KProperty<*>)
          : Stub<DelegateProvider.NoArgDelegate<IntDelegate<ArgBuilder>, IntArray>> {
        TODO()
      }

      override fun asNullable()
          : StubProvider<DelegateProvider.NoArgDelegate<IntDelegate<ArgBuilder>, IntArray?>> {
        TODO("not implemented")
      }

    }
  }
}


internal
sealed class ArrayPreDelegate<out T, out A : ArgumentSpec>(val args: A) {

  abstract fun toDelegate(propertyName: String): GraphqlPropertyDelegate<T>

  internal
  class PreIntArray<A : ArgumentSpec>(args: A)
    : ArrayPreDelegate<IntArray, A>(args),
      IntArrayDelegate<A> {
    override fun toDelegate(propertyName: String) =
        IntArrayStub(from("Int", true, propertyName), default, args.toMap())

    override fun config(block: A.() -> Unit) {
      args.block()
    }

    override var default: IntArray? = null

    companion object {
      fun basic(args: ArgBuilder): GraphqlDslBuilder<ArgBuilder> = PreIntArray(args)
    }
  }

  internal
  class PreFloatArray<A : ArgumentSpec>(args: A)
    : ArrayPreDelegate<FloatArray, A>(args),
      FloatArrayDelegate<A> {
    override fun toDelegate(propertyName: String) =
        FloatArrayStub(from("Float", true, propertyName), default, args.toMap())

    override fun config(block: A.() -> Unit) {
      args.block()
    }

    override var default: FloatArray? = null
  }

  internal
  class PreBooleanArray<A : ArgumentSpec>(args: A)
    : ArrayPreDelegate<BooleanArray, A>(args),
      BooleanArrayDelegate<A> {
    override fun toDelegate(propertyName: String) =
        BooleanArrayStub(from("Boolean", true, propertyName), default, args.toMap())

    override fun config(block: A.() -> Unit) {
      args.block()
    }

    override var default: BooleanArray? = null
  }

  internal
  class PreStringArray<A : ArgumentSpec>(args: A)
    : ArrayPreDelegate<Array<String>, A>(args),
      StringArrayDelegate<A> {
    override fun toDelegate(propertyName: String) =
        StringArrayStub(from("String", true, propertyName), default, args.toMap())

    override fun config(block: A.() -> Unit) {
      args.block()
    }

    override var default: Array<String>? = null
  }
}

private class NoArgArrImpl<out T, out X>(val ctor: (ArgBuilder) -> T)
  : DelegateProvider.NoArgDelegate<T, X>
    where T : ArrayPreDelegate<X, ArgBuilder>,
          T : GraphqlDslBuilder<ArgBuilder> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
      ctor(ArgBuilder())
          .toDelegate(property.name)
          .bindToContext(inst)

  override fun invoke(args: ArgBuilder, block: (T.() -> Unit)?) =
      DelegateProvider.delegateProvider { inst, prop ->
        ctor(args).toDelegate(prop.name).bindToContext(inst)
      }

  companion object {
    fun <T, X> new(ctor: (ArgBuilder) -> T): DelegateProvider.NoArgDelegate<T, X>
        where T : ArrayPreDelegate<X, ArgBuilder>,
              T : GraphqlDslBuilder<ArgBuilder> = NoArgArrImpl(ctor)
  }

}
