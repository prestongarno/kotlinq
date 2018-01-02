package com.prestongarno.kotlinq.core.properties

import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.api.Stub
import kotlin.reflect.KProperty


interface GraphQlPropertyContext<out X> : Stub<X> {
  companion object {

    internal
    class Builder<out S>(val delegateProvider: (GraphQlProperty) -> S) {
      fun build(property: GraphQlProperty): GraphQlPropertyContext<S> =
          GraphQlPropertyContextImpl(delegateProvider(property), property)
    }

  }
}

private class GraphQlPropertyContextImpl<out X>(
    val provider: X,
    val property: GraphQlProperty
) : GraphQlPropertyContext<X> {
  override fun getValue(inst: QSchemaType, property: KProperty<*>): X = provider
}

internal
fun <X> contextBuilder(init: (GraphQlProperty) -> X): GraphQlPropertyContext.Companion.Builder<X> =
    GraphQlPropertyContext.Companion.Builder(init)
