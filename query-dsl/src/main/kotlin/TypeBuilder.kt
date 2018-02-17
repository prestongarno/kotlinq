package org.kotlinq.dsl

import TypeDefinition
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq

@GraphQlDslObject
class TypeBuilder(
    private val graph: GraphQlInstance,
    val defaultTypeName: String = "Object"
) : DslExtensionScope, GraphQlInstance by graph {


  private val adapterService get() = Kotlinq.adapterService

  override fun String.invoke(arguments: Map<String, Any>, typeName: String?, block: TypeBuilder.() -> Unit) {
    adapterService.instanceProperty(
        info(this, typeName ?: defaultTypeName, arguments, Any::class),
        { GraphBuilder(typeName ?: defaultTypeName, block).build() })
        .also(graph::bindProperty)
  }

  override fun String.invoke(arguments: Map<String, Any>, typeName: String?, definition: TypeDefinition) {
    adapterService.instanceProperty(
        info(this,
            typeName ?: defaultTypeName,
            arguments,
            Any::class),
        definition::invoke)
        .also(graph::bindProperty)
  }

  override fun String.spread(block: FragmentScopeBuilder.() -> Unit) {
    val builder = FragmentScopeBuilder().apply(block)
    adapterService.fragmentProperty(
        info(this,
            defaultTypeName,
            builder.arguments,
            Any::class),
        builder.fragments.values.toSet())
        .also(graph::bindProperty)
  }

  var arguments: Map<String, Any> = emptyMap()
  var isNullable: Boolean = true


  class TypeFieldBuilder(val fieldName: String, val typeName: String)
}