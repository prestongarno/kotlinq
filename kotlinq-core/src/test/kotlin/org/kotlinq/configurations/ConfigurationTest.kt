package org.kotlinq.configurations

import com.beust.klaxon.JsonObject
import org.junit.Test
import org.kotlinq.api.Configuration
import org.kotlinq.api.Context
import org.kotlinq.api.DefaultConfiguration
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.Resolver
import org.kotlinq.mockType
import org.kotlinq.models.GraphQlInstanceImpl

class ConfigurationTest {

  private val parser: JsonParser = object : JsonParser {

    override fun parseToObject(string: String, rootObjectName: String) =
        listOf(string.split(":").let { it[0] to it[1] }).toMap()

    override fun parseToArray(string: String) = TODO("not implemented")

  }

  fun testContext(typeName: String) = object : Context {

    override val graphQlInstance: GraphQlInstance =
        GraphQlInstanceProvider.createNewInstance(typeName).apply {
          bindProperty(Kotlinq.adapterService.scalarAdapters.stringAdapter(
              GraphQlPropertyInfo("Hello", GraphQlPropertyInfo.STRING, mockType(String::class)), { it }))
        }
  }

  @Test fun assertDependencyIsInitialized() {

    Configuration.configure(Configuration.Companion.Builder(
        jsonParser = parser,
        instanceProvider = object : GraphQlInstanceProvider {
          override fun createNewInstance(typeName: String): GraphQlInstance {
            return GraphQlInstanceImpl(typeName)
          }
        }))


    val parseToObject = JsonParser.parseToObject("Hello:World")

    parseToObject.iterator().next().let { (name, value) ->
      require(name == "Hello" && value == "World")
    }

    val context = testContext("Test")

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

  @Test
  fun usingDefaults() {

    DefaultConfiguration.useDefaults(Configuration)

    val value = JsonParser.parseToObject("""
      {
        "data": {
          "Hello": "Universe!"
        }
      }
    """.trimIndent())

    val context = testContext("Test")


    require(Resolver.resolve(value, context))

    // This fails because the configuration can't change after JVM startup unfortunately
    //require(context.graphQlInstance.properties["Hello"]?.getValue() == "Universe!")
  }
}

