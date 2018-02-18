package org.kotlinq.dsl

import org.kotlinq.api.GraphQlPropertyInfo
import kotlin.reflect.KClass
import kotlin.reflect.KType


fun org.kotlinq.api.Context.toGraphQl(pretty: Boolean = true, inlineFragments: Boolean = false) =
    this.graphQlInstance.toGraphQl(pretty, inlineFragments)

internal
fun info(
    graphQlName: String,
    graphQlTypeName: String = GraphQlPropertyInfo.STRING,
    arguments: Map<String, Any> = emptyMap(),
    clazz: KClass<*> = String::class,
    type: KType = mockType(clazz, false)
) = GraphQlPropertyInfo(graphQlName, graphQlTypeName, type, arguments)
