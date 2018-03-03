package org.kotlinq.test

import com.google.common.truth.Subject
import kotlin.reflect.KClass


val KClass<*>.name get() = simpleName!!

inline fun <reified T> Subject<*, *>.isInstanceOf() =
    isInstanceOf(T::class.java)

inline fun <reified T> Subject<*, *>.isNotInstanceOf() =
    isNotInstanceOf(T::class.java)
