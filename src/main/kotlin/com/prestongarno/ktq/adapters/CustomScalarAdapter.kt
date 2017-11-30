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

package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.adapters.custom.InputStreamScalarMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import com.prestongarno.ktq.internal.CollectionDelegate
import com.prestongarno.ktq.internal.stringify
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.stubs.CustomScalarStub
import kotlin.reflect.KProperty

fun <E : CustomScalar, P : QScalarMapper<Q>, Q, A : ArgBuilder> newScalarDelegate(
    qproperty: GraphQlProperty,
    mapper: P,
    arguments: A?
): CustomScalarStub<E, Q, A> = CustomScalarAdapter<E, P, Q, A>(qproperty, mapper, arguments)

private
class CustomScalarAdapter<E : CustomScalar, out P : QScalarMapper<Q>, Q, out B : ArgBuilder>(
    qproperty: GraphQlProperty,
    val mapper: P,
    val arguments: B? = null
) : PreDelegate(qproperty),
    CustomScalarStub<E, Q, B> {

  override var default: Q? = null

  override fun config(scope: B.() -> Unit) {
    arguments?.scope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<Q> =
      CustomScalarFieldImpl(qproperty, arguments.toMap(), mapper, default).bind(inst)

}

@CollectionDelegate(Any::class)
private data class CustomScalarFieldImpl<out Q>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any> = emptyMap(),
    val adapter: QScalarMapper<Q>,
    val default: Q?
) : Adapter,
    QField<Q> {

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
}
