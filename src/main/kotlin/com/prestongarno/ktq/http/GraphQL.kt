package com.prestongarno.ktq.http

import com.prestongarno.ktq.QModel
import java.util.*


object GraphQL {
  /**
   * The entry point for a ktq/GraphQL application*/
  fun initialize(endpoint: String): GraphHttpAdapter = GraphHttpImpl(endpoint)
}

internal class GraphHttpImpl(override val endpoint: String) : GraphHttpAdapter {
  override var authorization: Authorization? = null
}

