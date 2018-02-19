package org.kotlinq.dsl

import org.kotlinq.api.Adapter
import org.kotlinq.api.GraphQlInstance


/**
 * Usually this would be done with a lambda,
 * but it's an important concept so an interface is defined
 */
interface BindableContext {

  fun register(adapter: Adapter)


  companion object {

    fun from(call: (Adapter) -> Unit): BindableContext =
        BindableContextImpl(call)

    fun from(instance: GraphQlInstance): BindableContext =
        BindableContextImpl(instance::bindProperty)

    private
    class BindableContextImpl(private val call: (Adapter) -> Unit) : BindableContext {
      override fun register(adapter: Adapter) = call(adapter)
    }
  }
}