package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ListArgBuilder
import com.prestongarno.ktq.ListConfig
import com.prestongarno.ktq.ListStub
import com.prestongarno.ktq.QModel
import kotlin.reflect.KProperty


internal class ScalarListAdapter<I, out B : ListArgBuilder>(argBuilder: B? = null) :
    ListStub<I>,
    ListConfig<I, B>,
    ListArgBuilder {

  @Suppress("UNCHECKED_CAST") private val argBuilder: B = argBuilder ?: this as B

  private val values = mutableListOf<I>()
  private val argMap = mutableMapOf<String, Any>()

  override fun config(): B {
    return argBuilder
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<I> = values

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): ListStub<I> = this

  @Suppress("UNCHECKED_CAST") override fun <T> build(): ListStub<T> = this as ListStub<T>

  override fun addArg(name: String, value: Any): ListArgBuilder = apply { argMap.put(name, value) }

}

