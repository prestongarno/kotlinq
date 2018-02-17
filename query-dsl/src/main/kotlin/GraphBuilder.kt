package org.kotlinq.dsl

import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.GraphQlPropertyInfo
import kotlin.reflect.KClass
import kotlin.reflect.KProperty2

fun query(name: String = "Query", definition: TypeBuilder.() -> Unit): Context =
    GraphBuilder(name, definition).build()

// Maybe do it like JS where listOf(varargs) and comma-separate so it works with references?
// [[unaryMinus] [(String) -> ScalarProperty]]
fun queryGraph(vararg properties: KProperty2<PrimitiveScope, String, Unit>): Context {
  TODO()
}

internal
class GraphBuilder(
    graphQlTypeName: String,
    private val definition: TypeBuilder.() -> Unit,
    private val delegate: GraphQlInstance = GraphQlInstanceProvider.createNewInstance(graphQlTypeName)
) : GraphQlInstance by delegate {

  fun build(): Context {
    TypeBuilder(this).apply(definition)
    return MockContext(this)
  }

  override fun equals(other: Any?) = delegate == other
  override fun hashCode() = delegate.hashCode()

  private
  class MockContext(override val graphQlInstance: GraphQlInstance) : Context {
    override fun equals(other: Any?): Boolean =
        other is Context && other.graphQlInstance == graphQlInstance
    override fun hashCode(): Int = graphQlInstance.hashCode()
  }
}

infix fun String.ofType(name: String): TypeBuilder.TypeFieldBuilder {
  return TypeBuilder.TypeFieldBuilder(this, name)
}

internal
fun info(
    graphQlName: String,
    graphQlTypeName: String = GraphQlPropertyInfo.STRING,
    arguments: Map<String, Any> = emptyMap(),
    clazz: KClass<*> = String::class
) = GraphQlPropertyInfo(graphQlName, graphQlTypeName, mockType(clazz), arguments)


