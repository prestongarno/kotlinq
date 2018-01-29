package org.kotlinq.context

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlInstance

internal
interface ContextAware : Adapter {
  val context: GraphQlInstance
}