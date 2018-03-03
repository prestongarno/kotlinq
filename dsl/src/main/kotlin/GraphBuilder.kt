package org.kotlinq.dsl

import org.kotlinq.api.Fragment
import org.kotlinq.api.Kotlinq


internal
class GraphBuilder(private val definition: SelectionSet) {

  fun build(typeName: String): Fragment {
    val context = Kotlinq.newContextBuilder()
    TypeBuilder({ context.register(it).ignore() }).apply(definition)
    return context.build(typeName)
  }


}

