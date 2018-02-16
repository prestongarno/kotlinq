package org.kotlinq.services

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton
import org.kotlinq.api.Adapter
import org.kotlinq.api.AdapterService
import org.kotlinq.api.Context
import org.kotlinq.api.DeserializingAdapter
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.JsonParser
import org.kotlinq.api.ModelAdapter
import org.kotlinq.api.ParsingAdapter
import org.kotlinq.api.Resolver
import org.kotlinq.api.ScalarAdapterService
import org.kotlinq.services.ConfigurableDependency.Companion.configurableDependencyWithDefault
import org.kotlinq.models.GraphQlInstanceProviderImpl
import org.kotlinq.printer.PrinterImpl
import org.kotlinq.properties.AdapterServiceImpl
import org.kotlinq.resolvers.JsonParserImpl
import org.kotlinq.resolvers.ResolverImpl
import java.io.InputStream
import kotlin.reflect.KProperty0


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
    bind<Resolver>() with singleton { resolver }
    bind<JsonParser>() with singleton { jsonParser }
    bind<GraphQlInstanceProvider>() with singleton { graphQlInstanceProvider }
  }

  fun <T> reconfigure(instance: T) {
    getWrapper(instance)?.also {
      // if reference is same as wrapper, intention is to use the encapsulated default instance
      if (instance !== it) it.use(instance) else it.use(it.default)
    }
  }

  @Suppress("UNCHECKED_CAST")
  private fun <T> getWrapper(instance: T): ConfigurableDependency<T>? {
    return when (instance) {
      is AdapterService -> ::adapterService.container()
      is GraphQlInstanceProvider -> ::graphQlInstanceProvider.container()
      is JsonParser -> ::jsonParser.container()
      is GraphQlFormatter -> ::printerService.container()
      is Resolver -> ::resolver.container()
      else -> return null
    } as ConfigurableDependency<T>
  }

  @Suppress("UNCHECKED_CAST")
  private fun <T : Any> KProperty0<T>.container() = get() as ConfigurableDependency<T>

  private val defaultAdapterService = AdapterServiceImpl()

  val adapterService: AdapterService = object : AdapterService, ConfigurableDependency<AdapterService> {

    override val default: AdapterService get() = defaultAdapterService

    private
    var __privInstance: AdapterService = defaultAdapterService

    private var scalars = defaultAdapterService.scalarAdapters

    override val scalarAdapters: ScalarAdapterService get() = scalars

    override fun use(instance: AdapterService) {

      require(instance !== this)

      synchronized(__privInstance) {
        __privInstance = instance
      }
    }

    override fun instance(): AdapterService = __privInstance

    override fun deserializer(info: GraphQlPropertyInfo, init: (InputStream) -> Any?): Adapter =
        instance().deserializer(info, init)

    override fun parser(info: GraphQlPropertyInfo, init: (String) -> Any?): Adapter =
        instance().parser(info, init)

    override fun enumDeserializer(info: GraphQlPropertyInfo): Adapter =
        instance().enumDeserializer(info)

    override fun instanceProperty(info: GraphQlPropertyInfo, init: () -> Context): Adapter =
        instance().instanceProperty(info, init)

    override fun fragmentProperty(info: GraphQlPropertyInfo, fragments: Set<() -> Context>): Adapter =
        instance().fragmentProperty(info, fragments)
  }


  private val defaultGraphQlInstanceProvider = GraphQlInstanceProviderImpl()

  val graphQlInstanceProvider: GraphQlInstanceProvider = object :
      GraphQlInstanceProvider,
      ConfigurableDependency<GraphQlInstanceProvider> by
      configurableDependencyWithDefault(defaultGraphQlInstanceProvider) {

    override fun createNewInstance(typeName: String): GraphQlInstance =
        instance().createNewInstance(typeName)
  }

  private val defaultJsonParser = JsonParserImpl()

  val jsonParser: JsonParser = object : JsonParser,
      ConfigurableDependency<JsonParser> by configurableDependencyWithDefault(defaultJsonParser) {

    override fun parseToObject(string: String, rootObjectName: String) =
        instance().parseToObject(string, rootObjectName)

    override fun parseToArray(string: String) =
        instance().parseToArray(string)
  }


  private val defaultPrinterService = PrinterImpl()

  val printerService: GraphQlFormatter = object : GraphQlFormatter,
      ConfigurableDependency<GraphQlFormatter> by configurableDependencyWithDefault(defaultPrinterService) {

    override fun printGraphQl(instance: GraphQlInstance, pretty: Boolean, inlineFragments: Boolean): String =
        this.instance().printGraphQl(instance, pretty, inlineFragments)
  }


  private val defaultResolver = ResolverImpl()

  val resolver: Resolver = object : Resolver,
      ConfigurableDependency<Resolver> by configurableDependencyWithDefault(defaultResolver) {

    override fun resolve(value: Map<String, Any?>, target: Context): Boolean = instance().resolve(value, target)
    override fun visitDeserializer(target: DeserializingAdapter) = instance().visitDeserializer(target)
    override fun visitFragment(target: Fragment) = instance().visitFragment(target)
    override fun visitFragmentContext(target: FragmentAdapter) = instance().visitFragmentContext(target)
    override fun visitModel(target: ModelAdapter) = instance().visitModel(target)
    override fun visitScalar(target: ParsingAdapter) = instance().visitScalar(target)
    override fun equals(other: Any?) = instance() == other
    override fun hashCode() = instance().hashCode()
    override fun toString() = instance().toString()
  }

  fun useDefaults() {
    reconfigure(defaultAdapterService)
    reconfigure(defaultGraphQlInstanceProvider)
    reconfigure(defaultJsonParser)
    reconfigure(defaultPrinterService)
    reconfigure(defaultResolver)
  }
}

