package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.context.ContextAware

internal
interface ModelAdapter: Adapter {

  /**
   * Called when the model is resolved
   *
   * Lazy sequence just to see how good these things really are
   */
  fun onQuery(value: Sequence<Pair<String, String>>): Boolean

  /**
   * A method for properties within the this context
   * to call when a value is requested on the front end
   */
  fun parseField(adapter: ContextAware): Boolean
}

internal
interface ModelListAdapter : ContextAware {
  fun resolve(value: List<Map<String, String>>): Boolean
}

internal
interface DeserializingAdapter : ContextAware {
  fun resolve(stream: java.io.InputStream): Boolean
}

internal
interface ParsingAdapter : ContextAware {
  val initializer: (String) -> Any?
}

