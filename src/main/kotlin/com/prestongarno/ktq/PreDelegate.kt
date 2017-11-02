package com.prestongarno.ktq

/**
 * Base class for all objects who produce immutable field delegates */
internal abstract class PreDelegate(val qproperty: GraphQlProperty) : ArgBuilder {
  /**
   * A map of arguments for the field (for graphql) */
  val args by lazy { mutableMapOf<String, Any>() }

}


