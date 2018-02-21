package org.kotlinq.dsl

fun org.kotlinq.api.Fragment.toGraphQl(pretty: Boolean = true, inlineFragments: Boolean = false) =
    this.graphQlInstance.toGraphQl(pretty, inlineFragments)

internal
fun Any?.ignore() = Unit
