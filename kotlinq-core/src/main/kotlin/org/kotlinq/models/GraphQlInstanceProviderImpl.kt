package org.kotlinq.models

import org.kotlinq.api.Context
import org.kotlinq.api.GraphQlInstance
import org.kotlinq.api.GraphQlInstanceProvider

internal
class GraphQlInstanceProviderImpl : GraphQlInstanceProvider {
  override fun createNewInstance(typeName: String, context: Context): GraphQlInstance {
    return GraphQlInstanceImpl(typeName, context)
  }
}