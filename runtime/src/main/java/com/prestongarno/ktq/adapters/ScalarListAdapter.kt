package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ListArgBuilder
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QModel
import kotlin.reflect.KProperty


internal class ScalarListAdapter<I, out B : ListArgBuilder>(val builderInit: ( (ListArgBuilder) -> B )?) :
    ListStub<I>,
    ListConfig<I, B>,
    ListArgBuilder {

  val values = mutableListOf<I>()
  val argMap = mutableMapOf<String, Any>()
  lateinit var property: KProperty<*>

  @Suppress("UNCHECKED_CAST")
  override fun config(): B = builderInit?.invoke(this)?: this as B

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<I> = values

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): ListStub<I> {
    println(property)
    this.property = property
    return this
  }

  @Suppress("UNCHECKED_CAST") override fun <T> build(): ListStub<T> = this as ListStub<T>

  override fun addArg(name: String, value: Any): ListArgBuilder = apply { argMap.put(name, value) }

}

