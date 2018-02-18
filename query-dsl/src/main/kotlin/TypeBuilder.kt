package org.kotlinq.dsl

import TypeDefinition
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import org.kotlinq.dsl.fields.FreeProperty
import org.kotlinq.dsl.fields.LeafBinding
import kotlin.reflect.KFunction0

@GraphQlDslObject
class TypeBuilder(
    private val graph: GraphQlInstance,
    val defaultTypeName: String = "Object"
) : DslExtensionScope, GraphQlInstance by graph {

  override fun FreeProperty.def(block: TypeBuilder.() -> Unit) = invoke(block)

  override fun FreeProperty.on(context: TypeDefinition) {
    bindProperty(this.bindToNode(this@TypeBuilder)
        .withDefinition(context))
  }

  override fun Node.spread(block: FragmentScopeBuilder.() -> Unit) {
    FragmentScopeBuilder.fragmentsFromBlock(block)?.also { fragments ->
      graph.bindProperty(withFragmentScope(fragments))
    }
  }

  override fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty =
      FreeProperty(this, arguments.toMap())

  override fun FreeProperty.invoke(block: TypeBuilder.() -> Unit) {
    bindProperty(this.bindToNode(this@TypeBuilder)
        .withDefinition(blockToInitializer(typeName ?: "Any", block)))
  }

  override fun FreeProperty.rangeTo(block: FragmentScopeBuilder.() -> Unit) {
    FragmentScopeBuilder.fragmentsFromBlock(block)?.also { fragments ->
      bindProperty(bindToNode(graph).withFragmentScope(fragments))
    }
  }

  override fun KFunction0<LeafBinding>.not() {
    this.invoke().invoke(this@TypeBuilder, false)
  }

  override fun KFunction0<LeafBinding>.unaryMinus() {
    this.invoke().invoke(this@TypeBuilder, false)
  }

  override fun FreeProperty.not(): Node {
    return Node(name, arguments, nullable = false, context = this@TypeBuilder)
  }

  override fun FreeProperty.unaryMinus(): Node {
    return Node(name, arguments, nullable = true, context = this@TypeBuilder)
  }

  override fun String.invoke(arguments: Map<String, Any>, typeName: String?): FreeProperty {
    return FreeProperty(this, arguments)
  }

  private val adapterService get() = Kotlinq.adapterService

  companion object {

    internal
    fun blockToInitializer(typeName: String, block: TypeBuilder.() -> Unit): () -> Context =
        GraphBuilder(typeName, block)::build
  }
}