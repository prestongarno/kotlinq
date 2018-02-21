package org.kotlinq.dsl.fields

import org.kotlinq.api.Kind
import org.kotlinq.dsl.Leaf
import org.kotlinq.dsl.Node
import org.kotlinq.dsl.ScalarSymbol


data class FreeProperty internal constructor(
    val name: String,
    val arguments: Map<String, Any> = emptyMap(),
    private var isNullable: Boolean = false) {

  internal fun nullability(isNullable: Boolean = false) =
      apply { this.isNullable = isNullable }

  internal
  fun asLeaf(symbol: ScalarSymbol): Leaf =
      Leaf(name, arguments, typeKindFromInstance(symbol.kind))


  internal
  fun asNode(kind: Kind): Node =
      Node(name, arguments, typeKindFromInstance(kind))

  private fun typeKindFromInstance(kind: Kind) =
      if (isNullable) kind.asNullable() else kind

  internal
  fun isNullable() = isNullable

}