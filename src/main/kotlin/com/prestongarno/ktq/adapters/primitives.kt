package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.GraphQlProperty
import com.prestongarno.ktq.SchemaStub
import kotlin.reflect.KProperty

interface ScalarDelegate<out D : PrimitiveStub> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): D
}

sealed class PrimitiveStub(
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

sealed class ScalarDelegateImpl<T : ArgBuilder, out D : PrimitiveStub>(
    val graphqlProperty: GraphQlProperty,
    val arginit: (ArgBuilder) -> T,
    val config: (T.() -> Unit)? = null
) : ArgBuilder,
    ScalarDelegate<D> {

  val args by lazy { mutableMapOf<String, Any>() }

  abstract fun config(config: T.() -> Unit): ScalarDelegate<D>

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args[name] = value }

}

class StringDelegate<A : ArgBuilder>(
    schemaProperty: GraphQlProperty,
    arginit: (ArgBuilder) -> A,
    config: (A.() -> Unit)? = null
) : ScalarDelegateImpl<A, StringStub>(schemaProperty, arginit, config) {

  var default: String? = null

  fun withDefault(value: String): ScalarDelegateImpl<A, StringStub> = apply { this.default = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): StringStub =
      StringStub(graphqlProperty, default, apply { config?.invoke(arginit(this)) }.args.toMap())
          .apply { inst.fields.add(this) }

  override fun config(config: A.() -> Unit): StringDelegate<A> =
      StringDelegate(graphqlProperty, arginit, config)
}

class IntegerDelegate<A : ArgBuilder>(
    schemaProperty: GraphQlProperty,
    arginit: (ArgBuilder) -> A,
    config: (A.() -> Unit)? = null
) : ScalarDelegateImpl<A, IntStub>(schemaProperty, arginit, config) {

  var default: Int? = null

  fun withDefault(value: Int): ScalarDelegateImpl<A, IntStub> = apply { this.default = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): IntStub =
      IntStub(graphqlProperty, default, apply { config?.invoke(arginit(this)) }.args.toMap())
          .apply { inst.fields.add(this) }

  override fun config(config: A.() -> Unit): IntegerDelegate<A> =
      IntegerDelegate(graphqlProperty, arginit, config)
}

class FloatDelegate<A : ArgBuilder>(
    schemaProperty: GraphQlProperty,
    arginit: (ArgBuilder) -> A,
    config: (A.() -> Unit)? = null
) : ScalarDelegateImpl<A, FloatStub>(schemaProperty, arginit, config) {

  var default: Float? = null

  fun withDefault(value: Float): ScalarDelegateImpl<A, FloatStub> = apply { this.default = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): FloatStub =
      FloatStub(graphqlProperty, default, apply { config?.invoke(arginit(this)) }.args.toMap())
          .apply { inst.fields.add(this) }

  override fun config(config: A.() -> Unit): FloatDelegate<A> =
      FloatDelegate(graphqlProperty, arginit, config)
}

class BooleanDelegate<A : ArgBuilder>(
    schemaProperty: GraphQlProperty,
    arginit: (ArgBuilder) -> A,
    config: (A.() -> Unit)? = null
) : ScalarDelegateImpl<A, BooleanStub>(schemaProperty, arginit, config) {

  var default: Boolean? = null

  fun withDefault(value: Boolean): ScalarDelegateImpl<A, BooleanStub> = apply { this.default = value }

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): BooleanStub =
      BooleanStub(graphqlProperty, default, apply { config?.invoke(arginit(this)) }.args.toMap())
          .apply { inst.fields.add(this) }

  override fun config(config: A.() -> Unit): BooleanDelegate<A> =
      BooleanDelegate(graphqlProperty, arginit, config)
}

class StringStub(
    graphqlProperty: GraphQlProperty,
    var default: String? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveStub(graphqlProperty, args) {

  var value: String? = null

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): String {
    return value ?: default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    if (result is String) {
      value = result
      resolved = true
      return true
    } else {
      value = default
    }
    return false
  }

}

class IntStub(
    property: GraphQlProperty,
    val default: Int? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveStub(property, args) {

  var value = 0

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Int {
    return if (isResolved) value else default ?: throw NullPointerException(
        "Graphql qproperty ${this.qproperty.graphqlName} was null (kotlin qproperty $property)")
  }

  override fun accept(result: Any?): Boolean {
    resolved = false
    if (result is Int) {
      value = result
      resolved = true
    } else {
      val secondary = result?.toString()?.toIntOrNull() ?: default
      if (secondary != null) {
        resolved = true
        value = secondary
      } else if (default != null) {
        resolved = true
        value = default
      }
    }
    return resolved
  }
}

class FloatStub(
    property: GraphQlProperty,
    val default: Float? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveStub(property, args) {

  var value = 0f

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Float {
    return if (isResolved) value else default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    resolved = false
    if (result is Float) {
      value = result
      resolved = true
    } else {
      val secondary = result?.toString()?.toFloatOrNull() ?: default
      if (secondary != null) {
        resolved = true
        value = secondary
      } else if (default != null) {
        resolved = true
        value = default
      }
    }
    return resolved
  }
}

class BooleanStub(
    property: GraphQlProperty,
    val default: Boolean? = null,
    args: Map<String, Any> = emptyMap()
) : PrimitiveStub(property, args) {

  var value: Boolean = false

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Boolean {
    return if (isResolved) value else default ?: throw NullPointerException(
        "Graphql property ${this.qproperty.graphqlName} was null (kotlin property $property)")
  }

  override fun accept(result: Any?): Boolean {
    resolved = false
    if (result is Boolean) {
      value = result
      resolved = true
    } else {
      val secondary = result?.toString().let {
        if (it == "true" || it == "false")
          it.toBoolean()
        else null
      }
      if (secondary != null) {
        resolved = true
        value = secondary
      } else if (default != null) {
        resolved = true
        value = default
      }
    }
    return resolved
  }

}

