package org.kotlinq.dsl

import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import org.kotlinq.dsl.fields.FreeProperty
import LeafGetter
import TypeBuilderBlock
import org.kotlinq.dsl.TypeDefinition.Companion.fromBuilder

@GraphQlDslObject
class TypeBuilder(
    private val graph: GraphQlInstance,
    val defaultTypeName: String = "Object"
) : DslExtensionScope, GraphQlInstance by graph {

  // todo nominal type definitions needs to be modeled as a domain principle
  override fun FreeProperty.define(typeName: String, block: TypeBuilderBlock) {
    copy(typeName = typeName).asNode()
        .withDefinition(fromBuilder(typeName, block))
        .also(graph::bindProperty)
  }

  override fun String.invoke(block: TypeBuilderBlock): TypeDefinition =
      fromBuilder(this, block)

  override fun FreeProperty.on(context: TypeDefinition) {
    bindProperty(asNode().withDefinition(context))
  }

  override fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty =
      FreeProperty(this, arguments.toMap())

  /**
   * TODO rethink the relationship between Names and [GraphQlInstance],
   * because right now there it a circular dependency a la [Kotlinq.createGraphQlInstance]
   */
  override fun FreeProperty.rangeTo(block: FragmentContextBuilder.() -> Unit) {
    FragmentContextBuilder.fragmentsFromBlock(block)?.also { fragments ->
      graph.bindProperty(asNode().withFragmentScope(fragments.map { it.contextDefinition }.toSet()))
    }
  }

  override fun LeafGetter.not() {
    this().invoke(this@TypeBuilder)
  }

  override fun FreeProperty.not() =
      apply { flagNullable() }

  override fun String.invoke(arguments: Map<String, Any>, typeName: String?): FreeProperty {
    return FreeProperty(this, arguments)
  }

  companion object {

    internal
    fun blockToInitializer(typeName: String, block: TypeBuilder.() -> Unit): () -> Context =
        GraphBuilder(typeName, block)::build
  }
}