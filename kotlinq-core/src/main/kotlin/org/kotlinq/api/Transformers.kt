package org.kotlinq.api

import org.kotlinq.adapters.ModelAdapter


// TODO module?
internal
interface Resolver {

  val modelResolver: (value: String, target: ModelAdapter) -> Boolean
}
