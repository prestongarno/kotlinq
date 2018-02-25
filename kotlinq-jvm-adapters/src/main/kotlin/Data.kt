package org.kotlinq.jvm


interface Data {
  val result: GraphQlResult
}

operator fun Map<String, Any?>.invoke(): Data = object : Data {
  override val result: GraphQlResult = toResult()
}