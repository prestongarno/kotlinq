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
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.api.Fragment
import com.prestongarno.kotlinq.core.api.FragmentContext
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.schema.stubs.InterfaceListStub
import kotlin.reflect.KProperty

/**
 * Factory method for an Interface List GraphQL field delegate provider
 * @param I the type of interface
 * @param A the type of [ArgumentSpec] */
fun <I, A> newInterfaceListStub(
    qproperty: GraphQlProperty, argBuilder: A?
): InterfaceListStub<I, A>
    where I : QType, I : QInterface, A : ArgumentSpec =
    InterfaceListStubImpl(qproperty, argBuilder)

private data class InterfaceListStubImpl<I, out A>(
    private val qproperty: GraphQlProperty,
    private val argBuilder: A?
) : InterfaceListStub<I, A>
    where I : QType,
          I : QInterface,
          A : ArgumentSpec {

  private val fragments = mutableSetOf<Fragment>()

      //InterfaceListField<I>(qproperty, fragments, argBuilder.toMap()).bind(inst)

  override fun <T : I> on(initializer: () -> QModel<T>) {
    fragments += Fragment(initializer)
  }

  override fun config(argumentScope: A.() -> Unit) {
    argBuilder?.argumentScope()
  }
}

private data class InterfaceListField<out I>(
    override val qproperty: GraphQlProperty,
    override val fragments: Set<Fragment>,
    override val args: Map<String, Any>
) : QField<List<QModel<I>>>,
    FragmentContext,
    Adapter
    where I : QType,
          I : QInterface {

  private var value = emptyList<QModel<I>>()

  override fun accept(result: Any?): Boolean {
    return if (result is Collection<*>) {
      result.filterIsInstance<JsonObject>()
          .mapNotNull {
            it["__typename"]?.let { resultType ->
              fragments.find {
                it.model.graphqlType == resultType
              }
            }?.to(it)
          }.let {
        value = it.mapNotNull { (gen, json) ->
          @Suppress("UNCHECKED_CAST")
          gen.initializer().apply {
            accept(json)
          } as? QModel<I>
        }
      }
      return true
    } else false
  }

  override fun toRawPayload(): String {
    return qproperty.graphqlName + args.stringify() +
        fragments.joinToString(prefix = "{__typename,", postfix = "}") {
          "... on " + it.model.graphqlType + it.model.toGraphql()
        }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<QModel<I>> = value

  override fun hashCode(): Int =
      (qproperty.hashCode() * 31) +
          (args.hashCode() * 31) +
          (fragments.hashCode() * 31)

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as InterfaceListField<*>

    if (qproperty != other.qproperty) return false
    if (fragments != other.fragments) return false
    if (args != other.args) return false

    return true
  }
}
