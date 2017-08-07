package com.prestongarno.transpiler.experimental

import com.prestongarno.ktq.runtime.GraphQL
import org.junit.Test

class TestCases {
	@Test
	fun testInitAndRequest() {
		GraphQL.initialize("https://api.github.com/graphql")
				.authorization("foobar")

		GraphQL.send({ BasicUserInfo(1) })
	}

	@Test
	fun sampleCreateBasicUserInfo() {
		val instance = BasicUserInfo(0)
		instance.name
	}
}

fun main(args: Array<String>) {
    println("Only create an instance:")
    BasicUserInfo(10)

    println("\nNow with property access:")
    BasicUserInfo(50).name
}