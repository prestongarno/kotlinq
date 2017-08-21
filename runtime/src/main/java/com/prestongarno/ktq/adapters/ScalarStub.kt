package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

internal class ScalarStub<T, A: ArgBuilder<T>>(val argBuilder: A? = null) :
    Stub<T>,
    Config<T, A> {

  @Suppress("UNCHECKED_CAST") override fun config(): A = argBuilder ?: ScalarPayload<T>() as A

  override fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): Stub<T> = this

  override fun getValue(inst: QModel<*>, property: KProperty<*>): T {
    throw UnsupportedOperationException()
  }
}