package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

internal class TypeStubAdapter<
    U : QModel<T>,
    T : QType,
    out A : TypeArgBuilder<T, QModel<T>>>(

    private var argBuilder: A? = null)

  : TypeStub<U, T>,
    InitStub<T>,
    ConfigType<A, T>,
    TypeArgBuilder<T, QModel<T>> {

  @Suppress("UNCHECKED_CAST")
  override fun <U : QModel<T>> build(init: () -> U): TypeStub<U, T>
      = apply { result = init.invoke() } as TypeStub<U, T>

  val args: MutableMap<in String, Any> = HashMap()

  var result: QModel<T>? = null

  override fun config(): A {
    @Suppress("UNCHECKED_CAST")
    return if (argBuilder == null)
      this as A
    else argBuilder as A
  }

  override fun init(of: () -> QModel<T>): TypeStub<QModel<T>, T> {
    result = of.invoke()
    @Suppress("UNCHECKED_CAST")
    return this as TypeStub<QModel<T>, T>
  }

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeStub<U, T> {
    return this
  }

  override fun getValue(inst: QModel<*>, property: KProperty<*>): U {
    @Suppress("UNCHECKED_CAST") return result as U
  }

  override fun addArg(name: String, value: Any): TypeArgBuilder<T, QModel<T>>
      = apply { args.put(name, value) }

}