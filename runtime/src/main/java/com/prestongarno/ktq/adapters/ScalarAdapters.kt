package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.NullableStub
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.Stub
import kotlin.reflect.KProperty

internal class PrimitiveStubAdapter<T>(inst: QType) : Mapper<T>(inst), Stub<T> {

  override operator fun getValue(inst: QType, property: KProperty<*>): T = this.value
      ?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>)
      : Stub<T> = apply { this.property = property }
}

internal class NullablePrimitiveStubAdapter<T>(inst: QType) : Mapper<T>(inst), NullableStub<T> {

  override fun getValue(inst: QType, property: KProperty<*>): T? = this.value

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>)
      : NullableStub<T> = apply { this.property = property }

}

