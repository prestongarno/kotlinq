package org.kotlinq.models

import com.github.salomonbrys.kodein.instance
import org.kotlinq.api.Adapter
import org.kotlinq.api.Configuration
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider


internal
class GraphQlInstanceImpl(override val graphQlTypeName: String, val context: Context) : GraphQlInstance {

  private
  val instanceProperties: MutableMap<String, Adapter> =
      mutableMapOf()

  override val properties: Map<String, Adapter>
    get() = instanceProperties


  override fun isResolved(): Boolean =
      instanceProperties.filterNot { it.value.type.isMarkedNullable }
          .count { !it.value.isResolved() } == 0

  override fun toGraphQl(pretty: Boolean, extractFragments: Boolean): String =
      GraphQlFormatter.printGraphQl(pretty, extractFragments, context)

  override fun bindProperty(adapter: Adapter) {
    instanceProperties[adapter.name] = adapter
  }


  companion object : GraphQlInstanceProvider by Configuration.kodein.instance()
}