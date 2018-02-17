package org.kotlinq.dsl


data class ScalarProperty internal constructor(
    private val context: GraphBuilder,
    private val scalar: Scalar) {

  operator fun invoke() {
    // graph callback
  }
}
