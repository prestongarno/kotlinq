package org.kotlinq.api.services.wrappers

import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.services.Wrapper

internal
class PrinterWrapper(default: GraphQlFormatter)
  : Wrapper<GraphQlFormatter>(default, GraphQlFormatter::class),
    GraphQlFormatter {

  override fun printGraphQl(instance: GraphQlInstance, pretty: Boolean, inlineFragments: Boolean): String =
      this.instance().printGraphQl(instance, pretty, inlineFragments)
}