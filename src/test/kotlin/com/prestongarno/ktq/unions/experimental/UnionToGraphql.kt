package com.prestongarno.ktq.unions.experimental

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.CustomScalar
import com.prestongarno.ktq.CustomScalarInitStub
import com.prestongarno.ktq.InitStub
import com.prestongarno.ktq.ListConfigType
import com.prestongarno.ktq.ListInitStub
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType
import com.prestongarno.ktq.compiler.QCompiler
import com.prestongarno.ktq.QSchemaType.QCustomScalar
import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QType
import com.prestongarno.ktq.QSchemaType.QTypeList
import com.prestongarno.ktq.QSchemaUnion
import com.prestongarno.ktq.Stub
import com.prestongarno.ktq.TypeListArgBuilder
import org.junit.Ignore
import org.junit.Test

class UnionToGraphql {

  @Ignore @Test fun initializeSchemaModelForFile() {
    QCompiler.initialize()
        .packageName("com.prestongarno.ktq")
        .schema("""
          |
          |interface Actor {
          |  login: String
          |}
          |
          |createTypeStub User implements Actor {
          |  login: String
          |  email: String?
          |  repositories: [Repository]
          |}
          |
          |createTypeStub Organization implements Actor {
          |  login: String
          |  members: [User]
          |  owner: User
          |  repositories: [Repository]
          |}
          |
          |createUnionStub Account = User | Bot | Organization
          |
          |createTypeStub Bot implements Actor {
          |  login: String
          |  owner: User
          |}
          |
          |scalar URL
          |
          |createTypeStub Repository {
          |  name: String
          |  url: URL
          |}
          |
          |createTypeStub Query {
          |  searchAccounts(first: Int, searchTerm: String!): [Account]
          |}
          |
          """.trimMargin("|"))
        .compile()
        .result { println(it) }
  }

  @Ignore @Test fun testUnionToGraphqlIsCorrect() {

    val userModelInitializer = {
      object : QModel<User>(User) {
        val login by User.login
      }
    }
    val organizationModelInitializer = {
      object : QModel<Organization>(Organization) {
        val login by Organization.login
        val members by Organization.members.init { userModelInitializer() }
      }
    }

    val queryModel = object : QModel<Query>(Query) {
      val accountSearch by Query.searchAccounts.config {
        first(10)
        searchTerm("google.com")
      }.init {
        object : QModel<Account>(Account) {
          val organizations by Account.Organization.init { organizationModelInitializer() }
        }
      }
    }

    assertThat(queryModel.toGraphql())
        .isEqualTo("""
          |{
          |  searchAccounts(first: 10,searchTerm: \"google.com\"){
          |    ... fragment Organization{
          |      login,
          |      members{
          |        login
          |      }
          |    }
          |  }
          |}
          """.trimMargin("|"))

  }

  @Test fun multipleUnionFields() {

    val userModelInitializer = {
      object : QModel<User>(User) {
        val login by User.login
      }
    }
    val organizationModelInitializer = {
      object : QModel<Organization>(Organization) {
        val login by Organization.login
        val members by Organization.members.init { userModelInitializer() }
      }
    }

    val queryModel = object : QModel<Query>(Query) {
      val accountSearch by Query.searchAccounts.config {
        first(10)
        searchTerm("google.com")
      }.init {
        object : QModel<Account>(Account) {
          val organizations by Account.Organization.init { organizationModelInitializer() }

          val users by Account.User.init { userModelInitializer() }
        }
      }
    }

    assertThat(queryModel.toGraphql())
        .isEqualTo("""
          |{
          |  searchAccounts(first: 10,searchTerm: \"google.com\"){
          |    ... fragment Organization{
          |      login,
          |      members{
          |        login
          |      }
          |    },
          |    ... fragment User{
          |      login
          |    }
          |  }
          |}
          """.trimMargin("|"))
  }

  @Test fun tripleUnionFields() {

    val userModelInitializer = {
      object : QModel<User>(User) {
        val login by User.login
      }
    }
    val botModelInitializer = {
      object : QModel<Bot>(Bot) {
        val login by Bot.login
        val owner by Bot.owner.init { userModelInitializer() }
      }
    }
    val organizationModelInitializer = {
      object : QModel<Organization>(Organization) {
        val login by Organization.login
        val members by Organization.members.init { userModelInitializer() }
      }
    }

    val queryModel = object : QModel<Query>(Query) {
      val accountSearch by Query.searchAccounts.config {
        first(10)
        searchTerm("google.com")
      }.init {
        object : QModel<Account>(Account) {
          val organizations by Account.Organization.init { organizationModelInitializer() }
          val users by Account.User.init { userModelInitializer() }
          val bots by Account.Bot.init { botModelInitializer() }
        }
      }
    }

    assertThat(queryModel.toGraphql())
        .isEqualTo("""
          |{
          |  searchAccounts(first: 10,searchTerm: \"google.com\"){
          |    ... fragment Organization{
          |      login,
          |      members{
          |        login
          |      }
          |    },
          |    ... fragment User{
          |      login
          |    },
          |    ... fragment Bot{
          |      login,
          |      owner{
          |        login
          |      }
          |    }
          |  }
          |}
          """.trimMargin("|"))
  }
}

object Account : QSchemaUnion by QSchemaUnion.create(Account) {
  val User: ListInitStub<User> by QTypeList.stub()

  val Bot: ListInitStub<Bot> by QTypeList.stub()

  val Organization: ListInitStub<Organization> by QTypeList.stub()
}

interface Actor : QSchemaType {
  val login: Stub<String>
}

object Bot : QSchemaType, Actor {
  override val login: Stub<String> by QScalar.stub()

  val owner: InitStub<User> by QType.stub()
}

object Organization : QSchemaType, Actor {
  override val login: Stub<String> by QScalar.stub()

  val members: ListInitStub<User> by QTypeList.stub()

  val owner: InitStub<User> by QType.stub()

  val repositories: ListInitStub<Repository> by QTypeList.stub()
}

object Query : QSchemaType {
  val searchAccounts: ListConfigType<Account, SearchAccountsArgs> by QTypeList.configStub { SearchAccountsArgs(it) }

  class SearchAccountsArgs(args: TypeListArgBuilder) : TypeListArgBuilder by args {
    fun first(value: Int): SearchAccountsArgs = apply { addArg("first", value) }
    fun searchTerm(value: String): SearchAccountsArgs = apply { addArg("searchTerm", value) }
  }
}

object Repository : QSchemaType {
  val name: Stub<String> by QScalar.stub()

  val url: CustomScalarInitStub<URL> by QCustomScalar.stub()
}

object URL : CustomScalar

object User : QSchemaType, Actor {
  override val login: Stub<String> by QScalar.stub()

  val email: Stub<String> by QScalar.stub()

  val repositories: ListInitStub<Repository> by QTypeList.stub()
}
