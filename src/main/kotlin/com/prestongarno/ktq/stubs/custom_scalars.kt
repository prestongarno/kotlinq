package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarArgBuilder
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.custom.QScalarMapper
import com.prestongarno.ktq.hooks.DelegateProvider

interface CustomScalarInitStub<T: CustomScalar> : SchemaStub {
  fun <U: QScalarMapper<A>, A> init(init: U): CustomStub<U, A>
}

interface CustomScalarConfiguration<T: CustomScalar, out A: CustomScalarArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): CustomScalarInitStub<T>
}

interface CustomScalarConfigStub<T: CustomScalar, out A: CustomScalarArgBuilder> : CustomScalarConfiguration<T, A>

interface CustomStub<U: QScalarMapper<T>, T> : DelegateProvider<T> {
  fun withDefault(value: T): CustomStub<U, T>
}

