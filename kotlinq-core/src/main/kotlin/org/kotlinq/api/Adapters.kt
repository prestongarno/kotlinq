package org.kotlinq.api


/** Adapter for a property which returns a nested [Fragment] graphQlInstance */
interface InstanceAdapter : Adapter, ReifiedFragmentContext {

  /**
   * Instead of implementing [Fragment], contains a fragment so that
   * equals and hashcode contracts can easily optimize printing/resolving GraphQL request
   */
  override val fragment: Fragment

  /**
   * Sets the value of this property.
   * Initializes a new context and calls [Resolver.resolve] on the new context
   */
  fun setValue(result: Map<String, Any?>, resolver: Resolver = Resolver): Boolean

  override fun getValue(): Fragment?

  override fun accept(resolver: GraphVisitor) {
    resolver.enterField(this)
    if (resolver.notifyEnter(fragment, inline = false))
      resolver.visitContext(fragment)
  }
}

/**
 * Adapter for a property which returns a nested [Definition] graphQlInstance,
 * but can be mapped to any combination of types (i.e. a workaround for the JavaScript spread operator)
 */
interface FragmentAdapter : FragmentContext {
  fun setValue(typeName: String, values: Map<String, Any?>, resolver: Resolver = Resolver): Boolean
  override fun accept(resolver: GraphVisitor) {
    resolver.visitFragmentContext(this)
  }
}

/**
 * Adapter for a custom-deserialized property.
 * Should usually be for Custom GraphQL Scalars
 */
interface DeserializingAdapter : Adapter {
  fun setValue(value: java.io.InputStream?): Boolean
  val initializer: (java.io.InputStream) -> Any?
  override fun accept(resolver: GraphVisitor) {
    resolver.enterField(this)
  }
}

/**
 * Adapter for a custom-deserialized property, but from a UTF-8 String for convenience.
 * Should usually be for Custom GraphQL Scalars, but is also the base interface for all primitive types
 */
interface ParsingAdapter : Adapter {
  fun setValue(value: String?): Boolean
  val initializer: (String) -> Any?
  override fun accept(resolver: GraphVisitor) {
    resolver.enterField(this)
  }
}

