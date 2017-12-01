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

import com.beust.klaxon.JsonArray
import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.QEnumType
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.stubs.EnumListStub
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

@PublishedApi internal
fun <T, A> newEnumListDelegate(
    qproperty: GraphQlProperty,
    arguments: A?,
    enumClass: KClass<T>
): EnumListStub<T, A> where T : Enum<*>, T : QEnumType, A : ArgBuilder =
    EnumListStubImpl(qproperty, arguments, enumClass)

@PublishedApi internal
fun <T> newEnumListField(
    qproperty: GraphQlProperty,
    enumClass: KClass<T>
): QField<List<T>> where T : Enum<*>, T : QEnumType =
    EnumListAdapterImpl(qproperty, emptyMap(), enumClass)

private data class EnumListStubImpl<T, out A>(
    val qproperty: GraphQlProperty,
    val arguments: A? = null,
    val enumClass: KClass<T>
) : EnumListStub<T, A>
    where T : Enum<*>,
          T : QEnumType,
          A : ArgBuilder {

  override var default: T? = null

  override fun config(scope: A.() -> Unit) {
    arguments?.scope()
  }

  override fun provideDelegate(
      inst: QModel<*>,
      property: KProperty<*>
  ): QField<List<T>> =
      EnumListAdapterImpl<T>(qproperty, arguments.toMap(), enumClass).bind(inst)

}

private data class EnumListAdapterImpl<T>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>,
    val enumClass: KClass<T>
) : QField<List<T>>, Adapter
    where T : Enum<*>,
          T : QEnumType {

  private val value = mutableListOf<T>()

  override fun toRawPayload(): String = qproperty.graphqlName + args.stringify()

  override fun accept(result: Any?): Boolean {
    if (result is JsonArray<*>)
      result.filterIsInstance<String>().mapNotNull { str ->
        enumClass.java.enumConstants.find {
          it.name == str
        }
      }.let { transformed ->
        value.addAll(transformed)
      }
    return result is JsonArray<*>
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<T> = value

}

