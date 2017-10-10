/**For organizing the utility objects/classes which provide stubs for schema types
 */
package com.prestongarno.ktq.internal

import com.prestongarno.ktq.QModel

internal interface ModelProvider {
  fun getModel() : QModel<*>
}

interface FragmentProvider {
  val fragments: Set<FragmentGenerator>
}

internal data class FragmentProviderImpl(override val fragments: Set<FragmentGenerator>): FragmentProvider {
}

data class FragmentGenerator(val initializer: () -> QModel<*>) {
  internal val model by lazy(initializer)
}