package org.kotlinq.configurations

import org.junit.Ignore
import org.junit.Test
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.Resolver
import org.kotlinq.api.services.Configuration
import org.kotlinq.api.services.ServiceContainer
import org.kotlinq.assertThrows
import org.kotlinq.eq
import org.kotlinq.info
import org.kotlinq.messageMatchingExactly
import org.kotlinq.println
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

class ConfigurationTest {

  private val parser: JsonParser = object : JsonParser {

    override fun parseToObject(string: String, rootObjectName: String) =
        listOf(string.split(":").let { it[0] to it[1] }).toMap()

    override fun parseToArray(string: String) = TODO("not implemented")

  }

  @Ignore("Test individual node behaviour post-refactor")
  @Test fun assertDependencyIsInitialized() {

    Configuration.configure {
      jsonParser = parser
    }


    val parseToObject = JsonParser.parseToObject("Hello:World")

    parseToObject.iterator().next().let { (name, value) ->
      require(name == "Hello" && value == "World")
    }

    val context = Kotlinq.newContextBuilder().build("Hello")

    require(Resolver.resolve(parseToObject, context))

    require(context.graphQlInstance.properties["Hello"]?.getValue() == "World")

  }

  @Ignore("Test individual node behaviour post-refactor")
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

    val context = Kotlinq.newContextBuilder().build("Hello")


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

    Kotlinq.newContextBuilder().build("Hello").graphQlInstance.properties.size eq 0

    Configuration.use(object : GraphQlInstanceProvider {
      override fun newContextBuilder()= throw NullPointerException("TEST")
    })

    assertThrows<NullPointerException> {
      Kotlinq.newContextBuilder().println()
    } messageMatchingExactly "TEST"

    // Pass self-reference to wrapper class to delegate to self
    Configuration.use(GraphQlInstanceProvider.Companion)

    // If didn't prevent the circular reference, this will stackoverflow
    Kotlinq.newContextBuilder()
  }

  @Test fun resetDependenciesWorksCorrectly() {
    ServiceContainer::class
        .memberProperties
        .filter { it.isAccessible }
        .forEach { it.get(ServiceContainer) }

    Configuration.use(object : GraphQlInstanceProvider {
      override fun newContextBuilder()= throw NullPointerException("TEST")
    })

    assertThrows<NullPointerException> {
      Kotlinq.newContextBuilder()
    }

    ServiceContainer.useDefaults()

    "prop" eq Kotlinq.newContextBuilder()
        .register(Kotlinq.adapterService.parser(info("prop", ""), { it }))
        .build("Hello")
        .graphQlInstance.properties["prop"]
        ?.propertyInfo?.graphQlName
  }
}

