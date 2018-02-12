package org.kotlinq.models

import com.github.salomonbrys.kodein.instance
import org.kotlinq.api.Adapter
import org.kotlinq.api.Configuration
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider


internal
class GraphQlInstanceImpl(override val graphQlTypeName: String) : GraphQlInstance {

  private
  val instanceProperties: MutableMap<String, Adapter> =
      mutableMapOf()

  override val properties: Map<String, Adapter>
    get() = instanceProperties


  override fun isResolved(): Boolean =
      instanceProperties.filterNot { it.value.type.isNullable }
          .count { !it.value.isResolved() } == 0

  override fun toGraphQl(pretty: Boolean, extractFragments: Boolean): String =
      GraphQlFormatter.printGraphQl(pretty, extractFragments, this)

  override fun bindProperty(adapter: Adapter) {
    instanceProperties[adapter.name] = adapter
  }

  override fun equals(other: Any?): Boolean {

    (other as? GraphQlInstance) ?: return false

    if (graphQlTypeName != other.graphQlTypeName)
      return false
    if (properties.size != other.properties.size)
      return false

    return other.properties.all { (name, adapter) ->
      properties[name] == adapter
    }
  }

  override fun hashCode(): Int {
    return graphQlTypeName.hashCode() + properties.entries.fold(0) {acc, (name, adapter) ->
      acc + (name.hashCode() + adapter.hashCode()).times(31)
    }
  }


  companion object : GraphQlInstanceProvider by Configuration.kodein.instance()
}