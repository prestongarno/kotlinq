package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QConfigStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.typedValueFrom
import kotlin.reflect.KProperty

/**
 * Adapter for scalar fields */
internal class ScalarStubAdapter<T, out B : ArgBuilder>(
    fieldName: String,
    val builderInit: (ArgBuilder) -> B
) : FieldAdapter(fieldName),
    Stub<T>,
    QConfigStub<T, B>,
    ArgBuilder {

  override fun accept(result: Any?): Boolean {
    @Suppress("UNCHECKED_CAST")
    value = if(result != null) property.typedValueFrom(result) as? T?: default else default
    return value != null
  }

  var value: T? = null
  var default: T? = null

  override fun withDefault(value: T) = apply { default = value }

  override fun config(): B = builderInit(ScalarStubAdapter<T, B>(graphqlName, builderInit))

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
    this.property = property
    return apply { super.onProvideDelegate(inst) }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = value ?: default ?:
      throw IllegalStateException("property '${property.name}' was null")

  @Suppress("UNCHECKED_CAST") override fun <T> build(): Stub<T> = this as Stub<T>

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value) }

}

