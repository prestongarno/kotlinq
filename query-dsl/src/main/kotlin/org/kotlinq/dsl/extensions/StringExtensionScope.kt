package org.kotlinq.dsl.extensions

import org.kotlinq.api.Fragment
import org.kotlinq.dsl.ScalarSymbol
import org.kotlinq.dsl.fields.FreeProperty


interface StringExtensionScope {

  val string: ScalarSymbol get() = ScalarSymbol.StringSymbol
  val integer: ScalarSymbol get() = ScalarSymbol.IntSymbol
  val boolean: ScalarSymbol get() = ScalarSymbol.BooleanSymbol
  val float: ScalarSymbol get() = ScalarSymbol.FloatSymbol

  operator fun String.invoke(
      typeSymbol: ScalarSymbol,
      arguments: Map<String, Any> = emptyMap()
  ) = invoke(typeSymbol to false, arguments)

  operator fun String.invoke(
      typeSymbol: ScalarSymbol,
      vararg arguments: Pair<String, Any>) =
      invoke(typeSymbol to false, *arguments)

  operator fun String.invoke(
      typeSymbol: Pair<ScalarSymbol, Boolean>,
      arguments: Map<String, Any> = emptyMap())

  operator fun String.invoke(
      typeSymbol: Pair<ScalarSymbol, Boolean>,
      vararg arguments: Pair<String, Any>) =
      invoke(typeSymbol, arguments.toMap())

  operator fun String.invoke(
      arguments: Map<String, Any> = emptyMap()
  ): FreeProperty = FreeProperty(this, arguments)

  operator fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty =
      FreeProperty(this, arguments.toMap())

  infix fun String.on(definition: Fragment)
}