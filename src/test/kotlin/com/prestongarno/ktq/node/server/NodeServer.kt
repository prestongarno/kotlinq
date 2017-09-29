package com.prestongarno.ktq.node.server

import org.junit.After
import org.junit.Before

abstract class NodeServer {

  abstract val serverNumber: Int

  lateinit var graphqlServerPs: Process

  @Before internal fun setUp() {
    graphqlServerPs = Runtime
        .getRuntime()
        .exec("./src/test/resources/test-server/start-server.sh $serverNumber")
    Thread.sleep(300L)
  }

  @After fun tearDown() {
    Runtime.getRuntime().exec("kill ktq-node").waitFor()
  }
}