/*
 * Copyright (C) 2017 Preston Garno
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

package com.prestongarno.kotlinq.http.internal

import com.prestongarno.kotlinq.core.QModel
import com.prestongarno.kotlinq.core.internal.resolve.resolve
import com.prestongarno.kotlinq.http.RequestBuilder
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status


internal enum class RequestType {
  QUERY,
  MUTATION
}

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
internal object Http4k {

  suspend fun <T : QModel<*>> send(requestBuilder: RequestBuilder<T>): T {
    val client: HttpHandler = OkHttp()

    val obj = requestBuilder.`for`()

    val networkResponse: Response = client(
        Request(Method.POST, requestBuilder.adapter.endpoint)
            .let { request ->
              requestBuilder.adapter.authorization
                  ?.let { request.header("Authorization", it.toString()) }
                  ?: request
            }.query(requestBuilder.type.name.toLowerCase(), obj.toGraphql())
    )

    if (networkResponse.status == Status.OK) {

        if (obj.resolve(networkResponse.body.stream)) {
          requestBuilder.successHandler?.invoke(obj)
        } else {
          requestBuilder.errorHandler?.invoke(400, "Malformed Response: ${networkResponse.body}")
        }

    } else if (networkResponse.status != Status.OK) {
      requestBuilder.errorHandler?.invoke(networkResponse.status.code, networkResponse.toMessage())
    }
    return obj
  }
}
