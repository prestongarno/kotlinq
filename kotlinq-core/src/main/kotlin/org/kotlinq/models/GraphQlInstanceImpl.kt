package org.kotlinq.models

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlInstance


internal
class GraphQlInstanceImpl(
    override val properties: Map<String, Adapter>) : GraphQlInstance {

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