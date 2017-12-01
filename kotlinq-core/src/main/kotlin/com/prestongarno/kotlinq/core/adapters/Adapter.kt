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

package com.prestongarno.kotlinq.core.adapters

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import kotlin.reflect.KProperty

/**
 * Reflective object which provides the information needed
 * to generate the correct representation of the GraphQL field query
 *
 * Base type of all delegates - this is the internal side of a property.
 * This class (internal API) and [com.prestongarno.ktq.adapters.QField] (public API delegate)
 * generally are both implemented on a property delegate */
interface Adapter {

  val qproperty: GraphQlProperty

  val args: Map<String, Any>

  fun accept(result: Any?): Boolean

  fun toRawPayload(): String
}

/**
 * Public API delegate representing an object which holds
 * the backing field information about a GraphQL property
 * @param T : The type of object or value which this provides */
interface QField<out T> {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): T
}


