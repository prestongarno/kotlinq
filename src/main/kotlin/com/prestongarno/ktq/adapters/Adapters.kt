package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.Property
import com.prestongarno.ktq.QModel

interface Adapter {

  val property: Property

  val args: Map<String, Any>

  fun onProvideDelegate(inst: QModel<*>)

  fun accept(result: Any?): Boolean

  fun toRawPayload(): String
}


