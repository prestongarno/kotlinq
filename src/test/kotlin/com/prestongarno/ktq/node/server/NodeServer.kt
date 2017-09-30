package com.prestongarno.ktq.node.server

import com.prestongarno.ktq.compiler.asFile
import com.prestongarno.ktq.compiler.child
import com.prestongarno.ktq.http.GraphHttpAdapter
import com.prestongarno.ktq.http.GraphQL
import org.junit.After
import org.junit.Before
import java.io.File
import java.io.PrintStream
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.StandardWatchEventKinds

abstract class NodeServer {

  abstract val serverNumber: Int

  lateinit var graphqlServerPs: Process

  lateinit var graphql: GraphHttpAdapter

  @Before internal fun setUp() {

    val psb = ProcessBuilder()
        .command("./src/test/resources/test-server/start-server.sh", "$serverNumber")

    val messageDir = File(System.getProperty("java.io.tmpdir"))
        .child("ktq-node")
        .apply {
          mkdir()
          deleteOnExit()
        }

    val result = File(messageDir.absolutePath.plus("/ktq-node-start-status.txt")).apply {
      deleteOnExit()
      require(!exists())
      psb.redirectOutput(this)
    }

    val tty = System.out
    System.setOut(PrintStream(result.absolutePath))
    val path = FileSystems.getDefault().getPath(messageDir.absolutePath)

    FileSystems.getDefault().newWatchService().use { watch ->
      graphqlServerPs = psb.start()
      val key = path.register(watch, StandardWatchEventKinds.ENTRY_MODIFY)
      while (true) {

        val eventMaybe = key.pollEvents().filter {
          val changed = it.context() as Path
          changed.fileName.startsWith(result.absolutePath.split("/").last())
        }.firstOrNull()

        if (eventMaybe != null) {
          require(result.readText()
              .trim()
              .toInt() == 0)
          break
        } else continue
      }
    }


    System.setOut(tty)
    graphql = GraphQL.initialize("http://localhost:4000/graphql")
  }

  @After fun tearDown() {
    Runtime.getRuntime().exec("kill ktq-node").waitFor()
  }
}