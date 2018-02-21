@file:Suppress("MemberVisibilityCanBePrivate")

package org.kotlinq.api.services

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton
import org.kotlinq.api.AdapterService
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Resolver
import org.kotlinq.api.services.wrappers.AdapterWrapper
import org.kotlinq.api.services.wrappers.GraphQlInstanceProviderWrapper
import org.kotlinq.api.services.wrappers.JsonParsingWrapper
import org.kotlinq.api.services.wrappers.PrinterWrapper
import org.kotlinq.api.services.wrappers.ResolverWrapper
import org.kotlinq.models.GraphQlInstanceProviderImpl
import org.kotlinq.printer.PrinterImpl
import org.kotlinq.properties.AdapterServiceImpl
import org.kotlinq.resolvers.JsonParserImpl
import org.kotlinq.resolvers.ResolverImpl


/**
 * Wrapper for highest level service dependency configuration
 *
 * Unfortunately manual delegation is required to re-configure
 * dependencies while easily supporting companion object delegate impl.,
 * so these values are wrapped and can be re-configured within the same VM without re-loading classes.
 *
 * when companion objects of interfaces can implement configurations indirectly,
 * it makes referencing the dependency explicit by domain model name.
 *
 * Probably overengineered.
 */
internal
object ServiceContainer {

  val kodein = Kodein {
    bind<AdapterService>() with singleton { adapterService }
    bind<GraphQlFormatter>() with singleton { printerService }
    bind<Resolver>() with singleton { defaultResolver }
    bind<JsonParser>() with singleton { jsonParser }
    bind<GraphQlInstanceProvider>() with singleton { graphQlInstanceProvider }
  }

  fun <T : Any> reconfigure(instance: T) =
      getWrapper(instance).forEach {
        if (it === instance) it.useDefault() else it.use(instance)
      }

  @Suppress("UNCHECKED_CAST")
  private fun <T : Any> getWrapper(instance: T): List<ConfigurableDependency<T>> {
    return wrappers.filter { wrapper -> wrapper.clazz.isInstance(instance) }
        .mapNotNull { it as? ConfigurableDependency<T> }
  }

  private val adapterService = AdapterWrapper(AdapterServiceImpl())
  private val graphQlInstanceProvider = GraphQlInstanceProviderWrapper(GraphQlInstanceProviderImpl())
  private val jsonParser = JsonParsingWrapper(JsonParserImpl())
  private val printerService = PrinterWrapper(PrinterImpl())
  private val defaultResolver = ResolverWrapper(ResolverImpl())

  private val wrappers = listOf(
      adapterService,
      graphQlInstanceProvider,
      jsonParser,
      printerService,
      defaultResolver)

  fun useDefaults() {
    wrappers.forEach(Wrapper<*>::useDefault)
  }
}

