package com.prestongarno.ktq.annotations

import java.lang.annotation.Inherited

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
@Inherited
annotation class Id(val key: String)
