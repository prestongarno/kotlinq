package com.prestongarno.ktq.runtime

import org.junit.Test

class TestDelegateIntegrity {
	@Test
	fun sampleCreateBasicUserInfo() {
		val message = QueryFooBar()
		//println(message)
		println(message.toPayload())
	}
}

class QueryFooBar : User() {
	public override val name by string()
	public override val bio by string()
	override val updatedAt by field { DateTimeConvert() }
	public override val createdAt by field { DateTimeConvert() }
	public override val id by scalarMapper { it }

	public override val repositories by RepositoryOwner.RepositoriesArgs()
			.affiliations(listOf(RepositoryAffiliation.OWNER, RepositoryAffiliation.COLLABORATOR))
			.orderBy(object : RepositoryOrder() {
				override val field by exact(RepositoryOrderField.CREATED_AT)
				override val direction by exact(OrderDirection.ASC)
			})
			.first(100)
			.privacy(RepositoryPrivacy.PUBLIC)
			.build()
			.field { RepoConnection() }
}

class RepoConnection : RepositoryConnection() {
	public override val nodes by list { SelectFromRepository() }
	public override val totalCount by int()
}

class SelectFromRepository : Repository() {
	public override val name by string()
	public override val description by string()
	public override val id by scalar()
	public override val isFork by bool()
}

class DateTimeConvert : DateTime() {
	override val value by scalarMapper { it }
}
