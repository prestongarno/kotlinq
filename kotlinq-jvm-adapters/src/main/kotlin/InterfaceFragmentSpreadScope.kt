package org.kotlinq.jvm

import org.kotlinq.api.PropertyInfo
import org.kotlinq.jvm.ClassFragment.Companion.fragment
import kotlin.reflect.KClass


@GraphQlDsl
class InterfaceFragmentSpreadScope<T : Data?> internal constructor(internal val info: PropertyInfo) {

  private val fragments = mutableSetOf<ClassFragment<*>>()

  inline fun <reified X : T> on(
      noinline init: (GraphQlResult) -> X,
      noinline block: (TypedFragmentScope<X>.() -> Unit)? = null) {
    @Suppress("UNCHECKED_CAST")
    registerReifiedTypeAsFragmentOption(init, X::class as KClass<Data>, block)
  }

  fun <X : T> on(fragment: ClassFragment<X>) {
    fragments += fragment
  }

  @PublishedApi internal
  fun <X : T> registerReifiedTypeAsFragmentOption(
      init: (GraphQlResult) -> X, clazz: KClass<Data>,
      fragmentBlock: (TypedFragmentScope<X>.() -> Unit)? = null) {

    fragments += fragment(clazz, init, fragmentBlock ?: empty())
  }

  internal
  fun build(block: InterfaceFragmentSpreadScope<T>.() -> Unit): FragmentSpread<T> =
      apply(block).let {
        FragmentSpread<T>(fragments, info)
      }
}

internal fun <T : Data?> empty(): TypedFragmentScope<T>.() -> Unit = { }

