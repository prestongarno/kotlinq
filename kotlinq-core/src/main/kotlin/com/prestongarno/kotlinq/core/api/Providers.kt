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

package com.prestongarno.kotlinq.core.api

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.PropertyType
import com.prestongarno.kotlinq.core.schema.QType
import kotlin.reflect.KProperty

typealias KDelegateContext<X> = Pair<X, KProperty<*>>

internal
fun <T> schemaProvider(onProvide: (KDelegateContext<QSchemaType>) -> T) = object : StubProvider<T> {
  override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) = Stub.stub(onProvide(inst to property))
}

fun <T, U> StubProvider<T>.withAlternate(onProvide: (T) -> U) = object : NullableStubProvider<T, U> {
  override fun provideDelegate(inst: QSchemaType, property: KProperty<*>) = this@withAlternate.provideDelegate(inst, property)
  override fun asNullable(): StubProvider<U> = schemaProvider { (inst, property) ->
        this@withAlternate
            .provideDelegate(inst, property)
            .getValue(inst, property)
            .let(onProvide)
  }
}

internal
fun KDelegateContext<QSchemaType>.toGraphQlProperty(
    typeName: String,
    isList: Boolean = false,
    type: PropertyType = PropertyType.from(typeName)
) = GraphQlProperty.from(typeName, isList, second.name, type)

internal
interface ModelProvider {
  val value: QModel<*>
}

/**
 * TODO -> Add type arguments for this and [Fragment] so no type casting on resolving iface fragment types
 */
internal
interface FragmentContext/*<I> where I : QType, I : QInterface*/ {
  val fragments: Set<Fragment>
}

class Fragment(val initializer: () -> QModel<QType>) {
  internal val model by lazy(initializer)

  override fun toString(): String {
    return "... on ${model.graphqlType}${model.toGraphql()}"
  }

  override fun equals(other: Any?): Boolean {
    return model == (other as? Fragment)?.model
  }

  override fun hashCode(): Int {
    return model.hashCode()
  }
}

