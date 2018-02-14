package org.kotlinq

import org.kotlinq.api.Context
import org.kotlinq.api.Fragment
import org.kotlinq.api.FragmentAdapter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.GraphQlType
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.ModelAdapter
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
        type: GraphQlType = GraphQlType.fromKtype(mockType(String::class)),
        arguments: Map<String, Any> = emptyMap()
    ) = graph.bindProperty(Kotlinq.adapterService.parser(name, type.ktype, { it }, arguments))

    infix fun String.ofType(name: String): TypeFieldBuilder {
      return TypeFieldBuilder(this, name)
    }

    infix fun TypeFieldBuilder.definedAs(block: TypeBuilder.() -> Unit) {
      val def = TypeBuilder(GraphBuilder(typeName, definition = block)).apply(block)
      graph.bindProperty(MockTypeField(fieldName, typeName, { GraphBuilder(typeName, definition = block).build() }, def.isNullable, def.arguments))
    }

    infix fun TypeFieldBuilder.spread(block: FragmentBuilder.() -> Unit) {
      val builder = FragmentBuilder().apply(block)
      MockFragmentField(
          fieldName,
          builder.fragments.map { it.value }.toSet(),
          typeName,
          builder.isNullable,
          arguments = builder.arguments)
          .also(this@TypeBuilder.graph::bindProperty)
    }

    class TypeFieldBuilder(val fieldName: String, val typeName: String)
  }

  class FragmentBuilder {

    val fragments = mutableMapOf<String, () -> Context>()

    var arguments: Map<String, Any> = emptyMap()
    var isNullable: Boolean = true

    infix fun String.fragmentDef(block: TypeBuilder.() -> Unit) {
      fragments[this] = { GraphBuilder(this, definition = block).build() }
    }


    infix fun String.def(block: TypeBuilder.() -> Unit) {
      fragments[this] = { GraphBuilder(this, definition = block).build() }
    }

    infix fun SpreadOperator.on(typeName: String): String = typeName

  }
}

infix fun String.ofType(name: String): GraphBuilder.TypeBuilder.TypeFieldBuilder {
  return GraphBuilder.TypeBuilder.TypeFieldBuilder(this, name)
}

private
class MockFragmentField(
    override val name: String,
    fragments: Set<() -> Context>,
    typeName: String = "Any",
    isNullable: Boolean = false,
    override val type: GraphQlType =
    GraphQlType.fromKtype(mockType(
        getClassOrNull(typeName) ?: Fragment::class,
        isMarkedNullable = isNullable)),
    override val arguments: Map<String, Any> = emptyMap(),
    val delegate: FragmentAdapter = Kotlinq.adapterService.fragmentProperty(name, type.ktype, fragments, arguments)
) : FragmentAdapter by delegate {
  override fun equals(other: Any?) = delegate == other
  override fun hashCode() = delegate.hashCode()
  override fun toString(): String = delegate.toString()
}


private
class MockTypeField(
    override val name: String,
    typeName: String,
    initializer: () -> Context,
    isNullable: Boolean = true,
    override val arguments: Map<String, Any> = emptyMap(),
    override val type: GraphQlType = GraphQlType.fromKtype(mockType(Context::class, isMarkedNullable = isNullable)),
    val delegate: ModelAdapter = Kotlinq.adapterService.instanceProperty(name, type.ktype, initializer, arguments)
) : ModelAdapter by delegate {
  override fun equals(other: Any?) = delegate == other
  override fun hashCode() = delegate.hashCode()
  override fun toString(): String = delegate.toString()
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
