package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.ScalarStub
import com.prestongarno.ktq.adapters.TypeStubAdapter

interface ArgBuilder<T> {
  fun addArg(name: String, value: Any): ArgBuilder<*>
  fun build(): Stub<T>

  companion object {
    fun <T> create(): ArgBuilder<T> = ScalarStub()
  }
}

interface TypeArgBuilder<T : QType, U : QModel<T>> {
  fun <U : QModel<T>> build(init: () -> U) : TypeStub<U, T>

  fun addArg(name: String, value: Any): TypeArgBuilder<T, U>

  companion object {
    fun <T: QType, A: TypeArgBuilder<T, QModel<T>>> create() : TypeArgBuilder<T, QModel<T>>
        = TypeStubAdapter<QModel<T>, T, A>()
  }
}

