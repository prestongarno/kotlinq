package org.kotlinq.dsl

import org.kotlinq.api.GraphQlPropertyInfo
import kotlin.reflect.KClass


fun org.kotlinq.api.Context.toGraphQl(pretty: Boolean = true, inlineFragments: Boolean = false) =
    this.graphQlInstance.toGraphQl(pretty, inlineFragments)

internal
fun info(
    graphQlName: String,
    graphQlTypeName: String = GraphQlPropertyInfo.STRING,
    arguments: Map<String, Any> = emptyMap(),
    clazz: KClass<*> = String::class
) = GraphQlPropertyInfo(graphQlName, graphQlTypeName, mockType(clazz), arguments)
