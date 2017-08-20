package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.NullableStub
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.Stub
import kotlin.reflect.KProperty

internal open class StubAdapter<T : QType, A : ArgBuilder<T>> (init: () -> T?, inst: QType, argBuilder: A? = null)
  : Mapper<T>(inst, init.invoke(), null, argBuilder), Stub<T, A> {

  override fun config(): A = super.getArgBuilder()

  override operator fun getValue(inst: QType, property: KProperty<*>): T = this.value
      ?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")

  override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T, A> = apply { this.property = property }
}

internal open class NullStubAdapter<T, A : ArgBuilder<T>> constructor(init: () -> T, inst: QType, argBuilder: A? = null)
  : Mapper<T>(inst, init.invoke(), null, argBuilder), NullableStub<T, A> {

  override fun config(): A = super.getArgBuilder()

  override fun getValue(inst: QType, property: KProperty<*>): T? = this.value

  override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>)
      : NullableStub<T, A> = apply { this.property = property }
}
