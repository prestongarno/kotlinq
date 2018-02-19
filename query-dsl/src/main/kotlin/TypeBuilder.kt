package org.kotlinq.dsl

import org.kotlinq.api.Definition
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import org.kotlinq.dsl.fields.FreeProperty
import org.kotlinq.dsl.TypeDefinition.Companion.fromBuilder

@GraphQlDslObject
class TypeBuilder internal constructor(
    private val bindableContext: BindableContext
) : DslExtensionScope {

  // todo nominal type definitions needs to be modeled as a domain principle
  override fun FreeProperty.define(typeName: String, block: TypeBuilderBlock) {
    copy(typeName = typeName).asNode()
        .withDefinition(fromBuilder(typeName, block))
        .also(bindableContext::register)
  }

  override fun String.invoke(block: TypeBuilderBlock): TypeDefinition =
      fromBuilder(this, block)

  override fun FreeProperty.on(context: TypeDefinition) {
    bindableContext.register(asNode().withDefinition(context))
  }

  override fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty =
      FreeProperty(this, arguments.toMap())

  /**
   * TODO rethink the relationship between Names and [GraphQlInstance],
   * because right now there it a circular dependency a la [Kotlinq.createGraphQlInstance]
   */
  override fun FreeProperty.rangeTo(block: FragmentContextBuilder.() -> Unit) {
    FragmentContextBuilder.fragmentsFromBlock(block)?.also { fragments ->
      bindableContext.register(asNode().withFragmentScope(
          fragments.map(TypeDefinition::definition).toSet()))
    }
  }

  // TODO make the function return the [Leaf] instance,
  // and bind here rather than passing the responsibility
  override fun (LeafGetter).not() {
    this().invoke(bindableContext)
  }

  override fun String.invoke(arguments: Map<String, Any>, typeName: String?): FreeProperty {
    return FreeProperty(this, arguments)
  }

  companion object {

    internal
    fun blockToInitializer(typeName: String, block: TypeBuilder.() -> Unit): () -> Definition =
        GraphBuilder(typeName, block)::build
  }
}