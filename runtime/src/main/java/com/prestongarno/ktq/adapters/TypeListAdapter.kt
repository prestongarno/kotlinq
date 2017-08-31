package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.TypeListArgBuilder
import com.prestongarno.ktq.TypeListStub
import kotlin.reflect.KProperty

internal class TypeListAdapter<I : QSchemaType, P : QModel<I>, out B : TypeListArgBuilder>(private var argBuilder: B? = null) :
    ListInitStub<I>,
    TypeListStub<P, I>,
    ListConfigType<I, B>,
    TypeListArgBuilder {

  override fun config(): B {
    @Suppress("UNCHECKED_CAST")
    return if (argBuilder == null)
      this as B
    else argBuilder as B
  }

  @Suppress("UNCHECKED_CAST")
  override fun <U : QModel<T>, T : QSchemaType> build(init: () -> U): TypeListStub<U, T>
      = apply { this.init = init as () -> P } as TypeListStub<U, T>

  override fun addArg(name: String, value: Any): TypeListArgBuilder = apply { argMap.put(name, value) }

  private val results = mutableListOf<P>()
  private val argMap = mutableMapOf<String, Any>()
  var init: (() -> P)? = null

  override fun <U : QModel<I>> init(of: () -> U): TypeListStub<U, I> {
    @Suppress("UNCHECKED_CAST")
    this.init = of as () -> P
    @Suppress("UNCHECKED_CAST") return this as TypeListStub<U, I>
  }

  // todo make this copy to immutable map
  override fun getValue(inst: QModel<*>, property: KProperty<*>): List<P> = results

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeListStub<P, I> = this

}