package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.internal.CollectionDelegate
import kotlin.reflect.KProperty

interface ScalarArrayDelegate<out D: PrimitiveArrayStub> : SchemaStub {
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

sealed class ScalarArrayDelegateImpl<T : ArgBuilder, out D : PrimitiveArrayStub>(
    val graphqlProperty: GraphQlProperty,
    val config: (T.() -> Unit)? = null
) : ScalarArrayDelegate<D> {

  val args by lazy { mutableMapOf<String, Any>() }

  abstract fun config(config: T.() -> Unit): ScalarArrayDelegate<D>

  operator fun invoke(literal: ScalarArrayDelegate<D>.() -> Unit): ScalarArrayDelegate<D> = apply { literal.invoke(this) }

}

class StringArrayDelegate<A : ArgBuilder>(
    schemaProperty: GraphQlProperty,
    config: (A.() -> Unit)? = null
) : ScalarArrayDelegateImpl<A, StringArrayStub>(schemaProperty, config) {

  var default: Array<String>? = null

  fun withDefault(value: Array<String>): ScalarArrayDelegateImpl<A, StringArrayStub> = apply { this.default = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): StringArrayStub =
      StringArrayStub(graphqlProperty, default, apply {}.args.toMap())
          .apply { inst.fields.add(this) }

  override fun config(config: A.() -> Unit): StringArrayDelegate<A> =
      StringArrayDelegate(graphqlProperty, config)
}

class IntegerArrayDelegate<A : ArgBuilder>(
    schemaProperty: GraphQlProperty,
    config: (A.() -> Unit)? = null
) : ScalarArrayDelegateImpl<A, IntArrayStub>(schemaProperty, config) {

  var default: IntArray? = null

  fun withDefault(value: IntArray): ScalarArrayDelegateImpl<A, IntArrayStub> = apply { this.default = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntArrayStub =
      IntArrayStub(graphqlProperty, default, apply {}.args.toMap())
          .apply { inst.fields.add(this) }

  override fun config(config: A.() -> Unit): IntegerArrayDelegate<A> =
      IntegerArrayDelegate(graphqlProperty, config)
}

class FloatArrayDelegate<A : ArgBuilder>(
    schemaProperty: GraphQlProperty,
    config: (A.() -> Unit)? = null
) : ScalarArrayDelegateImpl<A, FloatArrayStub>(schemaProperty, config) {

  var default: FloatArray? = null

  fun withDefault(value: FloatArray): ScalarArrayDelegateImpl<A, FloatArrayStub> = apply { this.default = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): FloatArrayStub =
      FloatArrayStub(graphqlProperty, default, apply {}.args.toMap())
          .apply { inst.fields.add(this) }

  override fun config(config: A.() -> Unit): FloatArrayDelegate<A> =
      FloatArrayDelegate(graphqlProperty, config)
}

class BooleanArrayDelegate<A : ArgBuilder>(
    schemaProperty: GraphQlProperty,
    config: (A.() -> Unit)? = null
) : ScalarArrayDelegateImpl<A, BooleanArrayStub>(schemaProperty, config) {

  var default: BooleanArray? = null

  fun withDefault(value: BooleanArray): ScalarArrayDelegateImpl<A, BooleanArrayStub> = apply { this.default = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanArrayStub =
      BooleanArrayStub(graphqlProperty, default, apply {}.args.toMap())
          .apply { inst.fields.add(this) }

  override fun config(config: A.() -> Unit): BooleanArrayDelegate<A> =
      BooleanArrayDelegate(graphqlProperty, config)
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
    return value?: default ?: throw NullPointerException(
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
      }?: if (default != null) {
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
    return value?: default ?: throw NullPointerException(
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
      }?: if (default != null) {
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
