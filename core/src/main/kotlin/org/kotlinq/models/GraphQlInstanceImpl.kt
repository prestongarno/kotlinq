package org.kotlinq.models

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentContext
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.InstanceAdapter
import org.kotlinq.common.cast


internal
class GraphQlInstanceImpl(
    properties: List<Adapter>) : GraphQlInstance {


  override val properties = properties
      .filterNot(Adapter::isEmptySelectionSet)
      .map { it.propertyInfo.graphQlName to it }
      .toMap()

  override fun isResolved(): Boolean =
      properties.filterNot { it.value.propertyInfo.isNullable }
          .count { !it.value.isResolved() } == 0

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




private
fun Adapter.isEmptySelectionSet(): Boolean =
    this.cast<InstanceAdapter>()?.fragment?.isEmpty()
        ?: this.cast<FragmentContext>()?.fragments?.isEmpty()
        ?: false

private fun Fragment.isEmpty(): Boolean =
    graphQlInstance.properties.isEmpty()

