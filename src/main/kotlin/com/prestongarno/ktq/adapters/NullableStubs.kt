package com.prestongarno.ktq.adapters


import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.NullableStub
import com.prestongarno.ktq.QConfigStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.Stub
import kotlin.reflect.KProperty

/**
 * Adapter for scalar fields */
internal class NullableStubAdapter<T, out B: ArgBuilder>(
    fieldName: String,
    val builderInit: (ArgBuilder) -> B
) : FieldAdapter(fieldName),
    NullableStub<T>,
    QConfigStub<T, B>,
    ArgBuilder {
  override fun accept(result: Any?) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  val value : T? = null

  override fun config(): B = builderInit(ScalarStubAdapter<T, B>(fieldName, builderInit))

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T> {
    this.property = property
    return apply { super.onProvideDelegate(inst) }
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T? = value

  @Suppress("UNCHECKED_CAST") override fun <T> build() : Stub<T> = this as Stub<T>

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value)  }

}
