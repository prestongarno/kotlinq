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
import com.prestongarno.kotlinq.core.CustomScalar
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.custom.InputStreamScalarListMapper
import com.prestongarno.kotlinq.core.adapters.custom.QScalarListMapper
import com.prestongarno.kotlinq.core.adapters.custom.StringScalarListMapper
import com.prestongarno.kotlinq.core.internal.CollectionDelegate
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.stubs.CustomScalarListStub
import kotlin.reflect.KProperty

internal
fun <E : CustomScalar, P : QScalarListMapper<Q>, Q, A : ArgumentSpec> newCustomScalarListField(
    qproperty: GraphQlProperty,
    mapper: P,
    arguments: A?
): CustomScalarListStub<E, Q, A> = CustomScalarListAdapter(qproperty, mapper, arguments)

private
class CustomScalarListAdapter<E : CustomScalar, out P : QScalarListMapper<Q>, Q, out A : ArgumentSpec>(
    qproperty: GraphQlProperty,
    val mapper: P,
    val arguments: A?
) : PreDelegate(qproperty),
    CustomScalarListStub<E, Q, A> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<List<Q>> =
      CustomScalarListStubImpl(qproperty, mapper, arguments.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) {
    arguments?.scope()
  }

}

@CollectionDelegate(Any::class)
private data class CustomScalarListStubImpl<out Q>(
    override val qproperty: GraphQlProperty,
    val adapter: QScalarListMapper<Q>,
    override val args: Map<String, Any>
) : QField<List<Q>>,
    Adapter {

  override fun toRawPayload(): String = qproperty.graphqlName + args.stringify()

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<Q> = adapter.value

  override fun accept(result: Any?): Boolean {
    val values = (result as? List<*> ?: listOf(result)).filterNotNull()
    when (adapter) {
      is InputStreamScalarListMapper<*> -> {
        (adapter as InputStreamScalarListMapper<*>).rawValue =
            values.map { "$it".byteInputStream() }
      }
      is StringScalarListMapper<*> -> {
        (adapter as StringScalarListMapper<*>).rawValue =
            values.map { "$it" }
      }
    }
    return true
  }

  override fun equals(other: Any?): Boolean {
    return (qproperty == (other as? Adapter)?.qproperty)
        && other.args == args
        && adapter == (other as? CustomScalarListStubImpl<*>)?.adapter
  }
}
