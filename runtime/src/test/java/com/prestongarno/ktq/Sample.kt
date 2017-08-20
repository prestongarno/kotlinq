package com.prestongarno.ktq

import org.junit.Test
import java.math.BigInteger
import kotlin.reflect.KFunction1

/** Generated Types "person" from the Schema
 */
interface Person : QType {
  fun username(): Stub<String> = stub<String>()
  fun email() = nullableStub<String>()
  fun <T: Contact> emergencyContact(init: () -> T) = stub<T>(init)
}

interface Contact : QType {
  fun <T : Person> of(init: () -> T) = stub(init)
  fun <T> of(of: KFunction1<Person, Stub<T>>) = stub<Person, T>("boss", of)
  fun areaCode() = stub<Int>()
  fun number() = stub<Int>()
}

interface Employee : Person {
  fun salary() = stub<BigInteger>()
  fun score() = stub<String>()
  fun <T : Person> boss(init: () -> T) = stub(init)
  fun <T> boss(of: KFunction1<Person, Stub<T>>) = stub<Person, T>("boss", of)
  fun <T> emergencyContact(of: KFunction1<Contact, Stub<T>>) = stub<Contact, T>("emergencyContact", of)
}

/** A concrete implementation of a Database/Query Type
 */
data class MyEmployeeSelect(val unrelated: String) : Employee {
  val name by username()
  val email by email()
  val salary by salary()
  val bossName: String by boss(Person::username)
  val emergencyContact by emergencyContact(::MappingContactInfo)
}

class BasicPersonInfo : Person {
  val name by username()
  val email by email()
}

class MappingContactInfo : Contact {
  val person by of(::BasicPersonInfo)
  val areaCode by areaCode()
  val number by number()
}

class Sample {
  @Test
  fun testTypesCorrect() {

    val foobaz = MyEmployeeSelect("HelloWorld")
    Tracker.global.map {
      "Entry: ${it.key} :: \n\tproperties = ${it.value.toList().map { "\n\t\t${it.first.property?.name}=${it.second}" }} "
    }.forEach { println(it) }
  }

}

