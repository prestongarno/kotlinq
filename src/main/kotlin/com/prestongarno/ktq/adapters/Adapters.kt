package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.Property
import com.prestongarno.ktq.QModel
import kotlin.reflect.KProperty

interface Adapter {

  val property: Property

  val args: Map<String, Any>

  fun onDelegate(inst: QModel<*>, property: KProperty<*>)

  fun accept(result: Any?): Boolean

  fun toRawPayload(): String
}


