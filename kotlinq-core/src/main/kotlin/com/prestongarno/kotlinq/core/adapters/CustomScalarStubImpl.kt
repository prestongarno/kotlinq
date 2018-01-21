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

package com.prestongarno.kotlinq.core.adapters

import com.prestongarno.kotlinq.core.Model
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate.Companion.wrapAsNullable
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.ListDelegate
import com.prestongarno.kotlinq.core.schema.stubs.Mapper
import kotlin.reflect.KProperty

internal data
class CustomScalarField<out Q: Any>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: Mapper<*, Q>,
    val default: Q?
) : GraphqlPropertyDelegate<Q> {

  private val nullable by lazy { wrapAsNullable(this, this::value) }

  override fun asList(): GraphqlPropertyDelegate<List<Q>> =
      ListDelegate(this)

  private var _value: Q? = null

  private val value: Q? get() = _value ?: default

  override fun getValue(inst: Model<*>, property: KProperty<*>): Q = value!!

  override fun accept(result: Any?): Boolean {
    _value = transform(result)
    return true
  }

  // TODO lazily evaluate this and actually use streams correctly
  override fun transform(obj: Any?): Q? = when (adapter) {
    is Mapper.StringMapper<Q> -> adapter.transform(obj.toString())
    is Mapper.StreamMapper<Q> -> adapter.transform(obj.toString().byteInputStream())
  }

  override fun toRawPayload(): String = qproperty.graphqlName + args.stringify()

  override fun asNullable(): GraphqlPropertyDelegate<Q?> = nullable

  override fun equals(other: Any?): Boolean {
    return qproperty == (other as? Adapter)?.qproperty
        && other.args == args
  }

  override fun hashCode(): Int = (qproperty.hashCode() * 31) + (args.hashCode() * 31)
}
