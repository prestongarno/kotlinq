package org.kotlinq.jvm

import kotlin.properties.ReadOnlyProperty


interface Data {

  val result: GraphQlResult

  @Suppress("UNCHECKED_CAST") // Should be totally safe I think?
  fun <T> ReadOnlyProperty<Any?, T?>.asList(): ReadOnlyProperty<Any?, List<T>> =
      (this as? GraphQlFieldDelegate<T>)?.withReturnType() ?: this as ReadOnlyProperty<Any?, List<T>>
}



fun GraphQlResult.toData(): Data = object : Data {
  override val result: GraphQlResult get() = this@toData
}

operator fun Map<String, Any?>.invoke(): Data = object : Data {
  override val result: GraphQlResult = toResult()
}