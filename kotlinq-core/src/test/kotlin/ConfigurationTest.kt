import org.junit.Test
import org.kotlinq.adapters.ParsedProperty
import org.kotlinq.api.Adapter
import org.kotlinq.api.AdapterService
import org.kotlinq.api.Configuration
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Resolver
import org.kotlinq.models.GraphQlInstanceImpl
import org.kotlinq.printer.PrinterImpl
import org.kotlinq.resolvers.ResolverImpl
import java.io.InputStream
import kotlin.reflect.KType

class ConfigurationTest {

  @Test fun assertDependencyIsInitialized() {

    val parser: JsonParser = object : JsonParser {
      override fun parseToArray(string: String): Map<String, Any?> {
        TODO("not implemented")
      }

      override fun parseToObject(string: String): Map<String, Any?> {
        return listOf(string.split(":").let { it[0] to it[1] }).toMap()
      }

    }
    Configuration.configure(Configuration.Companion.Builder(

        object : AdapterService {
          override fun deserializer(name: String, type: KType, init: (InputStream) -> Any?): Adapter {
            TODO("not implemented")
          }

          override fun parser(name: String, type: KType, init: (String) -> Any?): Adapter {
            TODO("not implemented")
          }

          override fun initializer(name: String, type: KType, init: () -> Any?): Adapter {
            TODO("not implemented")
          }

          override fun enumDeserializer(name: String, type: KType): Adapter {
            TODO("not implemented")
          }

          override fun instanceProperty(name: String, type: KType, init: () -> Context): Adapter {
            TODO("not implemented")
          }

          override fun fragmentProperty(name: String, type: KType, fragments: Set<() -> Context>): Adapter {
            TODO("not implemented")
          }

        },
        PrinterImpl(),
        ResolverImpl(),

        parser,
        object : GraphQlInstanceProvider {
          override fun createNewInstance(typeName: String, context: Context): GraphQlInstance {
            return GraphQlInstanceImpl(typeName, context)
          }

        }))


    val parseToObject = JsonParser.parseToObject("Hello:World")

    parseToObject.iterator().next().let { (name, value) ->
      require(name == "Hello" && value == "World")
    }

    val context = object : Context {

      override val graphQlInstance: GraphQlInstance =

          GraphQlInstanceProvider.createNewInstance("Test", this).apply {

            this.bindProperty(ParsedProperty(
                "Hello",
                ::stringTypeProperty.returnType,
                { it },
                emptyMap()))
          }
    }

    require(Resolver.resolve(parseToObject, context))

    require(context.graphQlInstance.properties["Hello"]?.getValue() == "World")

    context.graphQlInstance.toGraphQl(pretty = true, extractFragments = false).let {
      require(it == """
        |{
        |  Hello
        |}
      """.trimMargin("|"))
    }
  }
}

val stringTypeProperty = ""
