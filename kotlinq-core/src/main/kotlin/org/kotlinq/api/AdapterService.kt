package org.kotlinq.api

import kotlin.reflect.KType


interface AdapterService {

  fun deserializer(type: KType, init: (java.io.InputStream) -> Any?)
      : Adapter

  fun parser(type: KType, init: (String) -> Any?)
      : Adapter

  fun  initializer(type: KType, init: () -> Any?)
      : Adapter

  fun enumDeserializer(type: KType)
      : Adapter

}


