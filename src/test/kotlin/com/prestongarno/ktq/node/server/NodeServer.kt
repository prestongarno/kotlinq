package com.prestongarno.ktq.node.server

import com.prestongarno.ktq.http.GraphHttpAdapter
import com.prestongarno.ktq.http.GraphQL
import org.junit.After
import org.junit.Before

abstract class NodeServer {

  abstract val serverNumber: Int

  lateinit var graphqlServerPs: Process

  lateinit var graphql: GraphHttpAdapter

  @Before internal fun setUp() {
    graphqlServerPs = Runtime
        .getRuntime()
        .exec("./src/test/resources/test-server/start-server.sh $serverNumber")
    Thread.sleep(300L)
    graphql = GraphQL.initialize("http://localhost:4000/graphql")
  }

  @After fun tearDown() {
    Runtime.getRuntime().exec("kill ktq-node").waitFor()
  }
}