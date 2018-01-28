package org.kotlinq.static

import org.kotlinq.Model
import org.kotlinq.delegates.CollectionPropertyStub
import org.kotlinq.delegates.CollectionStub1
import org.kotlinq.delegates.CollectionStub2
import org.kotlinq.delegates.CollectionStubN
import org.kotlinq.delegates.GraphQlPropertyStub
import org.kotlinq.delegates.InitializingStub
import org.kotlinq.dsl.ArgBuilder
import org.kotlinq.dsl.ArgumentSpec
import org.kotlinq.static.PredicateProvider.Companion.using
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


class ContextBuilder<T : Any, out A : ArgumentSpec, S : GraphQlPropertyStub> {

  fun <B : ArgumentSpec> requiringArguments(): ConfiguredContextBuilder<InitializingStub<T>, B> =
      buildWithArguments(InitializingStub::class)

  fun asList(): CollectionPropertyBuilder<T> =
      CollectionPropertyBuilder()

  fun asNullable(): NullableTypeProvider<T> = NullableTypeProvider()

  companion object {
    fun <T : Any> schema(): ContextBuilder<T, ArgumentSpec, GraphQlPropertyStub> = ContextBuilder()
  }
}

class ConfiguredContextBuilder<out T : GraphQlPropertyStub, in A : ArgumentSpec>(private val clazz: KClass<T>) {
  fun build(): PredicateProvider<A, T> = using<A>().build(clazz)
}


class CollectionPropertyBuilder<Z> internal constructor() {

  fun <A : ArgumentSpec> requiringArguments()
      : ConfiguredContextBuilder<CollectionStub1<Z>, A> =
      buildWithArguments(CollectionStub1::class)

  fun asList() = Nested(this)

  fun build(): Provider<CollectionStub1<Z>> = Provider.provideCollection(this)


  /* Builder for a property delegate returning a property providing a nested list */
  class Nested<Z> internal constructor(context: CollectionPropertyBuilder<Z>) {

    fun asList() = MultiDimensional.from(this)

    fun build(): Provider<CollectionStub2<Z>> = object : Provider<CollectionStub2<Z>> {
      override fun provideDelegate(inst: Any, property: KProperty<*>) =
          readOnly(CollectionStub2<Z>(property.name, ArgBuilder()))
    }

    fun <A : ArgumentSpec> requiringArguments()
        : ConfiguredContextBuilder<CollectionStub2<Z>, A> =
        buildWithArguments(CollectionStub2::class)
  }

  class MultiDimensional<Z, T : List<List<List<*>>>> internal constructor(val builder: CollectionPropertyStub.Builder<Z, T>) {

    fun asList() = MultiDimensional(builder.asList())

    fun build(): Provider<CollectionStubN<Z, T>> = object : Provider<CollectionStubN<Z, T>> {
      override fun provideDelegate(inst: Any, property: KProperty<*>) =
          readOnly(builder.build(property.name))
    }

    fun <A : ArgumentSpec> requiringArguments()
        : ConfiguredContextBuilder<CollectionStubN<Z, List<List<List<Model<Z>>>>>, A> =
        buildWithArguments(CollectionStub2::class)

    companion object {
      internal fun <Z> from(nestedBuilder: Nested<Z>) = MultiDimensional(
          CollectionPropertyStub.Builder<Model<Z>, List<List<List<Model<Z>>>>>(listOf()))
    }
  }

}

class NullableTypeProvider<in T : Any> {
  fun <A : ArgumentSpec> requiringArguments(): ConfiguredContextBuilder<GraphQlPropertyStub.Disjoint<T>, A> = TODO()
  fun build(): GraphQlPropertyStub.Disjoint<T> = TODO()
}


@Suppress("UNCHECKED_CAST")
internal
fun <T : GraphQlPropertyStub, A : ArgumentSpec> buildWithArguments(clazz: KClass<out GraphQlPropertyStub>)
    : ConfiguredContextBuilder<T, A> =
    ConfiguredContextBuilder(clazz as KClass<T>)
