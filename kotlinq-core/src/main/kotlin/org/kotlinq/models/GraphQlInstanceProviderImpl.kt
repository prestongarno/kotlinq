package org.kotlinq.models

import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider

internal
class GraphQlInstanceProviderImpl : GraphQlInstanceProvider {

  override fun createNewInstance(typeName: String): GraphQlInstance =
      GraphQlInstanceImpl(typeName)

}