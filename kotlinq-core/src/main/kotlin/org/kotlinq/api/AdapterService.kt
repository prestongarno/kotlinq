package org.kotlinq.api

import kotlin.reflect.KType


/**
 * Provides adapters (entities) for properties with different return types (use cases)
 *
 * @author prestongarno
 */
interface AdapterService {

  val scalarAdapters: ScalarAdapterService

  fun deserializer(
      name: String,
      type: KType,
      init: (java.io.InputStream) -> Any?,
      arguments: Map<String, Any> = emptyMap()
  ): Adapter

  fun parser(
      name: String,
      type: KType,
      init: (String) -> Any?,
      arguments: Map<String, Any> = emptyMap()
  ): Adapter

  fun enumDeserializer(
      name: String,
      type: KType,
      arguments: Map<String, Any> = emptyMap()
  ): Adapter

  fun instanceProperty(
      name: String,
      type: KType,
      init: () -> Context,
      arguments: Map<String, Any> = emptyMap()
  ): ModelAdapter

  fun fragmentProperty(
      name: String,
      type: KType,
      fragments: Set<() -> Context>,
      arguments: Map<String, Any> = emptyMap()
  ): FragmentAdapter

}


