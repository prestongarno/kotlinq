package com.prestongarno.transpiler.tests.parsing

import com.prestongarno.transpiler.QCompiler
import com.prestongarno.transpiler.QLParser
import com.prestongarno.transpiler.qlang.spec.*
import org.junit.Test
import java.io.File
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GithubApiTest {
  @Test
  fun schemaTest() {
    val file = this::class.java.classLoader.getResource("graphql.schema.graphqls")

    QCompiler.initialize("GitHubGraphql")
        .packageName("com.prestongarno.ktq.github")
        .compile(File(file.toURI())) { content ->
          content.unions.forEach { union -> union.possibleTypes.forEach { t -> assert(t !is QUnknownType) } }
          content.ifaces.forEach { iface -> iface.fields.forEach { field -> assert(field.type !is QUnknownType) } }
          content.types.forEach { type ->
            type.interfaces.forEach { iface -> assert(!(iface is QUnknownInterface)) }
          }

          content.stateful.forEach { f ->
            f.fields.forEach { fooU: QField ->
              assert(fooU.type !is QUnknownType)
              fooU.args.forEach { foo -> assert(!(foo.type is QUnknownType)) }
            }
          }

          content.stateful.stream()
              .map { t ->
                t.fields.map {
                  Pair(t, it)
                }
              }.flatMap { it.stream() }
              .forEach { (type, field) ->
                when (type) {
                  is QTypeDef -> require(!field.abstract)
                  is QInterfaceDef -> require(field.abstract)
                }
              }
        }.result{}.writeToFile("/Users/admin/IdeaProjects/ktq/runtime/src/test/java/")
  }

  @Test
  fun userTypeDeclarationTest() {
    val user = """type User implements Node, Actor, RepositoryOwner, UniformResourceLocatable {
    avatarUrl(
    size: Int): URI!

    bio: String

    bioHTML: HTML!

    company: String

    companyHTML: HTML!

    contributedRepositories(
    first: Int

    after: String

    last: Int

    before: String

    privacy: RepositoryPrivacy

    orderBy: RepositoryOrder

    affiliations: [RepositoryAffiliation]

    isLocked: Boolean): RepositoryConnection!

    createdAt: DateTime!

    databaseId: Int @deprecated(reason: "Exposed database IDs will eventually be removed in favor of global Relay IDs.")

    email: String!

    followers(
    first: Int

    after: String

    last: Int

    before: String): FollowerConnection!

    following(
    first: Int

    after: String

    last: Int

    before: String): FollowingConnection!

    gist(
    name: String!): Gist

    gists(
    first: Int

    after: String

    last: Int

    before: String

    privacy: GistPrivacy): GistConnection!
    id: ID!

    isBountyHunter: Boolean!

    isCampusExpert: Boolean!

    isDeveloperProgramMember: Boolean!

    isEmployee: Boolean!

    isHireable: Boolean!

    isInvoiced: Boolean!

    isSiteAdmin: Boolean!

    isViewer: Boolean!

    issues(
    first: Int

    after: String

    last: Int

    before: String

    labels: [String!]

    orderBy: IssueOrder

    states: [IssueState!]): IssueConnection!

    location: String

    login: String!

    name: String

    organization(
    login: String!): Organization

    organizations(
    first: Int

    after: String

    last: Int

    before: String): OrganizationConnection!

    pinnedRepositories(
    first: Int

    after: String

    last: Int

    before: String

    privacy: RepositoryPrivacy

    orderBy: RepositoryOrder

    affiliations: [RepositoryAffiliation]

    isLocked: Boolean): RepositoryConnection!

    pullRequests(
    first: Int

    after: String

    last: Int

    before: String

    states: [PullRequestState!]

    labels: [String!]

    headRefName: String

    baseRefName: String

    orderBy: IssueOrder): PullRequestConnection!

    repositories(
    first: Int

    after: String

    last: Int

    before: String

    privacy: RepositoryPrivacy

    orderBy: RepositoryOrder

    affiliations: [RepositoryAffiliation]

    isLocked: Boolean

    isFork: Boolean): RepositoryConnection!

    repository(
    name: String!): Repository

    resourcePath: URI!

    starredRepositories(
    first: Int

    after: String

    last: Int

    before: String

    ownedByViewer: Boolean

    orderBy: StarOrder): StarredRepositoryConnection!

    updatedAt: DateTime! @deprecated(reason: "General type updated timestamps will eventually be replaced by other field specific timestamps.")

    url: URI!

    viewerCanFollow: Boolean!

    viewerIsFollowing: Boolean!

    watching(
    first: Int

    after: String

    last: Int

    before: String

    privacy: RepositoryPrivacy

    orderBy: RepositoryOrder

    affiliations: [RepositoryAffiliation]

    isLocked: Boolean): RepositoryConnection!

    websiteUrl: URI
}"""
    val content = QLParser().parse(user.byteInputStream())
    assertTrue(content.types.size == 1)
    val userType = content.types[0]
    val interfaces = userType.interfaces
    assertTrue(userType.fields.size == 40)
    assertTrue(interfaces.size == 4)
    interfaces.forEachIndexed { index, iface ->
      when (index) {
        0 -> assertTrue(iface.name == "Node")
        1 -> assertTrue(iface.name == "Actor")
        2 -> assertTrue(iface.name == "RepositoryOwner")
        3 -> assertTrue(iface.name == "UniformResourceLocatable")
      }
    }
    val F = false
    val T = true
    userType.fields.forEachIndexed { i, field ->
      val f = field; when (i) {
      0 -> assertTrue(checkField(f, "avatarUrl", "URI", false, false, 1, listOf("size"), listOf("Int"), listOf(false),
          listOf(true), listOf("")))
      1 -> assertTrue(checkField(f, "bio", "String", false, true))
      2 -> assertTrue(checkField(f, "bioHTML", "HTML", false, false))
      3 -> assertTrue(checkField(f, "company", "String", false, true))
      4 -> assertTrue(checkField(f, "companyHTML", "HTML", false, false))
      5 -> assertTrue(checkField(f, "contributedRepositories", "RepositoryConnection", false, false, 8,
          listOf("first", "after", "last", "before", "privacy", "orderBy", "affiliations", "isLocked"),
          listOf("Int", "String", "Int", "String", "RepositoryPrivacy", "RepositoryOrder", "RepositoryAffiliation", "Boolean"),
          listOf(F, F, F, F, F, F, T, F),
          listOf(T, T, T, T, T, T, T, T),
          listOf("", "", "", "", "", "", "", "")))
      6 -> assertTrue(checkField(f, "createdAt", "DateTime", false, false))
      7 -> assertTrue(checkField(f, "databaseId", "Int", false, true, 0,
          empty(), empty(), empty(), empty(), empty(),
          Pair("deprecated", "reason: \"Exposed database IDs will eventually be removed in favor of global Relay IDs.\"")))
      8 -> assertTrue(checkField(f, "email", "String", false, false))
      9 -> assertTrue(checkField(f, "followers", "FollowerConnection", false, false, 4,
          listOf("first", "after", "last", "before"), listOf("Int", "String", "Int", "String"),
          listOf(F, F, F, F), listOf(T, T, T, T), listOf("", "", "", "")))
      10 -> assertTrue(checkField(f, "following", "FollowingConnection", false, false, 4,
          listOf("first", "after", "last", "before"), listOf("Int", "String", "Int", "String"),
          listOf(F, F, F, F), listOf(T, T, T, T), listOf("", "", "", "")))
      11 -> assertTrue(checkField(f, "gist", "Gist", false, true, 1, listOf("name"), listOf("String"), listOf(F), listOf(F), listOf("")))
      12 -> assertTrue(checkField(f, "gists", "GistConnection", false, false, 5,
          listOf("first", "after", "last", "before", "privacy"), listOf("Int", "String", "Int", "String", "GistPrivacy"),
          listOf(F, F, F, F, F), listOf(T, T, T, T, T), listOf("", "", "", "", "")))
      13 -> assertTrue(checkField(f, "id", "ID", F, F))
      14 -> assertTrue(checkField(f, "isBountyHunter", "Boolean", F, F))
      15 -> assertTrue(checkField(f, "isCampusExpert", "Boolean", F, F))
      16 -> assertTrue(checkField(f, "isDeveloperProgramMember", "Boolean", F, F))
      17 -> assertTrue(checkField(f, "isEmployee", "Boolean", F, F))
      18 -> assertTrue(checkField(f, "isHireable", "Boolean", F, F))
      19 -> assertTrue(checkField(f, "isInvoiced", "Boolean", F, F))
      20 -> assertTrue(checkField(f, "isSiteAdmin", "Boolean", F, F))
      21 -> assertTrue(checkField(f, "isViewer", "Boolean", F, F))
      22 -> assertTrue(checkField(f, "issues", "IssueConnection", false, false, 7,
          listOf("first", "after", "last", "before", "labels", "orderBy", "states"), listOf("Int", "String", "Int", "String", "String", "IssueOrder", "IssueState"),
          listOf(F, F, F, F, T, F, T), listOf(T, T, T, T, F, T, F), listOf("", "", "", "", "", "", "")))
      23 -> assertTrue(checkField(f, "location", "String", false, true))
      24 -> assertTrue(checkField(f, "login", "String", false, false))
      25 -> assertTrue(checkField(f, "name", "String", false, true))
      26 -> assertTrue(checkField(f, "organization", "Organization", false, true, 1, listOf("login"), listOf("String"), listOf(false), listOf(false), listOf("")))
      27 -> assertTrue(checkField(f, "organizations", "OrganizationConnection", false, false, 4,
          listOf("first", "after", "last", "before"), listOf("Int", "String", "Int", "String"),
          listOf(F, F, F, F), listOf(T, T, T, T), listOf("", "", "", "")))
      28 -> assertTrue(checkField(f, "pinnedRepositories", "RepositoryConnection", false, false, 8,
          listOf("first", "after", "last", "before", "privacy", "orderBy", "affiliations", "isLocked"),
          listOf("Int", "String", "Int", "String", "RepositoryPrivacy", "RepositoryOrder", "RepositoryAffiliation", "Boolean"),
          listOf(F, F, F, F, F, F, T, F), listOf(T, T, T, T, T, T, T, T), listOf("", "", "", "", "", "", "", "")))
      29 -> assertTrue(checkField(f, "pullRequests", "PullRequestConnection", false, false, 9,
          listOf("first", "after", "last", "before", "states", "labels", "headRefName", "baseRefName", "orderBy"),
          listOf("Int", "String", "Int", "String", "PullRequestState", "String", "String", "String", "IssueOrder"),
          listOf(F, F, F, F, T, T, F, F, F), listOf(T, T, T, T, F, F, T, T, T), listOf("", "", "", "", "", "", "", "", "")))
      30 -> assertTrue(checkField(f, "repositories", "RepositoryConnection", false, false, 9,
          listOf("first", "after", "last", "before", "privacy", "orderBy", "affiliations", "isLocked", "isFork"),
          listOf("Int", "String", "Int", "String", "RepositoryPrivacy", "RepositoryOrder", "RepositoryAffiliation", "Boolean", "Boolean"),
          listOf(F, F, F, F, F, F, T, F, F), listOf(T, T, T, T, T, T, T, T, T), listOf("", "", "", "", "", "", "", "", "")))
      31 -> assertTrue(checkField(f, "repository", "Repository", false, true, 1, listOf("name"), listOf("String"), listOf(false), listOf(false), listOf("")))
      32 -> assertTrue(checkField(f, "resourcePath", "URI", false, false))
      33 -> assertTrue(checkField(f, "starredRepositories", "StarredRepositoryConnection", false, false, 6,
          listOf("first", "after", "last", "before", "ownedByViewer", "orderBy"),
          listOf("Int", "String", "Int", "String", "Boolean", "StarOrder"),
          listOf(F, F, F, F, F, F), listOf(T, T, T, T, T, T), listOf("", "", "", "", "", "")))
      34 -> assertTrue(checkField(f, "updatedAt", "DateTime", false, false, 0,
          empty(), empty(), empty(), empty(), empty(),
          Pair("deprecated", "reason: \"General type updated timestamps will eventually be replaced by other field specific timestamps.\"")))
      35 -> assertTrue(checkField(f, "url", "URI", false, false))
      36 -> assertTrue(checkField(f, "viewerCanFollow", "Boolean", false, false))
      37 -> assertTrue(checkField(f, "viewerIsFollowing", "Boolean", false, false))
      38 -> assertTrue(checkField(f, "watching", "RepositoryConnection", false, false, 8,
          listOf("first", "after", "last", "before", "privacy", "orderBy", "affiliations", "isLocked"),
          listOf("Int", "String", "Int", "String", "RepositoryPrivacy", "RepositoryOrder", "RepositoryAffiliation", "Boolean"),
          listOf(F, F, F, F, F, F, T, F), listOf(T, T, T, T, T, T, T, T), listOf("", "", "", "", "", "", "", "")))
      39 -> assertTrue(checkField(f, "websiteUrl", "URI", false, true))
      40 -> throw IllegalArgumentException("Only 39 expected fields")
    }
    }
  }

  companion object {
    fun checkField(field: QField,
                   expectName: String,
                   expectType: String,
                   expectIsList: Boolean,
                   expectIsNullable: Boolean,
                   expectArgCount: Int = 0,
                   expectArgNames: List<String> = empty(),
                   expectArgTypes: List<String> = empty(),
                   expectArgIsList: List<Boolean> = empty(),
                   expectArgNullable: List<Boolean> = empty(),
                   expectArgDefValue: List<String> = empty(),
                   expectDirective: Pair<String, String>? = null): Boolean {

      assertEquals(field.name, expectName, "Expected name '$expectName' but was '${field.name}'")
      assertEquals(field.type.name, expectType, "Expected '$expectName' type '$expectType' but was '${field.type.name}'")
      assertEquals(field.isList, expectIsList, "Expected ${field.name + if (expectIsList) "to be a list" else "not a list"}")
      assertEquals(field.nullable, expectIsNullable, "Expected ${field.name + if (expectIsNullable) " to be nullable" else " not nullable"}")
      assertEquals(field.args.size, expectArgCount, "Expected $expectName arg count $expectArgCount but was ${field.args.size}")

      field.args.forEachIndexed { index, arg ->
        assertTrue(arg.name == expectArgNames[index])
        assertTrue(arg.type.name == expectArgTypes[index])
        assertTrue(arg.nullable == expectArgNullable[index])
        assertTrue(arg.isList == expectArgIsList[index])
        assertTrue(arg.defaultValue == expectArgDefValue[index])
      }

      when { expectDirective != null ->
        assertTrue(field.directive.type.name == expectDirective.first && field.directive.value == expectDirective.second)
      }
      return true
    }

    inline fun <reified T : Any> empty() = Collections.emptyList<T>()
  }

}

