package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import kotlin.reflect.*

// TODO move these stubbing methods to a companion object
// - QType only really serves as a marker interface
interface QType {

  fun <T> stub(): Stub<T> = ScalarStub<T, ArgBuilder>()

  fun <T> nullableStub(): Stub<T> = ScalarStub<T, ArgBuilder>()

  fun <U : QModel<T>, T : QType> stub(of: () -> T): TypeStub<U, T> = TypeStubAdapter<T, U, TypeArgBuilder>()

  fun <T : QType> typeStub(): InitStub<T> = TypeStubAdapter<T, QModel<T>, TypeArgBuilder>()

  fun <T, A : ArgBuilder> configStub(argBuilder: A): Config<T, A> = ScalarStub(argBuilder)

  fun <T : QType, A : TypeArgBuilder> typeConfigStub(argBuilder: A) : ConfigType<T, A>
      = TypeStubAdapter(argBuilder)

  /*=================== List Stub Methods ================*/

  fun <T : List<T>> listStub() : ListStub<T> = TODO()

  fun <T> typeListStub() : ListInitStub<T> where T : QType = TypeListAdapter<QModel<T>, T, TypeListArgBuilder>()
}













