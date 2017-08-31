package com.prestongarno.ktq

import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance

abstract class QModel<out T: QSchemaType>(of: KClass<T>) {
  protected val model: T = of.objectInstance!!
}

