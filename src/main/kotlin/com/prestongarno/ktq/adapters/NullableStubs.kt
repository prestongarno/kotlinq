package com.prestongarno.ktq.adapters


/**
 * Adapter for scalar fields */
/*internal class NullableStubAdapter<T, out B: ArgBuilder>(
    qproperty: QProperty,
    val builderInit: (ArgBuilder) -> B
) : FieldConfig(qproperty),
    NullableStub<T>,
    QConfigStub<T, B>,
    ArgBuilder {

  override fun accept(result: Any?): Boolean = TODO("not implemented")

  val value : T? = null

  override fun config(): B = builderInit(NullableStubAdapter<T, B>(qproperty, builderInit))

  override fun <R : QModel<*>> provideDelegate(inst: R, qproperty: KProperty<*>): NullableStub<T> {
    return apply { super.onDelegate(inst, qproperty) }
  }

  override fun getValue(inst: QModel<*>, qproperty: KProperty<*>): T? = value

  @Suppress("UNCHECKED_CAST") override fun <T> build() : Stub<T> = this as Stub<T>

  override fun addArg(name: String, value: Any): ArgBuilder = apply { args.put(name, value)  }

}*/
