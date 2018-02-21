package org.kotlinq.dsl

fun org.kotlinq.api.Fragment.toGraphQl(pretty: Boolean = true) =
    this.graphQlInstance.toGraphQl(pretty = pretty)

internal
fun Any?.ignore() = Unit
