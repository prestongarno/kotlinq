package org.kotlinq.adapters

import org.kotlinq.api.Adapter
import org.kotlinq.api.Fragment

internal
interface ModelAdapter: Adapter, Fragment {
  fun resolve(value: Sequence<Pair<String, String>>): Boolean
}

internal
interface FragmentAdapter : Adapter {
  fun resolve(result: Pair<String, String>): Boolean
  val fragments: Map<String, Fragment>
}

internal
interface DeserializingAdapter : Adapter {
  fun resolve(stream: java.io.InputStream): Boolean
}

internal
interface ParsingAdapter : Adapter {
  val initializer: (String) -> Any?
}

