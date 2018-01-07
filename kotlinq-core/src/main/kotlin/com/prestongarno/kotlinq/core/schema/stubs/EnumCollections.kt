package com.prestongarno.kotlinq.core.schema.stubs


import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.adapters.EnumAdapterImpl
import com.prestongarno.kotlinq.core.properties.GraphQlProperty
import com.prestongarno.kotlinq.core.properties.GraphQlPropertyContext
import com.prestongarno.kotlinq.core.properties.contextBuilder
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.InternalDelegateProvider
import com.prestongarno.kotlinq.core.schema.QEnumType
import kotlin.reflect.KClass

typealias EBlock<T, A> = EnumStub<T, A>.() -> Unit

/**
 * This is an interface with nested sub-interfaces
 * which are required in order to retain exact type information/avoid erasure from
 * passing an initializer for the GraphQL type query implementation for this field
 */
interface EnumListStub {

  interface NoArg<T> : EnumListStub where T : QEnumType, T : Enum<T> {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: EBlock<T, ArgBuilder>): DelegateProvider<List<T?>>

    interface NotNull<T> where T : QEnumType, T : Enum<T> {
      operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: EBlock<T, ArgBuilder>): DelegateProvider<List<T>>
    }

  }

  interface OptionallyConfigured<T, A : ArgumentSpec> : EnumListStub, Configured<T, A> where T : QEnumType, T : Enum<T> {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: EBlock<T, ArgBuilder>): DelegateProvider<List<T?>>

    interface NotNull<T, A : ArgumentSpec> : Configured.NotNull<T, A> where T : QEnumType, T : Enum<T> {
      operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: EBlock<T, ArgBuilder>): DelegateProvider<List<T>>
    }
  }

  interface Configured<T, A : ArgumentSpec> : EnumListStub where T : QEnumType, T : Enum<T> {

    operator fun invoke(arguments: A, block: EBlock<T, A>): DelegateProvider<List<T?>>

    interface NotNull<T, A : ArgumentSpec> where T : QEnumType, T : Enum<T> {
      operator fun invoke(arguments: A, block: EBlock<T, A>): DelegateProvider<List<T>>
    }
  }

}

internal
sealed class EnumListStubImpl<T, A>(val clazz: KClass<T>, val qproperty: GraphQlProperty)
    where T : QEnumType, T : Enum<T>, A : ArgumentSpec {

  internal
  class NoArg<T>(clazz: KClass<T>, qprop: GraphQlProperty) : EnumListStubImpl<T, ArgBuilder>(clazz, qprop), EnumListStub.NoArg<T> where T : QEnumType, T : Enum<T> {

    override fun invoke(arguments: ArgBuilder, block: EBlock<T, ArgBuilder>)
        : InternalDelegateProvider<List<T?>> =
        newNullableDelegate(clazz, arguments, block)


    internal
    class NotNull<T>(clazz: KClass<T>, qprop: GraphQlProperty)
      : EnumListStubImpl<T, ArgBuilder>(clazz, qprop),
        EnumListStub.NoArg.NotNull<T>
        where T : QEnumType,
              T : Enum<T> {

      override fun invoke(arguments: ArgBuilder, block: EBlock<T, ArgBuilder>)
          : DelegateProvider<List<T>> =
          newDelegate(clazz, arguments, block)
    }
  }

  internal
  class OptionallyConfigured<T, A : ArgumentSpec>(clazz: KClass<T>, qprop: GraphQlProperty)
    : EnumListStubImpl<T, A>(clazz, qprop),
      EnumListStub.OptionallyConfigured<T, A>
      where T : QEnumType,
            T : Enum<T> {
    override fun invoke(arguments: ArgBuilder, block: EBlock<T, ArgBuilder>)
        : DelegateProvider<List<T?>> =
        newNullableDelegate(clazz, arguments, block)

    override fun invoke(arguments: A, block: EBlock<T, A>)
        : DelegateProvider<List<T?>> =
        newNullableDelegate(clazz, arguments, block)


    internal
    class NotNull<T, A : ArgumentSpec>(clazz: KClass<T>, qprop: GraphQlProperty)
      : EnumListStubImpl<T, A>(clazz, qprop),
        EnumListStub.OptionallyConfigured.NotNull<T, A>
        where T : QEnumType,
              T : Enum<T> {

      override fun invoke(arguments: ArgBuilder, block: EBlock<T, ArgBuilder>)
          : DelegateProvider<List<T>> =
          newDelegate(clazz, arguments, block)

      override fun invoke(arguments: A, block: EBlock<T, A>)
          : DelegateProvider<List<T>> =
          newDelegate(clazz, arguments, block)
    }
  }

  internal
  class Configured<T, A : ArgumentSpec>(clazz: KClass<T>, qprop: GraphQlProperty)
    : EnumListStubImpl<T, A>(clazz, qprop),
      EnumListStub.Configured<T, A>
      where T : QEnumType,
            T : Enum<T> {

    override fun invoke(arguments: A, block: EBlock<T, A>)
        : DelegateProvider<List<T?>> =
        newNullableDelegate(clazz, arguments, block)


    internal
    class NotNull<T, A : ArgumentSpec>(clazz: KClass<T>, qprop: GraphQlProperty)
      : EnumListStubImpl<T, A>(clazz, qprop),
        EnumListStub.Configured.NotNull<T, A>
        where T : QEnumType,
              T : Enum<T> {

      override fun invoke(arguments: A, block: EBlock<T, A>)
          : DelegateProvider<List<T>> =
          newDelegate(clazz, arguments, block)
    }
  }

  fun <T, X : ArgumentSpec> newDelegate(
      clazz: KClass<T>,
      args: X,
      block: EBlock<T, X>
  ): InternalDelegateProvider<List<T>>
      where T : QEnumType,
            T : Enum<T>
      = delegateProvider { inst, _ ->
    EnumAdapterImpl(clazz, args)
        .apply(block)
        .toDelegate(qproperty)
        .asList()
        .bindToContext(inst)
  }

  fun <T, X : ArgumentSpec> newNullableDelegate(
      clazz: KClass<T>,
      args: X,
      block: EBlock<T, X>
  ): InternalDelegateProvider<List<T?>>
      where T : QEnumType,
            T : Enum<T>
      = delegateProvider { inst, _ ->
    EnumAdapterImpl(clazz, args)
        .apply(block)
        .toDelegate(qproperty)
        .asNullable()
        .asList()
        .bindToContext(inst)
  }

  companion object {

    /**
     * Don't know why sealed class [EnumListStubImpl] does not smart-cast this to the generic reified argument.
     * TODO this is pretty safe, but should test thoroughly
     */
    internal
    inline fun <reified Z : EnumListStubImpl<out T, out A>, T, A : ArgumentSpec> newStub(clazz: KClass<T>)
        : GraphQlPropertyContext.Companion.Builder<Z>
        where T : QEnumType, T : Enum<T> =

        contextBuilder {
          when (Z::class) {
            NoArg::class -> NoArg(clazz, it)
            NoArg.NotNull::class -> NoArg.NotNull<T>(clazz, it)
            OptionallyConfigured::class -> OptionallyConfigured<T, A>(clazz, it)
            OptionallyConfigured.NotNull::class -> OptionallyConfigured.NotNull<T, A>(clazz, it)
            Configured::class -> Configured<T, A>(clazz, it)
            Configured.NotNull::class -> Configured.NotNull<T, A>(clazz, it)
            else -> throw IllegalStateException()
          } as Z
        }
  }
}

