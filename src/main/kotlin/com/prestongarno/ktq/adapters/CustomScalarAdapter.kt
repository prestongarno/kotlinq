package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import kotlin.reflect.KProperty

internal class CustomScalarAdapter<E : CustomScalar, P : QScalarMapper<Q>, Q, out B: CustomScalarArgBuilder>(
    fieldName: String, val builderInit: (CustomScalarArgBuilder) -> B
) : FieldAdapter(fieldName),
    CustomScalarArgBuilder,
    CustomScalarConfigStub<E, B>,
    CustomScalarInitStub<E>,
    CustomStub<P, Q> {

  override fun config(): B = builderInit(CustomScalarAdapter<E, P, Q, B>(fieldName, builderInit))

  internal lateinit var adapter: QScalarMapper<Q>

  override fun addArg(name: String, value: Any): Payload = apply { this.args.put(name, value) }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): Q = adapter.value

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): CustomStub<P, Q> {
    inst.fields.add(this)
    return this
  }

  override fun <U : QScalarMapper<T>, T> build(init: U): CustomStub<U, T> {
    this.adapter = adapter
    @Suppress("UNCHECKED_CAST") return this as CustomStub<U, T>
  }

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarMapper<A>, A> init(of: U): CustomStub<U, A> {
    this.adapter = of as QScalarMapper<Q>
    return this as CustomStub<U, A>
  }

}
