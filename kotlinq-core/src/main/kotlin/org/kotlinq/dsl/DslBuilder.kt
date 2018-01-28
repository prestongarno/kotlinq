package org.kotlinq.dsl

interface DslBuilder<T, out A : ArgumentSpec> {
  var default: T?
  fun config(block: A.() -> Unit)
}