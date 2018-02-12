package org.kotlinq.api

import kotlin.reflect.KType


/**
 * Root interface for all delegated properties.
 * Contains reflective type information about the GraphQL field
 * that this instance property represents and arguments for the query
 *
 * @author preston
 */
interface Adapter {

  /** Name of this GraphQL property in the schema */
  val name: String

  /** Resulting return type of this property */
  val type: GraphQlType

  /** Arguments passed to this delegated property for the GraphQL query */
  val arguments: Map<String, Any>

  /** Returns the result of the query, or null if unresolved */
  fun getValue(): Any?

  /**
   * Visitor pattern for setting values of the query on response
   * @param resolver the algorithm for resolving the native object graph from a GraphQL query
   */
  fun accept(resolver: GraphVisitor)


  /** @return true if this property is resolved */
  fun isResolved(): Boolean
}

