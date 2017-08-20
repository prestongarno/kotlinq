package com.prestongarno.ktq

import com.sun.org.apache.xpath.internal.Arg
import org.junit.Test
import java.math.BigInteger
import kotlin.reflect.KFunction1

/** Generated Types "person" from the Schema
 */
interface Person : QType {
  fun username(): Stub<String, ArgBuilder<String>> = stub()
  fun email() = nullableStub<String>()
}

interface Contact : QType {
  fun <T : Person> of(init: () -> T) = stub(init)
  fun areaCode() = stub<Int>()
  fun number() = stub<Int>()
}

interface Employee : Person {
  fun salary() = stub<BigInteger>()
  fun <T : Person> boss(init: () -> T) = stub(init)
  fun <T : Contact> emergencyContact(init: () -> T) = stub<T>(init)
}

class ContactInfoArg<T>(args: ContactInfoArg<T> = ArgBuilder.create<T, ContactInfoArg<T>>()) : ArgBuilder<T> by args {
  fun first(value: Int): ContactInfoArg<T> = apply { this.addArg("first", value) }
  fun limitTo(value: Int) = apply { this.addArg("limitTo", value) }
  fun startingAt(value: String) = apply { this.addArg("startingAt", value) }
}

/** A concrete implementation of a Database/Query Type
 */
data class MyEmployeeSelect(val unrelated: String) : Employee {
  val name by username()
  val email by email()
  val salary by salary()
  val bossName: BasicPersonInfo by boss(::BasicPersonInfo)
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

