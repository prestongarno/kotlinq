import org.junit.Test
import org.kotlinq.adapters.ModelAdapter
import org.kotlinq.adapters.ParsedProperty
import org.kotlinq.api.Adapter
import org.kotlinq.api.AdapterService
import org.kotlinq.api.Configuration
import org.kotlinq.api.GraphQlFormatter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Printer
import org.kotlinq.api.Resolver
import java.io.InputStream
import kotlin.reflect.KType

class ConfigurationTest {

  @Test fun assertDependencyIsInitialized() {

    val parser: JsonParser = object : JsonParser {
      override fun parseToObject(string: String): Sequence<Pair<String, String>> {
        var count = 0
        return generateSequence { if (count++ > 0) null else string.split(":").let { it[0] to it[1] } }
      }

      override fun parseToArray(string: String): Sequence<String> = TODO("not implemented")
    }
    Configuration.configure(Configuration.Companion.Builder(
        object : AdapterService {
          override val instanceProperty: (name: String, type: KType, init: () -> GraphQlInstance) -> Adapter get() = TODO()
          override val deserializer = { name: String, type: KType, init: (InputStream) -> Any? -> ParsedProperty("", TODO(), TODO()) }
          override val parser: (name: String, type: KType, init: (String) -> Any?) -> Adapter = { name, type, init -> TODO() }
          override val initializer: (name: String, type: KType, init: () -> Any?) -> Adapter = { name, type, init -> TODO() }
          override val enumDeserializer: (name: String, type: KType) -> Adapter get() = TODO()
        },
        object : GraphQlFormatter {
          override val prettyPrinter: Printer = { "" }
          override val prettyOptimizedPrinter: Printer = { "" }
          override val printer: Printer = { "" }
          override val optimizedPrinter: Printer = { "" }
        }, object : Resolver {
      override val modelResolver: (value: String, target: ModelAdapter) -> Boolean get() = TODO()
    },

        parser,
        object : GraphQlInstanceProvider {
          override fun createNewInstance(typeName: String): GraphQlInstance {
            TODO("not implemented")
          }

        }))


    JsonParser.parseToObject("Hello:World").iterator().next().let { (name, value) ->
      require(name == "Hello" && value == "World")
    }
  }
}
