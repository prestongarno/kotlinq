package org.kotlinq.dsl

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType

sealed class ScalarSymbol(
    internal val clazz: KClass<*>,
    internal val type: KType = clazz.createType()) {

  val typeName = clazz.simpleName!!

  internal
  object StringSymbol : ScalarSymbol(String::class)

  internal
  object IntSymbol : ScalarSymbol(Int::class)

  internal
  object BooleanSymbol : ScalarSymbol(Boolean::class)

  internal
  object FloatSymbol : ScalarSymbol(Float::class)
}


