package org.kotlinq.api

import org.kotlinq.api.services.Configuration


/**
 * Encapsulation of visitor-pattern algorithm for resolving GraphQL queries
 *
 * Currently no limit on nesting depth or size of response
 */
interface Resolver : GraphVisitor {

  fun resolve(value: Map<String, Any?>, target: Fragment): Boolean

  /**
   * Equivalent of:
   *
   * ```
   *     val parser: JsonParser = ...
   *     val map = parser.parseToObject("...")
   *     require(resolver.resolve(map, fragment))
   * ```
   */
  fun resolve(
      value: String,
      target: Fragment,
      parser: JsonParser = JsonParser.Companion): Boolean = resolve(parser.parseToObject(value), target)

  companion object : Resolver by Configuration.instance()
}
