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
import com.prestongarno.kotlinq.core.adapters.enumAdapter
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.schema.QEnumType
import kotlin.reflect.KClass

interface EnumStub<T, out A : ArgumentSpec> : GraphqlDslBuilder<A>
    where T : QEnumType?,
          T : Enum<*>? {

  var default: T?

  companion object {

    internal
    fun <T, A> stub(qproperty: GraphQlProperty, enumClass: KClass<T>): EnumStub<T, A>
        where T : QEnumType, T : Enum<*>, A : ArgumentSpec =
        enumAdapter(qproperty, enumClass)

  }
}
