package org.kotlinq

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq
import org.kotlinq.api.TypeContext


open class Model<out T : Any>(val model: T) : TypeContext {


  override val graphQlInstance: GraphQlInstance by lazy {
    Kotlinq.createGraphQlInstance(model::class.simpleName!!, this)
  }


  fun toGraphQl(pretty: Boolean = false, extractFragments: Boolean = false): String =
      graphQlInstance.toGraphQl(pretty = pretty, extractFragments = extractFragments)

}