package org.kotlinq.jvm


interface Data {
  val result: GraphQlResult
}

fun GraphQlResult.toData(): Data = object : Data {
  override val result: GraphQlResult get() = this@toData
}

operator fun Map<String, Any?>.invoke(): Data = object : Data {
  override val result: GraphQlResult = toResult()
}