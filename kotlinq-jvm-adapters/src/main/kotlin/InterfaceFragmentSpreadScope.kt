package org.kotlinq.jvm

import org.kotlinq.api.Fragment
import org.kotlinq.api.PropertyInfo
import kotlin.reflect.KClass


class InterfaceFragmentSpreadScope<T : Data?> internal constructor(
    internal val info: PropertyInfo
) {

  private val lookupTable = mutableMapOf<Fragment, (GraphQlResult) -> T>()

  inline fun <reified X : T> on(noinline init: (GraphQlResult) -> X) {
    @Suppress("UNCHECKED_CAST")
    registerReifiedTypeAsFragmentOption(init, X::class as KClass<Data>)
  }

  @PublishedApi internal
  fun <X : T> registerReifiedTypeAsFragmentOption(
      init: (GraphQlResult) -> X,
      clazz: KClass<Data>
  ) = TypedFragment.reflectionFragment(clazz)
      .let { lookupTable[it] = init }
      .ignore()


  internal
  fun build(block: InterfaceFragmentSpreadScope<T>.() -> Unit): FragmentSpread<T> =
      this.apply(block).let {
        FragmentSpread(lookupTable, info)
      }
}

