package com.prestongarno.ktq.http

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.http.internal.Http4k

interface GraphHttpAdapter {

  val endpoint: String

  var authorization: Authorization?

  fun <T : QModel<*>> createRequest(`for`: (() -> T)): GraphQlRequest<T> = RequestBuilder(this, `for`)
}

/**
 * A wrapper class for a GraphQL request */
interface GraphQlRequest<T : QModel<*>> {
  fun onSuccess(handler: (T) -> Unit): GraphQlRequest<T>

  fun onError(handler: (errorCode: Int, message: String) -> Unit): GraphQlRequest<T>

  suspend fun execute()
}

internal class RequestBuilder<T : QModel<*>>(
    internal val adapter: GraphHttpAdapter,
    internal val `for`: (() -> T)) : GraphQlRequest<T> {

  internal var successHandler: ((T) -> Unit)? = null

  internal var errorHandler: ((errorCode: Int, message: String) -> Unit)? = null

  override fun onSuccess(handler: (T) -> Unit) = apply { successHandler = handler }

  override fun onError(handler: (errorCode: Int, message: String) -> Unit) = apply { errorHandler = handler }

  override suspend fun execute() = Http4k.send(this)
}

