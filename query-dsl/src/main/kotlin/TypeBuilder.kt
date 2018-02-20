package org.kotlinq.dsl

import org.kotlinq.api.Definition
import org.kotlinq.dsl.TypeDefinition.Companion.fromBuilder
import org.kotlinq.dsl.fields.FreeProperty

@GraphQlDslObject
class TypeBuilder internal constructor(
    private val bindableContext: BindableContext
) : DslExtensionScope {
  override fun String.invoke(
      typeSymbol: Pair<ScalarSymbol, Boolean>,
      arguments: Map<String, Any>
  ) = FreeProperty(this, arguments, typeSymbol.second)
      .asLeaf(typeSymbol.first)
      .toAdapter()
      .let(bindableContext::register)

  // todo nominal type definitions needs to be modeled as a domain principle
  override fun FreeProperty.define(typeName: String, block: SelectionSet) {
    copy().asNode()
        .withDefinition(fromBuilder(typeName, block))
        .also(bindableContext::register)
  }


  override fun FreeProperty.on(context: TypeDefinition) {
    bindableContext.register(asNode().withDefinition(context))
  }


  fun def(typeName: String, block: SelectionSet) = defineType(typeName, block)

  override fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty =
      FreeProperty(this, arguments.toMap())

  override fun FreeProperty.rangeTo(block: FragmentContextBuilder.() -> Unit) {
    FragmentContextBuilder.fragmentsFromBlock(block)?.also { fragments ->
      bindableContext.register(asNode().withFragmentScope(
          fragments.map(TypeDefinition::definition).toSet()))
    }
  }

  override fun String.on(definition: TypeDefinition) =
      FreeProperty(this)
          .asNode()
          .withDefinition(definition)
          .let(bindableContext::register)

  companion object {

    internal
    fun blockToInitializer(typeName: String, block: TypeBuilder.() -> Unit): () -> Definition =
        GraphBuilder(typeName, block)::build
  }
}