package org.kotlinq.dsl

import org.kotlinq.api.Kind

sealed class ScalarSymbol(internal val kind: Kind) {

  init { require(kind.isScalar) }

  val typeName = kind.isScalar

  internal
  object StringSymbol : ScalarSymbol(Kind.Scalar._String)

  internal
  object IntSymbol : ScalarSymbol(Kind.Scalar._Int)

  internal
  object BooleanSymbol : ScalarSymbol(Kind.Scalar._Boolean)

  internal
  object FloatSymbol : ScalarSymbol(Kind.Scalar._Float)
}


