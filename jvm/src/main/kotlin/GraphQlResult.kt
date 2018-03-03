package org.kotlinq.jvm

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import kotlin.properties.ReadOnlyProperty


internal fun Map<String, Any?>.toResult(inst: GraphQlInstance) = GraphQlResult(this, inst)

internal fun Map<String, Any?>.toResult(fragment: ClassFragment<*>? = null) =
    toResult(fragment?.graphQlInstance ?: Kotlinq.newContextBuilder().build("${get("__typename")}").graphQlInstance)




class GraphQlResult internal constructor(
    map: Map<String, Any?>,
    graphQlInstance: GraphQlInstance
) {

  // copy in case of bad input (mutable map as Map)
  private val delegate: GraphQlFieldDelegate<Any?> = GraphQlFieldDelegate(map.toMap(), graphQlInstance)

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



