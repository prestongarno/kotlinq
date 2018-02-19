package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.ParsingAdapter
import kotlin.reflect.KClass
import kotlin.reflect.full.createType

sealed class GraphComponent(
    val name: String,
    val arguments: Map<String, Any>,
    val nullable: Boolean)


class Node internal constructor(
    name: String,
    arguments: Map<String, Any>,
    nullable: Boolean)
  : GraphComponent(name, arguments, nullable) {

  /**
   * Isolating all [kotlin.reflect] usages in DSL for eventually targeting Javascript
   */
  private
  fun createAdapterInfo(typeName: String) = info(name, typeName, arguments, nullable)

  /**
   * Fragment scope has an empty name for now,
   * interface enforcement is tricky when using strings only
   */
  internal fun withFragmentScope(fragments: Set<() -> Context>): Adapter =
      Kotlinq.adapterService.fragmentProperty(createAdapterInfo(""), fragments)

  internal fun withDefinition(definition: TypeDefinition): Adapter =
      Kotlinq.adapterService.instanceProperty(
          createAdapterInfo(definition.typeName),
          definition.contextDefinition)
}

class Leaf(
    name: String,
    arguments: Map<String, Any>,
    nullable: Boolean,
    val symbol: ScalarSymbol)
  : GraphComponent(name, arguments, nullable) {

  internal fun toAdapter(): ParsingAdapter =
      Kotlinq.adapterService.scalarAdapters.adapterFor(
          info(name, symbol.typeName, arguments, nullable, symbol.clazz, symbol.type)) // whoa
}

