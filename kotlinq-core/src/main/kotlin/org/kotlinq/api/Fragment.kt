package org.kotlinq.api


interface Fragment {
  val initializer: () -> Context
  val prototype: Context
}