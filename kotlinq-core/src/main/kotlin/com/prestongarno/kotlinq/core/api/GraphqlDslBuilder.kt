package com.prestongarno.kotlinq.core.api

import com.prestongarno.kotlinq.core.ArgumentSpec

interface GraphqlDslBuilder<out A : ArgumentSpec> {
  fun config(block: A.() -> Unit)
}
