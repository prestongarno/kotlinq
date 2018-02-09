package org.kotlinq.api


/** Adapter for a property which returns a nested [Context] instance */
interface ModelAdapter: Adapter, Fragment {

  /**
   * Sets the value of this property.
   * Initializes a new context and calls [Resolver.resolve] on the new context
   */
  fun setValue(result: Map<String, Any?>, resolver: Resolver = Resolver): Boolean
}

/**
 * Adapter for a property which returns a nested [Context] instance,
 * but can be mapped to any combination of types (i.e. a workaround for the JavaScript spread operator)
 */
interface FragmentAdapter : Adapter {
  fun setValue(typeName: String, values: Map<String, Any?>, resolver: Resolver = Resolver): Boolean
  val fragments: Map<String, Fragment>
}

/**
 * Adapter for a custom-deserialized property.
 * Should usually be for Custom GraphQL Scalars
 */
interface DeserializingAdapter : Adapter {
  fun setValue(value: java.io.InputStream?): Boolean
  val initializer: (java.io.InputStream) -> Any?
}

/**
 * Adapter for a custom-deserialized property, but from a UTF-8 String for convenience.
 * Should usually be for Custom GraphQL Scalars, but is also the base interface for all primitive types
 */
interface ParsingAdapter : Adapter {
  fun setValue(value: String?): Boolean
  val initializer: (String) -> Any?
}

