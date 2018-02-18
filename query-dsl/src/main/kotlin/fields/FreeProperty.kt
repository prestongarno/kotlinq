package org.kotlinq.dsl.fields

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.dsl.Leaf
import org.kotlinq.dsl.ScalarSymbol

typealias LeafBinding = (GraphQlInstance, Boolean) -> Unit

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
    val typeName: String? = null,
    private var hasNullability: Boolean = false) {

  internal fun flagNullable() {
    this.hasNullability = true
  }

  internal fun flagNotNull() {
    this.hasNullability = false
  }

  fun string(): LeafBinding = { inst, nullable ->
    bindAsLeafNode(nullable, ScalarSymbol.StringSymbol)
        .withContext(inst)
  }

  fun integer(): LeafBinding = { inst, nullable ->
    bindAsLeafNode(nullable, ScalarSymbol.IntSymbol)
        .withContext(inst)
  }

  fun boolean(): LeafBinding = { inst, nullable ->
    bindAsLeafNode(nullable, ScalarSymbol.BooleanSymbol)
        .withContext(inst)
  }

  fun float(): LeafBinding = { inst, nullable ->
    bindAsLeafNode(nullable, ScalarSymbol.BooleanSymbol)
        .withContext(inst)
  }

  companion object {

    fun Leaf.withContext(instance: GraphQlInstance) {
      instance.bindProperty(this.toAdapter())
    }

    private
    fun FreeProperty.bindAsLeafNode(nullable: Boolean = true, symbol: ScalarSymbol): Leaf =
        Leaf(name, arguments, nullable, symbol)

  }

}