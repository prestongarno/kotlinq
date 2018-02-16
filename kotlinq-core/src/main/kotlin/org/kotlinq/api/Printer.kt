package org.kotlinq.api

import org.kotlinq.services.Configuration


typealias Printer = (GraphQlInstance) -> String


/**
 * Interface which supports printing GraphQL requests in the 4 different formats supported
 *
 * @author prestongarno
 */
internal
interface GraphQlFormatter {

  fun printGraphQl(
      instance: GraphQlInstance,
      pretty: Boolean = false,
      inlineFragments: Boolean = true
  ): String

  companion object : GraphQlFormatter by Configuration.instance() {

  }
}

