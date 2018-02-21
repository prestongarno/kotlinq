package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.PropertyInfo
import org.kotlinq.api.Kind

sealed class GraphComponent(
    val name: String,
    val arguments: Map<String, Any>,
    val kind: Kind)


class Node internal constructor(
    name: String,
    arguments: Map<String, Any>,
    kind: Kind)
  : GraphComponent(name, arguments, kind) {

  /**
   * Isolating all [kotlin.reflect] usages in DSL for eventually targeting Javascript
   */
  private
  fun propertyInfo(kind: Kind) =
      PropertyInfo.named(name)
          .arguments(arguments)
          .typeKind(kind)
          .build()

  /**
   * Fragment scope has an empty name for now,
   * interface enforcement is tricky when using strings only
   */
  internal fun withFragmentSelection(fragments: Set<Fragment>, info: PropertyInfo = propertyInfo(kind)): Adapter =
      Kotlinq.adapterService.fragmentProperty(info, fragments)

  internal fun withFragmentSelection(info: FragmentContextBuilder.FragmentInfo): Adapter =
      withFragmentSelection(info.fragments, propertyInfo(info.typeKind))

  internal fun withDefinition(definition: Fragment): Adapter = Kotlinq.adapterService
      .instanceProperty(propertyInfo(kind), definition)
}

class Leaf(
    name: String,
    arguments: Map<String, Any>,
    kind: Kind)
  : GraphComponent(name, arguments, kind) {

  internal fun toAdapter(): ParsingAdapter = Kotlinq.adapterService.scalarAdapters
      .adapterFor(PropertyInfo.named(name)
              .typeKind(kind)
              .arguments(arguments)
              .build())

}

