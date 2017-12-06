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

import com.beust.klaxon.JsonObject
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.QUnionType
import com.prestongarno.kotlinq.core.api.Fragment
import com.prestongarno.kotlinq.core.api.FragmentContext
import com.prestongarno.kotlinq.core.internal.ValueDelegate
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.stubs.UnionStub
import kotlin.reflect.KProperty

fun <T : QUnionType, A : ArgumentSpec> newUnionField(
    qproperty: GraphQlProperty,
    unionObject: T,
    arguments: A?
): UnionStub<T, A> = UnionAdapterImpl(qproperty, unionObject, arguments)

@kotlin.Suppress("AddVarianceModifier")
private
class UnionAdapterImpl<T : QUnionType, out A : ArgumentSpec>(
    val qproperty: GraphQlProperty,
    val unionObject: T,
    val arguments: A? = null
) : UnionStub<T, A> {

  private var mutableFragments: Set<Fragment>? = null

  override fun fragment(scope: T.() -> Unit) = unionObject.queue(unionObject, scope) {
    mutableFragments = reset()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<*>?> =
      UnionStubImpl(qproperty, mutableFragments ?: emptySet(), arguments.toMap()).bind(inst)

  override fun config(scope: A.() -> Unit) {
    arguments?.apply(scope)
  }

}

@ValueDelegate(QModel::class) private
data class UnionStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any> = emptyMap()
) : Adapter,
    QField<QModel<*>?>,
    FragmentContext {

  var value: QModel<QType>? = null


  override fun accept(result: Any?): Boolean {
    return if (result is JsonObject) {
      value = result["__typename"]?.let { resultType ->
        fragments.find {
          it.model.graphqlType == resultType
        }?.initializer?.invoke()
      }
      return value?.accept(result) == true
    } else false
  }

  override fun toRawPayload(): String = qproperty.graphqlName +
      args.stringify() +
      fragments.joinToString(prefix = "{__typename,", postfix = "}") {
        it.model.run {
          "... on " + graphqlType + toGraphql()
        }
      }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): QModel<QType>? = value
}

