package org.kotlinq.models

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentContext
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.ReifiedFragmentContext
import kotlin.coroutines.experimental.buildSequence


internal
class GraphQlInstanceImpl(
    override val properties: Map<String, Adapter>) : GraphQlInstance {

  override val edges = buildSequence {
    for ((_, adapter) in properties) {
      when (adapter) {
        is ReifiedFragmentContext -> yield(adapter.fragment)
        is FragmentContext -> {
          val context: FragmentContext = adapter
          for ((_, fragment) in context.fragments) {
            yield(fragment)
          }
        }
      }
    }
  }

  override val nodes: Sequence<Adapter> =
      properties.values.asSequence()


  override fun isResolved(): Boolean =
      properties.filterNot { it.value.propertyInfo.isNullable }
          .count { !it.value.isResolved() } == 0

  override fun toGraphQl(pretty: Boolean, inlineFragments: Boolean): String =
      GraphQlFormatter.printGraphQl(this, pretty, inlineFragments)

  override fun equals(other: Any?): Boolean {

    (other as? GraphQlInstance) ?: return false

    if (properties.size != other.properties.size)
      return false

    return other.properties.all { (name, adapter) ->
      properties[name] == adapter
    }
  }

  override fun hashCode(): Int =
      properties.entries.fold(0) { acc, (name, adapter) ->
        acc.times(31) + (name.hashCode() + adapter.hashCode())
      }

}