package com.prestongarno.transpiler.tests.parsing

import com.prestongarno.transpiler.QLParser
import org.junit.Test

class GithubApiTest {
	@Test
	fun schemaTest() {
		val file = this::class.java.classLoader.getResource("graphql.schema.graphqls")

		val content = QLParser().parse(file.openStream()).content
		content.forEach { t -> println("$t\n") }
		println("Parsing created a total of [${content.size}] types")

/*		val input = Scanner(file.openStream()).useDelimiter("\\A").next()
		val regex = Regex("\\{(.*?)}", RegexOption.DOT_MATCHES_ALL)
		regex.findAll(input).map { result -> QLexer.baseFields(result.value)}.flatMap { list -> list.iterator().asSequence() }
				.forEach { field -> println(field) }*/
		val wtf = "type User implements Node, Actor, RepositoryOwner, UniformResourceLocatable {\n" +
				"    avatarUrl(\n" +
				"    size: Int): URI!\n" +
				"\n" +
				"    bio: String\n" +
				"\n" +
				"    bioHTML: HTML!\n" +
				"\n" +
				"    company: String\n" +
				"\n" +
				"    companyHTML: HTML!\n" +
				"\n" +
				"    contributedRepositories(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String\n" +
				"\n" +
				"    privacy: RepositoryPrivacy\n" +
				"\n" +
				"    orderBy: RepositoryOrder\n" +
				"\n" +
				"    affiliations: [RepositoryAffiliation]\n" +
				"\n" +
				"    isLocked: Boolean): RepositoryConnection!\n" +
				"\n" +
				"    createdAt: DateTime!\n" +
				"\n" +
				"    databaseId: Int @deprecated(reason: \"Exposed database IDs will eventually be removed in favor of global Relay IDs.\")\n" +
				"\n" +
				"    email: String!\n" +
				"\n" +
				"    followers(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String): FollowerConnection!\n" +
				"\n" +
				"    following(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String): FollowingConnection!\n" +
				"\n" +
				"    gist(\n" +
				"    name: String!): Gist\n" +
				"\n" +
				"    gists(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String\n" +
				"\n" +
				"    privacy: GistPrivacy): GistConnection!\n" +
				"    id: ID!\n" +
				"\n" +
				"    isBountyHunter: Boolean!\n" +
				"\n" +
				"    isCampusExpert: Boolean!\n" +
				"\n" +
				"    isDeveloperProgramMember: Boolean!\n" +
				"\n" +
				"    isEmployee: Boolean!\n" +
				"\n" +
				"    isHireable: Boolean!\n" +
				"\n" +
				"    isInvoiced: Boolean!\n" +
				"\n" +
				"    isSiteAdmin: Boolean!\n" +
				"\n" +
				"    isViewer: Boolean!\n" +
				"\n" +
				"    issues(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String\n" +
				"\n" +
				"    labels: [String!]\n" +
				"\n" +
				"    orderBy: IssueOrder\n" +
				"\n" +
				"    states: [IssueState!]): IssueConnection!\n" +
				"\n" +
				"    location: String\n" +
				"\n" +
				"    login: String!\n" +
				"\n" +
				"    name: String\n" +
				"\n" +
				"    organization(\n" +
				"    login: String!): Organization\n" +
				"\n" +
				"    organizations(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String): OrganizationConnection!\n" +
				"\n" +
				"    pinnedRepositories(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String\n" +
				"\n" +
				"    privacy: RepositoryPrivacy\n" +
				"\n" +
				"    orderBy: RepositoryOrder\n" +
				"\n" +
				"    affiliations: [RepositoryAffiliation]\n" +
				"\n" +
				"    isLocked: Boolean): RepositoryConnection!\n" +
				"\n" +
				"    pullRequests(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String\n" +
				"\n" +
				"    states: [PullRequestState!]\n" +
				"\n" +
				"    labels: [String!]\n" +
				"\n" +
				"    headRefName: String\n" +
				"\n" +
				"    baseRefName: String\n" +
				"\n" +
				"    orderBy: IssueOrder): PullRequestConnection!\n" +
				"\n" +
				"    repositories(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String\n" +
				"\n" +
				"    privacy: RepositoryPrivacy\n" +
				"\n" +
				"    orderBy: RepositoryOrder\n" +
				"\n" +
				"    affiliations: [RepositoryAffiliation]\n" +
				"\n" +
				"    isLocked: Boolean\n" +
				"\n" +
				"    isFork: Boolean): RepositoryConnection!\n" +
				"\n" +
				"    repository(\n" +
				"    name: String!): Repository\n" +
				"\n" +
				"    resourcePath: URI!\n" +
				"\n" +
				"    starredRepositories(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String\n" +
				"\n" +
				"    ownedByViewer: Boolean\n" +
				"\n" +
				"    orderBy: StarOrder): StarredRepositoryConnection!\n" +
				"\n" +
				"    updatedAt: DateTime! @deprecated(reason: \"General type updated timestamps will eventually be replaced by other field specific timestamps.\")\n" +
				"\n" +
				"    url: URI!\n" +
				"\n" +
				"    viewerCanFollow: Boolean!\n" +
				"\n" +
				"    viewerIsFollowing: Boolean!\n" +
				"\n" +
				"    watching(\n" +
				"    first: Int\n" +
				"\n" +
				"    after: String\n" +
				"\n" +
				"    last: Int\n" +
				"\n" +
				"    before: String\n" +
				"\n" +
				"    privacy: RepositoryPrivacy\n" +
				"\n" +
				"    orderBy: RepositoryOrder\n" +
				"\n" +
				"    affiliations: [RepositoryAffiliation]\n" +
				"\n" +
				"    isLocked: Boolean): RepositoryConnection!\n" +
				"\n" +
				"    websiteUrl: URI\n" +
				"}"
		QLParser().parse(wtf.byteInputStream())
	}
}

