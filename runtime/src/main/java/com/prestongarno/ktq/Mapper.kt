package com.prestongarno.ktq

import kotlin.reflect.KProperty

internal abstract class Mapper<T>(inst: QType, var value: T? = null, var property: KProperty<*>?) {
  constructor(inst: QType) : this(inst, null, null)

  init {
    Tracker.putProperty(inst, this, this.value)
  }

}
