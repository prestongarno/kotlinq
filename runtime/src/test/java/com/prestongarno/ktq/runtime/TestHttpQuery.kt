package com.prestongarno.ktq.runtime

import org.junit.Test

class TestHttpQuery {
	@Test
	fun testInitAndRequest() {
		GraphQL.initialize("https://api.github.com/graphql")
				.authorization("foobar")
	}

}