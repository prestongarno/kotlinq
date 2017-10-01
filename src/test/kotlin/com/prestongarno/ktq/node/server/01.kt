package com.prestongarno.ktq.node.server

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
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Ignore

class TestOne : NodeServer() {

  override val serverNumber: Int = 1

  @Ignore @Test fun gen() { QCompiler.initialize().schema("""
      |type Query {
      |  me: User
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
      |  owner: User
      |}
      |""".trimMargin("|"))
      .compile().result { println(it) }}

  // Need to redesign the current union type model in order to
  // support single fields
  @Ignore @Test fun uglyDesignMistakeOnUnions() {

    val myUserInit = {
      object : QModel<User>(User) {
        val name by model.name
      }
    }

    val myActorInit = {
      object : QModel<Actor>(Actor) {
        val users by model.User.init { myUserInit() }
      }
    }

    val userQuery = {
      object : QModel<Query>(Query) {
        val smth by model.me.init(myActorInit)
      }
    }

    runBlocking {
      val result = graphql
          .query { userQuery() }
          .run()
      assertThat(result.smth.users.first()?.name).isEqualTo("Preston Garno")
    }
  }

}

object Actor : QSchemaUnion {
  val User: ListInitStub<User> by QTypeList.stub()

  val Bot: ListInitStub<Bot> by QTypeList.stub()
}

object Bot : QSchemaType {
  val name: Stub<String> by QScalar.stub()

  val owner: InitStub<User> by QType.stub()
}

object Query : QSchemaType {
  val me: InitStub<Actor> by QType.stub()
}

object User : QSchemaType {
  val name: Stub<String> by QScalar.stub()
}
