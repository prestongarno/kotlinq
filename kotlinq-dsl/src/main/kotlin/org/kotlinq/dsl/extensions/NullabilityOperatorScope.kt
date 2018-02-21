package org.kotlinq.dsl.extensions

import org.kotlinq.dsl.fields.FreeProperty


interface NullabilityOperatorScope {
  /**
   * Hint that a type may be null
   *
   * ```
   *   query {
   *     !"search" on def("Human")..{
   *       "name"(string)
   *     }
   *   }
   * ```
   */
  operator fun String.not(): FreeProperty = FreeProperty(this, isNullable = true)

  /**
   * Hint that a type may be null
   *
   * ```
   *   query {
   *     !"search"("type" to Type.HUMAN) on def("Human") {
   *       "name"(string)
   *     }
   *   }
   * ```
   */
  operator fun FreeProperty.not(): FreeProperty =
      apply { nullability() }
}