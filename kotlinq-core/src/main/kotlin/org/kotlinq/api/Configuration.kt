package org.kotlinq.api

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton


/**
 * Simple module/dependency container.
 * NOTE: Companion objects of interface definitions use this
 * at class initialization to implicitly implement interfaces.
 * If no custom dependency configuration is specified at start-up,
 * it's impossible to re-configure the dependencies after first use,
 * due to the way that Kotlin's class-level delegation works
 *
 * @author prestongarno
 */
internal
interface Configuration {

  fun configure(configuration: Builder)


  companion object : Configuration {

    private lateinit var container: Kodein

    inline fun <reified T : Any> instance() = kodein.instance<T>()

    /** kodein dependency container */
    private val kodein: Kodein
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

      val kodein = Kodein {
        bind<AdapterService>() with singleton { adapterService }
        bind<GraphQlFormatter>() with singleton { printer }
        bind<Resolver>() with singleton { resolver }
        bind<JsonParser>() with singleton { jsonParser }
        bind<GraphQlInstanceProvider>() with singleton { instanceProvider }
      }
    }

  }
}