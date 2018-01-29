package org.kotlinq

import org.kotlinq.adapters.Adapter
import org.kotlinq.api.GraphQlInstance


class GraphQlInstanceImpl(val impl: Model<*>) : GraphQlInstance {

  private
  val instanceProperties: MutableMap<String, Adapter> = mutableMapOf()

  override val graphQlTypeName: String =
      impl.model::class.simpleName!!

  // provides a read-only set of properties for the back end to view
  override val properties: Map<String, Adapter>
    get() = instanceProperties

}