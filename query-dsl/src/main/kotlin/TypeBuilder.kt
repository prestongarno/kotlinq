package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.dsl.fields.FreeProperty

@GraphQlDslObject
class TypeBuilder internal constructor(
    private val bindableContext: (Adapter) -> Unit
) : DslExtensionScope {

  override fun String.invoke(
      typeSymbol: Pair<ScalarSymbol, Boolean>,
      arguments: Map<String, Any>
  ) = FreeProperty(this, arguments, typeSymbol.second)
      .asLeaf(typeSymbol.first)
      .toAdapter()
      .let(bindableContext::invoke)

  // todo nominal type definitions needs to be modeled as a domain principle
  override fun FreeProperty.define(typeName: String, block: SelectionSet) {
    copy().asNode()
        .withDefinition(GraphBuilder(typeName, block).build())
        .also(bindableContext::invoke)
  }


  override fun FreeProperty.on(context: Fragment) {
    bindableContext(asNode().withDefinition(context))
  }


  fun def(typeName: String, block: SelectionSet) = fragment(typeName, block)

  override fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty =
      FreeProperty(this, arguments.toMap())

  override fun FreeProperty.rangeTo(block: FragmentContextBuilder.() -> Unit) {
    FragmentContextBuilder.fragmentsFromBlock(block)?.also { fragments ->
      bindableContext(asNode().withFragmentScope(fragments))
    }
  }

  override fun String.on(definition: Fragment) =
      FreeProperty(this)
          .asNode()
          .withDefinition(definition)
          .let(bindableContext::invoke)

}