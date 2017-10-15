package com.prestongarno.ktq.adapters

import com.prestongarno.ktq.QProperty

interface Adapter {

  val graphqlProperty: QProperty

  val args: Map<String, Any>

  fun accept(result: Any?): Boolean

  fun toRawPayload(): String
}


