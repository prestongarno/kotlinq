package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ListArgBuilder
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.typedListValueFrom
import kotlin.reflect.KProperty

internal class ScalarListAdapter<I, out B : ListArgBuilder>(
    fieldName: String,
    val builderInit: (ListArgBuilder) -> B
) : FieldAdapter(fieldName),
    ListStub<I>,
    ListConfig<I, B>,
    ListArgBuilder {

  @Suppress("UNCHECKED_CAST") override fun accept(result: Any?) {
    if (result != null) {
      if (result is List<*>)
        result.filterNotNull().run {
          values.addAll((property.typedListValueFrom(this))
              .map { it as I })
        }
      else
        values.addAll(property.typedListValueFrom(result).map { it as I })
    }
  }

  val values = mutableListOf<I>()

  override fun config(): B = builderInit(ScalarListAdapter<I, B>(fieldName, builderInit))

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<I> = values

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): ListStub<I> {
    this.property = property
    return apply { super.onProvideDelegate(inst) }
  }

  @Suppress("UNCHECKED_CAST") override fun <T> build(): ListStub<T> = this as ListStub<T>

  override fun addArg(name: String, value: Any): ListArgBuilder = apply { args.put(name, value) }
}
