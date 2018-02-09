package org.kotlinq.api

import kotlin.reflect.KType


interface ScalarAdapterService {

  val mappers: TypeMappers

  fun intAdapter(
      name: String,
      type: KType,
      mapper: (String) -> Int = mappers.intMapper,
      arguments: Map<String, Any>
  ): IntAdapter

  fun stringAdapter(
      name: String,
      type: KType,
      mapper: (String) -> String = mappers.stringMapper,
      arguments: Map<String, Any>
  ): StringAdapter

  fun floatAdapter(
      name: String,
      type: KType,
      mapper: (String) -> Float = mappers.floatMapper,
      arguments: Map<String, Any>
  ): FloatAdapter

  fun booleanAdapter(
      name: String,
      type: KType,
      mapper: (String) -> Boolean = mappers.booleanMapper,
      arguments: Map<String, Any>
  ): BooleanAdapter


  interface TypeMappers {
    val booleanMapper: (String) -> Boolean
    val floatMapper: (String) -> Float
    val intMapper: (String) -> Int
    val stringMapper: (String) -> String
  }
}