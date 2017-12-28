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
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.prestongarno.kotlinq.core.api.Fragment
import com.prestongarno.kotlinq.core.api.FragmentContext
import com.prestongarno.kotlinq.core.internal.ValueDelegate
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.schema.stubs.UnionStub
import kotlin.reflect.KProperty

internal
fun <T : QUnionType, A : ArgumentSpec> newUnionField(
    qproperty: GraphQlProperty,
    unionObject: T,
    arguments: A?
): UnionStubImpl<T, A> = UnionStubImpl(qproperty, unionObject, arguments)

internal
class UnionStubImpl<out T : QUnionType, out A : ArgumentSpec>(
    qproperty: GraphQlProperty,
    private val unionObject: T,
    val arguments: A? = null
) : PreDelegate<GraphqlPropertyDelegate<QModel<*>?>, QModel<*>?>(qproperty), UnionStub<T, A> {

  override fun toDelegate(): GraphqlPropertyDelegate<QModel<*>?> =
    UnionAdapterImpl(qproperty, fragments ?: emptySet(), arguments.toMap())

  private var nullable: Boolean = false
  override val flagNullable = { value: Boolean -> nullable = value }

  private var fragments: Set<Fragment>? = null

  override fun fragment(scope: T.() -> Unit) = unionObject.queue(unionObject, scope) {
    fragments = reset()
  }

  override fun config(block: A.() -> Unit) {
    arguments?.apply(block)
  }

}

@ValueDelegate(QModel::class) private
data class UnionAdapterImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any> = emptyMap()
) : GraphqlPropertyDelegate<QModel<*>?>,
    FragmentContext {

  var value: QModel<QType>? = null

  override fun asNullable(): GraphqlPropertyDelegate<QModel<*>?> = this

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

  override fun equals(other: Any?): Boolean {
    if (other !is Adapter) return false
    if (other !is QField<*>) return false
    if (other !is FragmentContext) return false

    if (other.qproperty != qproperty) return false
    if (other.fragments != fragments) return false
    if (other.args.size != args.size) return false
    return other.args.entries.find {
      args[it.key] == null
    } == null
  }
  override fun hashCode(): Int =
      (qproperty.hashCode() * 31) +
          (args.hashCode() * 31) +
          (fragments.hashCode() * 31)
}

