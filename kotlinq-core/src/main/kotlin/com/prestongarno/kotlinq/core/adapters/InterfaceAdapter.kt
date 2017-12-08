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
import com.prestongarno.kotlinq.core.QInterface
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.QType
import com.prestongarno.kotlinq.core.api.Fragment
import com.prestongarno.kotlinq.core.api.FragmentContext
import com.prestongarno.kotlinq.core.internal.ValueDelegate
import com.prestongarno.kotlinq.core.internal.stringify
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.stubs.InterfaceStub
import kotlin.reflect.KProperty

/**
 * Base type of a R.H.S. delegate provider
 */
internal
class InterfaceAdapterImpl<I, out A : ArgumentSpec>(
    qproperty: GraphQlProperty,
    val arguments: A? = null
) : PreDelegate(qproperty),
    InterfaceStub<I, A>

    where I : QType,
          I : QInterface {


  private val fragments = mutableSetOf<Fragment>()

  override fun <T : I> on(initializer: () -> QModel<T>) {
    fragments += Fragment(initializer)
  }

  override fun config(argumentScope: A.() -> Unit) {
    arguments?.argumentScope()
  }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<QModel<I>?> =
      InterfaceDelegateImpl<I>(qproperty, arguments.toMap(), fragments.toSet()).bind(inst)
}

@ValueDelegate(QModel::class)
private
class InterfaceDelegateImpl<I : QType>(
    override val qproperty: GraphQlProperty,
    override val args: Map<String, Any>,
    override val fragments: Set<Fragment>
) : Adapter,
    FragmentContext,
    QField<QModel<I>?> {

  var value: QModel<I>? = null

  override fun accept(result: Any?): Boolean {
    if (result !is JsonObject) return false
    value = fragments.find { it.model.graphqlType == result["__typename"] }
        ?.initializer?.invoke()?.let {
      it.accept(result)
      @Suppress("UNCHECKED_CAST")
      it as? QModel<I>
    }
    return value?.isResolved == true
  }

  override fun toRawPayload(): String =
      qproperty.graphqlName + args.stringify() + fragments.joinToString(
          prefix = "{__typename, ",
          postfix = "}",
          transform = Fragment::toString)

  override operator fun getValue(
      inst: QModel<*>,
      property: KProperty<*>
  ): QModel<I>? = value

  override fun hashCode(): Int =
      (qproperty.hashCode() * 31) +
          (args.hashCode() * 31) +
          (fragments.hashCode() * 31)

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as InterfaceDelegateImpl<*>

    if (qproperty != other.qproperty) return false
    if (args != other.args) return false
    if (fragments != other.fragments) return false

    return true
  }
}
