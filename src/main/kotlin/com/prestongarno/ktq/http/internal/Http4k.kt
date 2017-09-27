package com.prestongarno.ktq.http.internal

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.http.RequestBuilder
import com.prestongarno.ktq.http.TokenAuth
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

internal object Http4k {

  suspend fun <T : QModel<*>> send(requestBuilder: RequestBuilder<T>) {
    val client: HttpHandler = OkHttp()

    val obj = requestBuilder.`for`()

    if (requestBuilder.adapter.authorization is TokenAuth) {
    }
    val networkResponse: Response = client(
        Request(Method.POST, requestBuilder.adapter.endpoint)
            .let { request ->
              requestBuilder.adapter.authorization
                  ?.let { request.header("Authorization", it.toString()) }
                  ?: request
            }.body("{ \"${requestBuilder.type.name.toLowerCase()}\": \"" + obj.toGraphql().replace("[\\s\n\r]".toRegex(), "") + "\" }")
            .apply {
              println(body)
            })


    if (networkResponse.status == Status.OK && requestBuilder.successHandler != null) {
      requestBuilder.successHandler!!.invoke(obj.apply {
        val result = Parser().parse(networkResponse.body.stream)
        if (result is JsonObject && result["data"] is JsonObject) {
          accept(result["data"] as JsonObject)
        } else if (requestBuilder.errorHandler != null) {
          requestBuilder.errorHandler!!(400, "Malformed Response: ${networkResponse.body.toString()}")
        }
      })
    } else if (requestBuilder.errorHandler != null) {
      requestBuilder.errorHandler!!(networkResponse.status.code, networkResponse.toMessage())
    }
  }
}