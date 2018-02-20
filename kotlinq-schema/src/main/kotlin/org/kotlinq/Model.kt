package org.kotlinq

import org.kotlinq.api.Definition
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq


open class Model<out T : Any>(override val graphQlTypeName: String, val model: T) : Definition {


  override val graphQlInstance: GraphQlInstance by lazy(Kotlinq.Companion::createGraphQlInstance)

  fun toGraphQl(pretty: Boolean = false, inlineFragments: Boolean = true): String =
      graphQlInstance.toGraphQl(pretty = pretty, inlineFragments = inlineFragments)

}