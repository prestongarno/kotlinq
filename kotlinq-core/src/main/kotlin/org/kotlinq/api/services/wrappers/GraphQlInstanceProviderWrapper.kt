package org.kotlinq.api.services.wrappers

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider
import org.kotlinq.api.services.Wrapper

internal class GraphQlInstanceProviderWrapper(default: GraphQlInstanceProvider)
  : Wrapper<GraphQlInstanceProvider>(default, GraphQlInstanceProvider::class),
    GraphQlInstanceProvider {

  override fun createNewInstance(): GraphQlInstance {
    return instance().createNewInstance()
  }
}
