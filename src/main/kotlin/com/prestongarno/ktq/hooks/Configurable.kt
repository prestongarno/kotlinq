package com.prestongarno.ktq.hooks

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.DelegateProvider
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.SchemaStub
import com.prestongarno.ktq.TypeStub
import com.prestongarno.ktq.adapters.QField
import com.prestongarno.ktq.adapters.applyNotNull
import kotlin.reflect.KProperty

/**
 * Represents an intermediate object that enforces passing of an [ArgBuilder]
 * instance to the object. This is for GraphQL fields with one or more non-null
 * arguments specified in the schema
 *
 *
 * This interface enforces passing of arguments by not having the standard operator
 * function like [OptionalConfig.provideDelegate]
 *
 *
 * @param D : The type of SchemaStub which will provide a delegate type <T>
 * @param A : The type of ArgBuilder which configures on this field
 */
interface Configurable<out D : DelegateProvider<*>, in A: ArgBuilder> : SchemaStub {
  operator fun invoke(arguments: A, scope: (D.() -> Unit)? = null): D

  companion object {
    fun <T : DelegateProvider<*>, A : ArgBuilder> new(
        constructor: (A) -> T
    ): Configurable<T, A> = DefaultConfigurable(constructor)
  }
}

/**
 * Represents an intermediate object that optionally allows invoking by passing
 * an [ArgBuilder] instance to the object. This is for GraphQL fields with
 * exactly zero non-null arguments specified for this field, but with one or
 * more arguments/parameters specified
 * @param D : The type of [DelegateProvider] which this field supplies
 * @param A : The type of ArgBuilder which configures on this field
 */
interface OptionalConfig<out D : DelegateProvider<T>, out T : Any?, in A: ArgBuilder> : SchemaStub {

  operator fun invoke(arguments: A, scope: (D.() -> Unit)? = null): D

  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T>

  companion object {
    fun <D : DelegateProvider<T>, T : Any?, A : ArgBuilder> new(
        constructor: (A?) -> D
    ): OptionalConfig<D, T, A> = DefaultOptionalConfig(constructor)
  }
}

/**
 * Represents an intermediate object that optionally allows invoking by passing
 * an [ArgBuilder] instance to the object. This is for GraphQL fields with
 * exactly zero non-null arguments specified for this field, and zero arguments/parameters specified.
 * This interface exists to support adding arguments arbitrarily to GraphQL queries/mutations
 * @param D : The type of [DelegateProvider] which this field supplies
 */
interface NoArgConfig<out D : DelegateProvider<T>, out T : Any?> : SchemaStub {

  operator fun invoke(arguments: ArgBuilder? = ArgBuilder(), scope: (D.() -> Unit)? = null): D

  operator fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      invoke().provideDelegate(inst, property)

  companion object {
    fun <D : DelegateProvider<T>, T : Any?> new(
        constructor: (ArgBuilder?) -> D
    ): NoArgConfig<D, T> = DefaultNoArgConfig(constructor)
  }
}

/**
 * Default [Configurable] class for dynamic class-level delegation by schema stub types
 * @param T : The type of [DelegateProvider] that this function returns
 * @param A : The type of [ArgBuilder] that this DelegateProvider takes
 * @param constructor a function that constructs a new [DelegateProvider]
 */
private class DefaultConfigurable<out T : DelegateProvider<*>, in A : ArgBuilder>(
    private val constructor: (A) -> T
) : Configurable<T, A> {

  override operator fun invoke(arguments: A, scope: (T.() -> Unit)?): T =
      constructor.invoke(arguments).applyNotNull(scope)
}

/**
 * Default [OptionalConfig] class for dynamic class-level delegation by schema stub types
 * @param T : The type of [DelegateProvider] that this function returns
 * @param A : The type of [ArgBuilder] that this DelegateProvider takes
 * @param constructor a function that constructs a new [DelegateProvider]
 */
private class DefaultOptionalConfig<out D : DelegateProvider<T>, out T : Any?, in A : ArgBuilder>(
    private val constructor: (A?) -> D
) : OptionalConfig<D, T, A> {

  override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): QField<T> =
      constructor(null).provideDelegate(inst, property)

  override fun invoke(arguments: A, scope: (D.() -> Unit)?): D =
      constructor(arguments).applyNotNull(scope)
}

private class DefaultNoArgConfig<out D : DelegateProvider<T>, out T : Any?>(
    private val constructor: (ArgBuilder?) -> D
) : NoArgConfig<D, T> {

  override fun invoke(arguments: ArgBuilder?, scope: (D.() -> Unit)?): D =
      constructor(arguments).applyNotNull(scope)
}
