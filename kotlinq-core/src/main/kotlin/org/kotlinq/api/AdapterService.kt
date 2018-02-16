package org.kotlinq.api

import kotlin.reflect.KType


/**
 * Provides adapters (entities) for properties with different return types (use cases)
 *
 * @author prestongarno
 */
interface AdapterService {

  val scalarAdapters: ScalarAdapterService

  fun deserializer(info: GraphQlPropertyInfo, init: (java.io.InputStream) -> Any?): Adapter

  fun parser(info: GraphQlPropertyInfo, init: (String) -> Any?): Adapter

  fun enumDeserializer(info: GraphQlPropertyInfo): Adapter

  fun instanceProperty(info: GraphQlPropertyInfo, init: () -> Context): Adapter

  fun fragmentProperty(info: GraphQlPropertyInfo, fragments: Set<() -> Context>): Adapter

}


