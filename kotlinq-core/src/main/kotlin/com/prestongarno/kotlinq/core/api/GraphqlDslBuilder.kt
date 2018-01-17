package com.prestongarno.kotlinq.core.api

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider

typealias ConfiguredProvider<T, A> = NullableStubProvider<GraphqlDslBuilder.ConfiguredContext<T, A>, GraphqlDslBuilder.ConfiguredContext<T?, A>>
typealias OptionallyConfiguredProvider<T, A> = NullableStubProvider<GraphqlDslBuilder.OptionallyConfiguredContext<T, A>, GraphqlDslBuilder.OptionallyConfiguredContext<T?, A>>
typealias NoArgProvider<T> = NullableStubProvider<GraphqlDslBuilder.NoArgContext<T>, GraphqlDslBuilder.NoArgContext<T?>>

typealias DefaultBuilderBlock<T, A> = GraphqlDslBuilder.DefaultBuilder<T, A>.() -> Unit

interface GraphqlDslBuilder<out A : ArgumentSpec> {
  fun config(block: A.() -> Unit)


  /**
   * Generic context for a type with no [ArgumentSpec] type (i.e. no arguments required)
   * @param T the resulting type that this delegate provides
   */
  interface NoArgContext<T> {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: DefaultBuilderBlock<T, ArgBuilder> = empty())
        : DelegateProvider<T>
  }

  interface OptionallyConfiguredContext<T, A : ArgumentSpec> : ConfiguredContext<T, A> {
    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: DefaultBuilderBlock<T, ArgBuilder> = empty())
        : DelegateProvider<T>
  }

  interface ConfiguredContext<T, A : ArgumentSpec> {
    operator fun invoke(arguments: A, block: DefaultBuilderBlock<T, A> = empty())
        : DelegateProvider<T>
  }


  interface DefaultBuilder<T, out A : ArgumentSpec> : GraphqlDslBuilder<A> {

    var default: T?


    companion object {

      internal
      fun <T> noArgContext(
          default: T? = null,
          ctor: (ArgBuilder?, DefaultBuilder<T, ArgBuilder>) -> GraphqlPropertyDelegate<T>
      ) = object : NoArgContext<T> {
        override fun invoke(arguments: ArgBuilder, block: DefaultBuilderBlock<T, ArgBuilder>) = delegateProvider { qmodel, _ ->
          create(default, arguments, block, ctor).bindToContext(qmodel)
        }
      }

      internal
      fun <T, A : ArgumentSpec> optionallyConfiguredContext(
          default: T? = null,
          ctor: (ArgumentSpec?, DefaultBuilder<T, ArgumentSpec>) -> GraphqlPropertyDelegate<T>
      ) = object : OptionallyConfiguredContext<T, A> {

        override fun invoke(arguments: ArgBuilder, block: DefaultBuilderBlock<T, ArgBuilder>) = delegateProvider { model, _ ->
          create(default, arguments, block, ctor).bindToContext(model)
        }

        override fun invoke(arguments: A, block: DefaultBuilderBlock<T, A>) = delegateProvider { model, _ ->
          create(default, arguments, block, ctor).bindToContext(model)
        }

      }

      internal
      fun <T, A : ArgumentSpec> configuredContext(
          default: T? = null,
          ctor: (A?, DefaultBuilder<T, A>) -> GraphqlPropertyDelegate<T>
      ) = GraphQlDelegateContext.Configured(default, ctor)

    }

  }

}

internal
interface DelegateContext<T, A : ArgumentSpec> {
  fun asList(): DelegateContext<List<T>, A>
}

internal sealed
class DefaultBuilderImpl<T, out A : ArgumentSpec>(val args: A?, override var default: T? = null) : GraphqlDslBuilder.DefaultBuilder<T, A> {

  override fun config(block: A.() -> Unit) = args?.block() ?: Unit

  fun asList(): ListBuilder<T, A> = ListBuilder(this)

  class Builder<T, out A : ArgumentSpec>(args: A?, default: T? = null) : DefaultBuilderImpl<T, A>(args, default)

  class ListBuilder<T, out A : ArgumentSpec>(val child: DefaultBuilderImpl<T, A>)
    : DefaultBuilderImpl<List<T>, A>(child.args, emptyList())
}

internal sealed
class GraphQlDelegateContext<T, A : ArgumentSpec>(
    val default: T? = null,
    val ctor: (A?, DefaultBuilderImpl<T, A>) -> GraphqlPropertyDelegate<T>
) : DelegateContext<T, A> {

  internal
  class Configured<T, A : ArgumentSpec>(
      default: T? = null,
      ctor: (A?, DefaultBuilderImpl<T, A>) -> GraphqlPropertyDelegate<T>
  ) : GraphQlDelegateContext<T, A>(default, ctor), GraphqlDslBuilder.ConfiguredContext<T, A> {

    override fun asList(): DelegateContext<List<T>, A> = ListContextWrapper(this)

    override fun invoke(arguments: A, block: DefaultBuilderBlock<T, A>) =
        delegateProvider { qmodel, _ -> create(default, arguments, block, ctor).bindToContext(qmodel) }
  }

  internal
  class ListContextWrapper<T, A : ArgumentSpec>(
      child: GraphQlDelegateContext<T, A>
  ): GraphQlDelegateContext<List<T>, A>(emptyList(), ctor = { args, builder ->
    child.ctor(args, ((builder as DefaultBuilderImpl.ListBuilder<List<T>, A>).child as DefaultBuilderImpl<T, A>)).asList()
  }) {

    override fun asList(): DelegateContext<List<List<T>>, A> = ListContextWrapper(this)
  }

}

private
fun <T, A : ArgumentSpec> create(
    default: T? = null,
    arguments: A? = null,
    block: DefaultBuilderImpl<T, A>.() -> Unit = empty(),
    onDelegate: (A?, DefaultBuilderImpl<T, A>) -> GraphqlPropertyDelegate<T>
) = DefaultBuilderImpl.Builder(arguments, default).apply(block).let({ onDelegate(arguments, it) })

