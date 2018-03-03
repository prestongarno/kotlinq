package org.kotlinq

import org.kotlinq.api.BindableContext
import org.kotlinq.api.Kotlinq


open class Model<out T : Any>(val graphQlTypeName: String, val model: T) {


  var instanceBuilder: BindableContext? = Kotlinq.newContextBuilder()

  internal val fragment by lazy {
    val value = instanceBuilder!!.build(graphQlTypeName)
    instanceBuilder = null
    value
  }

  fun toGraphQl(pretty: Boolean = false, inlineFragments: Boolean = true): String =
      fragment.graphQlInstance.toGraphQl(pretty = pretty, inlineFragments = inlineFragments)

}