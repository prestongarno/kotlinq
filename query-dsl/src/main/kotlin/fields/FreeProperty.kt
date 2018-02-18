package org.kotlinq.dsl.fields

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.dsl.Leaf
import org.kotlinq.dsl.ScalarSymbol
import org.kotlinq.dsl.TypeBuilder

typealias LeafBinding = (GraphQlInstance, Boolean) -> Unit

/**
 * A [FreeProperty] is a DSL concept which can be compared to
 * an "unbound" callable (kotlin) or a java lambda before it is
 * "bound" or "linked" to a callsite before executing.
 *
 * TODO add typename property here
 */
data class FreeProperty(val name: String, val arguments: Map<String, Any> = emptyMap()) {

  private
  fun bindAsLeafNode(symbol: ScalarSymbol, nullable: Boolean = true): Leaf =
      Leaf(name, arguments, nullable, symbol)

  fun string(): LeafBinding = { inst, nullable ->
    bindAsLeafNode(ScalarSymbol.StringSymbol, nullable)
        .withContext(inst)
  }

  fun integer(): LeafBinding = { inst, nullable ->
    bindAsLeafNode(ScalarSymbol.IntSymbol, nullable)
        .withContext(inst)
  }

  fun boolean(): LeafBinding = { inst, nullable ->
    bindAsLeafNode(ScalarSymbol.BooleanSymbol, nullable)
        .withContext(inst)
  }

  fun float(): LeafBinding = { inst, nullable ->
    bindAsLeafNode(ScalarSymbol.BooleanSymbol, nullable)
        .withContext(inst)
  }

  companion object {

    fun Leaf.withContext(instance: GraphQlInstance) {
      instance.bindProperty(this.toAdapter())
    }
  }

}