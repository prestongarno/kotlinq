package org.kotlinq.dsl

import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.Kotlinq
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

@Deprecated("Use GraphComponent for instances instead")
private
fun String.fromScalar(scalarSymbol: ScalarSymbol) = Kotlinq.adapterService.scalarAdapters.run {
  val info = GraphQlPropertyInfo(this@fromScalar, scalarSymbol.clazz.simpleName!!, scalarSymbol.type)
  when (scalarSymbol) {
    ScalarSymbol.StringSymbol -> stringAdapter(info)
    ScalarSymbol.IntSymbol -> intAdapter(info)
    ScalarSymbol.BooleanSymbol -> booleanAdapter(info)
    ScalarSymbol.FloatSymbol -> floatAdapter(info)
  }
}
