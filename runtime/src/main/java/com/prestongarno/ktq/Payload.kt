package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.ScalarListAdapter
import com.prestongarno.ktq.adapters.ScalarStub
import com.prestongarno.ktq.adapters.TypeListAdapter
import com.prestongarno.ktq.adapters.TypeStubAdapter

interface ArgBuilder {
  fun addArg(name: String, value: Any): ArgBuilder
  fun <T> build(): Stub<T>

  companion object {
    @Suppress("UNCHECKED_CAST")
    fun <T, A : ArgBuilder> create(): A = ScalarStub<T, A>() as A
  }
}

interface TypeArgBuilder {

  fun <U, T> build(init: () -> U): TypeStub<U, T>
      where U : QModel<T>,
            T : QSchemaType

  fun addArg(name: String, value: Any): TypeArgBuilder

  companion object {
    @Suppress("UNCHECKED_CAST")
    fun <T : QSchemaType, A : TypeArgBuilder> create(): A = TypeStubAdapter<T, QModel<T>, A>() as A
  }
}

interface ListArgBuilder {

  fun <T> build(): ListStub<T>

  fun addArg(name: String, value: Any): ListArgBuilder

  companion object {
    @Suppress("UNCHECKED_CAST")
    fun <T, A : ListArgBuilder> create(): A = ScalarListAdapter<T, A>() as A
  }
}

interface TypeListArgBuilder {

  fun <U, T> build(init: () -> U): TypeListStub<U, T>
      where U : QModel<T>,
            T : QSchemaType

  fun addArg(name: String, value: Any): TypeListArgBuilder

  companion object {
    @Suppress("UNCHECKED_CAST")
    fun <T : QSchemaType, A : TypeListArgBuilder> create(): A = TypeListAdapter<T, QModel<T>, A>() as A
  }
}
