package org.kotlinq.jvm

import kotlin.properties.ReadOnlyProperty


class GraphQlResult internal constructor(map: Map<String, Any?>) {

  // copy in case of bad input (mutable map as Map)
  private val delegate: GraphQlFieldDelegate<Any?> = GraphQlFieldDelegate(map.toMap())

  operator fun <T : Data?> invoke(): ReadOnlyProperty<Any?, T?> =
      delegate.withReturnType()

  fun string(): ReadOnlyProperty<Any?, String> =
      delegate.withReturnType()
  fun integer(): ReadOnlyProperty<Any?, Int> =
      delegate.withReturnType()
  fun bool(): ReadOnlyProperty<Any?, Boolean> =
      delegate.withReturnType()
  fun floatingPoint(): ReadOnlyProperty<Any?, Float> =
      delegate.withReturnType()
}


internal fun Map<String, Any?>.toResult() = GraphQlResult(this)
