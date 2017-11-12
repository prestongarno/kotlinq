package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.properties.GraphQlProperty
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.internal.ValueDelegate
import com.prestongarno.ktq.toArgumentMap
import kotlin.reflect.KProperty

interface ScalarDelegate<out D : PrimitiveStub> : SchemaStub {
  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): D
}

class StringDelegateConfig<A : ArgBuilder>(val qproperty: GraphQlProperty) : SchemaStub {
  operator fun invoke(arguments: A, scope: (StringDelegate<A>.() -> Unit)): StringDelegate<A> =
      StringDelegate(qproperty, arguments).apply(scope)
}
class IntegerDelegateConfig<A : ArgBuilder>(val qproperty: GraphQlProperty) : SchemaStub {
  operator fun invoke(arguments: A, scope: IntegerDelegate<A>.() -> Unit): IntegerDelegate<A> =
      IntegerDelegate(qproperty, arguments).apply(scope)
}
class FloatDelegateConfig<A : ArgBuilder>(val qproperty: GraphQlProperty) : SchemaStub {
  operator fun invoke(arguments: A, scope: FloatDelegate<A>.() -> Unit): FloatDelegate<A> =
      FloatDelegate(qproperty, arguments).apply(scope)
}
class BooleanDelegateConfig<A : ArgBuilder>(val qproperty: GraphQlProperty) : SchemaStub {
  operator fun invoke(arguments: A, scope: BooleanDelegate<A>.() -> Unit): BooleanDelegate<A> =
      BooleanDelegate(qproperty, arguments).apply(scope)
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
}

sealed class ScalarDelegateImpl<A : ArgBuilder, D : PrimitiveStub>(
    internal val qproperty: GraphQlProperty,
    internal var argBuilder: A? = null,
    internal var config: (A.() -> Unit)? = null
) : ScalarDelegate<D> {

  abstract fun config(config: A.() -> Unit)

  abstract internal fun toPrimitiveStub(): D

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): D = toPrimitiveStub().apply {
    inst.fields.add(this)
  }
}

class StringDelegate<A : ArgBuilder>(
    qproperty: GraphQlProperty,
    argBuilder: A? = null,
    config: (A.() -> Unit)? = null
) : ScalarDelegateImpl<A, StringStub>(qproperty, argBuilder, config) {

  override fun toPrimitiveStub(): StringStub {
    return StringStub(qproperty, toArgumentMap(argBuilder, config), default)
  }

  var default: String? = null

  override fun config(config: A.() -> Unit) {
    this.config = config
  }

  operator fun invoke(scope: StringDelegate<A>.() -> Unit) = apply(scope)

}

class IntegerDelegate<A : ArgBuilder>(
    qproperty: GraphQlProperty,
    argBuilder: A? = null,
    config: (A.() -> Unit)? = null
) : ScalarDelegateImpl<A, IntStub>(qproperty, argBuilder, config) {

  var default = 0

  override fun config(config: A.() -> Unit) {
    this.config = config
  }

  override fun toPrimitiveStub(): IntStub {
    return IntStub(qproperty, toArgumentMap(argBuilder, config), default)
  }

  operator fun invoke(scope: IntegerDelegate<A>.() -> Unit) = apply(scope)
}

class FloatDelegate<A : ArgBuilder>(
    qproperty: GraphQlProperty,
    argBuilder: A? = null,
    config: (A.() -> Unit)? = null
) : ScalarDelegateImpl<A, FloatStub>(qproperty, argBuilder, config) {

  var default = 0f

  override fun config(config: A.() -> Unit) {
    this.config = config
  }

  override fun toPrimitiveStub(): FloatStub {
    return FloatStub(qproperty, toArgumentMap(argBuilder, config), default)
  }

  operator fun invoke(scope: FloatDelegate<A>.() -> Unit) = apply(scope)
}

class BooleanDelegate<A : ArgBuilder>(
    qproperty: GraphQlProperty,
    argBuilder: A? = null,
    config: (A.() -> Unit)? = null
) : ScalarDelegateImpl<A, BooleanStub>(qproperty, argBuilder, config) {

  var default: Boolean = false

  override fun config(config: A.() -> Unit) {
    this.config = config
  }

  override fun toPrimitiveStub(): BooleanStub {
    return BooleanStub(qproperty, toArgumentMap(argBuilder, config), default)
  }

  operator fun invoke(scope: BooleanDelegate<A>.() -> Unit) = apply(scope)
}

@ValueDelegate(String::class)
class StringStub(
    graphqlProperty: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    var default: String? = null
) : PrimitiveStub(graphqlProperty, args) {

  private var value: String? = null

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

@ValueDelegate(Int::class)
class IntStub(
    property: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    private val default: Int
) : PrimitiveStub(property, args) {

  var value = 0

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Int {
    return if (isResolved) value else default
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

@ValueDelegate(Float::class)
class FloatStub(
    property: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    private val default: Float = 0.0f
) : PrimitiveStub(property, args) {

  private var value = 0f

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Float {
    return if (isResolved) value else default
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

@ValueDelegate(Boolean::class)
class BooleanStub(
    property: GraphQlProperty,
    args: Map<String, Any> = emptyMap(),
    private val default: Boolean = false
) : PrimitiveStub(property, args) {

  private var value: Boolean = false

  operator fun getValue(inst: QModel<*>, property: KProperty<*>): Boolean {
    return if (isResolved) value else default
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

