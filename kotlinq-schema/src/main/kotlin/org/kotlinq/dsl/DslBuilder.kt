package org.kotlinq.dsl

// TODO default value
interface DslBuilder<T, out A : ArgumentSpec> {
  fun config(block: A.() -> Unit)
}