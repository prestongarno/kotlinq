package org.kotlinq.jvm


class GraphQlResult internal constructor(internal val map: Map<String, Any?>) {

  operator fun invoke(): Data =
      object : Data {
        override val result = this@GraphQlResult
      }

  fun canResolveTo(fragment: TypedFragment<*>): Boolean = false

}


internal fun Map<String, Any?>.toResult() = GraphQlResult(this)
