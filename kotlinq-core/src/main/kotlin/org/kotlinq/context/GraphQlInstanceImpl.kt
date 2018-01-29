package org.kotlinq.context

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlInstance


/**
 * I thought callhacks were so 2017...
 */
internal
class GraphQlInstanceImpl(override val graphQlTypeName: String)
  : GraphQlInstance {

  fun resolve(name: String, value: String) {
  }

  private
  val instanceProperties
      : MutableMap<String, Adapter> =
      mutableMapOf()

  // provides a read-only set of properties for the back end to view
  override val properties: Map<String, Adapter>
    get() = instanceProperties

}