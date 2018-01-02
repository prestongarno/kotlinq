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
import com.prestongarno.kotlinq.core.internal.ValueDelegate
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.ListDelegate
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.schema.stubs.EnumStub
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal
class EnumAdapterImpl<T, out A>(
    private val enumClass: KClass<T>,
    private val argBuilder: A?
) : PreDelegate<T, A>(),
    EnumStub<T, A>

    where T : Enum<T>,
          T : QEnumType,
          A : ArgumentSpec {

  override var default: T? = null

  override fun toDelegate(property: GraphQlProperty): GraphqlPropertyDelegate<T> =
      EnumFieldImpl(property, enumClass, argBuilder.toMap(), default)

  override fun config(block: A.() -> Unit) {
    argBuilder?.block()
  }
}

@ValueDelegate(Enum::class)
private
class EnumFieldImpl<T>(
    override val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>,
    override val args: Map<String, Any>,
    private val default: T? = null
) : GraphqlPropertyDelegate<T> where T : Enum<T>, T : QEnumType {

  var value: T? = default

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = value ?: default!!

  override fun asList(): GraphqlPropertyDelegate<List<T>> = ListDelegate(this)

  override fun asNullable() = GraphqlPropertyDelegate.wrapAsNullable(this, this::value)

  override fun accept(result: Any?): Boolean {
    // TODO don't call the java reflection type - use kotlin enums only
    value = transform(result)
    return value != null
  }

  override fun transform(obj: Any?): T? =
      enumClass.java.enumConstants?.find { it.name == "$obj" } ?: default

  override fun toRawPayload(): String = qproperty.graphqlName + args.stringify()

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as EnumFieldImpl<*>

    if (qproperty != other.qproperty) return false
    if (enumClass != other.enumClass) return false
    if (args != other.args) return false

    return true
  }

  override fun hashCode(): Int {
    var result = qproperty.hashCode()
    result = 31 * result + enumClass.hashCode()
    result = 31 * result + args.hashCode()
    return result
  }

}

