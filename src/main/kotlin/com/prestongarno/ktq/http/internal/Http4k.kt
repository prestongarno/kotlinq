package com.prestongarno.ktq.http.internal

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.http.BasicAuth
import com.prestongarno.ktq.http.RequestBuilder
import com.prestongarno.ktq.http.TokenAuth
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status


internal object Http4k {

  suspend fun <T: QModel<*>> send(requestBuilder: RequestBuilder<T>) {
    val client: HttpHandler = OkHttp()

    val auth = requestBuilder.adapter.authorization

    val request = Request(Method.POST,
        if (auth is BasicAuth) "${auth}" else ""
            + requestBuilder.adapter.endpoint)


    val obj = requestBuilder.`for`()
    request.body(obj.toGraphql().replace("[\n\r\\s]".toRegex(), ""))

    if (requestBuilder.adapter.authorization is TokenAuth) {
      request.replaceHeader("Authorization", "${requestBuilder.adapter.authorization}")
    }

    val networkResponse: Response = client(request)

    if (networkResponse.status == Status.OK && requestBuilder.successHandler != null) {
      requestBuilder.successHandler!!.invoke(obj.apply {
        onResponse(networkResponse.body.toString())
      })
    }
    else if (requestBuilder.errorHandler != null) {
      requestBuilder.errorHandler!!(networkResponse.status.code, networkResponse.toMessage())
    }
  }
}