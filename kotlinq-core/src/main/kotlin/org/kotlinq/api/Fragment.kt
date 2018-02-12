package org.kotlinq.api


/**
 * Represents a GraphQL Fragment definition
 */
interface Fragment {
  val initializer: () -> Context
  /** Prototype context object. Needed for the printing of a GraphQL request */
  val prototype: Context

  val typeName: String get() = prototype.graphQlInstance.graphQlTypeName
}