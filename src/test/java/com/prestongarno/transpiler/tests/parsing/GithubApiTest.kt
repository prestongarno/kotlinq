package com.prestongarno.transpiler.tests.parsing

import com.prestongarno.transpiler.QLParser
import com.prestongarno.transpiler.qlang.specc.QField
import com.prestongarno.transpiler.qlang.specc.QFieldInputArg
import com.prestongarno.transpiler.qlang.specc.QTypeDef
import org.junit.Test
import java.util.*

class GithubApiTest {
    @Test
    fun schemaTest() {

		val file = this::class.java.classLoader.getResource("graphql.schema.graphqls")
		val content = QLParser().parse(file.openStream()).content
        println("Parsing created a total of [${content.size}] types")
		content.forEach { t -> println("$t\n") }
    }

	@Test
	fun userTypeDeclarationTest() {
		val user = "type User implements Node, Actor, RepositoryOwner, UniformResourceLocatable {\n    avatarUrl(\n    size: Int): URI!\n\n    bio: String\n\n    bioHTML: HTML!\n\n    company: String\n\n    companyHTML: HTML!\n\n    contributedRepositories(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: RepositoryPrivacy\n\n    orderBy: RepositoryOrder\n\n    affiliations: [RepositoryAffiliation]\n\n    isLocked: Boolean): RepositoryConnection!\n\n    createdAt: DateTime!\n\n    databaseId: Int @deprecated(reason: \"Exposed database IDs will eventually be removed in favor of global Relay IDs.\")\n\n    email: String!\n\n    followers(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String): FollowerConnection!\n\n    following(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String): FollowingConnection!\n\n    gist(\n    name: String!): Gist\n\n    gists(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: GistPrivacy): GistConnection!\n    id: ID!\n\n    isBountyHunter: Boolean!\n\n    isCampusExpert: Boolean!\n\n    isDeveloperProgramMember: Boolean!\n\n    isEmployee: Boolean!\n\n    isHireable: Boolean!\n\n    isInvoiced: Boolean!\n\n    isSiteAdmin: Boolean!\n\n    isViewer: Boolean!\n\n    issues(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    labels: [String!]\n\n    orderBy: IssueOrder\n\n    states: [IssueState!]): IssueConnection!\n\n    location: String\n\n    login: String!\n\n    name: String\n\n    organization(\n    login: String!): Organization\n\n    organizations(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String): OrganizationConnection!\n\n    pinnedRepositories(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: RepositoryPrivacy\n\n    orderBy: RepositoryOrder\n\n    affiliations: [RepositoryAffiliation]\n\n    isLocked: Boolean): RepositoryConnection!\n\n    pullRequests(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    states: [PullRequestState!]\n\n    labels: [String!]\n\n    headRefName: String\n\n    baseRefName: String\n\n    orderBy: IssueOrder): PullRequestConnection!\n\n    repositories(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: RepositoryPrivacy\n\n    orderBy: RepositoryOrder\n\n    affiliations: [RepositoryAffiliation]\n\n    isLocked: Boolean\n\n    isFork: Boolean): RepositoryConnection!\n\n    repository(\n    name: String!): Repository\n\n    resourcePath: URI!\n\n    starredRepositories(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    ownedByViewer: Boolean\n\n    orderBy: StarOrder): StarredRepositoryConnection!\n\n    updatedAt: DateTime! @deprecated(reason: \"General type updated timestamps will eventually be replaced by other field specific timestamps.\")\n\n    url: URI!\n\n    viewerCanFollow: Boolean!\n\n    viewerIsFollowing: Boolean!\n\n    watching(\n    first: Int\n\n    after: String\n\n    last: Int\n\n    before: String\n\n    privacy: RepositoryPrivacy\n\n    orderBy: RepositoryOrder\n\n    affiliations: [RepositoryAffiliation]\n\n    isLocked: Boolean): RepositoryConnection!\n\n    websiteUrl: URI\n}"
		val types = QLParser().parse(user.byteInputStream()).content
		assert(types.size == 1 && types[0] is QTypeDef)
		val userType = types[0] as QTypeDef
		val interfaces = userType.interfaces
		assert(interfaces.size == 4)
		interfaces.forEachIndexed { index, iface ->
			when(index) {
				0 -> assert(iface.name == "Node")
				1 -> assert(iface.name == "Actor")
				2 -> assert(iface.name == "RepositoryOwner")
				3 -> assert(iface.name == "UniformResourceLocatable")
			}
		}
		val F = false
		val T = true
		userType.fields.forEachIndexed { i, field -> val f = field as QField; when(i) {
			0 -> assert(checkField(f, "avatarUrl", "URI", false, false, 1, listOf("size"), listOf("Int"), listOf(false),
					listOf(true), listOf("")))
			1 -> assert(checkField(f, "bio", "String", false, true))
			2 -> assert(checkField(f, "bioHTML", "HTML", false, false))
			3 -> assert(checkField(f, "company", "String", false, true))
			4 -> assert(checkField(f, "companyHTML", "HTML", false, false))
			5 -> assert(checkField(f, "contributedRepositories", "RepositoryConnection", false, false, 8,
					listOf("first", "after", "last", "before", "privacy", "orderBy", "affiliations", "isLocked"),
					listOf("Int", "String", "Int", "String", "RepositoryPrivacy", "RepositoryOrder", "RepositoryAffiliation", "Boolean"),
					listOf(F,F,F,F,F,F,T,F),
					listOf(T,T,T,T,T,T,T,T),
					listOf("","","","","","","","")))
			6 -> assert(checkField(f, "createdAt", "DateTime", false, false))
			7 -> assert(checkField(f, "databaseId", "Int", false, true, 0,
					empty(), empty(), empty(), empty(), empty(),
					Pair("deprecated", "reason: \"Exposed database IDs will eventually be removed in favor of global Relay IDs.\"")))
		} }
		println(userType)
	}

//	databaseId: Int @deprecated(reason: "Exposed database IDs will eventually be removed in favor of global Relay IDs.")
//
//	email: String!
//
//	followers(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String): FollowerConnection!
//
//	following(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String): FollowingConnection!
//
//	gist(
//	name: String!): Gist
//
//	gists(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String
//
//	privacy: GistPrivacy): GistConnection!
//	id: ID!
//
//	isBountyHunter: Boolean!
//
//	isCampusExpert: Boolean!
//
//	isDeveloperProgramMember: Boolean!
//
//	isEmployee: Boolean!
//
//	isHireable: Boolean!
//
//	isInvoiced: Boolean!
//
//	isSiteAdmin: Boolean!
//
//	isViewer: Boolean!
//
//	issues(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String
//
//	labels: [String!]
//
//	orderBy: IssueOrder
//
//	states: [IssueState!]): IssueConnection!
//
//	location: String
//
//	login: String!
//
//	name: String
//
//	organization(
//	login: String!): Organization
//
//	organizations(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String): OrganizationConnection!
//
//	pinnedRepositories(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String
//
//	privacy: RepositoryPrivacy
//
//	orderBy: RepositoryOrder
//
//	affiliations: [RepositoryAffiliation]
//
//	isLocked: Boolean): RepositoryConnection!
//
//	pullRequests(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String
//
//	states: [PullRequestState!]
//
//	labels: [String!]
//
//	headRefName: String
//
//	baseRefName: String
//
//	orderBy: IssueOrder): PullRequestConnection!
//
//	repositories(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String
//
//	privacy: RepositoryPrivacy
//
//	orderBy: RepositoryOrder
//
//	affiliations: [RepositoryAffiliation]
//
//	isLocked: Boolean
//
//	isFork: Boolean): RepositoryConnection!
//
//	repository(
//	name: String!): Repository
//
//	resourcePath: URI!
//
//	starredRepositories(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String
//
//	ownedByViewer: Boolean
//
//	orderBy: StarOrder): StarredRepositoryConnection!
//
//	updatedAt: DateTime! @deprecated(reason: "General type updated timestamps will eventually be replaced by other field specific timestamps.")
//
//	url: URI!
//
//	viewerCanFollow: Boolean!
//
//	viewerIsFollowing: Boolean!
//
//	watching(
//	first: Int
//
//	after: String
//
//	last: Int
//
//	before: String
//
//	privacy: RepositoryPrivacy
//
//	orderBy: RepositoryOrder
//
//	affiliations: [RepositoryAffiliation]
//
//	isLocked: Boolean): RepositoryConnection!
//
//	websiteUrl: URI


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
		               expectDirective: Pair<String, String>? = null) : Boolean {
			assert(field.name == expectName, {"Expected name '$expectName' but was '${field.name}'"})
			assert(field.type.name == expectType, {"Expected '$expectName' type '$expectType' but was '${field.type.name}'"})
			assert(field.isList == expectIsList, {"Expected ${field.name + if(expectIsList) "to be a list" else "not a list"}"})
			assert(field.nullable == expectIsNullable, {"Expected ${field.name + if(expectIsNullable) "to be nullable" else "not nullable"}"})
			assert(field.args.size == expectArgCount, {"Expected $expectName arg count $expectArgCount but was ${field.args.size}"})
			field.args.forEachIndexed { index, arg ->
				assert(arg is QFieldInputArg)
				arg as QFieldInputArg
				assert(arg.name == expectArgNames[index])
				assert(arg.type.name == expectArgTypes[index])
				assert(arg.nullable == expectArgNullable[index])
				assert(arg.isList == expectArgIsList[index])
				assert(arg.defaultValue == expectArgDefValue[index])
			}
			when { expectDirective != null -> assert(field.directive.type.name == expectDirective.first && field.directive.value == expectDirective.second) }
			return true
		}

		inline fun <reified T: Any> empty() = Collections.emptyList<T>()
	}

}

