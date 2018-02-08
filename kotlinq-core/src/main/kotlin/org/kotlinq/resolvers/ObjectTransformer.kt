package org.kotlinq.resolvers

import org.kotlinq.adapters.ModelAdapter
import org.kotlinq.api.JsonParser
import org.kotlinq.api.Resolver

internal
class ObjectTransformerImpl(
    override val modelResolver: (value: String, target: ModelAdapter) -> Boolean
) : Resolver

