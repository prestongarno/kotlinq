package com.prestongarno.transpiler.experimental

import com.prestongarno.transpiler.experimental.generated.*

class BasicUserInfo() : User() {
	override public val name by string()
	override public val bio by string()
	override public val company by string()
	override val repositories by field { BasicRepoConnection() }
}

class BasicRepoConnection : RepositoryConnection() {
	public override val nodes by list { Repo() }
}

class Repo : Repository() {
	override val name by string()
	override val description by string()
	override val forks by field { RepoCount() }
	override val stargazers by field { StarCount() }

	class RepoCount : RepositoryConnection() {
		override val totalCount by int()
	}

	class StarCount : StargazerConnection() {
		override val totalCount by int()
		override val nodes by list { UserAvatar() }
	}
}

class UserAvatar : User() {
	override val avatarUrl by AvatarUrlArgs()
			.size(100)
			.build(this)
			.field<URI>(TODO())
	// TODO :: Scalars need to be of a single field of type "Object" because what type it is is an implementation detail
}

