@file:Suppress("unused")

package com.prestongarno.ktq

import org.junit.Test

interface URL {
  val url: Stub<String>
}

interface Friendable {

  val friendCount: Config<FriendCountArgs, Int>
  val friends: ConfigType<FriendsArgs, OtherUser>

  class FriendsArgs(args: TypeArgBuilder<OtherUser, QModel<OtherUser>> = TypeArgBuilder.create())
    : TypeArgBuilder<OtherUser, QModel<OtherUser>> by args {

    fun first(value: Int) = apply { addArg("first", value) }
    fun after(value: String) = apply { addArg("after", value) }
    fun startAt(value: Int) = apply { addArg("startAt", value) }
  }
  class FriendCountArgs(args: ArgBuilder<Int> = ArgBuilder.create()) : ArgBuilder<Int> by args
}

object Location : QType {
  val latitude : Stub<Int> by lazy { stub<Int>() }
  val longitude by lazy { stub<Int>() }
  val streetAddress by lazy { stub<String>() }
  val city by lazy { stub<String>() }
  val state by lazy { stub<String>() }
  val zip by lazy { stub<Int>() }
}

object OtherUser : URL, Friendable, QType {
  override val friendCount by lazy { configStub(Friendable.FriendCountArgs()) }
  val name by lazy { stub<String>() }
  val enemies by lazy { typeStub<OtherUser>() }
  override val friends by lazy { typeConfigStub(Friendable.FriendsArgs()) }
  val address by lazy { typeConfigStub(AddressArgs()) }
  override val url by lazy { stub<String>() }

  class AddressArgs(args: TypeArgBuilder<Location, QModel<Location>> = TypeArgBuilder.create())
    : TypeArgBuilder<Location, QModel<Location>> by args {

    fun language(value: String) = apply { addArg("language", value) }
  }
}

class SimpleAddress(exactValue: String) : QModel<Location>(Location::class) {
  val streetAddress = exactValue
}

class BasicUserInfo : QModel<OtherUser>(OtherUser::class) {
  val name by model.name
  val url by model.url
}

data class MyUser(
    private val limitOfFriends: Int,
    private val lang: String) : QModel<OtherUser>(OtherUser::class) {

  val username by model.name
  val url by model.url

  val enemies: BasicUserInfo by model.enemies
      .init(::BasicUserInfo)

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
    val foobaz = MyUser(1000, "ENGLISH")
    println("$foobaz \n\t:: ${foobaz.friends}\n\t::${foobaz.address}")
    println(foobaz.address.streetAddress)
    val foobar = MyUser(-69, "CHINESE")
    println("$foobar \n\t:: ${foobar.friends}\n\t::${foobar.address}")
    println(foobar.address.streetAddress)
    println(foobar.enemies)
    println(OtherUser.enemies)
  }

}
