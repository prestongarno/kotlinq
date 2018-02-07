package org.kotlinq.adapters

import org.kotlinq.api.Adapter

internal
interface ModelAdapter: Adapter {

  /**
   * Called when the model is resolved
   *
   * Lazy sequence just to see how good these things really are
   */
  fun resolve(value: Sequence<Pair<String, String>>): Boolean
}

internal
interface ModelListAdapter : Adapter {
  fun resolve(value: List<Map<String, String>>): Boolean
}

internal
interface DeserializingAdapter : Adapter {
  fun resolve(stream: java.io.InputStream): Boolean
}

internal
interface ParsingAdapter : Adapter {
  val initializer: (String) -> Any?
}

