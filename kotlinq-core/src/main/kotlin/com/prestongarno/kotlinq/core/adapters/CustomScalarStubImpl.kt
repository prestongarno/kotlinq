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
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.schema.CustomScalar
import com.prestongarno.kotlinq.core.schema.stubs.CustomScalarStub
import kotlin.reflect.KProperty

internal
class CustomScalarStubImpl<E : CustomScalar, Q, out A : ArgumentSpec>(
    private val mapper: CustomScalarStub.Mapper<Q>,
    val arguments: A? = null
) : PreDelegate<Q, A>(),
    CustomScalarStub<E, Q, A> {

  override fun toDelegate(property: GraphQlProperty): GraphqlPropertyDelegate<Q> {
    return CustomScalarFieldImpl(property, arguments.toMap(), mapper, default)
  }

  override var default: Q? = null

  override fun config(block: A.() -> Unit) {
    arguments?.block()
  }
}

private data class CustomScalarFieldImpl<Q>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: CustomScalarStub.Mapper<Q>,
    val default: Q?
) : GraphqlPropertyDelegate<Q> {

  private val nullable by lazy {
    GraphqlPropertyDelegate.wrapAsNullable(this, this::value)
  }

  private var _value: Q? = null

  private val value: Q? get() = _value ?: default

  override fun getValue(inst: QModel<*>, property: KProperty<*>): Q = value!!

  override fun accept(result: Any?): Boolean {
    _value = acceptAndReturn(result)
    return true
  }

  // TODO lazily evaluate this
  override fun acceptAndReturn(obj: Any?): Q? {
    return when (adapter) {
      is CustomScalarStub.Mapper.InputStreamMapper<Q> -> adapter.invoke("${obj ?: ""}".byteInputStream())
      is CustomScalarStub.Mapper.StringMapper<Q> -> adapter.invoke("${obj ?: ""}")
    }
  }

  override fun toRawPayload(): String = qproperty.graphqlName + args.stringify()

  override fun asNullable(): GraphqlPropertyDelegate<Q?> = nullable

  override fun equals(other: Any?): Boolean {
    return qproperty == (other as? Adapter)?.qproperty
        && other.args == args
  }

  override fun hashCode(): Int = (qproperty.hashCode() * 31) + (args.hashCode() * 31)
}
