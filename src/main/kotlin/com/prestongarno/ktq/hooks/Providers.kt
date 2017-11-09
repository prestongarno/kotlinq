/**For organizing the utility objects/classes which provide stubs for schema types
 */
package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.QModel

internal interface ModelProvider {
  val value: QModel<*>
}

data class Fragment(val initializer: () -> QModel<*>) {
  internal val model by lazy(initializer)
}

