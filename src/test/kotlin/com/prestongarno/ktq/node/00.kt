package com.prestongarno.ktq.node

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.node.server.NodeServer
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Test
import kotlin.test.assertTrue

class TestZero() : NodeServer() {
  override val serverNumber = 0

  @Test fun testHelloGraphqlWorld() {
    val helloWorldModel = object : QModel<Query>(Query) {
      val hello by model.hello
    }

    runBlocking {
      val foo = graphql.query { helloWorldModel }
          .onError { code, message -> throw IllegalArgumentException("$code: $message") }
          .run()

      assertTrue(foo.isResolved())
      assertThat(foo.hello).isEqualTo("world")
    }
  }

  @Test fun testGetMyName() {
    val myUserInit = {
      object : QModel<User>(User) {
        val name by model.name
      }
    }

    val queryMyName = object : QModel<Query>(Query) {
      val me by model.me.init { myUserInit() }
    }

    runBlocking {
      val result = graphql.query { queryMyName }
          .onError { code, message -> throw IllegalArgumentException("$code: $message") }
          .run()
      assertTrue(result.resolved)
      assertTrue(result.me.resolved)
      assertThat(result.me.name)
          .isEqualTo("Preston Garno")
    }
  }

  @Test fun allGraphql() {
    val myUserInit = {
      object : QModel<User>(User) {
        val name by model.name
      }
    }

    val queryAll = object : QModel<Query>(Query) {
      val me by model.me.init(myUserInit)
      val hello by model.hello
    }

    runBlocking {
      val result = graphql
          .query { queryAll }
          .onError { code, message -> throw IllegalArgumentException("$code: $message") }
          .run()

      assertThat(result.hello).isEqualTo("world")
      assertThat(result.me).isNotNull()
      assertThat(result.me.name).isEqualTo("Preston Garno")
    }
  }

  object Query : QSchemaType {
    val hello: Stub<String> by QSchemaType.QScalar.stub()

    val me: InitStub<User> by QSchemaType.QType.stub()
  }

  object User : QSchemaType {
    val name: Stub<String> by QSchemaType.QScalar.stub()
  }
}


