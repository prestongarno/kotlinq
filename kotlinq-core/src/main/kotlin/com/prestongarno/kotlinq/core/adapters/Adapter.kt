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
 * This class (internal API) and [com.prestongarno.kotlinq.core.adapters.QField] (public API delegate)
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


/**
 * Internal interface to represent a union between [Adapter] and [QField]
 * @param T the return type of the field
 */
internal
interface GraphqlPropertyDelegate<out T : Any?> : QField<T>, Adapter {
  /**
   * Called on construction of a graphql object model.
   * Default behaviour: binds this property delegate to the instance of [graphqlModel]
   */
  fun bindToContext(graphqlModel: QModel<*>) = apply { graphqlModel.register(this) }

  fun asNullable(): GraphqlPropertyDelegate<T?>

  companion object {

    internal
    fun <T> wrapAsNullable(
        instance: GraphqlPropertyDelegate<T>,
        scope: () -> T?
    ): GraphqlPropertyDelegate<T?> = object : Adapter by instance, GraphqlPropertyDelegate<T?> {
      override fun getValue(inst: QModel<*>, property: KProperty<*>) = scope()
      override fun asNullable(): GraphqlPropertyDelegate<T?> = instance.asNullable()
      override fun equals(other: Any?): Boolean = instance == other
      override fun hashCode(): Int = instance.hashCode() * 31
    }
  }
}
