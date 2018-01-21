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

import com.beust.klaxon.JsonObject
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.Model
import com.prestongarno.kotlinq.core.api.Fragment
import com.prestongarno.kotlinq.core.api.FragmentContext
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.ListDelegate
import com.prestongarno.kotlinq.core.schema.QUnionType
import com.prestongarno.kotlinq.core.schema.stubs.UnionStub
import kotlin.reflect.KProperty

internal
class UnionStubImpl<out T : QUnionType, A : ArgumentSpec>(
    private val unionObject: T,
    val arguments: A? = null
) : PreDelegate<Model<*>?, A>(), UnionStub<T, A> {

  override fun toDelegate(property: GraphQlProperty): GraphqlPropertyDelegate<Model<*>?> =
      UnionAdapterImpl(property, fragments ?: emptySet(), arguments.toMap())

  // type cast is fine here, as long as we don't call this in context of providing a single property
  @Suppress("UNCHECKED_CAST")
  fun asNotNull(): PreDelegate<Model<*>, A> = this as PreDelegate<Model<*>, A>

  private var fragments: Set<Fragment>? = null

  override fun fragment(scope: T.() -> Unit) = unionObject.queue(unionObject, scope) { fragments = reset() }

  override fun config(block: A.() -> Unit) {
    arguments?.apply(block)
  }

}

private
data class UnionAdapterImpl(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any> = emptyMap()
) : GraphqlPropertyDelegate<Model<*>?>,
    FragmentContext {

  var value: Model<*>? = null

  override fun asNullable(): GraphqlPropertyDelegate<Model<*>?> = this

  override fun accept(result: Any?): Boolean {
    if (result is JsonObject) value = transform(result)
    return value == null || value?.isResolved ?: false
  }

  override fun transform(obj: Any?): Model<*>? = (obj as? JsonObject)?.let { jsonObject ->
    jsonObject["__typename"]?.let { typeName ->

      fragments.find { it.model.graphqlType == typeName }
          ?.initializer
          ?.invoke()
          ?.apply { accept(jsonObject) }
    }
  }

  override fun toRawPayload(): String = qproperty.graphqlName +
      args.stringify() +
      fragments.joinToString(prefix = "{__typename,", postfix = "}") {
        it.model.run {
          "... on " + graphqlType + toGraphql()
        }
      }

  override fun getValue(inst: Model<*>, property: KProperty<*>): Model<*> = value!!

  override fun equals(other: Any?): Boolean {
    if (other !is Adapter) return false
    if (other !is GraphQlField<*>) return false
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

  override fun asList(): GraphqlPropertyDelegate<List<Model<*>>> =
      object : GraphqlPropertyDelegate<Model<*>>,
          Adapter by this@UnionAdapterImpl,
          FragmentContext by this@UnionAdapterImpl {
        override fun getValue(inst: Model<*>, property: KProperty<*>): Model<*> = this@UnionAdapterImpl.value!! // this should never happen
        override fun asNullable() = this@UnionAdapterImpl.asNullable()
        override fun asList() = this@UnionAdapterImpl.asList()
        override fun transform(obj: Any?) = this@UnionAdapterImpl.transform(obj)
        override fun equals(other: Any?) = this@UnionAdapterImpl == other
        override fun hashCode() = this@UnionAdapterImpl.hashCode()
      }.let { ListDelegate(it) }
}

