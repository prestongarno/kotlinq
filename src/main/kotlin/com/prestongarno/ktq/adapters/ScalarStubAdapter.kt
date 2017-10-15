package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.FieldAdapter
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QConfigStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.typedValueFrom
import kotlin.reflect.KProperty

/**
 * Adapter for scalar fields */
internal class ScalarStubAdapter<T, out B : ArgBuilder>(
    property: QProperty,
    val builderInit: (ArgBuilder) -> B
) : FieldAdapter(property),
    Stub<T>,
    QConfigStub<T, B>,
    ArgBuilder {

  override fun accept(result: Any?): Boolean {
    @Suppress("UNCHECKED_CAST")
    value = if(result != null) targetProperty.typedValueFrom(result) as? T?: default else default
    return value != null
  }

  var value: T? = null
  var default: T? = null

  override fun withDefault(value: T) = apply { default = value }

  override fun config(): B = builderInit(ScalarStubAdapter<T, B>(graphqlProperty, builderInit))

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T> =
      apply { super.onDelegate(inst, property) }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = value ?: default ?:
      throw IllegalStateException("graphqlName '${property.name}' was null")

  @Suppress("UNCHECKED_CAST") override fun <T> build(): Stub<T> = this as Stub<T>

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value) }

}

