@file:Suppress("UNUSED_PARAMETER")

package org.kotlinq.dsl

import org.kotlinq.api.Context


@GraphQlDslObject
class FragmentContextBuilder internal constructor() {

  internal val fragments = mutableMapOf<String, () -> Context>()

  fun on(typeName: String, block: TypeBuilder.() -> Unit) {
    fragments[typeName] = { GraphBuilder(typeName, definition = block).build() }
  }

  companion object {

    internal
    fun fragmentsFromBlock(block: FragmentContextBuilder.() -> Unit)
        : Set<TypeDefinition>? = FragmentContextBuilder()
        .apply(block)
        .fragments.let { if (it.isEmpty()) null else it }
        ?.map { TypeDefinition(it.key, it.value) }?.toSet()
  }

}