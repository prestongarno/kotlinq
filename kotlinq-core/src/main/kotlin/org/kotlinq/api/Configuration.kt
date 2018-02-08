package org.kotlinq.api

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton


internal
interface Configuration {

  val kodein: Kodein

  fun configure(configuration: Builder)

  companion object : Configuration {

    private lateinit var container: Kodein

    override val kodein: Kodein
      get() {
        assertConfigured()
        return container
      }

    override fun configure(configuration: Builder) {
      this.container = configuration.kodein
    }

    private fun assertConfigured() {
      if (!this::container.isInitialized) DefaultConfiguration.useDefaults(this)
    }

    internal
    class Builder(
        adapterService: AdapterService = DefaultConfiguration.adapterService,
        printer: GraphQlFormatter = DefaultConfiguration.printerService,
        resolver: Resolver = DefaultConfiguration.resolver,
        jsonParser: JsonParser = DefaultConfiguration.jsonParser,
        instanceProvider: GraphQlInstanceProvider = DefaultConfiguration.graphQlInstanceProvider
    ) {

      val kodein by lazy {
        Kodein {
          bind<AdapterService>() with singleton { adapterService }
          bind<GraphQlFormatter>() with singleton { printer }
          bind<Resolver>() with singleton { resolver }
          bind<JsonParser>() with singleton { jsonParser }
          bind<GraphQlInstanceProvider>() with singleton { instanceProvider }
        }
      }
    }

  }
}