package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.schema.QType


/**
 * This is an interface with nested sub-interfaces
 * which are required in order to retain exact type information/avoid erasure from
 * passing an initializer for the GraphQL type query implementation for this field
 */
interface TypeList<in T : QType> {

  interface NoArg<in T : QType> : TypeList<T> {
    fun <U : QModel<T>> invoke(
        constructor: () -> U,
        arguments: ArgBuilder = ArgBuilder(),
        block: TypeStub<ArgBuilder>.() -> Unit = empty()
    ): DelegateProvider<List<U>>

  }

  interface OptionallyConfigured<in T : QType, in A : ArgumentSpec, out Z : List<*>?> : TypeList<T>, Configured<T, A?, Z> {
    operator fun <U : QModel<T>> invoke(
        constructor: () -> U,
        block: TypeStub<ArgBuilder>.() -> Unit = empty()
    ): DelegateProvider<Z> = invoke(constructor, null, empty())
  }

  interface Configured<in T : QType, in A : ArgumentSpec?, out Z : List<*>?> : TypeList<T> {
    operator fun <U : QModel<T>> invoke(
        constructor: () -> U,
        arguments: A,
        block: TypeStub<ArgBuilder>.() -> Unit = empty()
    ): DelegateProvider<Z>
  }

}

