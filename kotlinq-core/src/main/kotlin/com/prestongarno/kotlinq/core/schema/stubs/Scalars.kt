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

@file:Suppress("unused")

package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.Adapter
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.adapters.toMap
import com.prestongarno.kotlinq.core.api.GraphqlDslBuilder
import com.prestongarno.kotlinq.core.internal.ValueDelegate
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.internal.formatAs
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import kotlin.reflect.KProperty

interface ScalarDelegate<out D : PrimitiveStub> {

  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): D

  interface NoArg<out T : GraphqlDslBuilder<ArgBuilder>, out S : PrimitiveStub> : ScalarDelegate<S> {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: T.() -> Unit = empty()): ScalarDelegate<S>

    interface Nullable<out T : GraphqlDslBuilder<ArgBuilder>, out X> : DelegateProvider<X?> {
      operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: T.() -> Unit = empty()): DelegateProvider<X?>
    }
  }

  interface Configured<out T : GraphqlDslBuilder<A>, out S : PrimitiveStub, A : ArgumentSpec> {
    operator fun invoke(arguments: A, block: T.() -> Unit = empty()): ScalarDelegate<S>

    interface Nullable<out T : GraphqlDslBuilder<A>, A : ArgumentSpec, out X> {
      operator fun invoke(arguments: A, block: T.() -> Unit = empty()): DelegateProvider<X?>
    }
  }
}

internal
sealed class ScalarPreDelegate<out D : PrimitiveStub, A : ArgumentSpec>(val arguments: A?) : GraphqlDslBuilder<A> {

  override fun config(block: A.() -> Unit) {
    arguments?.block()
  }

  abstract fun toDelegate(propertyName: String): D

  class PreString<A : ArgumentSpec>(arguments: A) : ScalarPreDelegate<StringStub, A>(arguments), StringDelegate<A> {
    override var default: String? = null
    override fun toDelegate(propertyName: String): StringStub =
        StringStub(GraphQlProperty.from("String", false, propertyName), arguments.toMap(), default)

  }

  class PreInt<A : ArgumentSpec>(arguments: A) : ScalarPreDelegate<IntStub, A>(arguments), IntDelegate<A> {
    override var default: Int = 0
    override fun toDelegate(propertyName: String): IntStub =
        IntStub(GraphQlProperty.from("Int", false, propertyName), arguments.toMap(), default)
  }

  class PreFloat<A : ArgumentSpec>(arguments: A) : ScalarPreDelegate<FloatStub, A>(arguments), FloatDelegate<A> {
    override var default: Float = 0f
    override fun toDelegate(propertyName: String): FloatStub =
        FloatStub(GraphQlProperty.from("Float", false, propertyName), arguments.toMap(), default)
  }

  class PreBoolean<A : ArgumentSpec>(arguments: A) : ScalarPreDelegate<BooleanStub, A>(arguments), BooleanDelegate<A> {
    override var default: Boolean = false
    override fun toDelegate(propertyName: String): BooleanStub =
        BooleanStub(GraphQlProperty.from("Boolean", false, propertyName), arguments.toMap(), default)
  }
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

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as PrimitiveStub

    if (qproperty != other.qproperty) return false
    if (args != other.args) return false

    return true
  }

  override fun hashCode(): Int = 31 * qproperty.hashCode() + args.hashCode()

  override fun toString(): String {
    return qproperty.graphqlName + " (${qproperty.graphqlType})"
  }

}

internal fun <T : PrimitiveStub> T.bindToContext(inst: QModel<*>) = apply {
  inst.register(this)
}

@ValueDelegate(String::class)
class StringStub(
    graphqlProperty: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    var default: String? = null
) : PrimitiveStub(graphqlProperty, args) {

  internal
  var value: String? = null

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

  internal
  var value = default

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

  internal var value = default

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

  internal var value: Boolean = default

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Boolean = value

  override fun accept(result: Any?): Boolean {
    if (result is Boolean) value = result else result?.toString()?.let { it == "true" || value }
    resolved = true
    return true
  }

}

interface NullablePrimitive<out T> : GraphQlField<T?>, Adapter

internal
fun <T> PrimitiveStub.wrapAsNullable(get: () -> T?): NullablePrimitive<T> =
    object : NullablePrimitive<T>, Adapter by this@wrapAsNullable {
      override fun getValue(inst: QModel<*>, property: KProperty<*>): T? = get()
      override fun hashCode(): Int = super.hashCode() * 31
      override fun equals(other: Any?): Boolean =
          super.equals(other) && other is NullablePrimitive<*>
    }

internal
fun StringStub.wrapAsNullable() =
    wrapAsNullable { this.value ?: default }

internal
fun IntStub.wrapAsNullable() =
    wrapAsNullable { if (!isResolved) null else value }

internal
fun FloatStub.wrapAsNullable() =
    wrapAsNullable { if (!isResolved) null else value }

internal
fun BooleanStub.wrapAsNullable() =
    wrapAsNullable { if (!isResolved) null else value }

fun <T> NullablePrimitive<T?>.delegatingTo() = object : DelegateProvider<T?> {
  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<T?> = inst.register(this@delegatingTo)
}

fun <T> NullablePrimitive<T?>.bindingWith(inst: QModel<*>) =
    apply { inst.register(this) }
