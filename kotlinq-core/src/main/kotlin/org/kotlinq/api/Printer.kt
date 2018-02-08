package org.kotlinq.api

import com.github.salomonbrys.kodein.instance


typealias Printer = (Context) -> String


internal
interface GraphQlFormatter {

  val prettyPrinter: Printer

  val prettyOptimizedPrinter: Printer

  val printer: Printer

  val optimizedPrinter: Printer


  companion object : GraphQlFormatter by Configuration.kodein.instance() {

    fun printGraphQl(pretty: Boolean, extractFragments: Boolean, instance: Context): String {
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

