package org.kotlinq.api


typealias Printer = (TypeContext) -> String


internal
interface GraphQlFormatter {

  val prettyPrinter: Printer

  val prettyOptimizedPrinter: Printer

  val printer: Printer

  val optimizedPrinter: Printer


  companion object : GraphQlFormatter by Configuration.printer {

    fun printGraphQl(pretty: Boolean, extractFragments: Boolean, instance: TypeContext): String {
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

