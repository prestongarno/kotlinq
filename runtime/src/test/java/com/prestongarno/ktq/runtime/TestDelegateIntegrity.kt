package com.prestongarno.ktq.runtime

import org.junit.Test
import java.time.Instant
import java.util.*
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
	}

	@Test
	fun testNestedObjectTypes() {
		val message = QueryFooBar()
		println(message)
		message.values = mapOf(
				Pair("name", "foobarType"),
				Pair("bio", "My foo bio"),
				Pair("updatedAt", "DateTime { April 24, 1995 } "),
				Pair("createdAt", "DateTime { April 24, 1995 } "),
				Pair("id", "314159286"),
				Pair("repositories", "RepositoryConnection { totalCount = 100 }")
		)
		assertTrue(message.values?.size == 6)
		assertTrue(message.values?.size == message.fields.size)
		assertNotNull(message.fields.filter { it.fieldName == "name" })
		assertNotNull(message.fields.filter { it.fieldName == "bio" })
		assertNotNull(message.fields.filter { it.fieldName == "updatedAt" })
		assertNotNull(message.fields.filter { it.fieldName == "createdAt" })
		assertNotNull(message.fields.filter { it.fieldName == "id" })
		assertNotNull(message.fields.filter { it.fieldName == "repositories" })
		println(message)
		println(message.id)
		//println(message.createdAt.value)
		TODO("Need to make mapping from raw string values in the map to GraphTypes & Custom scalar types")
	}
}

class QueryFooBar : User() {
	public override val name by string()
	public override val bio by string()
	override val updatedAt by field { DateTimeConvert() }
	public override val createdAt by field { DateTimeConvert() }
	public override val id by string()

	public override val repositories by RepositoryOwner.RepositoriesArgs()
			.affiliations(listOf(RepositoryAffiliation.OWNER, RepositoryAffiliation.COLLABORATOR))
			/*.orderBy(object : RepositoryOrder() { //TODO: allow anonymous classes for flexibility
				override val field by exact(RepositoryOrderField.CREATED_AT)
				override val direction by exact(OrderDirection.ASC)
			})*/
			.first(100)
			.privacy(RepositoryPrivacy.PUBLIC)
			.build()
			.field { RepoConnection() }
}

class RepoConnection : RepositoryConnection() {
	//public override val nodes by list { SelectFromRepository() }
	public override val totalCount by int()
}


class DateTimeConvert : DateTime() {
	//public override val value by scalarMapper { Date.from(Instant.EPOCH) }
}
