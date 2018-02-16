package org.kotlinq.models

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.services.Configuration


internal
class GraphQlInstanceImpl(override val graphQlTypeName: String) : GraphQlInstance {

  private
  val instanceProperties: MutableMap<String, Adapter> =
      mutableMapOf()

  override val properties: Map<String, Adapter>
    get() = instanceProperties


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

    if (graphQlTypeName != other.graphQlTypeName)
      return false
    if (properties.size != other.properties.size)
      return false

    return other.properties.all { (name, adapter) ->
      properties[name] == adapter
    }
  }

  override fun hashCode(): Int = graphQlTypeName.hashCode() +
      properties.entries.fold(0) { acc, (name, adapter) ->
        acc.times(31) + (name.hashCode() + adapter.hashCode())
      }


  companion object : GraphQlInstanceProvider by Configuration.instance()
}