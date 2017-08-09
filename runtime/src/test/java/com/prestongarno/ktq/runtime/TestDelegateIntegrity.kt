package com.prestongarno.ktq.runtime

import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class SelectFromRepository : Repository() {
	public override val name by string()
	public override val description by string()
	public override val isFork by bool()
}

class TestDelegateIntegrity {
	@Test
	fun sampleCreateBasicUserInfo() {
		val foo = SelectFromRepository()
		println(foo)
		assertTrue(foo.fields.size == 3)
		assertNotNull(foo.fields.filter { it.fieldName == "name" })
		foo.values = (mapOf(
				Pair("name", "TestObject"),
				Pair("description", "This is a description od a test object"),
				Pair("isFork", "false")
		))
		println("foo.name = " + foo.name)
		println("foo.desription = " + foo.description)
		println("foo is fork?: " + foo.isFork)
		assertTrue(foo.name == "TestObject"
				&& foo.description == "This is a description od a test object"
				&& !foo.isFork)
		for( i in 1..100) println(SelectFromRepository())
	}
}

/*class QueryFooBar : User() {
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


class DateTimeConvert : DateTime() {
	override val value by scalarMapper { it }
}*/
