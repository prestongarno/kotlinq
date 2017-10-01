package com.prestongarno.ktq.node.server

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.prestongarno.ktq.http.GraphHttpAdapter
import com.prestongarno.ktq.http.GraphQL
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.After
import org.junit.Before
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.timerTask

abstract class NodeServer {

  abstract val serverNumber: Int

  lateinit var graphqlServerPs: Process

  lateinit var graphql: GraphHttpAdapter

  @Before internal fun setUp() {

    graphqlServerPs = ProcessBuilder()
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

        if (result.code() == 200 && result.body()?.byteStream()?.let {
          Parser().parse(it)?.let {
            it is JsonObject && it["status"] == "okay"
          } == true
        } == true) {
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