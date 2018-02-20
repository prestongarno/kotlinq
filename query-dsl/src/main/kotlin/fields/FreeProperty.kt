package org.kotlinq.dsl.fields

import org.kotlinq.dsl.Leaf
import org.kotlinq.dsl.Node
import org.kotlinq.dsl.ScalarSymbol

/**
 * A [FreeProperty] is a DSL concept which can be compared to
 * an "unbound" callable (kotlin) or a java lambda before it is
 * "bound" or "linked" to a callsite before executing.
 *
 * TODO add typename property here
 */
data class FreeProperty internal constructor(
    val name: String,
    val arguments: Map<String, Any> = emptyMap(),
    private var isNullable: Boolean = false) {

  internal fun flagNullable() {
    this.isNullable = true
  }

  internal fun flagNotNull() {
    this.isNullable = false
  }

  internal
  fun asLeaf(symbol: ScalarSymbol): Leaf =
      Leaf(name, arguments, isNullable, symbol)


  internal
  fun asNode(): Node = Node(name, arguments, isNullable)

}