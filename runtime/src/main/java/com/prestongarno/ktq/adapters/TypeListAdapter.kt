package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.TypeArgBuilder
import com.prestongarno.ktq.TypeListArgBuilder
import com.prestongarno.ktq.TypeListStub
import kotlin.reflect.KProperty

internal class TypeListAdapter<V, S, B> :
    ListInitStub<S>,
    TypeListStub<V, S>,
    TypeListArgBuilder
    where
      V : QModel<S>,
      S: QType,
      B: TypeListArgBuilder {

  @Suppress("UNCHECKED_CAST")
  override fun <U : QModel<T>, T : QType> build(init: () -> U): TypeListStub<U, T>
      = apply { this.init = init as () -> V } as TypeListStub<U, T>

  override fun addArg(name: String, value: Any): TypeListArgBuilder = apply { argMap.put(name, value) }

  val results = mutableListOf<V>()
  val argMap = mutableMapOf<String, Any>()
  var init: (() -> V)? = null

  override fun <U : QModel<S>> init(of: () -> U): TypeListStub<U, S> {
    println(of)
    println(of.invoke())
    @Suppress("UNCHECKED_CAST")
    this.init = of as () -> V
    @Suppress("UNCHECKED_CAST") return this as TypeListStub<U, S>
  }

  // todo make this copy to immutable map
  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<V> = results

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeListStub<V, S> = this

}