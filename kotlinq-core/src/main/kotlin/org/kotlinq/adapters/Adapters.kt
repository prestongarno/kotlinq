package org.kotlinq.adapters

import org.kotlinq.api.Adapter

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

