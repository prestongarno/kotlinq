package org.kotlinq.api

import kotlin.reflect.KType


/**
 * Factory methods for primitive platform delegate delegates.
 *
 * Added to prevent boxing every value on property access
 */
interface ScalarAdapterService {

  val mappers: TypeMappers

  fun intAdapter(
      info: GraphQlPropertyInfo,
      mapper: (String) -> Int = mappers.intMapper
  ): IntAdapter

  fun stringAdapter(
      info: GraphQlPropertyInfo,
      mapper: (String) -> String = mappers.stringMapper
  ): StringAdapter

  fun floatAdapter(
      info: GraphQlPropertyInfo,
      mapper: (String) -> Float = mappers.floatMapper
  ): FloatAdapter

  fun booleanAdapter(
      info: GraphQlPropertyInfo,
      mapper: (String) -> Boolean = mappers.booleanMapper
  ): BooleanAdapter


  interface TypeMappers {
    val booleanMapper: (String) -> Boolean
    val floatMapper: (String) -> Float
    val intMapper: (String) -> Int
    val stringMapper: (String) -> String
  }
}