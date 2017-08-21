@file:Suppress("unused")

package com.prestongarno.ktq

import org.junit.Test

interface UniformResourceLocatable {
  val url: Stub<String>
}

object Location : QType {
  val latitude by lazy { stub<Int>() }
  val longitude by lazy { stub<Int>() }
  val streetAddress by lazy { stub<String>() }
  val city by lazy { stub<String>() }
  val state by lazy { stub<String>() }
  val zip by lazy { stub<Int>() }
}

object User : UniformResourceLocatable, QType {
  val name by lazy { stub<String>() }
  val enemies by lazy { typeStub<User>() }
  val friends by lazy { typeConfigStub(FriendsArgs()) }
  val address by lazy { typeConfigStub(AddressArgs()) }
  override val url by lazy { stub<String>() }

  class FriendsArgs(args: TypeArgBuilder<User, QModel<User>> = TypeArgBuilder.create())
    : TypeArgBuilder<User, QModel<User>> by args {

    fun first(value: Int) = apply { addArg("first", value) }
    fun after(value: String) = apply { addArg("after", value) }
    fun startAt(value: Int) = apply { addArg("startAt", value) }
  }

  class AddressArgs(args: TypeArgBuilder<Location, QModel<Location>> = TypeArgBuilder.create())
    : TypeArgBuilder<Location, QModel<Location>> by args {

    fun language(value: String) = apply { addArg("language", value) }
  }
}

class SimpleAddress(exactValue: String) : QModel<Location>(Location::class) {
  val streetAddress = exactValue
}

class BasicUserInfo : QModel<User>(User::class) {
  val name by model.name
  val url by model.url
}

data class UserImpl(val limitOfFriends: Int, val lang: String) : QModel<User>(User::class) {
  val username by model.name
  val url by model.url

  val enemies: BasicUserInfo by model.enemies.init(::BasicUserInfo)

  val address by model.address.config()
      .language(lang)
      .build { SimpleAddress("666 Hell Lane") }

  val friends by model.friends.config()
      .first(limitOfFriends)
      .build(::BasicUserInfo)
}

class TestSample {
  @Test
  fun testCorrectTypes() {
    val foobaz = UserImpl(1000, "ENGLISH")
    println("$foobaz \n\t:: ${foobaz.friends}\n\t::${foobaz.address}")
    println(foobaz.address.streetAddress)
    val foobar = UserImpl(-69, "CHINESE")
    println("$foobar \n\t:: ${foobar.friends}\n\t::${foobar.address}")
    println(foobar.address.streetAddress)
    println(foobar.enemies)
  }

}
