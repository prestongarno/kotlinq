package org.kotlinq.delegates

import org.kotlinq.Model
import org.kotlinq.dsl.ArgBuilder
import org.kotlinq.dsl.ArgumentSpec
import org.kotlinq.dsl.DslBuilder
import providers.GraphQlPropertyProvider


class CollectionStub1<T>(
    name: String,
    args: ArgumentSpec? = null
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
    args: ArgumentSpec? = null
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
    args: ArgumentSpec? = null,
    token: Builder<*, T>? = null
) : CollectionPropertyStub(name, args) {

  override fun withArguments(arguments: ArgumentSpec)
      : CollectionStub1<T> = CollectionStub1(name, arguments)

  operator fun invoke(init: () -> X)
      // TODO fix this
      : GraphQlPropertyProvider<T> = collectionProvider(name, init as () -> Model<*>)

  operator fun invoke(init: () -> X, block: DslBuilder<X, ArgumentSpec>.() -> Unit)
      : GraphQlPropertyProvider<T> = TODO()

  companion object {

    fun <X> from(ancestor: CollectionStub2<X>)
        : CollectionStubN<X, List<List<List<Model<X>>>>>
        = CollectionStubN(ancestor.name, ancestor.args)

    fun <X, T : List<List<List<*>>>> explicit(
        name: String,
        args: ArgBuilder = ArgBuilder(),
        token: Builder<X, T>
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
): GraphQlPropertyProvider<U> = TODO()
