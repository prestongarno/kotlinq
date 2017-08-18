package com.prestongarno.ktq

import org.junit.Test
import java.math.BigInteger

/** Generated Types "person" from the Schema
 */
interface Person : QType {
	fun username() : Stub<String> = stub<String>()
	fun email() = nullableStub<String>()

	companion object {
		fun <T: Person> noop() : () -> T = {
			@Suppress("UNCHECKED_CAST")
			object : Person {} as T
		}
	}
}

interface Employee : Person {
	fun salary() = stub<BigInteger>()
	fun <T: Person> boss(init: () -> T = Person.noop()) = stub(init)

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
	val boss: Employee by boss()
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
