package com.prestongarno.ktq.github

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.http.GraphQL
import com.prestongarno.ktq.http.TokenAuth
import kotlinx.coroutines.experimental.runBlocking
import kotlin.coroutines.experimental.CoroutineContext

fun main(args: Array<String>) {
  runBlocking {
    GraphQL.initialize("https://api.github.com/graphql/").apply {
      @Suppress("RemoveSingleExpressionStringTemplate")
      authorization = System.getenv("GITHUB_KEY")?.run { TokenAuth(this) }?: throw IllegalStateException("Null token!")
    }.createRequest { ViewerQuery() }
        .onSuccess { println("Hello ${it.me.name}") }
        .onError { errorCode, message -> throw IllegalStateException("$errorCode: $message") }
        .execute()
  }
}

class ViewerQuery : QModel<Query>(Query) {
  val me by model.viewer.init { UserModel() }
}


class UserModel : QModel<User>(User) {
  val login by model.login
  val name by model.name
  val email by model.email
}
