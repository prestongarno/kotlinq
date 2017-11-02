package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.custom.QScalarListMapper
import com.prestongarno.ktq.hooks.DelegateProvider

interface ListInitStub<T : QSchemaType> : SchemaStub, ArgBuilder {
  fun <U : QModel<T>> querying(of: () -> U): TypeListStub<U, T>
}

interface CustomScalarListInitStub<T: CustomScalar> : SchemaStub, ArgBuilder {
  fun <U: QScalarListMapper<A>, A> querying(of: U): CustomScalarListStub<U, A>
}

interface ListInitConfig<T : QSchemaType, out A: ArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): ListInitStub<T>
}

interface CustomScalarListConfigStub<T: CustomScalar, out A: CustomScalarListArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): CustomScalarListInitStub<T>
}

interface ListConfigType<T, A> : ListInitConfig<T, A> where T: QSchemaType, A: ArgBuilder

interface TypeListStub<out U, out T> : DelegateProvider<List<U>> where U : QModel<T>, T : QSchemaType

interface CustomScalarListStub<U: QScalarListMapper<T>, out T> : DelegateProvider<List<T>>

interface UnionListStub : DelegateProvider<List<QModel<*>>>
