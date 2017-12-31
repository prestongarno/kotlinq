package com.prestongarno.kotlinq.core.properties

import com.prestongarno.kotlinq.core.QSchemaType
import com.prestongarno.kotlinq.core.api.Stub
import kotlin.reflect.KProperty


interface GraphQlPropertyContext<
    out X : GraphQlDelegate<T>,
    T> : Stub<X> {

  companion object {

    internal
    class Builder<out S : GraphQlDelegate<T>, T : Any?>(val delegateProvider: (GraphQlProperty) -> S) {

      fun build(property: GraphQlProperty): GraphQlPropertyContext<S, T> =
          GraphQlPropertyContextImpl(delegateProvider(property), property)

      fun <U : GraphQlDelegate<T?>> asNullable(): Builder<U, T?> = Builder { delegateProvider(it).asNullable() as U }
    }
  }
}

private class GraphQlPropertyContextImpl<out X : GraphQlDelegate<T>, T : Any?>(
    val provider: X,
    val property: GraphQlProperty
) : GraphQlPropertyContext<X, T> {
  override fun getValue(inst: QSchemaType, property: KProperty<*>): X = provider
}
