package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.QModel

interface Adapter {

  val graphqlName: String

  val args: Map<String, Any>

  fun onProvideDelegate(inst: QModel<*>) { inst.fields.add(this) }

  fun accept(result: Any?): Boolean

  fun toRawPayload(): String
}


