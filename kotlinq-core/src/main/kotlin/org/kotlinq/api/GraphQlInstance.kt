package org.kotlinq.api


interface Context {
  val graphQlInstance: GraphQlInstance
}

/**
 * This is used for a query class for property delegates
 * to bind themselves to during object construction
 */
interface GraphQlInstance {
  val graphQlTypeName: String
  val properties: Map<String, Adapter>
  fun isResolved(): Boolean
  fun bindProperty(adapter: Adapter)
  fun toGraphQl(pretty: Boolean = false, extractFragments: Boolean = false): String
}