package org.kotlinq.jvm

import kotlin.properties.ReadOnlyProperty


interface Data {

  val result: GraphQlResult

  @Suppress("UNCHECKED_CAST") // Should be totally safe I think?
  fun <T> ReadOnlyProperty<Any?, T?>.asList(): ReadOnlyProperty<Any?, List<T>> =
      (this as? GraphQlFieldDelegate<T>)?.withReturnType() ?: this as ReadOnlyProperty<Any?, List<T>>
}

/**
 * Convenience class to subclass [Data] easily
 */
abstract class GraphQlData(final override val result: GraphQlResult): Data {
  constructor(map: Map<String, Any?>) : this(map.toResult())
}



fun GraphQlResult.toData(): Data = object : Data {
  override val result: GraphQlResult get() = this@toData
}

operator fun Map<String, Any?>.invoke(): Data = object : Data {
  override val result: GraphQlResult = toResult()
}