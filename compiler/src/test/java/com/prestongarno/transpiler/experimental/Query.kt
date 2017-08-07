package com.prestongarno.transpiler.experimental

import com.prestongarno.transpiler.experimental.generated.*

class BasicUserInfo(val foo: Int): User() {
	override public val name by field<String>()
	override public val bio by field<String>()
	override public val company by field<String>()
	override val repositories by field<BasicRepoConnection>()
}

class BasicRepoConnection: RepositoryConnection() {
	public override val nodes by collection<anon>()

	object anon : Repository() {
		override val name by field<String>()
	}
}

class CommitSelection: Commit() {
	public override val comments by CommentsArgs()
			.first(100)
			.build(this)
			.field<CommitCommentConnection>()

	object anon: CommitCommentConnection() {
		override val nodes by collection<CommentCommitSelection>()
	}
}

class CommentCommitSelection: CommitComment() {
	public override val author by field<Actor>()
	public override val body by field<String>()
}
