package org.kotlinq.api

import org.kotlinq.api.services.Configuration


/**
 * Interface which supports printing GraphQL requests in the 4 different formats supported
 *
 * @author prestongarno
 */
internal
interface GraphQlFormatter {

  fun printGraphQl(
      fragment: Fragment,
      configuration: PrintingConfiguration = PrintingConfiguration.DEFAULT
  ): String

  companion object : GraphQlFormatter by Configuration.instance()
}

