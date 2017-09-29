package com.prestongarno.ktq.node

import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.http.GraphQL
import com.prestongarno.ktq.node.server.NodeServer
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test

class TestZero() : NodeServer() {
  override val serverNumber = 0

  @Test fun testGetMyName() {
    val myUserInit = { object : QModel<User>(User) { val name by model.name }}

    val queryMyName = object : QModel<Query>(Query) {
      val me by model.me.init(myUserInit)
    }

    runBlocking {
      GraphQL.initialize("http://localhost:4000/graphql")
          .query { queryMyName }
          .onSuccess { require(it.me.name == "Preston Garno") }
          .onError { code, message -> throw IllegalArgumentException("$code: $message") }
          .execute()
    }
  }
}

object Query : QSchemaType {
  val hello: Stub<String> by QSchemaType.QScalar.stub()

  val me: InitStub<User> by QSchemaType.QType.stub()
}

object User : QSchemaType {
  val name: Stub<String> by QSchemaType.QScalar.stub()
}