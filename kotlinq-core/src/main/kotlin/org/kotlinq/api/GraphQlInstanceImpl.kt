package org.kotlinq.api

import org.kotlinq.adapters.Adapter


/**
 * I thought callhacks were so 2017...
 */
internal
class GraphQlInstanceImpl(override val graphQlTypeName: String)
  : GraphQlInstance {

  private
  val instanceProperties
      : MutableMap<String, Adapter> =
      mutableMapOf()

  // provides a read-only set of properties for the back end to view
  override val properties: Map<String, Adapter>
    get() = instanceProperties

}