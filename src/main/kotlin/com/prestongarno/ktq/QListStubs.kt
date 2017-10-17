package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.custom.QScalarListMapper

interface ListInitStub<T : QSchemaType> : SchemaStub {
  fun <U : QModel<T>> init(of: () -> U): TypeListStub<U, T>
}

interface CustomScalarListInitStub<T: CustomScalar> : SchemaStub {
  fun <U: QScalarListMapper<A>, A> init(of: U): CustomScalarListStub<U, A>
}

interface ListConfiguration<T, out A: ArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): ListStub<T>
}

interface ListInitConfiguration<T : QSchemaType, out A: ArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): ListInitStub<T>
}

interface CustomScalarListConfigStub<T: CustomScalar, out A: CustomScalarListArgBuilder> : SchemaStub {
  fun config(provider: A.() -> Unit): CustomScalarListInitStub<T>
}

interface ListConfig<T, A : ArgBuilder> : ListConfiguration<T, A>

interface ListConfigType<T, A> : ListInitConfiguration<T, A> where T: QSchemaType, A: ArgBuilder

interface ListStub<T> : DelegateProvider<List<T>>

interface TypeListStub<U, out T> : DelegateProvider<List<U>> where U : QModel<T>, T : QSchemaType

interface CustomScalarListStub<U: QScalarListMapper<T>, T> : DelegateProvider<List<T>>
