package org.kotlinq.dsl

import org.kotlinq.api.Kind

sealed class ScalarSymbol(internal val kind: Kind) {

  init { require(kind.isScalar) }

  val typeName = kind.isScalar

  internal
  object StringSymbol : ScalarSymbol(Kind._String)

  internal
  object IntSymbol : ScalarSymbol(Kind._Int)

  internal
  object BooleanSymbol : ScalarSymbol(Kind._Boolean)

  internal
  object FloatSymbol : ScalarSymbol(Kind._Float)
}


