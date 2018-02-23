package org.kotlinq.api.services.wrappers

import org.kotlinq.api.Fragment
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.PrintingConfiguration
import org.kotlinq.api.services.Wrapper

internal
class PrinterWrapper(default: GraphQlFormatter)
  : Wrapper<GraphQlFormatter>(default, GraphQlFormatter::class),
    GraphQlFormatter {

  override fun printGraphQl(fragment: Fragment, configuration: PrintingConfiguration): String =
      instance().printGraphQl(fragment, configuration)
}