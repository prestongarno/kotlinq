package org.kotlinq.api


/**
 * Factory methods for primitive platform delegate delegates.
 *
 * Added to prevent boxing every value on property access
 */
interface ScalarAdapterService {

  val mappers: TypeMappers

  /**
   * TODO eventually add specific types for primitives
   */
  fun adapterFor(
      info: GraphQlPropertyInfo,
      mapper: (String) -> Int = mappers.intMapper
  ): ParsingAdapter

  interface TypeMappers {
    val booleanMapper: (String) -> Boolean
    val floatMapper: (String) -> Float
    val intMapper: (String) -> Int
    val stringMapper: (String) -> String
  }
}