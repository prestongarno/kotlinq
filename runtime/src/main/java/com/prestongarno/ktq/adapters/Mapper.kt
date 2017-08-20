package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.*
import kotlin.reflect.KProperty

internal abstract class Mapper<T>(
    inst: QType,
    var value: T? = null,
    var property: KProperty<*>?,
    private var args: ArgBuilder<*>?) {

  fun <A : ArgBuilder<T>> getArgBuilder() : A {
    if(args == null)
      args = Payload<Stub<T, A>>(this as Stub<*, *>)
    @Suppress("UNCHECKED_CAST") return args as A
  }

  constructor(inst: QType, args: ArgBuilder<T>?) : this(inst, null, null, args)

  init {
    Tracker.putProperty(inst, this, this.value)
  }

}
