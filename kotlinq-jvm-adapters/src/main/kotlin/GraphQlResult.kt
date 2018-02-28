package org.kotlinq.jvm

import kotlin.properties.ReadOnlyProperty


class GraphQlResult internal constructor(internal val map: Map<String, Any?>) {

  operator fun <T : Data?> invoke(): ReadOnlyProperty<Any?, T> = NullableDelegate(this)

  fun string(): ReadOnlyProperty<Any?, String> = NullableDelegate(this)
  fun integer(): ReadOnlyProperty<Any?, Int> = NullableDelegate(this)
  fun bool(): ReadOnlyProperty<Any?, Boolean> = NullableDelegate(this)
  fun floatingPoint(): ReadOnlyProperty<Any?, Float> = NullableDelegate(this)

  fun canResolveTo(fragment: ClassFragment<*>): Boolean = false

}


internal fun Map<String, Any?>.toResult() = GraphQlResult(this)
