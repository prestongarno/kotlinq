package org.kotlinq.dsl.extensions

import org.kotlinq.dsl.fields.FreeProperty


interface NullabilityOperatorScope {
  operator fun String.not(): FreeProperty = FreeProperty(this, isNullable = true)
  //free property invokations
  operator fun FreeProperty.not(): FreeProperty =
      apply { nullability() }
}