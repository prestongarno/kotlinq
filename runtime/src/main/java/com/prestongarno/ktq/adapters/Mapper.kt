package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QType
import com.prestongarno.ktq.Tracker
import kotlin.reflect.KProperty

internal abstract class Mapper<T>(
    inst: QType,
    var value: T? = null,
    var property: KProperty<*>?) {
    //var args: ArgBuilder?) {

  constructor(inst: QType) : this(inst, null, null)

  init {
    Tracker.putProperty(inst, this, this.value)
  }

}
