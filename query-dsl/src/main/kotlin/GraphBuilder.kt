package org.kotlinq.dsl

import org.kotlinq.api.Definition
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq


internal
class GraphBuilder(
    val graphQlTypeName: String,
    private val definition: TypeBuilder.() -> Unit) {

  fun build(): Definition {
    val context = Kotlinq.createGraphQlInstance(graphQlTypeName)
    TypeBuilder(BindableContext.from(context::bindProperty))
        .apply(definition)
    return DefinitionImpl(graphQlTypeName, context)
  }

  private
  data class DefinitionImpl(
      override val graphQlTypeName: String,
      override val graphQlInstance: GraphQlInstance) : Definition



}

