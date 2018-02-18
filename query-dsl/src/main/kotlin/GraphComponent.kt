package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.ParsingAdapter
import kotlin.reflect.KClass
import kotlin.reflect.full.createType

sealed class GraphComponent(
    name: String,
    arguments: Map<String, Any>,
    nullable: Boolean,
    clazz: KClass<*>,
    typeName: String = clazz.simpleName!!) {

  internal
  val info = info(name, typeName, arguments, clazz, clazz.createType(nullable = nullable))
}

/**
 * Currently, the class name and typeName do not
 * have strict relationship. This may change in the future.
 */
class Node internal constructor(
    name: String,
    arguments: Map<String, Any>,
    nullable: Boolean,
    typeName: String = "Any",
    private val context: GraphQlInstance)
  : GraphComponent(name, arguments, nullable, Any::class, typeName) {

  operator fun invoke(typeName: String? = null, block: TypeBuilder.() -> Unit) {
  }

  internal fun withFragmentScope(fragments: Set<() -> Context>): Adapter =
      Kotlinq.adapterService.fragmentProperty(info, fragments)

  internal fun withDefinition(contextInit: () -> Context): Adapter =
      Kotlinq.adapterService.instanceProperty(info, contextInit)
}

@Suppress("CanBeParameter")
class Leaf(
    name: String,
    arguments: Map<String, Any>,
    nullable: Boolean,
    val symbol: ScalarSymbol)
  : GraphComponent(name, arguments, nullable, symbol.clazz, symbol.typeName) {

  internal fun toAdapter(): ParsingAdapter =
      Kotlinq.adapterService.scalarAdapters.adapterFor(info)
}

