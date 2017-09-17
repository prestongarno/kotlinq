package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import com.prestongarno.ktq.adapters.custom.ScalarAdapter
import kotlin.reflect.KProperty

internal class CustomScalarAdapter<E: CustomScalar, I: ScalarAdapter<T>, T>(
    fieldName: String
) : FieldAdapter(fieldName),
    CustomInitStub<E>,
    CustomStub<I, T> {

  internal lateinit var init: ScalarAdapter<T>

  @Suppress("UNCHECKED_CAST") override fun <U : ScalarAdapter<A>, A> init(of: U): CustomStub<U, A> {
    this.init = of as ScalarAdapter<T>
    return this as CustomStub<U, A>
  }

  override fun addArg(name: String, value: Any): Payload = apply { this.args.put(name, value) }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T = init.value

  override fun toRawPayload(indentation: Int): String = fieldName

  override fun toString() = toRawPayload()

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): CustomStub<I, T> {
    inst.fields.add(this)
    return this
  }

}
