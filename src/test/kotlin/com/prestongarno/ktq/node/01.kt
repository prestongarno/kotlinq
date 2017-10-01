package com.prestongarno.ktq.node

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.compiler.QCompiler
import org.junit.Test
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QType
import com.prestongarno.ktq.QSchemaType.QTypeList
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.node.server.NodeServer
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Ignore
import kotlin.test.assertTrue

class TestOne : NodeServer() {

  override val serverNumber: Int = 1

  @Ignore @Test fun gen() {
    QCompiler.initialize().schema("""
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

  // Need to redesign the current union type model in order to
  // support single fields
  @Ignore @Test fun uglyDesignMistakeOnUnions() {

    var myActorInit: (() -> QModel<Actor>)? = null

    val myBotInit = {
      object : QModel<Bot>(Bot) {
        val name by model.name
        val owner by model.owner.init(myActorInit!!)
      }
    }

    myActorInit = {
      object : QModel<Actor>(Actor) {
        val users by Actor.Bot.init { myBotInit() }
      }
    }

    val userQuery = {
      object : QModel<Query>(Query) {
        val smth by Query.me.init(myActorInit!!)
      }
    }

    runBlocking {
      val result = graphql
          .query { userQuery() }
          .onSuccess { throw IllegalStateException("This shouldn't be called") }
          .onError { code, message -> println(message) }
          .run()

      //println(result.toGraphql())
    }
  }

  object Actor : QSchemaUnion {
    val User: ListInitStub<User> by QTypeList.stub()

    val Bot: ListInitStub<Bot> by QTypeList.stub()
  }

  object Bot : QSchemaType {
    val name: Stub<String> by QScalar.stub()

    val owner: InitStub<Actor> by QType.stub()
  }

  object Query : QSchemaType {
    val me: InitStub<Actor> by QType.stub()
  }

  object User : QSchemaType {
    val name: Stub<String> by QScalar.stub()
  }
}

