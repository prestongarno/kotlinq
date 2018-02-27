package org.kotlinq.jvm

import org.kotlinq.api.Fragment
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1


class InterfaceFragmentSpread<in T : Data?> internal constructor(
    private val property: KProperty1<*, T>) {

  private val fragments = mutableSetOf<Fragment>()

  inline fun <reified X : T> on() {
    @Suppress("UNCHECKED_CAST") // WHY THE CAST WARNING THOUGH?
    registerReifiedTypeAsFragmentOption(X::class as KClass<Data>)
  }

  @PublishedApi
  internal fun registerReifiedTypeAsFragmentOption(clazz: KClass<Data>) =
    TypedFragment.reflectionFragment(clazz)
        .let(fragments::add)
        .ignore()


  internal fun build(block: InterfaceFragmentSpread<T>.() -> Unit): Set<Fragment> {
    this.block()
    return fragments.toSet()
  }
}

