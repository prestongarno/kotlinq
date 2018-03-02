package org.kotlinq.jvm

infix fun <T : Any, U : Any> T.with(other: U?): Pair<T, U>? =
    other?.let { this to it }

fun <T> use(value: T, block: T.() -> Unit) = value.apply(block)

fun <T : Any, R : Any, Z> T.useWith(value: R, block: T.(R) -> Z): Z = this.block(value)

fun Any?.ignore() = Unit

fun <T> List<T>.contains(predicate: (T) -> Boolean) = find(predicate) != null


fun <T> List<T>.none(predicate: (T) -> Boolean) = find(predicate) == null