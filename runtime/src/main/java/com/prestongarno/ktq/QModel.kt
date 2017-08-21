package com.prestongarno.ktq

import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance

abstract class QModel<out T: QType>(of: KClass<T>) {
  protected val model: T = of.objectInstance!!
}

