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

package com.prestongarno.kotlinq.http.node.server

import com.prestongarno.kotlinq.http.GraphHttpAdapter
import com.prestongarno.kotlinq.http.GraphQL
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.After
import org.junit.Before
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.timerTask
import kotlin.streams.toList

abstract class NodeServer {

  abstract val serverNumber: Int

  lateinit var graphqlServerPs: Process

  lateinit var graphql: GraphHttpAdapter

  @Before internal
  fun setUp() {

    graphqlServerPs = ProcessBuilder()
        .inheritIO()
        .command(
            "./src/test/resources/test-server/start-server.sh",
            "$serverNumber",
            "&")
        .start()

    val client = OkHttpClient()

    val isStarted = AtomicBoolean(false)

    Timer().scheduleAtFixedRate(timerTask {
      try {
        val result = client.newCall(Request.Builder()
            .get()
            .url("http://localhost:4000/status")
            .build())
            .execute()

        if (result.code() == 200 && result.body()
            ?.byteStream()
            ?.bufferedReader()
            ?.lines()
            ?.toList()
            ?.joinToString()
            ?.contains("okay") == true) {
          isStarted.set(true)
          cancel()
        }

      } catch (ex: Exception) {
      }
    }, Date.from(Instant.now(Clock.offset(Clock.systemUTC(), Duration.ofMillis(50L)))), 100L)


    do {

      if (isStarted.get())
        break
      else Thread.sleep(10)

    } while (!isStarted.get())

    graphql = GraphQL.initialize("http://localhost:4000/graphql")
  }

  @After fun tearDown() {
    Runtime.getRuntime().exec("kill ktq-node").waitFor()
  }
}
