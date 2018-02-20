package org.kotlinq.dsl


typealias SelectionSet = TypeBuilder.() -> Unit


internal fun SelectionSet.asInitializer(typeName: String) =
    GraphBuilder(typeName, this)::build