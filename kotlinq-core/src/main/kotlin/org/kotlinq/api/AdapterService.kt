package org.kotlinq.api

import kotlin.reflect.KType


// Provides adapters (entities) for properties with different return types (use cases)
interface AdapterService {

  val deserializer: (name: String, type: KType, init: (java.io.InputStream) -> Any?) -> Adapter

  val parser: (name: String, type: KType, init: (String) -> Any?) -> Adapter

  val initializer: (name: String, type: KType, init: () -> Any?) -> Adapter

  val enumDeserializer: (name: String, type: KType) -> Adapter

  val instanceProperty: (name: String, type: KType, init: () -> TypeContext) -> Adapter

}


