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

  fun onResolve(name: String, value: String): Boolean =
      instanceProperties[name]?.take(value) == true

  override val properties: Map<String, Adapter>
    get() = instanceProperties

  override fun isResolved(): Boolean {
    TODO("not implemented")
  }

}