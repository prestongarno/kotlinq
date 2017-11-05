package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.stubs.CustomScalarListStub
import com.prestongarno.ktq.stubs.CustomStub

interface ArgBuilder {
  fun addArg(name: String, value: Any) : ArgBuilder
}

interface CustomScalarArgBuilder : ArgBuilder {
  fun <U: QScalarMapper<T>, T> build(init: U): CustomStub<U, T>
}
interface CustomScalarListArgBuilder : ArgBuilder {
  fun <U: QScalarListMapper<T>, T> build(init: U): CustomScalarListStub<U, T>
}
