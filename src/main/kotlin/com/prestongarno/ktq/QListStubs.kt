package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.custom.QScalarListMapper

interface ListInitStub<T : QSchemaType> : SchemaStub {
  fun <U : QModel<T>> init(of: () -> U): TypeListStub<U, T>
}

interface ListConfig<out T, out A : ListArgBuilder> : SchemaStub {
  fun config(): A
}

interface ListConfigType<out T, out A> : SchemaStub where T: QSchemaType, A: TypeListArgBuilder {
  fun config(): A
}
interface CustomScalarListInitStub<T: CustomScalar> : SchemaStub {
  fun <U: QScalarListMapper<A>, A> init(of: U): CustomScalarListStub<U, A>
}
interface CustomScalarListConfigStub<T: CustomScalar, out A: CustomScalarListArgBuilder> : SchemaStub {
  fun config(): A
}

interface ListStub<T> : DelegateProvider<List<T>>

interface TypeListStub<U, out T> : DelegateProvider<List<U>> where U : QModel<T>, T : QSchemaType

interface CustomScalarListStub<U: QScalarListMapper<T>, T> : DelegateProvider<List<T>>
