package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import kotlin.reflect.KProperty

internal class CustomScalarListAdapter<E : CustomScalar, P : QScalarListMapper<Q>, Q, out B : CustomScalarListArgBuilder>(
    fieldName: String, val builderInit: (CustomScalarListArgBuilder) -> B
) : FieldAdapter(fieldName),
    CustomScalarListArgBuilder,
    CustomScalarListConfigStub<E, B>,
    CustomScalarListInitStub<E>,
    CustomScalarListStub<P, Q> {

  override fun config(): B = builderInit(CustomScalarListAdapter<E, P, Q, B>(fieldName, builderInit))

  internal lateinit var adapter: P

  override fun addArg(name: String, value: Any): Payload = apply { this.args.put(name, value) }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<Q> = adapter.value

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): CustomScalarListStub<P, Q> {
    inst.fields.add(this)
    return this
  }

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarListMapper<T>, T> build(init: U): CustomScalarListStub<U, T> {
    this.adapter = init as P
    return this as CustomScalarListStub<U, T>
  }

  @Suppress("UNCHECKED_CAST") override fun <U : QScalarListMapper<A>, A> init(of: U): CustomScalarListStub<U, A> {
    this.adapter = of as P
    return this as CustomScalarListStub<U, A>
  }
}

