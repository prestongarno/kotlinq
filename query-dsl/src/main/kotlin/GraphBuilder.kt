package org.kotlinq.dsl

import org.kotlinq.api.Fragment
import org.kotlinq.api.Kotlinq


internal
class GraphBuilder(
    val graphQlTypeName: String,
    private val definition: TypeBuilder.() -> Unit) {

  fun build(): Fragment {
    val context = Kotlinq.newContextBuilder()
    TypeBuilder({context.register(it).ignore()}).apply(definition)
    return context.build(graphQlTypeName)
  }


}

