package org.kotlinq.api

import org.kotlinq.adapters.Adapter


interface Resolver {
  fun transform(value: String, target: Adapter): Boolean
}
