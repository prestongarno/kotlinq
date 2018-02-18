@file:Suppress("UNUSED_PARAMETER")

package org.kotlinq.dsl

import org.kotlinq.api.Context


@GraphQlDslObject
class FragmentScopeBuilder internal constructor() {

  internal val fragments = mutableMapOf<String, () -> Context>()

  fun on(typeName: String, block: TypeBuilder.() -> Unit) {
    fragments[typeName] = { GraphBuilder(typeName, definition = block).build() }
  }

  companion object {

    internal
    fun fragmentsFromBlock(block: FragmentScopeBuilder.() -> Unit)
        : Set<() -> Context>? =
        FragmentScopeBuilder().apply(block)
            .fragments.let { if (it.isEmpty()) null else it }
            ?.map { it.value }?.toSet()
  }

}