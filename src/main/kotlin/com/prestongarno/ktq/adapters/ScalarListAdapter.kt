package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.FieldAdapter
import com.prestongarno.ktq.ListArgBuilder
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QProperty
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.typedListValueFrom
import kotlin.reflect.KProperty

internal class ScalarListAdapter<I, out B : ListArgBuilder>(
    property: QProperty,
    val builderInit: (ListArgBuilder) -> B
) : FieldAdapter(property),
    ListStub<I>,
    ListConfig<I, B>,
    ListArgBuilder {

  @Suppress("UNCHECKED_CAST") override fun accept(result: Any?): Boolean {
    if (result != null) {
      if (result is List<*>)
        result.filterNotNull().run {
          values.addAll((graphqlProperty.kproperty.typedListValueFrom(this))
              .map { it as I })
        }
      else
        values.addAll(graphqlProperty.kproperty.typedListValueFrom(result).map { it as I })
    }
    return true
  }

  val values = mutableListOf<I>()

  override fun config(): B = builderInit(ScalarListAdapter<I, B>(graphqlProperty, builderInit))

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<I> = values

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): ListStub<I> =
      apply { super.onDelegate(inst, property) }

  @Suppress("UNCHECKED_CAST") override fun <T> build(): ListStub<T> = this as ListStub<T>

  override fun addArg(name: String, value: Any): ListArgBuilder = apply { args.put(name, value) }
}
