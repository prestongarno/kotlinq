package org.kotlinq.api

import org.kotlinq.adapters.ModelAdapter


internal
interface Resolver {

  val modelResolver: (value: String, target: ModelAdapter) -> Boolean

  companion object : Resolver by Configuration.resolver
}
