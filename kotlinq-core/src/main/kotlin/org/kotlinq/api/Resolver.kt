package org.kotlinq.api

import org.kotlinq.adapters.ModelAdapter


internal
interface Resolver {

  fun resolve(value: String, target: TypeContext): Boolean

  companion object : Resolver by Configuration.resolver
}
