package com.prestongarno.ktq.http

import com.prestongarno.ktq.QModel

interface GraphHttpAdapter {

  val endpoint: String

  fun <T: QModel<*>> createRequest(`for`: (() -> T)): GraphQlRequest<T> = RequestBuilder(`for`)
}

/**
 * A wrapper class for a GraphQL request */
interface GraphQlRequest<T: QModel<*>> {
  fun onSuccess(handler: (T) -> Unit): GraphQlRequest<T>

  fun onError(handler: (errorCode: Int, message: String) -> Unit): GraphQlRequest<T>

  fun execute()
}

internal class RequestBuilder<T: QModel<*>>(internal val `for`: (() -> T)) : GraphQlRequest<T> {

  internal var successHandler: ((T) -> Unit)? = null

  internal var errorHandler: ((errorCode: Int, message: String) -> Unit)? = null

  override fun onSuccess(handler: (T) -> Unit) = apply { successHandler = handler }

  override fun onError(handler: (errorCode: Int, message: String) -> Unit) = apply { errorHandler = handler }

  override fun execute() = TODO("not implemented")
}
