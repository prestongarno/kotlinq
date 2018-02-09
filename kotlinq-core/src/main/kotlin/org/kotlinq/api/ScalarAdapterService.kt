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
      name: String,
      type: KType,
      mapper: (String) -> Int = mappers.intMapper,
      arguments: Map<String, Any> = emptyMap()
  ): IntAdapter

  fun stringAdapter(
      name: String,
      type: KType,
      mapper: (String) -> String = mappers.stringMapper,
      arguments: Map<String, Any> = emptyMap()
  ): StringAdapter

  fun floatAdapter(
      name: String,
      type: KType,
      mapper: (String) -> Float = mappers.floatMapper,
      arguments: Map<String, Any> = emptyMap()
  ): FloatAdapter

  fun booleanAdapter(
      name: String,
      type: KType,
      mapper: (String) -> Boolean = mappers.booleanMapper,
      arguments: Map<String, Any> = emptyMap()
  ): BooleanAdapter


  interface TypeMappers {
    val booleanMapper: (String) -> Boolean
    val floatMapper: (String) -> Float
    val intMapper: (String) -> Int
    val stringMapper: (String) -> String
  }
}