package com.prestongarno.ktq

import com.prestongarno.ktq.adapters.FieldAdapter
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance

abstract class QModel<out T: QSchemaType>(of: KClass<T>) {
  protected val model: T = of.objectInstance!!

  internal val fields = mutableListOf<FieldAdapter>()
}

