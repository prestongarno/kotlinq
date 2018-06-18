package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.dsl.fields.FreeProperty
import org.kotlinq.introspection.Kind

@GraphQlDslObject
class TypeBuilder internal constructor(
    private val bindableContext: (Adapter) -> Unit
) : DslExtensionScope {

  override fun String.listOf(nullTypeSymbol: Pair<ScalarSymbol, Boolean>) =
      FreeProperty(this)
          .nullability(nullTypeSymbol.second)
          .asLeaf(nullTypeSymbol.first)
          .asList()
          .toAdapter()
          .let(bindableContext)

  override fun FreeProperty.listOf(definition: Fragment) =
      asNode(Kind.typeNamed(definition.typeName).asList())
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
    bindableContext(asNode(Kind.typeNamed(context.typeName))
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
          .asNode(Kind.typeNamed(definition.typeName))
          .withDefinition(definition)
          .let(bindableContext)
}

