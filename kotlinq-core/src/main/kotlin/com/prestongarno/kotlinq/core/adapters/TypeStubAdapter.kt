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
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate.Companion.wrapAsNullable
import com.prestongarno.kotlinq.core.api.DefaultBuilderImpl
import com.prestongarno.kotlinq.core.api.ModelProvider
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.ListDelegate
import com.prestongarno.kotlinq.core.schema.QType
import kotlin.reflect.KProperty

internal
fun <I : QType, T : QModel<I>> typeAdapter(
    qproperty: GraphQlProperty,
    init: () -> T,
    context: Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>
): GraphqlPropertyDelegate<T> = TypeStubAdapter(qproperty, init, context.first.toMap())

internal data
class TypeStubAdapter<out I : QType, out P : QModel<I>>(
    override val qproperty: GraphQlProperty,
    val init: () -> P,
    override val args: Map<String, Any> = emptyMap()
) : GraphqlPropertyDelegate<P>,
    ModelProvider {

  private var _value: P? = null

  override val value get() = _value ?: init().also { _value = it }

  val nullable: GraphqlPropertyDelegate<P?> by lazy { wrapAsNullable(this, this::_value) }

  override fun asNullable()
      : GraphqlPropertyDelegate<P?> =
      nullable

  override fun asList()
      : GraphqlPropertyDelegate<List<P>> =
      ListDelegate(this)

  override fun getValue(inst: QModel<*>, property: KProperty<*>)
      : P =
      value

  override fun accept(result: Any?): Boolean {
    _value = transform(result)
    return _value?.isResolved == true
  }

  override fun transform(obj: Any?): P? = if (obj !is JsonObject) null else init().apply {
    resolved = fields.filterNot { (_, value) -> value.accept(obj[value.qproperty.graphqlName]) }.isEmpty()
  }

  override fun toRawPayload(): String =
      qproperty.graphqlName + args.stringify() + value.toGraphql()

  override fun hashCode(): Int =
      (value.hashCode() * 31) +
          (args.hashCode() * 31) +
          (qproperty.hashCode() * 31)

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as TypeStubAdapter<*, *>

    if (qproperty != other.qproperty) return false
    if (args != other.args) return false

    return true
  }
}