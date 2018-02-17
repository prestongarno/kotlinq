package org.kotlinq

import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq


open class Model<out T : Any>(val model: T) : Context {


  override val graphQlInstance: GraphQlInstance by lazy {
    Kotlinq.createGraphQlInstance(model::class.simpleName!!)
  }


  fun toGraphQl(pretty: Boolean = false, inlineFragments: Boolean = false): String =
      graphQlInstance.toGraphQl(pretty = pretty, inlineFragments = inlineFragments)

}