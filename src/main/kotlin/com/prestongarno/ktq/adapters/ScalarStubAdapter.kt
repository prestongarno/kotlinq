package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

internal class ScalarStubAdapter<T, out B: ArgBuilder>(
    fieldName: String,
    val builderInit: (ArgBuilder) -> B
) : FieldAdapter(fieldName),
    Stub<T>,
    QConfigStub<T, B>,
    ArgBuilder {

  var value : T? = null
  var default : T? = null

  override fun withDefault(value: T) = apply { default = value }

  override fun config(): B = builderInit(ScalarStubAdapter<T, B>(fieldName, builderInit))

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
    this.property = property
    return apply { super.onProvideDelegate(inst) }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = value?: default?:
      throw IllegalStateException("property '${property.name}' was null")

  @Suppress("UNCHECKED_CAST") override fun <T> build() : Stub<T> = this as Stub<T>

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value)  }

}

