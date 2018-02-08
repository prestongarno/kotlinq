package org.kotlinq.api

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import kotlin.reflect.KProperty


internal
interface Configuration {

  val kodein: Kodein


  companion object : Configuration {

    private lateinit var container: Kodein

    override val kodein: Kodein
      get() {
        assertConfigured()
        return container
      }

    fun configure(configuration: Builder) {
      this.container = configuration.kodein
    }

    private fun assertConfigured() {
      if (!this::container.isInitialized) throw IllegalStateException("Not Configured")
    }

    internal
    class Builder(
        adapterService: AdapterService,
        printer: GraphQlFormatter,
        resolver: Resolver,
        jsonParser: JsonParser,
        instanceProvider: GraphQlInstanceProvider
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