package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.InterfaceStubImpl
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyContext
import com.prestongarno.kotlinq.core.properties.contextBuilder
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.InternalDelegateProvider
import com.prestongarno.kotlinq.core.schema.QInterface
import com.prestongarno.kotlinq.core.schema.QType

typealias IBlock<T, A> = InterfaceStub<T, A>.() -> Unit

/**
 * This is an iface with nested sub-interfaces
 * which are required in order to retain exact type information/avoid erasure from
 * passing an initializer for the GraphQL type query implementation for this field
 */
interface InterfaceListStub {


  interface NoArg<T> : InterfaceListStub where T : QInterface, T : QType {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: IBlock<T, ArgBuilder>): DelegateProvider<List<QModel<T>?>>

    interface NotNull<T> where T : QInterface, T : QType {
      operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: IBlock<T, ArgBuilder>): DelegateProvider<List<QModel<T>>>
    }

  }


  interface OptionallyConfigured<T, A : ArgumentSpec> : InterfaceListStub, Configured<T, A> where T : QInterface, T : QType {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: IBlock<T, ArgBuilder>): DelegateProvider<List<QModel<T>?>>

    interface NotNull<T, A : ArgumentSpec> : Configured.NotNull<T, A> where T : QInterface, T : QType {
      operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: IBlock<T, ArgBuilder>): DelegateProvider<List<QModel<T>>>
    }
  }


  interface Configured<T, A : ArgumentSpec> : InterfaceListStub where T : QInterface, T : QType {

    operator fun invoke(arguments: A, block: IBlock<T, A>): DelegateProvider<List<QModel<T>?>>

    interface NotNull<T, A : ArgumentSpec> where T : QInterface, T : QType {
      operator fun invoke(arguments: A, block: IBlock<T, A>): DelegateProvider<List<QModel<T>>>
    }
  }

}

internal
sealed class InterfaceListStubImpl<T, A>(val qproperty: GraphQlProperty)
    where T : QInterface, T : QType, A : ArgumentSpec {

  internal
  class NoArg<T>(qprop: GraphQlProperty) : InterfaceListStubImpl<T, ArgBuilder>(qprop), InterfaceListStub.NoArg<T> where T : QInterface, T : QType {

    override fun invoke(arguments: ArgBuilder, block: IBlock<T, ArgBuilder>)
        : InternalDelegateProvider<List<QModel<T>?>> =
        newNullableDelegate(arguments, block)


    internal
    class NotNull<T>(qprop: GraphQlProperty)
      : InterfaceListStubImpl<T, ArgBuilder>(qprop),
        InterfaceListStub.NoArg.NotNull<T>
        where T : QInterface,
              T : QType {

      override fun invoke(arguments: ArgBuilder, block: IBlock<T, ArgBuilder>)
          : DelegateProvider<List<QModel<T>>> =
          newDelegate(arguments, block)
    }
  }

  internal
  class OptionallyConfigured<T, A : ArgumentSpec>(qprop: GraphQlProperty)
    : InterfaceListStubImpl<T, A>(qprop),
      InterfaceListStub.OptionallyConfigured<T, A>
      where T : QInterface,
            T : QType {
    override fun invoke(arguments: ArgBuilder, block: IBlock<T, ArgBuilder>)
        : DelegateProvider<List<QModel<T>?>> =
        newNullableDelegate(arguments, block)

    override fun invoke(arguments: A, block: IBlock<T, A>)
        : DelegateProvider<List<QModel<T>?>> =
        newNullableDelegate(arguments, block)


    internal
    class NotNull<T, A : ArgumentSpec>(qprop: GraphQlProperty)
      : InterfaceListStubImpl<T, A>(qprop),
        InterfaceListStub.OptionallyConfigured.NotNull<T, A>
        where T : QInterface,
              T : QType {

      override fun invoke(arguments: ArgBuilder, block: IBlock<T, ArgBuilder>)
          : DelegateProvider<List<QModel<T>>> =
          newDelegate(arguments, block)

      override fun invoke(arguments: A, block: IBlock<T, A>)
          : DelegateProvider<List<QModel<T>>> =
          newDelegate(arguments, block)
    }
  }

  internal
  class Configured<T, A : ArgumentSpec>(qprop: GraphQlProperty)
    : InterfaceListStubImpl<T, A>(qprop),
      InterfaceListStub.Configured<T, A>
      where T : QInterface,
            T : QType {

    override fun invoke(arguments: A, block: IBlock<T, A>)
        : DelegateProvider<List<QModel<T>?>> =
        newNullableDelegate(arguments, block)


    internal
    class NotNull<T, A : ArgumentSpec>(qprop: GraphQlProperty)
      : InterfaceListStubImpl<T, A>(qprop),
        InterfaceListStub.Configured.NotNull<T, A>
        where T : QInterface,
              T : QType {

      override fun invoke(arguments: A, block: IBlock<T, A>)
          : DelegateProvider<List<QModel<T>>> =
          newDelegate(arguments, block)
    }
  }

  fun <T, X : ArgumentSpec> newDelegate(
      args: X,
      block: IBlock<T, X>
  ): InternalDelegateProvider<List<QModel<T>>>
      where T : QInterface,
            T : QType
      = delegateProvider { inst, _ ->
    InterfaceStubImpl<T, X>(args)
        .apply(block)
        .toDelegate(qproperty)
        .asList()
        .bindToContext(inst)
  }

  fun <T, X : ArgumentSpec> newNullableDelegate(
      args: X,
      block: IBlock<T, X>
  ): InternalDelegateProvider<List<QModel<T>?>>
      where T : QInterface,
            T : QType
      = delegateProvider { inst, _ ->
    InterfaceStubImpl<T, X>(args)
        .apply(block)
        .toDelegate(qproperty)
        .asNullable()
        .asList()
        .bindToContext(inst)
  }

  companion object {

    /**
     * Don't know why sealed class [InterfaceListStubImpl] does not smart-cast this to the generic reified argument.
     * TODO this is pretty safe, but should test thoroughly
     */
    internal
    inline fun <reified Z : InterfaceListStubImpl<out T, out A>, T, A : ArgumentSpec> newStub()
        : GraphQlPropertyContext.Companion.Builder<Z>
        where T : QInterface, T : QType =

        contextBuilder {
          when (Z::class) {
            NoArg::class -> NoArg(it)
            NoArg.NotNull::class -> NoArg.NotNull<T>(it)
            OptionallyConfigured::class -> OptionallyConfigured<T, A>(it)
            OptionallyConfigured.NotNull::class -> OptionallyConfigured.NotNull<T, A>(it)
            Configured::class -> Configured<T, A>(it)
            Configured.NotNull::class -> Configured.NotNull<T, A>(it)
            else -> throw IllegalStateException()
          } as Z
        }
  }
}
