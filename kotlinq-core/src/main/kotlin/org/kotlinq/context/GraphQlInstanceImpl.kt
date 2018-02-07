package org.kotlinq.context

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlJsonParser


/**
 * I thought callhacks were so 2017...
 */
internal
class GraphQlInstanceImpl(override val graphQlTypeName: String)
  : GraphQlInstance {


  private
  val instanceProperties : MutableMap<String, Adapter> =
      mutableMapOf()

  override val properties: Map<String, Adapter>
    get() = instanceProperties

  override fun isResolved(): Boolean =
      instanceProperties.filterNot { it.value.type.isMarkedNullable }
          .count { it.value.isResolved() } == 0

}