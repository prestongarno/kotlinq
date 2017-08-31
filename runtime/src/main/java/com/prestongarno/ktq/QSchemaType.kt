package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import com.prestongarno.ktq.internal.QConfigProvider
import com.prestongarno.ktq.internal.QScalarStubProvider
import com.prestongarno.ktq.internal.QTypeStubProvider

// TODO This file and the entire API could use a bit of the DRY principle
interface QSchemaType {
  object QScalar : QScalarStubProvider<Stub<*>>, QConfigProvider<Any, ArgBuilder, QConfigStub<*, ArgBuilder>> {
    override fun <T> stub(): Stub<T> = ScalarStubAdapter<T, ArgBuilder>(null)

    override fun <T : Any, A : ArgBuilder> configStub(arginit: (ArgBuilder) -> A): QConfigStub<T, A>
        = ScalarStubAdapter(arginit)
  }

  object QType : QTypeStubProvider<InitStub<*>>, QConfigProvider<QSchemaType, TypeArgBuilder, QTypeConfigStub<*, TypeArgBuilder>> {
    override fun <T : QSchemaType> stub(): InitStub<T>
        = TypeStubAdapter<T, QModel<T>, TypeArgBuilder>(null)

    override fun <T : QSchemaType, A : TypeArgBuilder> configStub(arginit: (TypeArgBuilder) -> A): QTypeConfigStub<T, A>
        = TypeStubAdapter(arginit)
  }

  object QScalarList : QScalarStubProvider<ListStub<*>>, QConfigProvider<Any, ListArgBuilder, ListConfig<*, ListArgBuilder>> {
    override fun <T> stub(): ListStub<T>
        = ScalarListAdapter<T, ListArgBuilder>(null)

    override fun <T : Any, A : ListArgBuilder> configStub(arginit: (ListArgBuilder) -> A): ListConfig<T, A>
        = ScalarListAdapter(arginit)
  }

  object QTypeList : QTypeStubProvider<ListInitStub<*>>, QConfigProvider<QSchemaType, TypeListArgBuilder, ListConfigType<*, TypeListArgBuilder>> {
    override fun <T : QSchemaType> stub(): ListInitStub<T>
        = TypeListAdapter<T, QModel<T>, TypeListArgBuilder>(null)

    override fun <T : QSchemaType, A : TypeListArgBuilder> configStub(arginit: (TypeListArgBuilder) -> A): ListConfigType<T, A>
        = TypeListAdapter(arginit)
  }
}













