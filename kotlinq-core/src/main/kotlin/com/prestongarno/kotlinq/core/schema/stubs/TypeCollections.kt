package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.TypeStubAdapter
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.InternalDelegateProvider
import com.prestongarno.kotlinq.core.schema.QType

typealias Block<A> = TypeStub<A>.() -> Unit

/**
 * This is an interface with nested sub-interfaces
 * which are required in order to retain exact type information/avoid erasure from
 * passing an initializer for the GraphQL type query implementation for this field
 */
interface TypeListStub {

  interface NoArg<in T : QType> : TypeListStub {
    operator fun <U : QModel<T>> invoke(
        constructor: () -> U,
        arguments: ArgBuilder = ArgBuilder(),
        block: Block<ArgBuilder> = empty()
    ): DelegateProvider<List<U?>>

    interface NotNull<in T : QType> {
      operator fun <U : QModel<T>> invoke(
          constructor: () -> U,
          arguments: ArgBuilder = ArgBuilder(),
          block: Block<ArgBuilder> = empty()
      ): DelegateProvider<List<U>>
    }

  }

  interface OptionallyConfigured<in T : QType, A : ArgumentSpec> : TypeListStub, Configured<T, A> {
    operator fun <U : QModel<T>> invoke(
        constructor: () -> U,
        arguments: ArgBuilder = ArgBuilder(),
        block: Block<ArgBuilder> = empty()
    ): DelegateProvider<List<U?>>

    interface NotNull<in T : QType, A : ArgumentSpec> : Configured.NotNull<T, A> {
      fun <U : QModel<T>> invoke(constructor: () -> U, arguments: ArgBuilder = ArgBuilder(), block: Block<ArgBuilder>)
          : DelegateProvider<List<U>>
    }
  }

  interface Configured<in T : QType, A : ArgumentSpec> : TypeListStub {

    operator fun <U : QModel<T>> invoke(
        constructor: () -> U,
        arguments: A,
        block: Block<A> = empty()
    ): DelegateProvider<List<U?>>

    interface NotNull<in T : QType, A : ArgumentSpec> : Configured<T, A> {
      override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: A, block: Block<A>)
          : DelegateProvider<List<U>>
    }
  }

}


internal
sealed class TypeListStubImpl(val qproperty: GraphQlProperty) {

  class NoArg<in T : QType>(qprop: GraphQlProperty) : TypeListStubImpl(qprop), TypeListStub.NoArg<T> {

    override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: ArgBuilder, block: Block<ArgBuilder>) =
        newNullableDelegate(constructor, arguments, block)

    class NotNull<in T : QType>(qprop: GraphQlProperty) : TypeListStubImpl(qprop), TypeListStub.NoArg.NotNull<T> {
      override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: ArgBuilder, block: Block<ArgBuilder>): DelegateProvider<List<U>> =
          delegateProvider { inst, _ ->
            TypeStubAdapter(constructor, arguments)
                .apply(block)
                .toDelegate(qproperty)
                .asList()
                .bindToContext(inst)
          }
    }
  }

  class OptionallyConfigured<in T : QType, A : ArgumentSpec>(qprop: GraphQlProperty)
    : TypeListStubImpl(qprop),
      TypeListStub.OptionallyConfigured<T, A> {

    override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: A, block: Block<A>) =
        newNullableDelegate(constructor, arguments, block)

    override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: ArgBuilder, block: Block<ArgBuilder>) =
        newNullableDelegate(constructor, arguments, block)

    class NotNull<in T : QType, A : ArgumentSpec>(qprop: GraphQlProperty) : TypeListStubImpl(qprop), TypeListStub.OptionallyConfigured.NotNull<T, A> {

      override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: ArgBuilder, block: Block<ArgBuilder>) =
          newDelegate(constructor, arguments, block)

      override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: A, block: Block<A>) =
          newDelegate(constructor, arguments, block)
    }
  }

  class Configured<in T : QType, A : ArgumentSpec>(qprop: GraphQlProperty)
    : TypeListStubImpl(qprop),
      TypeListStub.Configured<T, A> {

    override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: A, block: Block<A>): DelegateProvider<List<U?>> =
        newNullableDelegate(constructor, arguments, block)

    class NotNull<in T : QType, A : ArgumentSpec>(qprop: GraphQlProperty)
      : TypeListStubImpl(qprop),
        TypeListStub.Configured.NotNull<T, A> {

      override fun <U : QModel<T>> invoke(constructor: () -> U, arguments: A, block: Block<A>): DelegateProvider<List<U>> =
          newDelegate(constructor, arguments, block)
    }
  }

  fun <U : QModel<T>, T : QType, X : ArgumentSpec> newDelegate(
      ctor: () -> U,
      args: X,
      block: Block<X>
  ): InternalDelegateProvider<List<U>> = delegateProvider { inst, _ ->
    TypeStubAdapter(ctor, args)
        .apply(block)
        .toDelegate(qproperty)
        .asList()
        .bindToContext(inst)
  }

  fun <U : QModel<T>, T : QType, X : ArgumentSpec> newNullableDelegate(
      ctor: () -> U,
      args: X,
      block: Block<X>
  ): InternalDelegateProvider<List<U?>> = delegateProvider { inst, _ ->
    TypeStubAdapter(ctor, args)
        .apply(block)
        .toDelegate(qproperty)
        .asNullable()
        .asList()
        .bindToContext(inst)
  }

  companion object {

    /**
     * Don't know why sealed class [TypeListStubImpl] does not smart-cast this to the generic reified argument.
     * TODO this is pretty safe, but should test thoroughly
     */
    internal
    inline fun <T : QType, A : ArgumentSpec, reified Z : TypeListStubImpl> newStub(): (GraphQlProperty) -> Z =
        when (Z::class) {
          NoArg::class -> { it -> NoArg<T>(it) as Z }
          NoArg.NotNull::class -> { it -> NoArg.NotNull<T>(it) as Z }
          OptionallyConfigured::class -> { it -> OptionallyConfigured<T, A>(it) as Z }
          OptionallyConfigured.NotNull::class -> { it -> OptionallyConfigured.NotNull<T, A>(it) as Z }
          Configured::class -> { it -> Configured<T, A>(it) as Z }
          Configured.NotNull::class -> { it -> Configured.NotNull<T, A>(it) as Z }
          else -> throw IllegalStateException()
        }
  }
}
