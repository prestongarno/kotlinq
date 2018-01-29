package org.kotlinq.api


/**
 * This is used for a query class for property delegates
 * to bind themselves to during object construction
 */
interface GraphQlInstance {
  val graphQlTypeName: String
  val properties: Map<String, Adapter>
  fun isResolved(): Boolean
}