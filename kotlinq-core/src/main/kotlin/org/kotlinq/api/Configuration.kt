package org.kotlinq.api

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import kotlin.reflect.KProperty


internal
interface Configuration {


  val adapterService: AdapterService

  val printer: GraphQlFormatter

  val resolver: Resolver

  val jsonParser: JsonParser

  val graphQlInstanceProvider: GraphQlInstanceProvider


  companion object : Configuration {

    private lateinit var container: Kodein

    override val adapterService: AdapterService
      get() {
        assertConfigured()
        return container.instance()
      }

    override val printer: GraphQlFormatter
      get() {
        assertConfigured()
        return container.instance()
      }

    override val resolver: Resolver
      get() {
        assertConfigured()
        return container.instance()
      }

    override val jsonParser: JsonParser
      get() {
        assertConfigured()
        return container.instance()
      }
    override val graphQlInstanceProvider: GraphQlInstanceProvider
      get() {
        assertConfigured()
        return container.instance()
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