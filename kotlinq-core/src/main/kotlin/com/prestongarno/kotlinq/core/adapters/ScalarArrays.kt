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
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate.Companion.wrapAsNullable
import com.prestongarno.kotlinq.core.internal.CollectionDelegate
import com.prestongarno.kotlinq.core.internal.formatAs
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlProperty.Companion.from
import com.prestongarno.kotlinq.core.properties.ListDelegate
import kotlin.reflect.KProperty

internal
sealed class PrimitiveArrayStub<out T : Any>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>
) : GraphqlPropertyDelegate<T> {

  internal var resolved = false

  val isResolved get() = resolved

  override fun asList(): GraphqlPropertyDelegate<List<T>> =
      ListDelegate(this)

  override fun toRawPayload(): String {
    return qproperty.graphqlName +
        if (args.isNotEmpty())
          args.entries.joinToString(",", "(", ")") { "${it.key}: ${formatAs(it.value)}" }
        else ""
  }
}

@CollectionDelegate(Array<String>::class)
internal
class StringArrayStub(
    graphqlProperty: GraphQlProperty,
    private val default: Array<String>? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveArrayStub<Array<String>>(graphqlProperty, args) {

  var value: Array<String>? = null

  override operator fun getValue(inst: QModel<*>, property: KProperty<*>): Array<String> {
    return value ?: default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean =
      transform(result)?.also { value = it } != null

  override fun asNullable(): GraphqlPropertyDelegate<Array<String>?> =
      wrapAsNullable(this, this::value)

  override fun transform(obj: Any?): Array<String>? =
      (obj as? Array<*>)?.map(Any?::toString)?.toTypedArray()

  companion object {
    fun create(propertyName: String, args: ArgumentSpec? = null, default: Array<String>? = null) =
        StringArrayStub(from("String", true, propertyName), default, args.toMap())
  }
}

@CollectionDelegate(IntArray::class)
internal
class IntArrayStub(
    property: GraphQlProperty,
    private val default: IntArray? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveArrayStub<IntArray>(property, args) {

  var value: IntArray? = null

  override operator fun getValue(inst: QModel<*>, property: KProperty<*>): IntArray {
    return value ?: default ?: throw NullPointerException(
        "Graphql qproperty ${this.qproperty.graphqlName} was null (kotlin qproperty $property)")
  }

  override fun accept(result: Any?): Boolean {
    if (result is IntArray) {
      value = result
      resolved = true
    } else {
      value = transform(result)
          ?.also { resolved = true }
    }
    return resolved
  }

  override fun asNullable(): GraphqlPropertyDelegate<IntArray?> =
      wrapAsNullable(this, this::value)

  override fun transform(obj: Any?): IntArray? =
      obj as? IntArray ?: (obj as? Array<*>)
          ?.mapNotNull { it.toString().toIntOrNull() }
          ?.toIntArray()

  companion object {
    fun create(propertyName: String, args: ArgumentSpec? = null, default: IntArray? = null) =
        IntArrayStub(from("Int", true, propertyName), default, args.toMap())
  }
}

@CollectionDelegate(FloatArray::class)
internal
class FloatArrayStub(
    property: GraphQlProperty,
    private val default: FloatArray? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveArrayStub<FloatArray>(property, args) {

  var value: FloatArray? = null

  override operator fun getValue(inst: QModel<*>, property: KProperty<*>): FloatArray {
    return value ?: default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    value = transform(result)
        ?.also { resolved = true }
    return resolved
  }

  override fun transform(obj: Any?): FloatArray? =
      obj as? FloatArray
          ?: (obj as? Array<*>)
          ?.mapNotNull { it.toString().toFloatOrNull() }
          ?.toFloatArray()
          ?: obj?.toString()
          ?.toFloatOrNull()
          ?.let { floatArrayOf(it) }

  override fun asNullable(): GraphqlPropertyDelegate<FloatArray?> =
      wrapAsNullable(this, this::value)

  companion object {
    fun create(propertyName: String, args: ArgumentSpec? = null, default: FloatArray? = null) =
        FloatArrayStub(from("Float", true, propertyName), default, args.toMap())
  }
}

@CollectionDelegate(BooleanArray::class)
internal
class BooleanArrayStub(
    property: GraphQlProperty,
    private val default: BooleanArray? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveArrayStub<BooleanArray>(property, args) {

  var value: BooleanArray? = null

  override operator fun getValue(inst: QModel<*>, property: KProperty<*>): BooleanArray {
    return value ?: default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    value = transform(result)?.also { resolved = true }
    return resolved
  }

  override fun asNullable(): GraphqlPropertyDelegate<BooleanArray?> =
      wrapAsNullable(this, this::value)

  override fun transform(obj: Any?): BooleanArray? = obj as? BooleanArray
      ?: (obj as? List<*>)
      ?.map { it.toString().toBoolean() }
      ?.toBooleanArray()
      ?: (obj as? Array<*>)
      ?.mapNotNull { it.toString().toBoolean() }
      ?.toBooleanArray()

  companion object {
    fun create(propertyName: String, args: ArgumentSpec? = null, default: BooleanArray? = null) =
        BooleanArrayStub(from("Boolean", true, propertyName), default, args.toMap())
  }
}

