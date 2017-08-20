package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.NullableStub
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.Stub
import kotlin.reflect.KProperty

internal open class StubAdapter<T : QType> (init: () -> T?, inst: QType)
  : Mapper<T>(inst, init.invoke(), null), Stub<T> {

  override operator fun getValue(inst: QType, property: KProperty<*>): T = this.value
      ?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")

  override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> = apply { this.property = property }
}

internal open class NullStubAdapter<T> constructor(private val init: () -> T, inst: QType)
  : Mapper<T>(inst, init.invoke(), null), NullableStub<T> {

  override fun getValue(inst: QType, property: KProperty<*>): T? = this.value

  override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>)
      : NullableStub<T> = apply { this.property = property }
}
