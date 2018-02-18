package org.kotlinq.api


/**
 * Root interface for all delegated properties.
 * Contains reflective type information about the GraphQL field
 * that this instance property represents and arguments for the query
 *
 * @author preston
 */
interface Adapter {

  /** Resulting return type of this property */
  val propertyInfo: GraphQlPropertyInfo

  /** Returns the result of the query, or null if unresolved */
  fun getValue(): Any?

  /**
   * Visitor pattern for setting values of the query on response
   * @param resolver the algorithm for resolving the native object graph from a GraphQL query
   */
  fun accept(resolver: GraphVisitor) = resolver.visit(this)


  /** @return true if this property is resolved */
  fun isResolved(): Boolean


  companion object {

    fun adapterHashcode(adapter: Adapter): Int =
        adapter.propertyInfo.hashCode()

    fun adapterEquals(thisAdapter: Adapter, other: Adapter?): Boolean =
        other is Adapter && other.propertyInfo == thisAdapter.propertyInfo
  }
}

