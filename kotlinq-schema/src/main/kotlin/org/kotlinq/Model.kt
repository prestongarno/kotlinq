package org.kotlinq

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.Kotlinq


open class Model<out T : Any>(val model: T) {

  /**
   * The context container for this [Model] implementation instance
   */
  internal
  val propertyContainer: GraphQlInstance by lazy {
    Kotlinq.createGraphQlInstance(model::class.simpleName!!)
  }


  fun toGraphQl(pretty: Boolean = false, extractFragments: Boolean = false): String =
      propertyContainer.toGraphQl(pretty = pretty, extractFragments = extractFragments)

}