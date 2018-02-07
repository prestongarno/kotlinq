package org.kotlinq.dsl


interface DslBuilder<T, out A : ArgumentSpec> {
  fun config(block: A.() -> Unit)
}