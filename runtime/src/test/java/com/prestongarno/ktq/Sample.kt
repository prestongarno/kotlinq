package com.prestongarno.ktq

import org.junit.Test
import java.math.BigInteger
import kotlin.reflect.KCallable
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

/** Generated Types "person" from the Schema
 */
interface Person : QType {
  fun username(): Stub<String> = stub<String>()
  fun email() = nullableStub<String>()
}

interface Employee : Person {
  fun salary() = stub<BigInteger>()
  fun <T : Person> boss(init: () -> T) = stub(init)
  fun <U> boss(of: KCallable<Stub<U>>) = stub<Person, U>("boss", of)
}

/** A concrete implementation of a Database/Query Type
 */
data class MyEmployeeSelect(val unrelated: String) : Employee {
  val name by username()
  val email by email()
  val salary by salary()
  val bossName: String by boss(Person::username)
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

