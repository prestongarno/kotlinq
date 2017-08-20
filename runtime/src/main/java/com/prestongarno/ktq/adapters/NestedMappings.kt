package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty

internal class EmptyStubAdapter<T>(val fieldName: String, val call: KCallable<Stub<T>>, inst: QType)
  : Mapper<T>(inst), Stub<T> {

  init { println("${inst::class.simpleName} ::mapping:: $fieldName -> ${call.name}") }

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T> = apply { this.property = property }

  override operator fun getValue(inst: QType, property: KProperty<*>): T = TODO()//this.value
  //?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")
}

internal class EmptyNullStubAdapter<U>(val fieldName: String, val call: KCallable<Stub<U>>, inst: QType)
  : Mapper<U>(inst), NullableStub<U> {

  init { println("${inst::class.simpleName} ::mapping:: ${fieldName} -> ${call.name}") }

  override fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<U> = apply { this.property = property }

  override operator fun getValue(inst: QType, property: KProperty<*>): U? = TODO()//this.value
  //?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")
}

