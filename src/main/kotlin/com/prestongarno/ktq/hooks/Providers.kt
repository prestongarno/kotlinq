/**For organizing the utility objects/classes which provide stubs for schema types
 */
package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType

internal interface ModelProvider {
  val value: QModel<*>
}

data class Fragment(val initializer: () -> QModel<QType>) {
  internal val model by lazy(initializer)

  override fun toString(): String {
    return "... on ${model.graphqlType}${model.toGraphql(false)}"
  }
}

