/*
 * Copyright (C) 2018 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

@file:Suppress("unused")

package com.prestongarno.kotlinq.http

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.http.internal.Http4k
import com.prestongarno.kotlinq.http.internal.RequestType

interface GraphHttpAdapter {

  val endpoint: String

  var authorization: Authorization?

  fun <T : QModel<*>> query(`for`: (() -> T)): GraphQlRequest<T> = RequestBuilder(RequestType.QUERY, this, `for`)

  fun <T : QModel<*>> mutate(`for`: (() -> T)): GraphQlRequest<T> = RequestBuilder(RequestType.MUTATION, this, `for`)
}

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
/**
 * A wrapper class for a GraphQL request */
interface GraphQlRequest<T : QModel<*>> {
  fun onSuccess(handler: (T) -> Unit): GraphQlRequest<T>

  fun onError(handler: (errorCode: Int, message: String) -> Unit): GraphQlRequest<T>

  suspend fun run(): T

  fun runAsync()
}

internal
class RequestBuilder<T : QModel<*>>(
    internal val type: RequestType,
    internal val adapter: GraphHttpAdapter,
    internal val `for`: (() -> T)) : GraphQlRequest<T> {

  internal var successHandler: ((T) -> Unit)? = null

  internal var errorHandler: ((errorCode: Int, message: String) -> Unit)? = null

  override fun onSuccess(handler: (T) -> Unit) = apply { successHandler = handler }

  override fun onError(handler: (errorCode: Int, message: String) -> Unit) = apply { errorHandler = handler }

  @Suppress("EXPERIMENTAL_FEATURE_WARNING")
  override suspend fun run() = Http4k.send(this)

  override fun runAsync() {
    throw UnsupportedOperationException("wut")
  }
}

