package org.kotlinq.jvm

internal
infix fun <T : Any, U : Any> T.with(other: U?): Pair<T, U>? =
    other?.let { this to it }

internal
fun <T> use(value: T, block: T.() -> Unit) = value.apply(block)

internal
fun <T : Any, R : Any, Z> T.useWith(value: R, block: T.(R) -> Z): Z = this.block(value)

internal
fun Any?.ignore() = Unit

internal
fun <T> List<T>.contains(predicate: (T) -> Boolean) = find(predicate) != null

internal
fun <T> List<T>.none(predicate: (T) -> Boolean) = find(predicate) == null