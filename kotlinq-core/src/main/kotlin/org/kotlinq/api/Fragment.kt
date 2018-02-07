package org.kotlinq.api


interface Fragment {
  val initializer: () -> TypeContext
}