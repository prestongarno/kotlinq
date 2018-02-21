package org.kotlinq.api


/**
 * Represents a GraphQL Fragment definition
 */
data class Fragment(
    val typeName: String,
    val graphQlInstance: GraphQlInstance)
