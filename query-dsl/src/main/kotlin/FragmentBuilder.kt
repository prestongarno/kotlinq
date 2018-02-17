package org.kotlinq.dsl

import org.kotlinq.api.Context

class FragmentBuilder {

  internal val fragments = mutableMapOf<String, () -> Context>()

  var arguments: Map<String, Any> = emptyMap()
  var isNullable: Boolean = true

  fun on(typeName: String, block: TypeBuilder.() -> Unit) = Unit

  infix fun String.def(block: TypeBuilder.() -> Unit) {
    fragments[this] = { GraphBuilder(this, definition = block).build() }
  }

  infix fun FragmentSpreadOperator.on(typeName: String): String = typeName

}