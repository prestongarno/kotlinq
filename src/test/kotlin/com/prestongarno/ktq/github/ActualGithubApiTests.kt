package com.prestongarno.ktq.github

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.http.GraphQL
import com.prestongarno.ktq.http.TokenAuth
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Ignore
import org.junit.Test

const val GITHUB = "https://api.github.com/graphql"

class ActualGithubApiTests {


  @Ignore @Test fun testMyLogin() {
    runBlocking {
      GraphQL.initialize(GITHUB).apply {
        authorization = System.getenv("GITHUB_KEY")?.run { TokenAuth(this) } ?: throw IllegalStateException("Null token!")
      }.query { ViewerQuery() }
          .onSuccess {
            println("Hello, ${it.me.name}")
            println("found ${it.me.pinnedRepositories.total} pinned repositories:" +
                "\n${it.me.pinnedRepositories.nodes.joinToString("\n\t", prefix = "\t") { "${it.name}: ${it.desc}" }}")
            assertThat(it.me.name).isEqualTo("Preston Garno")
            assertThat(it.me.login).isEqualTo("prestongarno")
          }
          .onError { errorCode, message -> System.err.println("$errorCode: $message") }
          .run()
    }
  }

  @Ignore @Test fun searchForJakeWharton() {
    runBlocking {
      GraphQL.initialize(GITHUB).apply {
        authorization = System.getenv("GITHUB_KEY")?.run { TokenAuth(this) } ?: throw IllegalStateException("Null token!")
      }.query { AmbitiousSearch("jake wharton") }
          .onSuccess { result ->
            result.search.users.flatMap { it.users }
                .joinToString { "${it.name}(${it.login})" }
          }.onError { code, message -> println("$code: $message") }
          .run()
    }
  }
}

class ViewerQuery : QModel<Query>(Query) {
  val me by model.viewer.init { UserModel() }
}

class AmbitiousSearch(val query: String) : QModel<Query>(Query) {
  val search by model.search.config()
      .query(query)
      .first(10)
      .type(SearchType.USER)
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
