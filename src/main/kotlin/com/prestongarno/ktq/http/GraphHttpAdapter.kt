package com.prestongarno.ktq.http

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.http.internal.Http4k
import com.prestongarno.ktq.http.internal.RequestType

interface GraphHttpAdapter {

  val endpoint: String

  var authorization: Authorization?

  fun <T : QModel<*>> query(`for`: (() -> T)): GraphQlRequest<T> = RequestBuilder(RequestType.QUERY, this, `for`)

  fun <T : QModel<*>> mutate(`for`: (() -> T)): GraphQlRequest<T> = RequestBuilder(RequestType.MUTATION, this, `for`)
}

/**
 * A wrapper class for a GraphQL request */
interface GraphQlRequest<T : QModel<*>> {
  fun onSuccess(handler: (T) -> Unit): GraphQlRequest<T>

  fun onError(handler: (errorCode: Int, message: String) -> Unit): GraphQlRequest<T>

  suspend fun run(): T

  fun runAsync()
}

internal class RequestBuilder<T : QModel<*>>(
    internal val type: RequestType,
    internal val adapter: GraphHttpAdapter,
    internal val `for`: (() -> T)) : GraphQlRequest<T> {

  internal var successHandler: ((T) -> Unit)? = null

  internal var errorHandler: ((errorCode: Int, message: String) -> Unit)? = null

  override fun onSuccess(handler: (T) -> Unit) = apply { successHandler = handler }

  override fun onError(handler: (errorCode: Int, message: String) -> Unit) = apply { errorHandler = handler }

  override suspend fun run() = Http4k.send(this)

  override fun runAsync() {
    throw UnsupportedOperationException("wut")
  }
}

