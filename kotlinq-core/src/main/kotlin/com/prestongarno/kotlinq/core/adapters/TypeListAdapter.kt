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

@file:Suppress("AddVarianceModifier")

package com.prestongarno.kotlinq.core.adapters

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.api.ModelProvider
import com.prestongarno.kotlinq.core.internal.CollectionDelegate
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.schema.stubs.TypeListStub
import kotlin.reflect.KProperty

internal
class TypeListAdapter<out P : QModel<I>, I : QType, out A : ArgumentSpec>(
    val init: () -> P,
    val argBuilder: A? = null
) : PreDelegate<List<P>, A>(),
    TypeListStub<P, I, A> {

  override fun toDelegate(property: GraphQlProperty): GraphqlPropertyDelegate<List<P>> {
    return TypeListStubImpl(property, init, argBuilder.toMap())
  }

  override fun config(block: A.() -> Unit) {
    argBuilder?.block()
  }
}

@CollectionDelegate(QModel::class)
private data class TypeListStubImpl<P : QModel<*>>(
    override val qproperty: GraphQlProperty,
    val init: () -> P,
    override val args: Map<String, Any>
) : GraphqlPropertyDelegate<List<P>>, ModelProvider {

  private val nullable by lazy { Nullable(this) }
  val results: List<P>? = null

  override val value: QModel<*> by lazy { init() }

  override fun toRawPayload(): String =
      qproperty.graphqlName + args.stringify() + value.toGraphql()

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<P> = results ?: emptyList()

  override fun accept(result: Any?): Boolean {
    return if (result is JsonArray<*>) {
      result.filterIsInstance<JsonObject>().filterNot { element ->
        init().let {
          //this.results.add(it)
          it.accept(element)
        }
      }.isEmpty()
    } else false
  }

  override fun asNullable(): GraphqlPropertyDelegate<List<P>?> = nullable

  override fun hashCode(): Int =
      (value.hashCode() * 31) +
          (args.hashCode() * 31) +
          (qproperty.hashCode() * 31)

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as TypeListStubImpl<*>

    if (qproperty != other.qproperty) return false
    if (args != other.args) return false

    return true
  }

  private class Nullable<P: QModel<*>>(val wrapped: TypeListStubImpl<P>) : Adapter by wrapped, GraphqlPropertyDelegate<List<P>?> {
    override fun getValue(inst: QModel<*>, property: KProperty<*>): List<P>? = wrapped.results
    override fun asNullable(): GraphqlPropertyDelegate<List<P>?> = this
  }

}
