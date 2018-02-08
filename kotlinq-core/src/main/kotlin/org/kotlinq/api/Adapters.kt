package org.kotlinq.api


interface ModelAdapter: Adapter, Fragment {
  fun setValue(result: Map<String, Any?>, resolver: Resolver = Resolver): Boolean
}

interface FragmentAdapter : Adapter {
  fun setValue(typeName: String, values: Map<String, Any?>, resolver: Resolver = Resolver): Boolean
  val fragments: Map<String, Fragment>
}

interface DeserializingAdapter : Adapter {
  fun setValue(value: java.io.InputStream?): Boolean
  val initializer: (java.io.InputStream) -> Any?
}

interface ParsingAdapter : Adapter {
  fun setValue(value: String?): Boolean
  val initializer: (String) -> Any?
}

