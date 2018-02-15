package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


typealias Printer = (GraphQlInstance) -> String


/**
 * Interface which supports printing GraphQL requests in the 4 different formats supported
 *
 * @author prestongarno
 */
internal
interface GraphQlFormatter {

  val prettyPrinter: Printer

  val prettyOptimizedPrinter: Printer

  val printer: Printer

  val optimizedPrinter: Printer


  companion object : GraphQlFormatter by Configuration.instance() {

    fun printGraphQl(pretty: Boolean, extractFragments: Boolean, instance: GraphQlInstance): String {
      return when {
        pretty && extractFragments -> prettyOptimizedPrinter
        pretty && !extractFragments -> prettyPrinter
        !pretty && !extractFragments -> printer
        !pretty && extractFragments -> optimizedPrinter
        else -> throw IllegalArgumentException()
      }.invoke(instance)
    }
  }
}

