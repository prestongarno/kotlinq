package org.kotlinq.jvm.annotations

/**
 * Annotate properties which should be ignored when members of a GraphQl Typed Fragment
 */
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Ignore
