package com.prestongarno.transpiler.tests.parsing

import org.junit.Test

class TypeDeclarationBuilderTests {
	@Test
	fun testSimpleValidDeclaration() {
/*		val name = "MyProfile implements UserInfo"
		val statements = listOf("name: [String]", "followerCount: Int")
		val result = <QTypeDef>(RootType.TYPE, name, statements)
		assert(result.name == "MyProfile")
		assert(result is QTypeDef)
		assert(result.fields.size == 2)
		assert(result.interfaces.size == 1)
		assert(result.interfaces[0] is QUnknownType)
		assert(result.interfaces[0].name == "UserInfo")
		println(result)*/
	}
	@Test
	fun testUnclosedArgBrackets() {
		val name = "MyProfile implements UserInfo"
		val statements = listOf("name: [String", "followerCount: Int")
/*		assertFailsWith(AssertionError::class, "Unclosed bracket", { build<QTypeDef>(RootType.TYPE, name, statements) })*/
	}

	@Test
	fun testUnclosedParentheses() {
		val name = "MyProfile implements UserInfo"
		val statements = listOf("name(arg: String: [String]", "followerCount: Int")
/*		assertFailsWith(AssertionError::class, "input argument declaration", { build<QTypeDef>(RootType.TYPE, name, statements) })*/
	}
}
