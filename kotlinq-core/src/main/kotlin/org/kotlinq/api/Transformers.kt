package org.kotlinq.api


// TODO module?
interface Resolver {
  fun transform(value: String, target: Adapter): Boolean
}
