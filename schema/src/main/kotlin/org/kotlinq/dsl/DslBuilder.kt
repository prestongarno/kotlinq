package org.kotlinq.dsl


interface DslBuilder<T, out A : ArgumentSpec> {
  // TODO: val default: T?
  fun config(block: A.() -> Unit)
}