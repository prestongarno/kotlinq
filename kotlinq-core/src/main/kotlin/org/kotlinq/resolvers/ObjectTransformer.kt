package org.kotlinq.resolvers

import org.kotlinq.adapters.ModelAdapter
import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlJsonParser
import org.kotlinq.api.Resolver

internal
class ObjectTransformerImpl(
    override val modelResolver: (value: String, target: ModelAdapter) -> Boolean = { value, target ->
      target.resolve(GraphQlJsonParser.parseToObject(value))
    }
) : Resolver

