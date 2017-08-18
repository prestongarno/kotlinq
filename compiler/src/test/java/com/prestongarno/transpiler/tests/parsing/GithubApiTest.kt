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
		val qCompiler = QCompiler()
		val content = qCompiler.compile(File(file.path))
		content.unions.forEach { union -> union.possibleTypes.forEach { t -> assert(!(t is QUnknownType)) } }
		content.ifaces.forEach { iface -> iface.fields.forEach { field -> assert(!(field.type is QUnknownType)) } }
		content.types.forEach { type ->
			type.interfaces.forEach { iface -> assert(!(iface is QUnknownType)) }
		}

		// Why the actual **** is this not compiling?
		val allF: List<QDefinedType> = content.all.toList()
		val whatTheFIsTypeInference: List<QStatefulType> = allF.filter { foo: QDefinedType -> foo is QStatefulType }.map { fook: QDefinedType -> fook as QStatefulType }
		whatTheFIsTypeInference.map { f: QStatefulType ->
			f.fields.forEach {
				fooU: QSymbol ->
				assert(!(fooU.type is QUnknownType))
				fooU.args.forEach { foo -> assert(!(foo.type is QUnknownType)) }
			}
		}

		qCompiler.generateKotlinTypes(content, "com.prestongarno.ktq",
				"/Users/admin/IdeaProjects/ktq/runtime/src/test/java/")
	}

	@Test
	fun userTypeDeclarationTest() {
		val user = "type User implements Node, Actor, RepositoryOwner, UniformResourceLocatable {\n    avatarUrl(\n    size: Int): URI!\n\n    bio: String\n\n    bioHTML: HTML!\n\n    company: String\n\n    companyHTML: HTML!\n\n    contributedRepositories(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: RepositoryPrivacy\n\n    orderBy: RepositoryOrder\n\n    affiliations: [RepositoryAffiliation]\n\n    isLocked: Boolean): RepositoryConnection!\n\n    createdAt: DateTime!\n\n    databaseId: Int @deprecated(reason: \"Exposed database IDs will eventually be removed in favor of global Relay IDs.\")\n\n    email: String!\n\n    followers(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String): FollowerConnection!\n\n    following(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String): FollowingConnection!\n\n    gist(\n    name: String!): Gist\n\n    gists(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: GistPrivacy): GistConnection!\n    id: ID!\n\n    isBountyHunter: Boolean!\n\n    isCampusExpert: Boolean!\n\n    isDeveloperProgramMember: Boolean!\n\n    isEmployee: Boolean!\n\n    isHireable: Boolean!\n\n    isInvoiced: Boolean!\n\n    isSiteAdmin: Boolean!\n\n    isViewer: Boolean!\n\n    issues(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    labels: [String!]\n\n    orderBy: IssueOrder\n\n    states: [IssueState!]): IssueConnection!\n\n    location: String\n\n    login: String!\n\n    name: String\n\n    organization(\n    login: String!): Organization\n\n    organizations(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String): OrganizationConnection!\n\n    pinnedRepositories(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: RepositoryPrivacy\n\n    orderBy: RepositoryOrder\n\n    affiliations: [RepositoryAffiliation]\n\n    isLocked: Boolean): RepositoryConnection!\n\n    pullRequests(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    states: [PullRequestState!]\n\n    labels: [String!]\n\n    headRefName: String\n\n    baseRefName: String\n\n    orderBy: IssueOrder): PullRequestConnection!\n\n    repositories(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: RepositoryPrivacy\n\n    orderBy: RepositoryOrder\n\n    affiliations: [RepositoryAffiliation]\n\n    isLocked: Boolean\n\n    isFork: Boolean): RepositoryConnection!\n\n    repository(\n    name: String!): Repository\n\n    resourcePath: URI!\n\n    starredRepositories(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    ownedByViewer: Boolean\n\n    orderBy: StarOrder): StarredRepositoryConnection!\n\n    updatedAt: DateTime! @deprecated(reason: \"General type updated timestamps will eventually be replaced by other field specific timestamps.\")\n\n    url: URI!\n\n    viewerCanFollow: Boolean!\n\n    viewerIsFollowing: Boolean!\n\n    watching(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: RepositoryPrivacy\n\n    orderBy: RepositoryOrder\n\n    affiliations: [RepositoryAffiliation]\n\n    isLocked: Boolean): RepositoryConnection!\n\n    websiteUrl: URI\n}"
		val content = QLParser().parse(user.byteInputStream())
		assertTrue(content.types.size == 1 && content.types[0] is QTypeDef)
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
			val f = field as QField; when (i) {
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
				assertTrue(arg is QFieldInputArg)
				arg as QFieldInputArg
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

