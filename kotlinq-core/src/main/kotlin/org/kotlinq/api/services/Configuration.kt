package org.kotlinq.api.services

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.instance
import org.kotlinq.api.AdapterService
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Resolver


/**
 * Simple module/dependency container.
 *
 * Modules are:
 *   * [AdapterService]: Creates nodes/edges
 *   * [GraphQlFormatter]: Prints the graph to a GraphQL query
 *   * [GraphQlInstanceProvider]: Creates instances (i.e. the idea of mapping GraphQl selection sets -> pojos)
 *   * [JsonParser]: Lower-level, used by next one. TODO should probably isolate this
 *   * [Resolver]: Resolves the graph with the result
 *
 * @author prestongarno
 */
internal
interface Configuration {

  fun configure(configuration: Builder.() -> Unit)


  companion object : Configuration {

    private val container: Kodein = ServiceContainer.kodein

    inline fun <reified T : Any> instance() = container.instance<T>()

    inline fun <reified T : Any> use(instance: T) =
        ServiceContainer.reconfigure(instance)

    override fun configure(configuration: Builder.() -> Unit) = Builder()
        .apply(configuration)
        .notNullValues
        .forEach(ServiceContainer::reconfigure)

    internal
    class Builder(
        var adapterService: AdapterService? = null,
        var printer: GraphQlFormatter? = null,
        var resolver: Resolver? = null,
        var jsonParser: JsonParser? = null,
        var instanceProvider: GraphQlInstanceProvider? = null) {

      internal
      val notNullValues: List<Any>
        get() =
          listOfNotNull(
              adapterService,
              printer,
              resolver,
              jsonParser,
              instanceProvider)
    }

  }
}

