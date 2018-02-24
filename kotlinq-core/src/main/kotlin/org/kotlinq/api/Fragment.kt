package org.kotlinq.api


/**
 * Represents a GraphQL Fragment definition
 */
data class Fragment(
    val typeName: String,
    val graphQlInstance: GraphQlInstance) {


  fun toGraphQl(pretty: Boolean = false): String = toGraphQl(
      if (pretty) PrintingConfiguration.PRETTY else PrintingConfiguration.DEFAULT
  ).printFragmentToString(this)

  fun toGraphQl(configuration: PrintingConfiguration) =
      Printer.fromConfiguration(configuration)

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
