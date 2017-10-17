package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.Adapter

internal abstract class FieldConfig(val graphqlProperty: GraphQlProperty) : ArgBuilder {

  /**
   * A map of arguments for the field (for graphql) */
  val args by lazy { mutableMapOf<String, Any>() }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as FieldConfig

    if (graphqlProperty != other.graphqlProperty) return false

    return true
  }

  override fun hashCode(): Int {
    return graphqlProperty.hashCode()
  }

  internal abstract fun toAdapter(): Adapter

}


