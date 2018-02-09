package org.kotlinq.api

import org.kotlinq.adapters.AdapterServiceImpl
import org.kotlinq.models.GraphQlInstanceProviderImpl
import org.kotlinq.printer.PrinterImpl
import org.kotlinq.resolvers.JsonParserImpl
import org.kotlinq.resolvers.ResolverImpl


/** Default library back-end dependency configuration */
internal
object DefaultConfiguration {

  val adapterService: AdapterService
      by lazy { AdapterServiceImpl() }

  val graphQlInstanceProvider: GraphQlInstanceProvider
      by lazy(::GraphQlInstanceProviderImpl)

  val jsonParser: JsonParser
      by lazy(::JsonParserImpl)

  val printerService: GraphQlFormatter
      by lazy { PrinterImpl() }

  val resolver: Resolver
      by lazy(::ResolverImpl)

  fun useDefaults(configuration: Configuration) {
    configuration.configure(Configuration.Companion.Builder(
        adapterService,
        printerService,
        resolver,
        jsonParser,
        graphQlInstanceProvider))
  }
}