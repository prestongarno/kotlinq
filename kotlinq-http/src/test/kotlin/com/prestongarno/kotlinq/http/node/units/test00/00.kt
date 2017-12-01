/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.prestongarno.kotlinq.http.node.units.test00

import com.prestongarno.node.server.NodeServer

/*

class TestZero() : NodeServer() {
  override val serverNumber = 0

  @Ignore @Test fun testHelloGraphqlWorld() {
    val helloWorldModel = object : QModel<Query>(Query) {
      val hello by Query.hello
    }

    runBlocking {
      val foo = graphql.query { helloWorldModel }
          .onError { code, message -> throw IllegalArgumentException("$code: $message") }
          .run()

      assertTrue(foo.isResolved())
      assertThat(foo.hello).isEqualTo("world")
    }
  }

  @Ignore @Test fun testGetMyName() {
    val myUserInit = {
      object : QModel<User>(User) {
        val name by User.name
      }
    }

    val queryMyName = object : QModel<Query>(Query) {
      val me by Query.me.querying { myUserInit() }
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

  @Ignore @Test fun allGraphql() {
    val myUserInit = {
      object : QModel<User>(User) {
        val name by User.name
      }
    }

    val queryAll = object : QModel<Query>(Query) {
      val me by Query.me.querying(myUserInit)
      val hello by Query.hello
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
    val hello: Stub<String> by QSchemaType.QScalar.stubPrimitive()

    val me: InitStub<User> by QSchemaType.QType.stub()
  }

  object User : QSchemaType {
    val name: Stub<String> by QSchemaType.QScalar.stubPrimitive()
  }
}

*/

