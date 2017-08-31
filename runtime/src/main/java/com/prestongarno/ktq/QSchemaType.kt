package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import com.prestongarno.ktq.internal.QConfigProvider
import com.prestongarno.ktq.internal.QScalarStubProvider
import com.prestongarno.ktq.internal.QTypeStubProvider

// TODO This file and the entire API could use a bit of the DRY principle
interface QSchemaType {

  fun <T> stub(): Stub<T> = ScalarStub<T, ArgBuilder>()

  fun <T> nullableStub(): Stub<T> = ScalarStub<T, ArgBuilder>()

  fun <T : QSchemaType> typeStub(): InitStub<T> = TypeStubAdapter<T, QModel<T>, TypeArgBuilder>()

  fun <T, A : ArgBuilder> configStub(argBuilder: A): QConfigStub<T, A> = ScalarStub(argBuilder)

  fun <T : QSchemaType, A : TypeArgBuilder> typeConfigStub(argBuilder: A): QTypeConfigStub<T, A>
      = TypeStubAdapter(argBuilder)

  fun <T : List<T>> listStub(): ListStub<T> = TODO()

  fun <T : QSchemaType> typeListStub(): ListInitStub<T>
      = TypeListAdapter<T, QModel<T>, TypeListArgBuilder>()

  object QScalar : QScalarStubProvider<Stub<*>>, QConfigProvider<Any, ArgBuilder, QConfigStub<*, ArgBuilder>> {
    override fun <T> stub(): Stub<T>
        = ScalarStub<T, ArgBuilder>()

    override fun <T : Any, A : ArgBuilder> configStub(argBuilder: A): QConfigStub<T, A>
        = ScalarStub(argBuilder)
  }

  object QType : QTypeStubProvider<InitStub<*>>, QConfigProvider<QSchemaType, TypeArgBuilder, QTypeConfigStub<*, TypeArgBuilder>> {
    override fun <T : QSchemaType> stub(): InitStub<T>
        = TypeStubAdapter<T, QModel<T>, TypeArgBuilder>()

    override fun <T : QSchemaType, A : TypeArgBuilder> configStub(argBuilder: A): QTypeConfigStub<T, A>
        = TypeStubAdapter(argBuilder)
  }

  object QScalarList : QScalarStubProvider<ListStub<*>>, QConfigProvider<Any, ListArgBuilder, ListConfig<*, ListArgBuilder>> {
    override fun <T> stub(): ListStub<T>
        = ScalarListAdapter<T, ListArgBuilder>()

    override fun <T : Any, A : ListArgBuilder> configStub(argBuilder: A): ListConfig<T, A>
        = ScalarListAdapter(argBuilder)
  }

  object QTypeList : QTypeStubProvider<ListInitStub<*>>, QConfigProvider<QSchemaType, TypeListArgBuilder, ListConfigType<*, TypeListArgBuilder>> {
    override fun <T : QSchemaType> stub(): ListInitStub<T>
        = TypeListAdapter<T, QModel<T>, TypeListArgBuilder>()

    override fun <T : QSchemaType, A : TypeListArgBuilder> configStub(argBuilder: A): ListConfigType<T, A>
        = TypeListAdapter(argBuilder)
  }
}













