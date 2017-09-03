package com.prestongarno.ktq

import kotlin.reflect.KProperty

interface ListInitStub<T : QSchemaType> : SchemaStub {
  fun <U : QModel<T>> init(of: () -> U): TypeListStub<U, T>
}

interface ListConfig<out T, out A : ListArgBuilder> : SchemaStub {
  fun config(): A
}

interface ListConfigType<out T, out A> : SchemaStub where T: QSchemaType, A: TypeListArgBuilder {
  fun config(): A
}

interface ListStub<T> : SchemaStub {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): List<T>
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): ListStub<T>
}

interface TypeListStub<U, T> : SchemaStub where U : QModel<T>, T : QSchemaType {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): List<U>
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): TypeListStub<U, T>
}
