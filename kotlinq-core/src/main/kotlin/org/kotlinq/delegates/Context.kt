package org.kotlinq.delegates

import dagger.Module
import org.kotlinq.Model
import org.kotlinq.delegates.GraphQlPropertyStub.Companion.create
import org.kotlinq.dsl.ArgBuilder
import org.kotlinq.dsl.ArgumentSpec
import org.kotlinq.dsl.DslBuilder
import javax.inject.Singleton
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSubclassOf

internal typealias BuilderBlock<Z, A> = DslBuilder<Z, A>.() -> Unit

sealed class GraphQlPropertyStub(val arguments: ArgumentSpec) {


  abstract fun withArguments(arguments: ArgumentSpec): GraphQlPropertyStub

  @Module
  @Singleton
  companion object {

    @Suppress("UNCHECKED_CAST")
    internal fun <T : GraphQlPropertyStub> create(clazz: KClass<T>, propertyName: String, args: ArgumentSpec = ArgBuilder()): T =
        when (clazz) {
          InitializingStub::class -> InitializingStub<Any>(propertyName, args)
          DeserializingStub::class -> DeserializingStub(propertyName, args)
          EnumStub::class -> EnumStub.create(propertyName, args)
          Disjoint::class -> Disjoint<Any>(propertyName, args)
          else -> {
            require(clazz.isSubclassOf(CollectionPropertyStub::class)) { "Unknown class $clazz" }
            CollectionPropertyStub.create(propertyName, args, clazz as KClass<CollectionPropertyStub>)
          }
        } as T
  }

  class Disjoint<in Z>(private val propertyName: String, args: ArgumentSpec = ArgBuilder()) : GraphQlPropertyStub(args) {

    override fun withArguments(arguments: ArgumentSpec): InitializingStub<Z> = TODO()

    operator fun <U : Model<Z>> invoke(init: () -> U): GraphQlPropertyProvider<U?> = initializingProvider(propertyName, init)
  }
}

class PredicateStub<in A : ArgumentSpec, out T : GraphQlPropertyStub>(
    private val name: String,
    private val clazz: KClass<T>
) {
  fun withArguments(arguments: A): T = create(clazz, name, arguments)
}

class InitializingStub<in Z>(
    private val propertyName: String,
    args: ArgumentSpec = ArgBuilder()
) : GraphQlPropertyStub(args) {

  override fun withArguments(arguments: ArgumentSpec): InitializingStub<Z> = InitializingStub(propertyName, arguments)

  operator fun <U : Model<Z>> invoke(
      init: () -> U
  ): GraphQlPropertyProvider<U> =
      initializingProvider(propertyName, init)


}

class DeserializingStub(
    private val propertyName: String,
    args: ArgumentSpec = ArgBuilder()
) : GraphQlPropertyStub(args) {

  override fun withArguments(arguments: ArgumentSpec) = DeserializingStub(propertyName, arguments)

  operator fun <Z> invoke(
      init: (java.io.InputStream) -> Z,
      block: DslBuilder<Z, ArgBuilder>.() -> Unit = {}
  ): GraphQlPropertyProvider<Z> =
      deserializingProvider(propertyName, init).also(block)

}

class EnumStub<Z : Enum<Z>>(
    private val propertyName: String,
    args: ArgumentSpec = ArgBuilder()
) : GraphQlPropertyStub(args), GraphQlPropertyProvider<Z> {

  override fun withArguments(arguments: ArgumentSpec): EnumStub<Z> = EnumStub(propertyName, arguments)

  override operator fun provideDelegate(inst: Model<*>, property: KProperty<*>): ReadOnlyProperty<Any, Z> = TODO()

  companion object {

    enum class None

    internal fun create(
        name: String,
        args: ArgumentSpec = ArgBuilder()
    ): EnumStub<None> = EnumStub(name, args)

  }
}

/**
 * Supports GraphQL stubs for n-dimensional arrays
 *
 * It supports retention of [Model] implementation type information up to a [List] of [List] of [Model]s,
 * any higher than that, then the resulting [List] root type arguments will be erased to [Model] type with the
 * static GraphQL type constraints
 */
sealed class CollectionPropertyStub(
    internal val name: String,
    args: ArgumentSpec = ArgBuilder()
) : GraphQlPropertyStub(args) {

  companion object {

    @Suppress("UNCHECKED_CAST")
    internal fun <T : CollectionPropertyStub> create(name: String, args: ArgumentSpec, clazz: KClass<T>)
        : T = when (clazz) {
      CollectionStub1::class -> CollectionStub1<Any>(name, args)
      CollectionStub2::class -> CollectionStub2<Any>(name, args)
      CollectionStubN::class -> CollectionStubN.from(CollectionStub2<Any>(name, args))
      else -> throw IllegalArgumentException()
    } as T
  }


  internal class Builder<X, T : List<List<List<*>>>>(val token: T) {

    fun asList(): Builder<X, List<T>> = Builder(listOf(token))

    fun build(name: String, args: ArgumentSpec = ArgBuilder())
        : CollectionStubN<X, T> = CollectionStubN.explicit<X, T>(name, ArgBuilder(), this)
  }
}

class CollectionStub1<T>(
    name: String,
    args: ArgumentSpec = ArgBuilder()
) : CollectionPropertyStub(name, args) {

  override fun withArguments(arguments: ArgumentSpec)
      : CollectionStub1<T> = CollectionStub1(name, arguments)

  operator fun <Z : Model<T>> invoke(init: () -> Z)
      : GraphQlPropertyProvider<List<Z>> = collectionProvider(name, init)

  operator fun <Z : Model<T>> invoke(init: () -> Z, block: BuilderBlock<Z, ArgumentSpec>)
      : GraphQlPropertyProvider<List<Z>> = collectionProvider(name, init, block)
}

class CollectionStub2<T>(
    name: String,
    args: ArgumentSpec = ArgBuilder()
) : CollectionPropertyStub(name, args) {

  override fun withArguments(arguments: ArgumentSpec)
      : CollectionStub2<T> = CollectionStub2(name, arguments)

  operator fun <Z : Model<T>> invoke(init: () -> Z)
      : GraphQlPropertyProvider<List<List<Z>>> = collectionProvider(name, init)

  operator fun <Z : Model<T>> invoke(init: () -> Z, block: BuilderBlock<Z, ArgumentSpec>)
      : GraphQlPropertyProvider<List<List<Z>>> = collectionProvider(name, init, block)
}

class CollectionStubN<X, T : List<List<List<*>>>> private constructor(
    name: String,
    args: ArgumentSpec = ArgBuilder(),
    token: CollectionPropertyStub.Builder<*, T>? = null
) : CollectionPropertyStub(name, args) {

  override fun withArguments(arguments: ArgumentSpec)
      : CollectionStub1<T> = CollectionStub1(name, arguments)

  operator fun invoke(init: () -> X)
      // TODO fix this
      : GraphQlPropertyProvider<T> = collectionProvider<T>(name, init as () -> Model<*>)

  operator fun invoke(init: () -> X, block: DslBuilder<X, ArgumentSpec>.() -> Unit)
      // TODO fix this
      : GraphQlPropertyProvider<T> =
      collectionProvider<Model<*>, T>(name, init as () -> Model<*>, block as DslBuilder<Model<*>, ArgumentSpec>.() -> Unit)

  companion object {

    internal
    fun <X> from(ancestor: CollectionStub2<X>)
        : CollectionStubN<X, List<List<List<Model<X>>>>>
        = CollectionStubN(ancestor.name, ancestor.arguments)

    internal
    fun <X, T : List<List<List<*>>>> explicit(
        name: String,
        args: ArgBuilder = ArgBuilder(),
        token: CollectionPropertyStub.Builder<X, T>
    ): CollectionStubN<X, T> = CollectionStubN(name, args, token)
  }
}


private
fun <U : List<*>> collectionProvider(name: String, init: () -> Model<*>): GraphQlPropertyProvider<U> =
    collectionProvider(name, init, { /*nothing */ })

@Suppress("UNCHECKED_CAST")
private
fun <T : Model<*>, U : List<*>> collectionProvider(
    name: String,
    init: () -> T,
    block: DslBuilder<T, ArgumentSpec>.() -> Unit
): GraphQlPropertyProvider<U> =
    initializingProvider(name, init, block as DslBuilder<Model<*>, ArgumentSpec>.() -> Unit)