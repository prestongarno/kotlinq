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

package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.internal.ValueDelegate
import com.prestongarno.ktq.internal.stringify
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.stubs.EnumStub
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

internal
class EnumConfigStubImpl<T, A>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : EnumStub.ConfigurableQuery<T, A>
    where T : Enum<*>, T : QEnumType, A : ArgBuilder {

  override fun invoke(arguments: A, scope: EnumStub<T, A>.() -> Unit): EnumStub<T, A> =
      EnumAdapterImpl(qproperty, enumClass, arguments).apply(scope)

}

internal
class EnumOptionalArgStubQuery<T, A>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : EnumStub.OptionalConfigQuery<T, A>
    where T : Enum<*>, T : QEnumType, A : ArgBuilder {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      EnumFieldImpl(qproperty, enumClass, emptyMap()).bind(inst)

  override fun invoke(arguments: A, scope: EnumStub<T, A>.() -> Unit): EnumStub<T, A> =
      EnumAdapterImpl(qproperty, enumClass, arguments).apply(scope)

  override fun invoke(scope: EnumStub<T, ArgBuilder>.() -> Unit): EnumStub<T, ArgBuilder> =
      EnumAdapterImpl(qproperty, enumClass, ArgBuilder()).apply(scope)

}

@PublishedApi internal
class EnumNoArgStub<T>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : EnumStub.Query<T>
    where T : Enum<*>, T : QEnumType {

  override fun provideDelegate(
      inst: QModel<*>,
      property: KProperty<*>
  ): QField<T> = EnumFieldImpl(qproperty, enumClass, emptyMap()).bind(inst)

  override fun invoke(
      arguments: ArgBuilder,
      scope: EnumStub<T, ArgBuilder>.() -> Unit
  ): EnumStub<T, ArgBuilder> =
      EnumAdapterImpl(qproperty, enumClass, arguments).apply(scope)
}

private
class EnumAdapterImpl<T, out A>(
    qproperty: GraphQlProperty,
    private val enumClass: KClass<T>,
    private val argBuilder: A?
) : PreDelegate(qproperty),
    EnumStub<T, A>

    where T : Enum<*>,
          T : QEnumType,
          A : ArgBuilder {

  override var default: T? = null

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      EnumFieldImpl(qproperty, enumClass, argBuilder.toMap(), default).bind(inst)

  override fun config(argumentScope: A.() -> Unit) {
    argBuilder?.argumentScope()
  }
}

@ValueDelegate(Enum::class)
private data class EnumFieldImpl<T>(
    override val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>,
    override val args: Map<String, Any>,
    private val default: T? = null
) : QField<T>, Adapter where T : Enum<*>, T : QEnumType {

  var value: T? = default

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = value ?: default!!

  override fun accept(result: Any?): Boolean {
    // TODO don't call the java reflection type - use kotlin enums only
    value = enumClass.java.enumConstants?.find { it.name == "$result" } ?: default
    return value != null
  }

  override fun toRawPayload(): String {
    return qproperty.graphqlName + args.stringify()
  }
}
