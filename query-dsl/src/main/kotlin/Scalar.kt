package org.kotlinq.dsl

import kotlin.reflect.KClass
import kotlin.reflect.KProperty0
import kotlin.reflect.KType
import kotlin.reflect.full.createType

sealed class Scalar(
    internal val clazz: KClass<*>,
    internal val type: KType = clazz.createType()) {

  object StringScalar : Scalar(String::class)
  object IntScalar : Scalar(Int::class)
  object BooleanScalar : Scalar(Boolean::class)
  object FloatScalar : Scalar(Float::class)
}

private object Foo {
  val a = ""

  val callA: KProperty0<String> = ::a

  init {

  }
}
