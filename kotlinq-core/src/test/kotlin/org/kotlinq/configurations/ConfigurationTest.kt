package org.kotlinq.configurations

import org.junit.Test
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.Resolver
import org.kotlinq.assertThrows
import org.kotlinq.api.services.Configuration
import org.kotlinq.api.services.ServiceContainer
import org.kotlinq.eq
import org.kotlinq.messageMatchingExactly
import org.kotlinq.mockType
import org.kotlinq.models.GraphQlInstanceImpl
import org.kotlinq.println
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class ConfigurationTest {

  private val parser: JsonParser = object : JsonParser {

    override fun parseToObject(string: String, rootObjectName: String) =
        listOf(string.split(":").let { it[0] to it[1] }).toMap()

    override fun parseToArray(string: String) = TODO("not implemented")

  }

  fun testContext(typeName: String) = object : Context {

    override val graphQlInstance: GraphQlInstance =
        GraphQlInstanceProvider.createNewInstance(typeName).apply {
          bindProperty(Kotlinq.adapterService.scalarAdapters.adapterFor(
              GraphQlPropertyInfo("Hello", GraphQlPropertyInfo.STRING, mockType(String::class))))
        }
  }

  @Test fun assertDependencyIsInitialized() {

    Configuration.configure {
      jsonParser = parser
      instanceProvider = object : GraphQlInstanceProvider {
        override fun createNewInstance(typeName: String) = GraphQlInstanceImpl(typeName)
      }
    }


    val parseToObject = JsonParser.parseToObject("Hello:World")

    parseToObject.iterator().next().let { (name, value) ->
      require(name == "Hello" && value == "World")
    }

    val context = testContext("Test")

    require(Resolver.resolve(parseToObject, context))

    require(context.graphQlInstance.properties["Hello"]?.getValue() == "World")

  }

  @Test
  fun usingDefaults() {

    ServiceContainer.useDefaults()

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
    require(context.graphQlInstance.properties["Hello"]?.getValue() == "Universe!")
  }

  @Test fun serviceConfigurationDoesNotTriggerStackOverflowByCircularConfiguration() {
    // make sure classes are all initialized
    ServiceContainer::class
        .memberProperties
        .filter { it.isAccessible }
        .forEach { it.get(ServiceContainer) }

    Kotlinq.createGraphQlInstance("GraphQlTypeMeta").graphQlTypeName eq "GraphQlTypeMeta"

    Configuration.use(object : GraphQlInstanceProvider {
      override fun createNewInstance(typeName: String): GraphQlInstance = throw NullPointerException("TEST")
    })

    assertThrows<NullPointerException> {
      Kotlinq.createGraphQlInstance("SomeType").println()
    } messageMatchingExactly "TEST"

    // Pass self-reference to wrapper class to delegate to self
    Configuration.use(GraphQlInstanceProvider.Companion)

    // If didn't prevent the circular reference, this will stackoverflow
    Kotlinq.createGraphQlInstance("Foo")
  }

  @Test fun resetDependenciesWorksCorrectly() {
    ServiceContainer::class
        .memberProperties
        .filter { it.isAccessible }
        .forEach { it.get(ServiceContainer) }

    Configuration.use(object : GraphQlInstanceProvider {
      override fun createNewInstance(typeName: String): GraphQlInstance = throw NullPointerException()
    })

    assertThrows<NullPointerException> {
      Kotlinq.createGraphQlInstance("foo")
    }

    ServiceContainer.useDefaults()

    Kotlinq.createGraphQlInstance("Hello").graphQlTypeName eq "Hello"
  }
}

