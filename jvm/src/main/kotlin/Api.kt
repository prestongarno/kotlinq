package org.kotlinq.jvm


/**
 * Create a [org.kotlinq.api.Fragment], but typed
 */
inline fun <reified T : Data?> fragment(
    noinline init: (GraphQlResult) -> T,
    noinline block: TypedFragmentScope<T>.() -> Unit = { /* nothing */ }
): ClassFragment<T> = ClassFragment(T::class, init, block)


inline operator fun <reified T : Data?> ((GraphQlResult) -> T).invoke(
    noinline block: TypedFragmentScope<T>.() -> Unit
): ClassFragment<T> = ClassFragment(T::class, this, block)