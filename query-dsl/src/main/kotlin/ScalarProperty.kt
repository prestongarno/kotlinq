package org.kotlinq.dsl


data class ScalarProperty internal constructor(
    private val context: GraphBuilder,
    private val scalarSymbol: ScalarSymbol) {

  operator fun invoke() {
    // graph callback
  }
}
