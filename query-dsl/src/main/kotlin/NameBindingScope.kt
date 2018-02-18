package org.kotlinq.dsl

import org.kotlinq.api.GraphQlInstance


/**
 * Definition of extension functions for [kotlin.String] type.
 * These are placed within a class-level scope to control usage to DSL only
 */
@GraphQlDslObject
interface NameBindingScope {

  fun String.string(): (GraphQlInstance) -> GraphComponent
}