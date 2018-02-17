package org.kotlinq.dsl

import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider


internal
class GraphBuilder(
    graphQlTypeName: String,
    private val definition: TypeBuilder.() -> Unit,
    private val delegate: GraphQlInstance = GraphQlInstanceProvider.createNewInstance(graphQlTypeName)
) : GraphQlInstance by delegate {

  fun build(): Context {
    TypeBuilder(this).apply(definition)
    return MockContext(this)
  }

  override fun equals(other: Any?) = delegate == other
  override fun hashCode() = delegate.hashCode()

  private
  class MockContext(override val graphQlInstance: GraphQlInstance) : Context {
    override fun equals(other: Any?): Boolean =
        other is Context && other.graphQlInstance == graphQlInstance
    override fun hashCode(): Int = graphQlInstance.hashCode()
  }
}

