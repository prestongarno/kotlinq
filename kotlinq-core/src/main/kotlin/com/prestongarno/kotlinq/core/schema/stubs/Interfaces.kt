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
import com.prestongarno.kotlinq.core.adapters.InterfaceStubImpl
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.properties.DelegateProvider
import com.prestongarno.kotlinq.core.properties.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyDelegate
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType

/**
 * Remember -> compile generate all interface types to be *both* [QType] ***and*** [QInterface]
 */
interface InterfaceStub<in I, out A : ArgumentSpec>
  : FragmentStub<I>,
    GraphqlDslBuilder<A>
    where I : QInterface, I : QType {

  interface OptionallyConfigured<I, A> : GraphQlPropertyDelegate.ConfiguredBlock<InterfaceStub<I, A>, A, QModel<I>?>
      where I : QInterface, I : QType, A : ArgumentSpec {
    operator fun invoke(block: InterfaceStub<I, ArgBuilder>.() -> Unit): DelegateProvider<QModel<I>?>
  }

  companion object {
    internal
    fun <I, A> optionallyConfigured(qproperty: GraphQlProperty): InterfaceStub.OptionallyConfigured<I, A>
        where I : QInterface, I : QType, A : ArgumentSpec =

        object : OptionallyConfigured<I, A> {
          override fun invoke(block: InterfaceStub<I, ArgBuilder>.() -> Unit): DelegateProvider<QModel<I>?> =
              delegateProvider { model, prop -> InterfaceStubImpl<I, ArgBuilder>(ArgBuilder()).apply(block).toDelegate(qproperty).bindToContext(model) }
          override fun invoke(args: A, block: InterfaceStub<I, A>.() -> Unit): DelegateProvider<QModel<I>?> =
              delegateProvider { model, prop -> InterfaceStubImpl<I, A>(args).apply(block).toDelegate(qproperty).bindToContext(model) }
        }
  }
}
