package org.kotlinq.api

import kotlin.reflect.KType

interface Adapter {

  val name: String
  val type: KType
  val arguments: Map<String, String>

  fun getValue(): Any?

  /**
   * Visitor pattern for setting values of the query on response
   */
  fun accept(resolver: Resolver)

  fun isResolved(): Boolean
}

