package org.kotlinq.delegates

import org.kotlinq.Model
import org.kotlinq.dsl.ArgBuilder
import org.kotlinq.dsl.ArgumentSpec
import org.kotlinq.dsl.DslBuilder
import org.kotlinq.providers.GraphQlPropertyProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSubclassOf

typealias BuilderBlock<Z, A> = DslBuilder<Z, A>.() -> Unit

sealed class GraphQlPropertyStub {


  abstract fun withArguments(arguments: ArgumentSpec): GraphQlPropertyStub

  // TODO Module
  companion object {

    @Suppress("UNCHECKED_CAST")
    fun <T : GraphQlPropertyStub> create(clazz: KClass<T>, propertyName: String, args: ArgumentSpec = ArgBuilder()): T =
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

  class Disjoint<in Z>(private val propertyName: String, args: ArgumentSpec = ArgBuilder()) : GraphQlPropertyStub() {

    override fun withArguments(arguments: ArgumentSpec): InitializingStub<Z> = TODO()

    operator fun <U : Model<Z>> invoke(init: () -> U): GraphQlPropertyProvider<U?> = TODO()
  }
}

class InitializingStub<in Z>(
    private val propertyName: String,
    args: ArgumentSpec? = null
) : GraphQlPropertyStub() {

  override fun withArguments(arguments: ArgumentSpec): InitializingStub<Z> =
      InitializingStub(propertyName, arguments)

  operator fun <U : Model<Z>> invoke(
      init: () -> U
  ): GraphQlPropertyProvider<U> = TODO()


}

class DeserializingStub(
    private val propertyName: String,
    args: ArgumentSpec? = null
) : GraphQlPropertyStub() {

  override fun withArguments(arguments: ArgumentSpec) = DeserializingStub(propertyName, arguments)

  operator fun <Z> invoke(
      init: (java.io.InputStream) -> Z,
      block: DslBuilder<Z, ArgBuilder>.() -> Unit = {}
  ): GraphQlPropertyProvider<Z> = TODO()

}

class EnumStub<Z : Enum<Z>>(
    private val propertyName: String,
    args: ArgumentSpec? = null
) : GraphQlPropertyStub(), GraphQlPropertyProvider<Z> {

  override fun withArguments(arguments: ArgumentSpec): EnumStub<Z> = EnumStub(propertyName, arguments)

  override operator fun provideDelegate(inst: Model<*>, property: KProperty<*>): ReadOnlyProperty<Any, Z> = TODO()

  companion object {

    enum class None

    fun create(
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
abstract
class CollectionPropertyStub(
    val name: String,
    val args: ArgumentSpec? = null
) : GraphQlPropertyStub() {

  companion object {

    // TODO module
    fun <T : CollectionPropertyStub> create(name: String, args: ArgumentSpec, clazz: KClass<T>)
        : T = when (clazz) {
      CollectionStub1::class -> CollectionStub1<Any>(name, args)
      CollectionStub2::class -> CollectionStub2<Any>(name, args)
      CollectionStubN::class -> CollectionStubN.from(CollectionStub2<Any>(name, args))
      else -> throw IllegalArgumentException()
    } as T
  }


  class Builder<X, T : List<List<List<*>>>>(val token: T) {

    fun asList(): Builder<X, List<T>> = Builder(listOf(token))

    fun build(name: String, args: ArgumentSpec = ArgBuilder())
        : CollectionStubN<X, T> = CollectionStubN.explicit(name, ArgBuilder(), this)
  }
}

