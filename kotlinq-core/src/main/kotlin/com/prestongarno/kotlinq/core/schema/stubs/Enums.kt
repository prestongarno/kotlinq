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

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.adapters.EnumAdapterImpl
import com.prestongarno.kotlinq.core.adapters.bind
import com.prestongarno.kotlinq.core.adapters.enumAdapter
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.properties.GraphQLPropertyContext
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.newGraphqlProperty
import com.prestongarno.kotlinq.core.schema.QEnumType
import kotlin.reflect.KClass

interface EnumStub<out T, out A : ArgumentSpec> : GraphqlDslBuilder<A>
    where T : QEnumType?,
          T : Enum<*>? {

  companion object {

    /**
     * Creates a new GraphQL property stub.
     * If this works across the whole type system it would be amazing.
     * Had hoped to only refrain from adding classes, but this might
     * remove 20+ classes. Might have found the most powerful abstraction to be at now. */
    internal
    fun <T, A> stub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T>
    ): GraphQLPropertyContext<EnumStub<T, A>, T>
        where T : QEnumType,
              T : Enum<*>,
              A : ArgumentSpec = newGraphqlProperty<EnumAdapterImpl<T, A>, A, T>(
        { enumAdapter(qproperty, enumClass, it) },
        { toDelegate().bind(it) })

    internal
    fun <T, A> nullableStub(
        qproperty: GraphQlProperty,
        enumClass: KClass<T> // TODO unsafe
    ): GraphQLPropertyContext<EnumStub<T?, A>, T?>
        where T : QEnumType,
              T : Enum<T>,
              A : ArgumentSpec = newGraphqlProperty<EnumAdapterImpl<T, A>, A, T?>(
        { enumAdapter(qproperty, enumClass, it) },
        { toDelegate().bind(it) })

  }
}
