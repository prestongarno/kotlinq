package org.kotlinq.dsl.fields

import org.kotlinq.dsl.Leaf
import org.kotlinq.dsl.ScalarSymbol

/**
 * A [FreeProperty] is a DSL concept which can be compared to
 * an "unbound" callable (kotlin) or a java lambda before it is
 * "bound" or "linked" to a callsite before executing.
 *
 * This class simply contains information about a query field and
 * arguments. It is the target of many DSL extension functions and properties.
 */
data class FreeProperty(val name: String, val arguments: Map<String, Any>) {


  internal fun bindAsLeafNode(symbol: ScalarSymbol, nullable: Boolean = true): Leaf =
      Leaf(name, arguments, nullable, symbol)
}