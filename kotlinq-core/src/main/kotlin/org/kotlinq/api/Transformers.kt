package org.kotlinq.api

import org.kotlinq.adapters.Adapter


// TODO module?
interface Resolver {
  fun transform(value: String, target: Adapter): Boolean
}
