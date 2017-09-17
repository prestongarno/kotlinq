package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.custom.QScalarListMapper
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
interface CustomScalarListInitStub<T: CustomScalar> : SchemaStub {
  fun <U: QScalarListMapper<A>, A> init(of: U): CustomScalarListStub<U, A>
}
interface CustomScalarListConfigStub<T: CustomScalar, out A: CustomScalarListArgBuilder> : SchemaStub {
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
interface CustomScalarListStub<U: QScalarListMapper<T>, T> : SchemaStub {
  operator fun getValue(inst: QModel<*>, property: KProperty<*>): List<T>
  operator fun <R : QModel<*>> provideDelegate(inst: R, property: KProperty<*>): CustomScalarListStub<U, T>
}
