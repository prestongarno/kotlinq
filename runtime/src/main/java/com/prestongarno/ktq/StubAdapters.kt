package com.prestongarno.ktq

import kotlin.reflect.KCallable
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

internal open class StubAdapter<T : QType> (init: () -> T?, inst: QType)
  : Mapper<T>(inst, init.invoke(), null), Stub<T> {

  override operator fun getValue(inst: QType, property: KProperty<*>): T = this.value
      ?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")

  override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> {
    this.property = property
    return this
  }
}

internal open class NullStubAdapter<T> constructor(private val init: () -> T, inst: QType)
  : Mapper<T>(inst, init.invoke(), null), NullableStub<T> {

  override fun getValue(inst: QType, property: KProperty<*>): T? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T> {
    this.property = property
    return this
  }
}

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


internal class EmptyStubAdapter<U>(val fieldName: String, val call: KCallable<Stub<U>>, inst: QType)
  : Mapper<U>(inst), Stub<U> {

  init { println("${inst::class.simpleName} ::mapping:: ${fieldName} -> ${call.name}") }

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<U> = apply { this.property = property }

  override operator fun getValue(inst: QType, property: KProperty<*>): U = TODO()//this.value
      //?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")

  override fun toString(): String = "EmptyStubAdapter for fields '$fieldName' of the type $call"

}

internal class EmptyNullStubAdapter<U>(val fieldName: String, val call: KCallable<Stub<U>>, inst: QType)
  : Mapper<U>(inst), NullableStub<U> {

  init { println("${inst::class.simpleName} ::mapping:: ${fieldName} -> ${call.name}") }

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<U> = apply { this.property = property }

  override operator fun getValue(inst: QType, property: KProperty<*>): U? = TODO()//this.value
  //?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")

  override fun toString(): String = "EmptyStubAdapter for fields '$fieldName' of the type $call"

}

