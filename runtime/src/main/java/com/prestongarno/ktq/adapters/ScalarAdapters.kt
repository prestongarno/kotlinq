package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.NullableStub
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.Stub
import kotlin.reflect.KProperty

internal class PrimitiveStubAdapter<T, A : ArgBuilder>(inst: QType,
    argBuilder: A? = null) : Mapper<T>(inst, argBuilder), Stub<T, A> {

  override operator fun getValue(inst: QType, property: KProperty<*>): T = this.value
      ?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>)
      : Stub<T, A> = apply { this.property = property }
}

internal class NullablePrimitiveStubAdapter<T, A : ArgBuilder>(inst: QType,
    argBuilder: A? = null) : Mapper<T>(inst, argBuilder), NullableStub<T, A> {

  override fun getValue(inst: QType, property: KProperty<*>): T? = this.value

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>)
      : NullableStub<T, A> = apply { this.property = property }

}

