package com.prestongarno.ktq.node.units.test01

import com.prestongarno.ktq.compiler.QCompiler
import org.junit.Test
import com.prestongarno.ktq.node.server.NodeServer
import org.junit.Ignore
import kotlin.reflect.KProperty

class TestOne : NodeServer() {

  override val serverNumber: Int = 1

  @Ignore @Test fun gen() {
    QCompiler.initialize().schema("""
        |
        |type Query {
        |  me: Actor
        |}
        |
        |union Actor = User | Bot
        |
        |type User {
        |  name: String
        |}
        |
        |type Bot {
        |  name: String
        |  owner: Actor
        |}
        |""".trimMargin("|"))
        .compile().result { println(it) }
  }

  // Need to redesign the current createUnionStub type model in order to
  // support single fields
  @Ignore @Test fun uglyDesignMistakeOnUnions() {

  }

}

/*****************************************************************/
/*****************************************************************/

