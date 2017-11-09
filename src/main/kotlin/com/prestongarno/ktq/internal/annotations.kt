package com.prestongarno.ktq.internal

import kotlin.reflect.KClass

/**
 * Denotes a class which will be referenced by a
 * GraphQL DSL property. Must be immutable */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class ValueDelegate(val provides: KClass<*>)

/**
 * Denotes a class which will be referenced by a
 * GraphQL DSL property. Must be immutable, and provides a collection */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class CollectionDelegate(val provides: KClass<*>)
