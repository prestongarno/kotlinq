package com.prestongarno.ktq

import kotlin.reflect.KProperty
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.superclasses
import kotlin.reflect.jvm.reflect

internal class StubAdapter<T: QType> internal constructor(val fieldName: String, private val init: () -> T, inst: QType)
	: Mapper<T>(init.invoke(), null), Stub<T> {

  init {
    Tracker.putProperty(inst, this, this.value)
  }

  override fun <U> mapDirect(of: (T) -> Stub<U>): Stub<U> {
    return of.invoke(this.value!!)
  }

  override operator fun getValue(inst: QType, property: KProperty<*>): T = this.value

	override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
    this.property = property
		return this
	}
}

internal class NullStubAdapter<T> internal constructor(val fieldName: String, private val init: () -> T, inst: QType)
  : Mapper<T>(init.invoke(), null), NullableStub<T> {

  init {
    Tracker.putProperty(inst, this, this.value)
  }

  override fun <U> mapDirect(of: (T) -> NullableStub<U>): NullableStub<U> {
    return of.invoke(this.value!!)
  }

  override fun getValue(inst: QType, property: KProperty<*>): T? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T> {
    this.property = property
    return this
	}
}

internal class PrimitiveStubAdapter<T>(val fieldName: String, inst: QType) : Mapper<T>(null), Stub<T> {

  init {
    Tracker.putProperty(inst, this, this.value)
  }

  override operator fun getValue(inst: QType, property: KProperty<*>): T = this.value

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
    this.property = property
    return this
  }

  override fun <U> mapDirect(of: (T) -> Stub<U>): Stub<U> {
    TODO("not supported") //To change body of created functions use File | Settings | File Templates.
  }

}

internal class NullablePrimitiveStubAdapter<T>(val fieldName: String, inst: QType) : Mapper<T>(null), NullableStub<T> {

  init {
    Tracker.putProperty(inst, this, this.value)
  }

  override fun getValue(inst: QType, property: KProperty<*>): T = this.value

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T> {
    this.property = property
    Tracker.putProperty(inst, this, this.value)
    return this
  }

  override fun <U> mapDirect(of: (T) -> NullableStub<U>): NullableStub<U> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}
