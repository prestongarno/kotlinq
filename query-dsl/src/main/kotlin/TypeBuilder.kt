package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.Kind
import org.kotlinq.dsl.fields.FreeProperty

@GraphQlDslObject
class TypeBuilder internal constructor(
    private val bindableContext: (Adapter) -> Unit
) : DslExtensionScope {

  override fun String.listOf(typeSymbol: ScalarSymbol) =
      FreeProperty(this)
          .asLeaf(typeSymbol)
          .toAdapter()
          .let(bindableContext)

  override fun FreeProperty.listOf(definition: Fragment) =
      asNode(Kind.named(definition.typeName).asList())
          .withDefinition(definition)
          .let(bindableContext)

  override fun String.invoke(
      typeSymbol: Pair<ScalarSymbol, Boolean>,
      arguments: Map<String, Any>
  ) = FreeProperty(this, arguments, typeSymbol.second)
      .asLeaf(typeSymbol.first)
      .toAdapter()
      .let(bindableContext)

  override fun FreeProperty.on(context: Fragment) {
    bindableContext(asNode(Kind.named(context.typeName))
        .withDefinition(context))
  }


  override fun def(typeName: String, block: SelectionSet): Fragment =
      fragment(typeName, block)


  override fun String.invoke(vararg arguments: Pair<String, Any>): FreeProperty =
      FreeProperty(this, arguments.toMap())

  override fun FreeProperty.rangeTo(block: FragmentSelection) {
    FragmentContextBuilder.fromBlock(block)?.also {
      asNode(it.typeKind).withFragmentSelection(it).let(bindableContext)
    }
  }

  override fun String.on(definition: Fragment) =
      FreeProperty(this)
          .asNode(Kind.named(definition.typeName))
          .withDefinition(definition)
          .let(bindableContext)
}

