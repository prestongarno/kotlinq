package org.kotlinq.dsl

import org.kotlinq.api.GraphQlPropertyInfo
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType


fun org.kotlinq.api.Fragment.toGraphQl(pretty: Boolean = true, inlineFragments: Boolean = false) =
    this.graphQlInstance.toGraphQl(pretty, inlineFragments)

internal
fun info(
    graphQlName: String,
    graphQlTypeName: String = GraphQlPropertyInfo.STRING,
    arguments: Map<String, Any> = emptyMap(),
    isNullable: Boolean = false,
    clazz: KClass<*> = Any::class,
    type: KType = clazz.createType(nullable = isNullable)
) = GraphQlPropertyInfo(graphQlName, graphQlTypeName, type, arguments)

internal
fun Any?.ignore() = Unit
