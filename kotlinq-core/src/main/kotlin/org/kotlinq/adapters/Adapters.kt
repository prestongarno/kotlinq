package org.kotlinq.adapters

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


interface Adapter {
  val name: String
  val type: KType
  fun getValue(): Any?
}

interface ModelAdapter: Adapter {
  fun resolve(value: Map<String, Any?>): Boolean
}

interface ModelCollectionAdapter : Adapter {
  fun resolve(value: List<Map<String, Any?>>): Boolean
}

interface DeserializingAdapter : Adapter {
  fun resolve(stream: java.io.InputStream): Boolean
}

interface ParsingAdapter : Adapter {
  val initializer: (String) -> Any?
}

