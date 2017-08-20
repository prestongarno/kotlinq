package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KCallable
import kotlin.reflect.KProperty

internal class EmptyStubAdapter<U, A : ArgBuilder<U>>(val fieldName: String,
    val call: KCallable<Stub<U, A>>,
    inst: QType,
    argBuilder: A? = null)
  : Mapper<U>(inst, argBuilder), Stub<U, A> {

  override fun config(): A = super.getArgBuilder()

  init {
    println("${inst::class.simpleName} ::mapping:: $fieldName -> ${call.name}")
  }

  override fun <R : QType> provideDelegate(inst: R,
      property: KProperty<*>): Stub<U, A> = apply { this.property = property }

  override operator fun getValue(inst: QType, property: KProperty<*>): U = TODO()//this.value
  //?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")
}

internal class EmptyNullStubAdapter<U, A : ArgBuilder<U>>(val fieldName: String,
    val call: KCallable<Stub<U, A>>,
    inst: QType,
    argBuilder: A? = null)
  : Mapper<U>(inst, argBuilder), NullableStub<U, A> {

  override fun config(): A = super.getArgBuilder()

  init {
    println("${inst::class.simpleName} ::mapping:: ${fieldName} -> ${call.name}")
  }

  override fun <R : QType> provideDelegate(inst: R,
      property: KProperty<*>): NullableStub<U, A> = apply { this.property = property }

  override operator fun getValue(inst: QType, property: KProperty<*>): U? = TODO()//this.value
  //?: throw IllegalStateException("Expected non-null value for '${property.name}', but was null")
}

