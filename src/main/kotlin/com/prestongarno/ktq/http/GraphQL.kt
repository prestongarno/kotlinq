package com.prestongarno.ktq.http


object GraphQL {
  /**
   * The entry point for a ktq/GraphQL application*/
  fun initialize(endpoint: String): GraphHttpAdapter = object : GraphHttpAdapter {
    override val endpoint: String = endpoint
    override val authorization: Authorization? = null
    override var timeout: Int = 120
  }
}