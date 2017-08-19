package com.prestongarno.ktq

import org.jetbrains.annotations.Nullable
import kotlin.reflect.*
import kotlin.reflect.full.allSuperclasses
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.superclasses

@Suppress("UNCHECKED_CAST")
interface QType {
  fun <T> stub(name: String): Stub<T> = PrimitiveStubAdapter<T>(name, this) as Stub<T>

  fun <T> nullableStub(name: String) = NullablePrimitiveStubAdapter<@Nullable T>(name, this) as NullableStub<@Nullable T>

  fun <T : QType> stub(name: String, of: () -> T) : Stub<T> {
    return StubAdapter(name, of, this) as Stub<T>
  }

  fun <T : QType> nullableStub(name: String, of: () -> T) = NullStubAdapter(name, of, this) as NullableStub<T>

  fun <T: QType> nop(of: () -> T) : T {
    val invoke = of.invoke()
    return invoke
  }

  companion object {
    inline fun <reified T: QType> none(inst: QType, type: T) : T {
      return type
    }
  }
}

interface Stub<T> {

  fun <U> mapDirect(of: (T) -> Stub<U>) : Stub<U>

  operator fun getValue(inst: QType, property: KProperty<*>): T

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): Stub<T>
}

interface NullableStub<T> {

  fun <U> mapDirect(of: (T) -> NullableStub<U>): NullableStub<U>

  operator fun getValue(inst: QType, property: KProperty<*>): T?

  operator fun <R : QType> provideDelegate(inst: R, property: KProperty<*>): NullableStub<T>
}
