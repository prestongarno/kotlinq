package com.prestongarno.transpiler.experimental

import com.prestongarno.transpiler.experimental.generated.*

class BasicUserInfo(): User() {
	override public val name by field<String>()
	override public val bio by field<String>()
	override public val company by field<String>()
	override val repositories by field<BasicRepoConnection>()
}

class BasicRepoConnection: RepositoryConnection() {
	public override val nodes by collection<Repo>()
}

class Repo : Repository() {
	override val name by field<String>()
	override val description by field<String>()
	override val forks by field<RepoCount>()
	override val stargazers by field<StarCount>()

	class RepoCount: RepositoryConnection() {
		override val totalCount by field<Int>()
	}
	class StarCount : StargazerConnection() {
		override val totalCount by field<Int>()
		override val nodes by collection<UserAvatar>()
	}
}

class UserAvatar : User() {
	override val avatarUrl by AvatarUrlArgs()
			.size(100)
			.build(this)
			.field<URI>()
}

