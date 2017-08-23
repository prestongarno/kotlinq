package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.*
import kotlin.reflect.*

interface QType {

  fun <T> stub(): Stub<T>
      = ScalarStub<T, ArgBuilder>()

  fun <T> nullableStub(): Stub<T>
      = ScalarStub<T, ArgBuilder>()

  fun <U : QModel<T>, T : QType> stub(of: () -> T): TypeStub<U, T>
      = TypeStubAdapter<T, U, TypeArgBuilder>()

  /****************************************
   *         Nested types fields          *
   ****************************************/

  /** Stub with no input for nested types, requiring only an initializer
   */
  fun <T : QType> typeStub(): InitStub<T>
      = TypeStubAdapter<T, QModel<T>, TypeArgBuilder>()

  /** Configurable stub for primitives
   */
  fun <T, A : ArgBuilder> configStub(argBuilder: A): Config<T, A>
      = ScalarStub(argBuilder)

  /** Configurable stubs for types
   */
  fun <T : QType, A : TypeArgBuilder> typeConfigStub(argBuilder: A)
      : ConfigType<T, A>
      = TypeStubAdapter(argBuilder)
}













