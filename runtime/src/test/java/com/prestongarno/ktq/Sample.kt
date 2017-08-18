package com.prestongarno.ktq

import org.junit.Test
import java.math.BigInteger

/** Generated Types "person" from the Schema
 */
interface Person : QType {
	fun username() = stub<String>()
	fun email() = nullableStub<String>()

	companion object {
		private val dummy: Person =  object : Person {}
		val noop : () -> Person = { dummy }
	}
}

interface Employee : Person {
	fun salary() = stub<BigInteger>()
	fun boss(init: () -> Person = Person.noop) = stub(init)

	companion object {
		val noop : () -> Employee = { object : Employee {} }
	}
}

/** A concrete implementation of a Database/Query Type
 */
data class MyEmployeeSelect(val unrelatedField: String) : Employee {
	val name by username()
	val email by email()
	val salary by salary()
	val bossName by boss()
}

class Sample {
	@Test
	fun testTypesCorrect() {
		MyEmployeeSelect("HelloWorld")
		Tracker.global.map {
			"Entry: ${it.key} :: properties = ${it.value.toList().map { "${it.first.property?.name}=${it.second}" }} "
		}.forEach { println(it) }
	}
}
