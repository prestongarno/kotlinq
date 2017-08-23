@file:Suppress("unused")

package com.prestongarno.ktq

import org.junit.Test

interface URL {
  val url: Stub<String>
}

interface Friendable {

  val friendCount: Config<Int, Friendable.FriendCountArgs>
  val friends: ConfigType<OtherUser, FriendsArgs>

  class FriendsArgs(args: TypeArgBuilder = TypeArgBuilder.create<OtherUser, FriendsArgs>())
    : TypeArgBuilder by args {

    fun first(value: Int) = apply { addArg("first", value) }
    fun after(value: String) = apply { addArg("after", value) }
    fun startAt(value: Int) = apply { addArg("startAt", value) }
  }
  class FriendCountArgs(args: ArgBuilder = ArgBuilder.create<Int, FriendCountArgs>()) : ArgBuilder by args
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
  override val friendCount by lazy { configStub<Int, Friendable.FriendCountArgs>(Friendable.FriendCountArgs()) }
  val name by lazy { stub<String>() }
  val enemies by lazy { typeStub<OtherUser>() }
  override val friends by lazy { typeConfigStub<OtherUser, Friendable.FriendsArgs>(Friendable.FriendsArgs()) }
  val address by lazy { typeConfigStub<Location, AddressArgs>(AddressArgs()) }
  override val url by lazy { stub<String>() }

  class AddressArgs(args: TypeArgBuilder = TypeArgBuilder.create<Location, AddressArgs>())
    : TypeArgBuilder by args {

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

  val address: SimpleAddress by model.address.config()
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
