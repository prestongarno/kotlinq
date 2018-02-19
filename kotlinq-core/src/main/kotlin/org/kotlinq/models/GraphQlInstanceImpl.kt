package org.kotlinq.models

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentContext
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import kotlin.coroutines.experimental.buildSequence


internal
class GraphQlInstanceImpl : GraphQlInstance {

  private
  val instanceProperties: MutableMap<String, Adapter> =
      mutableMapOf()

  override val properties: Map<String, Adapter>
    get() = instanceProperties

  override val edges = buildSequence {
    for ((_, adapter) in properties) {
      when (adapter) {
        is Fragment -> yield(adapter as Fragment)
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
      instanceProperties.filterNot { it.value.propertyInfo.isNullable }
          .count { !it.value.isResolved() } == 0

  override fun toGraphQl(pretty: Boolean, inlineFragments: Boolean): String =
      GraphQlFormatter.printGraphQl(this, pretty, inlineFragments)

  override fun bindProperty(adapter: Adapter) {
    instanceProperties[adapter.propertyInfo.graphQlName] = adapter
  }

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