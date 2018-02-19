package org.kotlinq.dsl


typealias LeafGetter = (() -> LeafBinding)

typealias LeafBinding = (BindableContext) -> Unit

typealias TypeBuilderBlock = TypeBuilder.() -> Unit