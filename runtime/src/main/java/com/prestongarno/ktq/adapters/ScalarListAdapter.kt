package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ListArgBuilder
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QModel
import kotlin.reflect.KProperty


internal class ScalarListAdapter<I, out B : ListArgBuilder>(val builderInit: (ListArgBuilder) -> B ) : FieldAdapter(),
    ListStub<I>,
    ListConfig<I, B>,
    ListArgBuilder {

  val values = mutableListOf<I>()

  override fun config(): B = builderInit(ScalarListAdapter<I, B>(builderInit))

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<I> = values

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): ListStub<I> {
    this.property = property
    return apply { super.onProvideDelegate(inst) }
  }

  @Suppress("UNCHECKED_CAST") override fun <T> build(): ListStub<T> = this as ListStub<T>

  override fun addArg(name: String, value: Any): ListArgBuilder = apply { args.put(name, value) }

  companion object {
  }
}

