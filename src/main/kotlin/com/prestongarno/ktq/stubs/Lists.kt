package com.prestongarno.ktq.stubs

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.DelegateProvider

interface UnionListStub : DelegateProvider<List<QModel<*>>>

interface CustomScalarListStub<U: QScalarListMapper<T>, out T> : DelegateProvider<List<T>>
interface CustomScalarListInitStub<T: CustomScalar> : SchemaStub {
  fun <U: QScalarListMapper<A>, A> querying(of: U): CustomScalarListStub<U, A>
}
interface CustomScalarListConfigStub<T: CustomScalar, out A: ArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): CustomScalarListInitStub<T>
}
