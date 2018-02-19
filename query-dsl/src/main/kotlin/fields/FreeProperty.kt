package org.kotlinq.dsl.fields

import org.kotlinq.dsl.BindableContext
import org.kotlinq.dsl.Leaf
import org.kotlinq.dsl.LeafBinding
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
    val typeName: String? = null,
    private var isNullable: Boolean = false) {

  internal fun flagNullable() {
    this.isNullable = true
  }

  internal fun flagNotNull() {
    this.isNullable = false
  }

  fun string(): LeafBinding = { inst ->
    asLeafNode(ScalarSymbol.StringSymbol).withContext(inst)
  }

  fun integer(): LeafBinding = {
    asLeafNode(ScalarSymbol.IntSymbol).withContext(it)
  }

  fun boolean(): LeafBinding = {
    asLeafNode(ScalarSymbol.BooleanSymbol).withContext(it)
  }

  fun float(): LeafBinding = {
    asLeafNode(ScalarSymbol.BooleanSymbol).withContext(it)
  }

  private
  fun asLeafNode(symbol: ScalarSymbol): Leaf =
      Leaf(name, arguments, isNullable, symbol)


  internal
  fun asNode(): Node = Node(name, arguments, isNullable)

  companion object {

    fun Leaf.withContext(it: BindableContext) {
      it.register(this.toAdapter())
    }

  }

}