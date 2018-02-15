package org.kotlinq

import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.GraphQlPropertyInfo
import org.kotlinq.api.Kotlinq
import kotlin.reflect.KClass

fun createGraph(definition: GraphBuilder.TypeBuilder.() -> Unit) =
    GraphBuilder("Query", definition = definition).build()

class GraphBuilder(
    override val graphQlTypeName: String,
    private val delegate: GraphQlInstance = GraphQlInstanceProvider.createNewInstance(graphQlTypeName),
    private val definition: TypeBuilder.() -> Unit
) : GraphQlInstance by delegate {

  override fun equals(other: Any?): Boolean =
      delegate.equals(other)

  override fun hashCode(): Int =
      delegate.hashCode()

  fun build(): Context {
    TypeBuilder(this).apply(definition)
    return MockContext(this)
  }

  class TypeBuilder(private val graph: GraphBuilder) {

    var arguments: Map<String, Any> = emptyMap()
    var isNullable: Boolean = true

    fun scalar(
        name: String,
        type: String = "String",
        clazz: KClass<*> = String::class,
        arguments: Map<String, Any> = emptyMap()
    ) = graph.bindProperty(Kotlinq.adapterService.parser(info(name, type, arguments, clazz), { it }))

    infix fun String.ofType(name: String): TypeFieldBuilder {
      return TypeFieldBuilder(this, name)
    }

    infix fun TypeFieldBuilder.definedAs(block: TypeBuilder.() -> Unit) {
      val def = TypeBuilder(GraphBuilder(typeName, definition = block)).apply(block)
      Kotlinq.adapterService.instanceProperty(
          info(fieldName, typeName, def.arguments, Any::class),
          { GraphBuilder(typeName, definition = block).build() })

          .also(graph::bindProperty)
    }

    infix fun TypeFieldBuilder.spread(block: FragmentBuilder.() -> Unit) {
      val builder = FragmentBuilder().apply(block)

      Kotlinq.adapterService.fragmentProperty(
          info(fieldName, typeName, builder.arguments, Any::class),
          builder.fragments.values.toSet())

          .also(graph::bindProperty)
    }

    class TypeFieldBuilder(val fieldName: String, val typeName: String)
  }

  class FragmentBuilder {

    val fragments = mutableMapOf<String, () -> Context>()

    var arguments: Map<String, Any> = emptyMap()
    var isNullable: Boolean = true

    infix fun String.def(block: TypeBuilder.() -> Unit) {
      fragments[this] = { GraphBuilder(this, definition = block).build() }
    }

    infix fun SpreadOperator.on(typeName: String): String = typeName

  }
}

infix fun String.ofType(name: String): GraphBuilder.TypeBuilder.TypeFieldBuilder {
  return GraphBuilder.TypeBuilder.TypeFieldBuilder(this, name)
}

class MockContext(override val graphQlInstance: GraphQlInstance) : Context

interface SpreadOperator {
  companion object `,,,` : SpreadOperator

  class FragmentPrefix(val typeName: String)
}

private
fun getClassOrNull(name: String): KClass<out Any>? = try {
  MockContext::class.java.classLoader.loadClass(name).kotlin
} catch (ex: Exception) {
  null
}

fun info(
    graphQlName: String,
    graphQlTypeName: String = GraphQlPropertyInfo.STRING,
    arguments: Map<String, Any> = emptyMap(),
    clazz: KClass<*> = String::class
) =
    GraphQlPropertyInfo(graphQlName, graphQlTypeName, mockType(clazz), arguments)
