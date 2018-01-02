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

package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.UnionStubImpl
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.delegates.ConfiguredBlock
import com.prestongarno.kotlinq.core.schema.QUnionType

interface UnionStub<out T : QUnionType, out A : ArgumentSpec> : GraphqlDslBuilder<A> {
  fun fragment(scope: T.() -> Unit)

  interface OptionallyConfigured<out T : QUnionType, A : ArgumentSpec> : ConfiguredBlock<UnionStub<T, A>, A, QModel<*>?> {
    operator fun invoke(block: UnionStub<T, ArgBuilder>.() -> Unit): DelegateProvider<QModel<*>?>
  }

  companion object {
    internal
    fun <T : QUnionType, A : ArgumentSpec> optionallyConfigured(qproperty: GraphQlProperty, unionObject: T): OptionallyConfigured<T, A> =
        object : OptionallyConfigured<T, A> {
          override fun invoke(args: A, block: UnionStub<T, A>.() -> Unit): DelegateProvider<QModel<*>?> = delegateProvider { qModel, _ ->
            UnionStubImpl(unionObject, args).apply(block).toDelegate(qproperty).bindToContext(qModel)
          }

          override fun invoke(
              block: UnionStub<T, ArgBuilder>.() -> Unit
          ): DelegateProvider<QModel<*>?> = delegateProvider { qModel, _ ->
            UnionStubImpl(unionObject, ArgBuilder()).apply(block).toDelegate(qproperty).bindToContext(qModel)
          }
        }
  }
}