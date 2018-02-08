import org.junit.Test
import org.kotlinq.api.Configuration
import org.kotlinq.api.Context
import org.kotlinq.api.DefaultConfiguration
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.Resolver
import org.kotlinq.models.GraphQlInstanceImpl

class ConfigurationTest {

  private val parser: JsonParser = object : JsonParser {

    override fun parseToObject(string: String, rootObjectName: String) =
        listOf(string.split(":").let { it[0] to it[1] }).toMap()

    override fun parseToArray(string: String) = TODO("not implemented")

  }

  val context = object : Context {

    override val graphQlInstance: GraphQlInstance =
        GraphQlInstanceProvider.createNewInstance("Test", this).apply {
          bindProperty(Kotlinq.adapterService.parser("Hello", ::stringTypeProperty.returnType, { it }))
        }
  }

  @Test fun assertDependencyIsInitialized() {

    Configuration.configure(Configuration.Companion.Builder(
        jsonParser = parser,
        instanceProvider = object : GraphQlInstanceProvider {
          override fun createNewInstance(typeName: String, context: Context): GraphQlInstance {
            return GraphQlInstanceImpl(typeName, context)
          }
        }))


    val parseToObject = JsonParser.parseToObject("Hello:World")

    parseToObject.iterator().next().let { (name, value) ->
      require(name == "Hello" && value == "World")
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

  @Test fun usingDefaults() {

    DefaultConfiguration.useDefaults(Configuration)

    val value = JsonParser.parseToObject("""
      {
        "data": {
          "Hello": "Universe!"
        }
      }
    """.trimIndent())

    Resolver.resolve(value, context)

    require(context.graphQlInstance.properties["Hello"]?.getValue() == "Universe!")
  }
}

val stringTypeProperty = ""
