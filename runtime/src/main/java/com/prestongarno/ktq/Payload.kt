package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.ScalarStub
import com.prestongarno.ktq.adapters.TypeStubAdapter

interface ArgBuilder {
  fun addArg(name: String, value: Any): ArgBuilder
  fun <T> build(): Stub<T>

  companion object {
    fun <T, A: ArgBuilder> create(): ArgBuilder = ScalarStub<T, A>()
  }
}

interface TypeArgBuilder {

  fun <U, T> build(init: () -> U) : TypeStub<U, T>
	where U : QModel<T>,
          T : QType

  fun addArg(name: String, value: Any): TypeArgBuilder

  companion object {
    fun <T, A> create(): TypeArgBuilder  where T: QType, A: TypeArgBuilder
        = TypeStubAdapter<T, QModel<T>, A>()
  }
}

