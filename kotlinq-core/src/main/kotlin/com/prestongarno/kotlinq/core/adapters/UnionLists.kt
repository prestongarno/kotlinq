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
import com.prestongarno.kotlinq.core.internal.CollectionDelegate
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.schema.stubs.UnionListStub
import java.util.Collections.emptySet
import kotlin.reflect.KProperty

internal
fun <T : QUnionType, A : ArgumentSpec> newUnionListStub(
    qproperty: GraphQlProperty,
    objectModel: T,
    arguments: A?
): UnionListStub<T, A> =
    UnionListAdapter(qproperty, objectModel, arguments)

@Suppress("AddVarianceModifier")
private class UnionListAdapter<I : QUnionType, out A : ArgumentSpec>(
    val qproperty: GraphQlProperty,
    val objectModel: I,
    val arguments: A?
) : UnionListStub<I, A> {

  private var fragments: Set<Fragment>? = null

  override fun config(scope: A.() -> Unit) {
    arguments?.scope()
  }

  override fun fragment(scope: I.() -> Unit) = objectModel.queue(objectModel, scope) {
    fragments = reset()
  }

  fun provideDelegate(
      inst: QModel<*>,
      property: KProperty<*>
  ): QField<List<QModel<*>>> =
      UnionListStubImpl(
          qproperty,
          fragments ?: emptySet(),
          arguments.toMap()
      )

}


@CollectionDelegate(QModel::class)
private
data class UnionListStubImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any>
) : GraphqlPropertyDelegate<List<QModel<*>>>, FragmentContext {

  override fun asNullable(): GraphqlPropertyDelegate<List<QModel<*>>?> { TODO("not implemented") }

  private var value: List<QModel<QType>> = emptyList()

  override fun accept(result: Any?): Boolean {
    return if (result is Collection<*>) {
      result.filterIsInstance<JsonObject>()
          .mapNotNull {
            it["__typename"]?.let { resultType ->
              fragments.find {
                it.model.graphqlType == resultType && (it.model as? QModel<*>) != null
              }
            }?.to(it)
          }.let {
        value = it.mapNotNull { (gen, json) ->
          gen.initializer().apply {
            accept(json)
          } as? QModel<QType>
        }
      }
      return true
    } else false
  }

  override fun toRawPayload(): String =
      qproperty.graphqlName +
          args.stringify() +
          fragments.joinToString(prefix = "{__typename,", postfix = "}") {
            it.model.run {
              "... on " + graphqlType + toGraphql()
            }
          }

  override fun getValue(inst: QModel<*>, property: KProperty<*>) = value

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

