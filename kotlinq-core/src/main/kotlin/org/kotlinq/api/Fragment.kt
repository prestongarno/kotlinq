package org.kotlinq.api


/**
 * Represents a GraphQL Fragment definition
 */
data class Fragment(
    val typeName: String,
    val graphQlInstance: GraphQlInstance) {


  fun toGraphQl(pretty: Boolean = false, idAndTypeName: Boolean = true): String =
      (if (pretty) PrintingConfiguration.PRETTY else PrintingConfiguration.DEFAULT).let {
        if (idAndTypeName) it else it.toBuilder().metaStrategy(Printer.MetaStrategy.NONE).build()
      }.let(::toGraphQl)

  fun toGraphQl(configuration: PrintingConfiguration) =
      toGraphQl(Printer.fromConfiguration(configuration))

  fun toGraphQl(printer: Printer) = printer.printFragmentToString(this)

  fun traverse(visitor: GraphVisitor) {
    if (visitor.notifyEnter(this, inline = false)) {
      visitor.visitContext(this)
      visitor.notifyExit(this)
    }
  }

  operator fun contains(other: Fragment): Boolean {
    var result = false

    GraphVisitor.builder()
        .onNotifyEnter {
          other != it || let {
            result = true
            false // stop visiting
          }
        }.build()
        .let(::traverse)

    return result
  }
}
