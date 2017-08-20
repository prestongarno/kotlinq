package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty

internal class EmptyStubAdapter<T, A : ArgBuilder>(val fieldName: String,
    val call: KCallable<Stub<T, A>>,
    inst: QType,
    argBuilder: A? = null)
  : Mapper<T>(inst, argBuilder), Stub<T, A> {

  init {
    println("${inst::class.simpleName} ::mapping:: $fieldName -> ${call.name}")
  }

  override fun <R : QType> provideDelegate(inst: R,
      property: KProperty<*>): Stub<T, A> = apply { this.property = property }

  override operator fun getValue(inst: QType, property: KProperty<*>): T = TODO()//this.value
  //?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")
}

internal class EmptyNullStubAdapter<U, A : ArgBuilder>(val fieldName: String,
    val call: KCallable<Stub<U, A>>,
    inst: QType,
    argBuilder: A? = null)
  : Mapper<U>(inst, argBuilder), NullableStub<U, A> {

  init {
    println("${inst::class.simpleName} ::mapping:: ${fieldName} -> ${call.name}")
  }

  override fun <R : QType> provideDelegate(inst: R,
      property: KProperty<*>): NullableStub<U, A> = apply { this.property = property }

  override operator fun getValue(inst: QType, property: KProperty<*>): U? = TODO()//this.value
  //?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")
}

