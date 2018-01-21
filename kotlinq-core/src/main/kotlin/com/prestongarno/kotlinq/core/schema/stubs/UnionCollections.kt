package com.prestongarno.kotlinq.core.schema.stubs

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.Model
import com.prestongarno.kotlinq.core.adapters.UnionStubImpl
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyContext
import com.prestongarno.kotlinq.core.properties.contextBuilder
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.InternalDelegateProvider
import com.prestongarno.kotlinq.core.schema.QType
import com.prestongarno.kotlinq.core.schema.QUnionType

typealias UBlock<T, A> = UnionStub<T, A>.() -> Unit

/**
 * This is an iface with nested sub-interfaces
 * which are required in order to retain exact type information/avoid erasure from
 * passing an initializer for the GraphQL type query implementation for this field
 */
interface UnionListStub {

  interface NoArg<T> : UnionListStub where T : QUnionType, T : QType {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: UBlock<T, ArgBuilder>): DelegateProvider<List<Model<*>?>>

    interface NotNull<T> where T : QUnionType, T : QType {
      operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: UBlock<T, ArgBuilder>): DelegateProvider<List<Model<*>>>
    }

  }

  interface OptionallyConfigured<T, A : ArgumentSpec> : UnionListStub, Configured<T, A> where T : QUnionType, T : QType {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: UBlock<T, ArgBuilder>): DelegateProvider<List<Model<*>?>>

    interface NotNull<T, A : ArgumentSpec> : Configured.NotNull<T, A> where T : QUnionType, T : QType {
      operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: UBlock<T, ArgBuilder>): DelegateProvider<List<Model<*>>>
    }
  }

  interface Configured<T, A : ArgumentSpec> : UnionListStub where T : QUnionType, T : QType {

    operator fun invoke(arguments: A, block: UBlock<T, A>): DelegateProvider<List<Model<*>?>>

    interface NotNull<T, A : ArgumentSpec> where T : QUnionType, T : QType {
      operator fun invoke(arguments: A, block: UBlock<T, A>): DelegateProvider<List<Model<*>>>
    }
  }

}

internal
sealed class UnionListStubImpl<T, A>(val unionObject: T, val qproperty: GraphQlProperty)
    where T : QUnionType, T : QType, A : ArgumentSpec {

  internal
  class NoArg<T>(obj: T, qprop: GraphQlProperty) : UnionListStubImpl<T, ArgBuilder>(obj, qprop), UnionListStub.NoArg<T> where T : QUnionType, T : QType {

    override fun invoke(arguments: ArgBuilder, block: UBlock<T, ArgBuilder>)
        : InternalDelegateProvider<List<Model<*>?>> =
        newNullableDelegate(unionObject, arguments, block)


    internal
    class NotNull<T>(obj: T, qprop: GraphQlProperty)
      : UnionListStubImpl<T, ArgBuilder>(obj, qprop),
        UnionListStub.NoArg.NotNull<T>
        where T : QUnionType,
              T : QType {

      override fun invoke(arguments: ArgBuilder, block: UBlock<T, ArgBuilder>)
          : DelegateProvider<List<Model<*>>> =
          newDelegate(unionObject, arguments, block)
    }
  }

  internal
  class OptionallyConfigured<T, A : ArgumentSpec>(obj: T, qprop: GraphQlProperty)
    : UnionListStubImpl<T, A>(obj, qprop),
      UnionListStub.OptionallyConfigured<T, A>
      where T : QUnionType,
            T : QType {
    override fun invoke(arguments: ArgBuilder, block: UBlock<T, ArgBuilder>)
        : DelegateProvider<List<Model<*>?>> =
        newNullableDelegate(unionObject, arguments, block)

    override fun invoke(arguments: A, block: UBlock<T, A>)
        : DelegateProvider<List<Model<*>?>> =
        newNullableDelegate(unionObject, arguments, block)


    internal
    class NotNull<T, A : ArgumentSpec>(obj: T, qprop: GraphQlProperty)
      : UnionListStubImpl<T, A>(obj, qprop),
        UnionListStub.OptionallyConfigured.NotNull<T, A>
        where T : QUnionType,
              T : QType {

      override fun invoke(arguments: ArgBuilder, block: UBlock<T, ArgBuilder>)
          : DelegateProvider<List<Model<*>>> =
          newDelegate(unionObject, arguments, block)

      override fun invoke(arguments: A, block: UBlock<T, A>)
          : DelegateProvider<List<Model<*>>> =
          newDelegate(unionObject, arguments, block)
    }
  }

  internal
  class Configured<T, A : ArgumentSpec>(obj: T, qprop: GraphQlProperty)
    : UnionListStubImpl<T, A>(obj, qprop),
      UnionListStub.Configured<T, A>
      where T : QUnionType,
            T : QType {

    override fun invoke(arguments: A, block: UBlock<T, A>)
        : DelegateProvider<List<Model<*>?>> =
        newNullableDelegate(unionObject, arguments, block)


    internal
    class NotNull<T, A : ArgumentSpec>(obj: T, qprop: GraphQlProperty)
      : UnionListStubImpl<T, A>(obj, qprop),
        UnionListStub.Configured.NotNull<T, A>
        where T : QUnionType,
              T : QType {

      override fun invoke(arguments: A, block: UBlock<T, A>)
          : DelegateProvider<List<Model<*>>> =
          newDelegate(unionObject, arguments, block)
    }
  }

  fun <T, X : ArgumentSpec> newDelegate(
      obj: T,
      args: X,
      block: UBlock<T, X>
  ): InternalDelegateProvider<List<Model<*>>>
      where T : QUnionType =
      delegateProvider { inst, _ ->
        UnionStubImpl(obj, args)
            .apply(block)
            .asNotNull()
            .toDelegate(qproperty)
            .asList()
            .bindToContext(inst)
      }

  fun <T, X : ArgumentSpec> newNullableDelegate(
      obj: T,
      args: X,
      block: UBlock<T, X>
  ): InternalDelegateProvider<List<Model<*>?>>
      where T : QUnionType =
      delegateProvider { inst, _ ->
        UnionStubImpl(obj, args)
            .apply(block)
            .toDelegate(qproperty)
            .asNullable()
            .asList()
            .bindToContext(inst)
      }

  companion object {

    /**
     * Don't know why sealed class [UnionListStubImpl] does not smart-cast this to the generic reified argument.
     * TODO this is pretty safe, but should test thoroughly
     */
    internal
    inline fun <reified Z : UnionListStubImpl<out T, out A>, T, A : ArgumentSpec> newStub(unionObject: T)
        : GraphQlPropertyContext.Companion.Builder<Z>
        where T : QUnionType =

        contextBuilder {
          when (Z::class) {
            NoArg::class -> NoArg(unionObject, it)
            NoArg.NotNull::class -> NoArg.NotNull(unionObject, it)
            OptionallyConfigured::class -> OptionallyConfigured<T, A>(unionObject, it)
            OptionallyConfigured.NotNull::class -> OptionallyConfigured.NotNull<T, A>(unionObject, it)
            Configured::class -> Configured<T, A>(unionObject, it)
            Configured.NotNull::class -> Configured.NotNull<T, A>(unionObject, it)
            else -> throw IllegalStateException()
          } as Z
        }
  }
}
