/**For organizing the utility objects/classes which provide stubs for schema types
 */
package com.prestongarno.ktq.internal

import com.prestongarno.ktq.QModel

internal interface ModelProvider {
  val value: QModel<*>
}

interface FragmentProvider {
  val fragments: Set<FragmentGenerator>
}

data class FragmentGenerator(val initializer: () -> QModel<*>) {
  internal val model by lazy(initializer)
}

fun <T> nullPointer(): () -> T = { throw NullPointerException() }