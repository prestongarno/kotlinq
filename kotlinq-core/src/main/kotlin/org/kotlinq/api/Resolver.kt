package org.kotlinq.api

import org.kotlinq.services.Configuration


/**
 * Encapsulation of visitor-pattern algorithm for resolving GraphQL queries
 *
 * Currently no limit on nesting depth or size of response
 */
interface Resolver : GraphVisitor {

  fun resolve(value: Map<String, Any?>, target: Context): Boolean

  companion object : Resolver by Configuration.instance()
}
