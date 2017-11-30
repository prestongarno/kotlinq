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

package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.Adapter
import com.prestongarno.ktq.internal.ValueDelegate
import com.prestongarno.ktq.internal.formatAs
import com.prestongarno.ktq.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface ScalarDelegate<out D : PrimitiveStub> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): D
}

@ValueDelegate(Any::class)
sealed class PrimitiveStub(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>
) : Adapter {

  internal var resolved = false

  val isResolved get() = resolved

  override fun toRawPayload(): String = qproperty.graphqlName +
      if (args.isNotEmpty())
        args.entries.joinToString(",", "(", ")") { "${it.key}: ${formatAs(it.value)}" }
      else ""
}

@ValueDelegate(String::class)
class StringStub(
    graphqlProperty: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    var default: String? = null
) : PrimitiveStub(graphqlProperty, args) {

  private var value: String? = null

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): String {
    return value ?: default ?: throw NullPointerException(
        "GraphQL property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    value = result?.toString() ?: default
    resolved = value != null
    return resolved
  }

}

@ValueDelegate(Int::class)
class IntStub(
    property: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    default: Int = 0
) : PrimitiveStub(property, args) {

  private var value = default

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Int = value

  override fun accept(result: Any?): Boolean {
    value = result as? Int ?: (result?.toString()?.toIntOrNull() ?: value)
    resolved = true
    return true
  }
}

@ValueDelegate(Float::class)
class FloatStub(
    property: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    default: Float = 0f
) : PrimitiveStub(property, args) {

  private var value = default

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Float = value

  override fun accept(result: Any?): Boolean {
    value = result as? Float ?: (result?.toString()?.toFloatOrNull() ?: value)
    resolved = true
    return true
  }
}

@ValueDelegate(Boolean::class)
class BooleanStub(
    property: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    default: Boolean = false
) : PrimitiveStub(property, args) {

  private var value: Boolean = default

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Boolean = value

  override fun accept(result: Any?): Boolean {
    if (result is Boolean) value = result else result?.toString()?.let { it == "true" || value }
    resolved = true
    return true
  }

}
