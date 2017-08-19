package com.prestongarno.ktq

import org.junit.Test
import java.math.BigInteger
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

/** Generated Types "person" from the Schema
 */
interface Person : QType {
  fun username(): Stub<String> = stub<String>("username")
  fun email() = nullableStub<String>("email")

  companion object {
    @Suppress("UNCHECKED_CAST") fun <T : Person> default(): T = object : Person {} as T
  }
}

class BasicPerson : Person {
  val name by username()
}

interface Employee : Person {
  fun salary() = stub<BigInteger>("salary")
  fun <T : Person> boss(init: () -> T) = stub("boss", init)

  companion object {
    val noop: () -> Employee = { object : Employee {} }
  }
}

/** A concrete implementation of a Database/Query Type
 */
data class MyEmployeeSelect(val unrelated: String) : Employee {
  val name by username()
  val email by email()
  val salary by salary()
  val bossName: String by boss { Person.default<Person>() }
      .mapDirect(Person::username)
}

class Sample {
  @Test
  fun testTypesCorrect() {
    val foobaz = MyEmployeeSelect("HelloWorld")
    Tracker.global.map {
      "Entry: ${it.key} :: properties = ${it.value.toList().map { "${it.first.property?.name}=${it.second}" }} "
    }.forEach { println(it) }
    println("FO_o")
  }

}

