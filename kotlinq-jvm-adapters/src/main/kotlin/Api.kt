package org.kotlinq.jvm


/**
 * Create a [org.kotlinq.api.Fragment], but types
 */
inline fun <reified T : Data?> fragment(
    noinline init: (GraphQlResult) -> T,
    noinline block: TypedFragmentScope<T>.() -> Unit = { /* nothing */ }
) = ClassFragment(T::class, init, block)
