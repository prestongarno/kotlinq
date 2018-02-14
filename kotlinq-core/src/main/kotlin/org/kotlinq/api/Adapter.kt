package org.kotlinq.api


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


  companion object {

    fun adapterHashcode(adapter: Adapter): Int = adapter.run {
      var hashcode = name.hashCode() * 31
      hashcode += type.hashCode()
      hashcode *= 31
      hashcode += arguments.hashCode()
      hashcode *= 31
      return@run hashcode
    }

    @Suppress("USELESS_CAST") // SMART cast, acshually
    fun adapterEquals(thisAdapter: Adapter, other: Adapter?): Boolean {
      other as? Adapter ?: return false
      if (other.name != thisAdapter.name) return false
      if (other.type != thisAdapter.type) return false
      if (other.arguments.size != thisAdapter.arguments.size) return false

      return other.arguments.count {
        thisAdapter.arguments[it.key] != it.value
      } == 0
    }
  }
}

