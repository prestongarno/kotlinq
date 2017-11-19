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
import com.prestongarno.ktq.stubs.EnumStub
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.hooks.ConfiguredQuery
import com.prestongarno.ktq.hooks.NoArgConfig
import com.prestongarno.ktq.hooks.OptionalConfiguration
import com.prestongarno.ktq.internal.ValueDelegate
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
// Kotlin/Intellij bug??? can't move the `where` clause up to this line!
@PublishedApi internal class EnumConfigStubImpl<T, A>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : ConfiguredQuery<EnumStub<T, A>, A> by ConfiguredQuery.new({
  EnumAdapterImpl(qproperty, enumClass, it)
})
    where T : Enum<*>, T : QEnumType, A : ArgBuilder

@PublishedApi internal class EnumOptionalArgStubQuery<T, A>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : OptionalConfiguration<EnumStub<T, A>, T, A> by OptionalConfiguration.new({
  EnumAdapterImpl(qproperty, enumClass, it)
})
    where T : Enum<*>, T : QEnumType, A : ArgBuilder

@PublishedApi internal class EnumNoArgStub<T>(
    private val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>
) : NoArgConfig<EnumStub<T, ArgBuilder>, T> by NoArgConfig.new({
  EnumAdapterImpl(qproperty, enumClass, it ?: ArgBuilder())
})
    where T : Enum<*>, T : QEnumType

private class EnumAdapterImpl<T, out A>(
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

  override fun config(argumentScope: A.() -> Unit) = argBuilder?.argumentScope()?: Unit
}

@ValueDelegate(Enum::class)
private data class EnumFieldImpl<T>(
    override val qproperty: GraphQlProperty,
    private val enumClass: KClass<T>,
    override val args: Map<String, Any> = emptyMap(),
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
    return qproperty.graphqlName +
        if (args.isNotEmpty())
          args.entries.joinToString(",", "(", ")") { (key, value) ->
            "$key: ${formatAs(value)}"
          }
        else ""
  }
}
