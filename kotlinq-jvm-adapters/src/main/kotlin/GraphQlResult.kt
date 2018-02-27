package org.kotlinq.jvm

import kotlin.properties.ReadOnlyProperty


class GraphQlResult internal constructor(
    private val map: Map<String, Any?>
) : Map<String, Any?> by map {


  inline operator fun <reified T> invoke(init: (GraphQlResult) -> T) : ReadOnlyProperty<Any?, T> {
    TODO()
  }

  fun canResolveTo(fragment: TypedFragment<*>): Boolean = false

}


internal fun Map<String, Any?>.toResult() = GraphQlResult(this)
