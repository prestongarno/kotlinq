package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import kotlin.reflect.KClass

class TypeBuilder(private val graph: GraphQlInstance): PrimitiveScope, GraphQlInstance by graph {


  var arguments: Map<String, Any> = emptyMap()
  var isNullable: Boolean = true

  private
  fun scalarToAdapter(name: String, scalar: Scalar): Adapter {
    TODO()
  }


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