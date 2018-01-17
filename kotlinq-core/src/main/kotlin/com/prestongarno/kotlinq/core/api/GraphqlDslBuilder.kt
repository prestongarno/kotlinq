package com.prestongarno.kotlinq.core.api

import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.ArgumentSpec
import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.adapters.GraphQlField
import com.prestongarno.kotlinq.core.adapters.GraphqlPropertyDelegate
import com.prestongarno.kotlinq.core.internal.empty
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider
import com.prestongarno.kotlinq.core.properties.delegates.DelegateProvider.Companion.delegateProvider
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

typealias ConfiguredProvider<T, A> = NullableStubProvider<GraphqlDslBuilder.ConfiguredContext<T, A>, GraphqlDslBuilder.ConfiguredContext<T?, A>>
typealias ConfiguredListProvider<T, A> = StubProvider<GraphqlDslBuilder.ConfiguredContext<List<T>, A>>
typealias OptionallyConfiguredProvider<T, A> = NullableStubProvider<GraphqlDslBuilder.OptionallyConfiguredContext<T, A>, GraphqlDslBuilder.OptionallyConfiguredContext<T?, A>>
typealias OptionallyConfiguredListProvider<T, A> = StubProvider<GraphqlDslBuilder.OptionallyConfiguredContext<List<T>, A>>
typealias NoArgProvider<T> = NullableStubProvider<GraphqlDslBuilder.NoArgContext<T>, GraphqlDslBuilder.NoArgContext<T?>>
typealias NoArgListProvider<T> = StubProvider<GraphqlDslBuilder.NoArgContext<List<T>>>

typealias DefaultBuilderBlock<T, A> = GraphqlDslBuilder.DefaultBuilder<out T, A>.() -> Unit

internal
typealias DslEvaluationResult<T> = Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>



interface GraphqlDslBuilder<out A : ArgumentSpec> {
  fun config(block: A.() -> Unit)

  interface Context<out T> {
    fun asNullable(): Context<T?>
  }

  interface NoArgContext<T> : Context<T>, DelegateProvider<T> {

    operator fun invoke(arguments: ArgBuilder = ArgBuilder(), block: DefaultBuilderBlock<T, ArgBuilder> = empty())
        : DelegateProvider<T>

    override fun asNullable(): NoArgContext<T?>
  }


  interface OptionallyConfiguredContext<T, A : ArgumentSpec> : ConfiguredContext<T, A>, DelegateProvider<T> {

    operator fun invoke(block: DefaultBuilderBlock<T, ArgBuilder> = empty()): DelegateProvider<T>

    override fun asNullable(): OptionallyConfiguredContext<T?, A>
  }


  interface ConfiguredContext<T, A : ArgumentSpec> : Context<T> {

    operator fun invoke(arguments: A, block: DefaultBuilderBlock<T, A> = empty())
        : DelegateProvider<T>

    override fun asNullable(): ConfiguredContext<T?, A>
  }


  interface DefaultBuilder<T, out A : ArgumentSpec> : GraphqlDslBuilder<A> {

    var default: T?


    companion object {

      internal
      fun <T> noArgContext(
          default: T? = null,
          ctor: (Pair<ArgumentSpec?, DefaultBuilder<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
      ) = GraphQlDelegateContext.NoArg(default, ctor)

      internal
      fun <T, A : ArgumentSpec> optionallyConfiguredContext(
          default: T? = null,
          ctor: (Pair<ArgumentSpec?, DefaultBuilder<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
      ) = GraphQlDelegateContext.OptionallyConfigured<T, A>(default, ctor)

      internal
      fun <T, A : ArgumentSpec> configuredContext(
          default: T? = null,
          ctor: (Pair<ArgumentSpec?, DefaultBuilder<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
      ) = GraphQlDelegateContext.Configured<T, A>(default, ctor)

    }

  }

}

internal
interface DelegateContext<T, A : ArgumentSpec> {
  fun asList(): DelegateContext<List<T>, A>
}


internal sealed
class GraphQlDelegateContext<T, A : ArgumentSpec>(
    val default: T? = null,
    val ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
) : DelegateContext<T, A> {

  abstract override fun asList(): GraphQlDelegateContext<List<T>, A>

  internal
  open class Configured<T, A : ArgumentSpec>(
      default: T? = null,
      ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
  ) : GraphQlDelegateContext<T, A>(default, ctor), GraphqlDslBuilder.ConfiguredContext<T, A> {

    private val nullable by lazy { Nullable() }

    override fun asNullable(): GraphqlDslBuilder.ConfiguredContext<T?, A> = nullable

    override fun asList(): Configured<List<T>, A> = ContextWrapper(this)

    override fun invoke(arguments: A, block: DefaultBuilderBlock<T, A>) = delegateProvider { qmodel, _ ->
      create(default, arguments, block, ctor)
          .bindToContext(qmodel)
    }

    internal
    inner class Nullable : GraphqlDslBuilder.ConfiguredContext<T?, A>, DelegateContext<T, A> {

      override fun asList(): Configured<List<T>, A> = this@Configured.asList()

      override fun asNullable() = this

      override fun invoke(arguments: A, block: DefaultBuilderBlock<T?, A>) = delegateProvider { model, _ ->
        create(default, arguments, block, ctor)
            .asNullable()
            .bindToContext(model)
      }
    }

    private
    class ContextWrapper<T, A : ArgumentSpec>(
        child: GraphQlDelegateContext<T, A>
    ) : GraphQlDelegateContext.Configured<List<T>, A>(emptyList(), ctor = { (args, builder) ->
      child.ctor(args to builder.unwrap()).asList()
    })
  }


  internal
  open class OptionallyConfigured<T, A : ArgumentSpec>(
      default: T? = null,
      ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
  ) : GraphQlDelegateContext<T, A>(default, ctor),
      GraphqlDslBuilder.OptionallyConfiguredContext<T, A>,
      DelegateProvider<T> {

    private val nullable by lazy { Nullable() }

    override fun asNullable(): GraphqlDslBuilder.OptionallyConfiguredContext<T?, A> = nullable

    override fun invoke(block: DefaultBuilderBlock<T, ArgBuilder>) =
        delegateProvider { qmodel, _ -> create(default, ArgBuilder(), block, ctor).bindToContext(qmodel) }

    override fun invoke(arguments: A, block: DefaultBuilderBlock<T, A>) =
        delegateProvider { qmodel, _ -> create(default, arguments, block, ctor).bindToContext(qmodel) }

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) =
        invoke(empty()).provideDelegate(inst, property)

    override fun asList(): OptionallyConfigured<List<T>, A> = object : OptionallyConfigured<List<T>, A>(
        default = emptyList(),
        ctor = { (args, builder) -> ctor(args to builder.unwrap()).asList() }) { /* nothing */ }

    internal
    inner class Nullable : GraphqlDslBuilder.OptionallyConfiguredContext<T?, A>, DelegateContext<T, A> {

      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>): GraphQlField<T?> {
        return invoke(empty())
            .provideDelegate(inst, property)
            .asNullable()
            .bindToContext(inst)
      }

      override fun asList() = this@OptionallyConfigured.asList()

      override fun asNullable() = this

      override fun invoke(block: DefaultBuilderBlock<T?, ArgBuilder>) = delegateProvider { model, _ ->
        create(default, ArgBuilder(), block, ctor)
            .asNullable()
            .bindToContext(model)
      }

      override fun invoke(arguments: A, block: DefaultBuilderBlock<T?, A>) = delegateProvider { model, _ ->
        create(default, arguments, block, ctor)
            .asNullable()
            .bindToContext(model)
      }

    }
  }


  internal
  open class NoArg<T>(
      default: T? = null,
      ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
  ) : GraphQlDelegateContext<T, ArgBuilder>(default, ctor), GraphqlDslBuilder.NoArgContext<T> {

    private val nullable by lazy { Nullable() }
    override fun asNullable(): GraphqlDslBuilder.NoArgContext<T?> = nullable

    override fun invoke(arguments: ArgBuilder, block: DefaultBuilderBlock<T, ArgBuilder>) =
        delegateProvider { qmodel, _ ->
          create(default, arguments, block, ctor)
              .bindToContext(qmodel)
        }

    override fun asList(): GraphQlDelegateContext.NoArg<List<T>> =
        ContextWrapper(this)

    override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) = ArgBuilder().let {
      ctor(it to DefaultBuilderImpl.Builder(it, default))
    }.bindToContext(inst)

    internal
    inner class Nullable : GraphqlDslBuilder.NoArgContext<T?>, DelegateContext<T, ArgBuilder> {

      override fun asList() = this@NoArg.asList()

      override fun asNullable() = this

      override fun invoke(arguments: ArgBuilder, block: DefaultBuilderBlock<T?, ArgBuilder>) =
          delegateProvider { model, _ ->
            create(default, arguments, block, ctor)
                .asNullable()
                .bindToContext(model)
          }


      override fun provideDelegate(inst: QModel<*>, property: KProperty<*>) = ArgBuilder().let {
        ctor(it to DefaultBuilderImpl.Builder(it, default))
      }.asNullable()
          .bindToContext(inst)
    }

    private
    class ContextWrapper<T>(
        child: GraphQlDelegateContext.NoArg<T>
    ) : GraphQlDelegateContext.NoArg<List<T>>(emptyList(), ctor = { (args, builder) ->
      child.ctor(args to builder.unwrap()).asList()
    })
  }

  companion object {
    internal
    fun <T : Any> newBuilder(default: T? = null): Builder<T, ArgBuilder> = Builder.newBuilder(default)
  }

  internal
  class Builder<T, A : ArgumentSpec> private constructor(private val default: T? = null) {

    fun <L : ArgumentSpec> withArgs(): Builder<T, L> = Builder(default)

    infix fun <X : GraphQlDelegateContext<T, A>> takingArguments(init: (Builder<T, A>) -> ArgumentPolicy<T, A, X>) =
        Finalizer(let(init))

    internal
    sealed class ArgumentPolicy<T, A : ArgumentSpec, out U : GraphQlDelegateContext<T, A>>(val builder: Builder<T, A>) {

      abstract fun build(ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>): U

      class Always<T : Any, A : ArgumentSpec>(builder: Builder<T, A>) : ArgumentPolicy<T, A, GraphQlDelegateContext.Configured<T, A>>(builder) {
        override fun build(ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>): Configured<T, A> =
            Configured(builder.default, ctor)
      }

      class Sometimes<T : Any, A : ArgumentSpec>(builder: Builder<T, A>) : ArgumentPolicy<T, A, GraphQlDelegateContext.OptionallyConfigured<T, A>>(builder) {
        override fun build(ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>): OptionallyConfigured<T, A> =
            OptionallyConfigured(builder.default, ctor)
      }

      class Never<T : Any>(builder: Builder<T, ArgBuilder>) : ArgumentPolicy<T, ArgBuilder, GraphQlDelegateContext.NoArg<T>>(builder) {
        override fun build(ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>): NoArg<T> =
            NoArg(builder.default, ctor)
      }
    }

    internal
    class Finalizer<T, A : ArgumentSpec, out U : GraphQlDelegateContext<T, A>>(val policy: ArgumentPolicy<T, A, U>) {
      infix fun resultingIn(ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>): U = policy.build(ctor)
    }


    companion object {

      internal
      fun <T : Any> newBuilder(default: T? = null) = Builder<T, ArgBuilder>(default)

    }
  }

}


internal sealed
class DefaultBuilderImpl<T, out A : ArgumentSpec>(
    val args: A?,
    override var default: T? = null
) : GraphqlDslBuilder.DefaultBuilder<T, A> {

  override fun config(block: A.() -> Unit) = args?.block() ?: Unit

  fun asList(): ListBuilder<T, A> = ListBuilder(this)


  class Builder<T, out A : ArgumentSpec>(args: A?, default: T? = null) : DefaultBuilderImpl<T, A>(args, default)

  class ListBuilder<T, out A : ArgumentSpec>(val child: DefaultBuilderImpl<T, A>)
    : DefaultBuilderImpl<List<T>, A>(child.args, emptyList())
}

private
fun <T, A : ArgumentSpec> DefaultBuilderImpl<List<T>, A>.unwrap(): DefaultBuilderImpl<T, A> =
    (this as? DefaultBuilderImpl.ListBuilder<T, A>)?.child
        ?: DefaultBuilderImpl.Builder<T, A>(args, null)

private
fun <T, A : ArgumentSpec> create(
    default: T? = null,
    arguments: A? = null,
    block: DefaultBuilderImpl<T, A>.() -> Unit = empty(),
    onDelegate: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
) = DefaultBuilderImpl.Builder(arguments, default).let {
  onDelegate(arguments to it.apply(block))
}

internal
fun <T : Any> DefaultBuilderImpl.Builder<T, ArgBuilder>.toContext(
    ctor: (Pair<ArgumentSpec?, DefaultBuilderImpl<T, ArgumentSpec>>) -> GraphqlPropertyDelegate<T>
) = GraphQlDelegateContext.NoArg(default, ctor)


internal infix
fun <T> GraphQlDelegateContext.NoArg<T>.providingInstead(clazz: KClass<List<*>>) = asList()

internal infix
fun <T, A : ArgumentSpec> GraphQlDelegateContext.OptionallyConfigured<T, A>.providingInstead(clazz: KClass<List<*>>) = asList()

internal infix
fun <T, A : ArgumentSpec> GraphQlDelegateContext.Configured<T, A>.providingInstead(clazz: KClass<List<*>>) = asList()

internal infix
fun <T> GraphQlDelegateContext.NoArg<T>.allowing(clazz: KClass<Nothing>) = asNullable()

internal infix
fun <T, A : ArgumentSpec> GraphQlDelegateContext.OptionallyConfigured<T, A>.allowing(clazz: KClass<Nothing>) = asNullable()

internal infix
fun <T, A : ArgumentSpec> GraphQlDelegateContext.Configured<T, A>.allowing(clazz: KClass<Nothing>) = asNullable()

