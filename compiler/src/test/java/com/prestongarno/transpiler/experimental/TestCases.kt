package com.prestongarno.transpiler.experimental

import com.prestongarno.ktq.runtime.GraphQL
import org.junit.Test

class TestCases {
	@Test
	fun testInitAndRequest() {
		GraphQL.initialize("https://api.github.com/graphql")
				.authorization("foobar")

		GraphQL.send({ BasicUserInfo() })
	}

	@Test
	fun sampleCreateBasicUserInfo() {
		val instance = BasicUserInfo()
		println(instance)
	}
}
