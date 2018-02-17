package org.kotlinq.dsl

import org.kotlinq.api.Context


@GraphQlDslObject
class FragmentScopeBuilder(
    var arguments: Map<String, Any> = emptyMap(),
    var isNullable: Boolean = true) {

  internal val fragments = mutableMapOf<String, () -> Context>()

  fun on(typeName: String, block: TypeBuilder.() -> Unit) = Unit

  infix fun String.def(block: TypeBuilder.() -> Unit) {
    fragments[this] = { GraphBuilder(this, definition = block).build() }
  }

}