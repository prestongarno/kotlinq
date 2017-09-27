package com.prestongarno.ktq.github

import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.http.GraphQL
import com.prestongarno.ktq.http.TokenAuth
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
  runBlocking {
    GraphQL.initialize("https://api.github.com/graphql").apply {
      authorization = System.getenv("GITHUB_KEY")?.run { TokenAuth(this) } ?: throw IllegalStateException("Null token!")
    }.createRequest { ViewerQuery() }
        .onSuccess {
          println("Hello, ${it.me.name}, with login ${it.me.login}")
          println("found ${it.me.pinnedRepositories.total} pinned repositories:\n${it.me.pinnedRepositories.nodes.joinToString("\n\t") { "${it.name}: ${it.desc}" }}")
        }
        .onError { errorCode, message -> System.err.println("$errorCode: $message") }
        .execute()
  }
}

class ViewerQuery : QModel<Query>(Query) {
  val me by model.viewer.init { UserModel() }
}

class AmbitiousSearch : QModel<Query>(Query) {
  val search by model.search.config()
      .first(10)
      .type(SearchType.USER)
      .query("jake wharton")
      .build { Connection() }
}

class Connection : QModel<SearchResultItemConnection>(SearchResultItemConnection) {
  val users by model.nodes.init { Item() }
}

class Item : QModel<SearchResultItem>(SearchResultItem) {
  val users by model.User.init { UserModel() }
}


class UserModel : QModel<User>(User) {
  val login by model.login
  val name by model.name
  val email by model.email
  val pinnedRepositories by model.pinnedRepositories.config()
      .first(10)
      .privacy(RepositoryPrivacy.PUBLIC)
      .build { RepoConnection() }
}

class RepoConnection : QModel<RepositoryConnection>(RepositoryConnection) {
  val total by model.totalCount
  val nodes by model.nodes.init { Repo() }
}

class Repo : QModel<Repository>(Repository) {
  val name by model.name
  val desc by model.description
}
