package org.kotlinq.dsl

import org.kotlinq.api.Context
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
 *
 * @param thunk context constructor.
 */
internal
class Node(
    name: String,
    arguments: Map<String, Any>,
    nullable: Boolean,
    typeName: String = "Any",
    internal
    val thunk: () -> Context)
  : GraphComponent(name, arguments, nullable, Any::class, typeName)

@Suppress("CanBeParameter")
internal
class Leaf(
    name: String,
    arguments: Map<String, Any>,
    nullable: Boolean,
    val symbol: ScalarSymbol)
  : GraphComponent(name, arguments, nullable, symbol.clazz, symbol.typeName)



