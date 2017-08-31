package com.prestongarno.ktq

import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance

abstract class QModel<out T: QSchemaType>(of: KClass<T>) {
  protected val model: T = of.objectInstance!!
  // TODO => add data structure so that per-instance we can store which fields, etc are in a model
}

