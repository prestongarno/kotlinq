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

import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.custom.InputStreamScalarMapper
import com.prestongarno.kotlinq.core.adapters.custom.QScalarMapper
import com.prestongarno.kotlinq.core.adapters.custom.StringScalarMapper
import com.prestongarno.kotlinq.core.internal.CollectionDelegate
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.schema.CustomScalar
import com.prestongarno.kotlinq.core.schema.stubs.CustomScalarStub
import kotlin.reflect.KProperty

internal
class CustomScalarAdapter<E : CustomScalar, out P : QScalarMapper<Q>, Q, out B : ArgumentSpec>(
    val mapper: P,
    val arguments: B? = null
) : PreDelegate<Q, B>(),
    CustomScalarStub<E, Q, B> {

  override fun toDelegate(property: GraphQlProperty): GraphqlPropertyDelegate<Q> {
    return CustomScalarFieldImpl(property, arguments.toMap(), mapper, default)
  }

  override var default: Q? = null

  override fun config(scope: B.() -> Unit) {
    arguments?.scope()
  }

}

@CollectionDelegate(Any::class)
private data class CustomScalarFieldImpl<out Q>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: QScalarMapper<Q>,
    val default: Q?
) : GraphqlPropertyDelegate<Q> {

  private val nullable by lazy { Nullable(this) }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): Q = adapter.value ?: default!!

  override fun accept(result: Any?): Boolean {
    when (adapter) {
      is InputStreamScalarMapper<*> -> adapter.rawValue = "${result ?: ""}".byteInputStream()
      is StringScalarMapper<*> -> adapter.rawValue = "${result ?: ""}"
      else -> throw IllegalArgumentException("No such adapter supported")
    }
    return true
  }

  override fun toRawPayload(): String = qproperty.graphqlName + args.stringify()

  override fun asNullable(): GraphqlPropertyDelegate<Q?> = nullable

  override fun equals(other: Any?): Boolean {
    return (qproperty == (other as? Adapter)?.qproperty)
        && other.args == args
  }

  override fun hashCode(): Int = (qproperty.hashCode() * 31) + (args.hashCode() * 31)

  private class Nullable<Q>(val adapter: CustomScalarFieldImpl<Q>) : Adapter by adapter, GraphqlPropertyDelegate<Q?> {
    override fun asNullable(): GraphqlPropertyDelegate<Q?> = this
    override fun getValue(inst: QModel<*>, property: KProperty<*>): Q? =
        adapter.adapter.value ?: adapter.default
  }
}
