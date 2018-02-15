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


/** Default implementation. Should not need to change */
internal
class FragmentImpl(override val initializer: () -> Context) : Fragment {

  override val prototype: Context by lazy { initializer() }

  override fun hashCode(): Int =
      prototype.graphQlInstance.hashCode()

  override fun equals(other: Any?): Boolean =
      other is Fragment
          && other.typeName == typeName
          && other.prototype.graphQlInstance == prototype.graphQlInstance

  override fun toString(): String = "... on " + prototype.graphQlInstance.graphQlTypeName +
      prototype.graphQlInstance.properties
          .map { "'${it.key}': ${it.value.propertyInfo}" }
          .joinToString(separator = ", ", prefix = " { ", postfix = " }")
}