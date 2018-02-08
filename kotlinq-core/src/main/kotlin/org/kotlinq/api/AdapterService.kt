package org.kotlinq.api

import kotlin.reflect.KType


// Provides adapters (entities) for properties with different return types (use cases)
interface AdapterService {

  fun deserializer(name: String, type: KType, init: (java.io.InputStream) -> Any?): Adapter

  fun parser(name: String, type: KType, init: (String) -> Any?): Adapter

  fun enumDeserializer(name: String, type: KType): Adapter

  fun instanceProperty(name: String, type: KType, init: () -> Context): Adapter

  fun fragmentProperty(name: String, type: KType, fragments: Set<() -> Context>): Adapter

}


