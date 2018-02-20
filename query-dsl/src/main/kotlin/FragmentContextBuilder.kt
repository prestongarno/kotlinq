@file:Suppress("UNUSED_PARAMETER")

package org.kotlinq.dsl

import org.kotlinq.api.Fragment


@GraphQlDslObject
class FragmentContextBuilder internal constructor() {

  internal val fragments = mutableListOf<Fragment>()

  fun on(typeName: String, block: TypeBuilder.() -> Unit) {
    fragments += GraphBuilder(typeName, definition = block).build()
  }

  companion object {

    internal
    fun fragmentsFromBlock(block: FragmentContextBuilder.() -> Unit)
        : Set<Fragment>? =
        FragmentContextBuilder().apply(block).fragments.let {
          if (it.isEmpty()) null else it.toSet()
        }
  }

}