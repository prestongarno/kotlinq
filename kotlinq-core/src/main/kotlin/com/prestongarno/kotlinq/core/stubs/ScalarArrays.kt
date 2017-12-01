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

package com.prestongarno.kotlinq.core.stubs

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.SchemaStub
import com.prestongarno.kotlinq.core.adapters.Adapter
import com.prestongarno.kotlinq.core.internal.CollectionDelegate
import com.prestongarno.kotlinq.core.internal.formatAs
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import kotlin.reflect.KProperty

interface ScalarArrayDelegate<out D : PrimitiveArrayStub> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): D
}


sealed class PrimitiveArrayStub(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>
) : Adapter {
  internal var resolved = false

  val isResolved get() = resolved

  override fun toRawPayload(): String {
    return qproperty.graphqlName +
        if (args.isNotEmpty())
          args.entries.joinToString(",", "(", ")") { "${it.key}: ${formatAs(it.value)}" }
        else ""
  }
}

@CollectionDelegate(Array<String>::class)
class StringArrayStub(
    graphqlProperty: GraphQlProperty,
    private val default: Array<String>? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveArrayStub(graphqlProperty, args) {

  var value: Array<String>? = null

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Array<String> {
    return value ?: default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    if (result is Array<*>) {
      value = result.map { it.toString() }.toTypedArray()
      resolved = true
      return true
    } else {
      value = default
    }
    return false
  }

}

@CollectionDelegate(IntArray::class)
class IntArrayStub(
    property: GraphQlProperty,
    private val default: IntArray? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveArrayStub(property, args) {

  var value: IntArray? = null

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): IntArray {
    return value ?: default ?: throw NullPointerException(
        "Graphql qproperty ${this.qproperty.graphqlName} was null (kotlin qproperty $property)")
  }

  override fun accept(result: Any?): Boolean {
    resolved = false
    if (result is IntArray) {
      value = result
      resolved = true
    } else {
      val secondary = kotlin.IntArray(0)
      result?.toString()?.toIntOrNull()?.let {
        secondary[0] = it
        resolved = true
        value = secondary
      } ?: if (default != null) {
        resolved = true
        value = default
      }
    }
    return resolved
  }
}

@CollectionDelegate(FloatArray::class)
class FloatArrayStub(
    property: GraphQlProperty,
    private val default: FloatArray? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveArrayStub(property, args) {

  var value: FloatArray? = null

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): FloatArray {
    return value ?: default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    resolved = false
    if (result is FloatArray) {
      value = result
      resolved = true
    } else {
      val secondary = kotlin.FloatArray(0)
      result?.toString()?.toFloatOrNull()?.let {
        secondary[0] = it
        resolved = true
        value = secondary
      } ?: if (default != null) {
        resolved = true
        value = default
      }
    }
    return resolved
  }
}

@CollectionDelegate(BooleanArray::class)
class BooleanArrayStub(
    property: GraphQlProperty,
    private val default: BooleanArray? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveArrayStub(property, args) {

  var value: BooleanArray = BooleanArray(0)

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): BooleanArray {
    return if (isResolved) value else default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    resolved = false
    if (result is List<*>) {
      value = result.map { it.toString().toBoolean() }.toBooleanArray()
      resolved = true
    } else {
      val secondary = result?.toString().let {
        if (it == "true" || it == "false")
          listOf(it.toBoolean()).toBooleanArray()
        else null
      }
      if (secondary != null) {
        resolved = true
        value = secondary
      }
    }
    return resolved
  }

}
