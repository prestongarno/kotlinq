package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.ScalarStub
import com.prestongarno.ktq.adapters.TypeListAdapter
import com.prestongarno.ktq.adapters.TypeStubAdapter

interface ArgBuilder {
  fun addArg(name: String, value: Any): ArgBuilder
  fun <T> build(): Stub<T>

  companion object {
    @Suppress("UNCHECKED_CAST")
    fun <T, A: ArgBuilder> create(): A = ScalarStub<T, A>() as A
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

interface TypeListArgBuilder {

  fun <U, T> build(init: () -> U) : TypeListStub<U, T>
      where U : QModel<T>,
            T : QType

  fun addArg(name: String, value: Any): TypeListArgBuilder

  companion object {
    fun <T, A> create(): TypeListArgBuilder  where T: QType, A: TypeListArgBuilder
        = TypeListAdapter<QModel<T>, T, A>()
  }
}
