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
        |createTypeStub Query {
        |  me: Actor
        |}
        |
        |createUnionStub Actor = User | Bot
        |
        |createTypeStub User {
        |  name: String
        |}
        |
        |createTypeStub Bot {
        |  name: String
        |  owner: Actor
        |}
        |""".trimMargin("|"))
        .compile().result { println(it) }
  }

  // Need to redesign the current createUnionStub createTypeStub model in order to
  // support single fields
  @Ignore @Test fun uglyDesignMistakeOnUnions() {

  }

}

/*****************************************************************/
/*****************************************************************/

