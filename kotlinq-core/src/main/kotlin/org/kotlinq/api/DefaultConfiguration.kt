package org.kotlinq.api

import org.kotlinq.adapters.AdapterServiceImpl
import org.kotlinq.models.GraphQlInstanceProviderImpl
import org.kotlinq.printer.PrinterImpl
import org.kotlinq.resolvers.JsonParserImpl
import org.kotlinq.resolvers.ResolverImpl


internal
object DefaultConfiguration {

  val adapterService by lazy { AdapterServiceImpl() }

  val printerService by lazy { PrinterImpl() }

  val resolver by lazy { ResolverImpl() }

  val jsonParser by lazy { JsonParserImpl() }

  val graphQlInstanceProvider by lazy { GraphQlInstanceProviderImpl() }

  fun useDefaults(configuration: Configuration) {
    configuration.configure(Configuration.Companion.Builder(
        AdapterServiceImpl(),
        PrinterImpl(),
        ResolverImpl(),
        JsonParserImpl(),
        GraphQlInstanceProviderImpl()))
  }
}