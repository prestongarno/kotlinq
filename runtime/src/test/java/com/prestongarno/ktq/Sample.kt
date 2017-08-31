@file:Suppress("unused")

package com.prestongarno.ktq

import com.prestongarno.ktq.QSchemaType.*
import org.junit.Test

interface URL {
  val url: Stub<String>
}

interface Friendable {

  val friendCount: QConfigStub<Int, Friendable.FriendCountArgs>
  val friends: ListConfigType<OtherUser, FriendsArgs>

  class FriendsArgs(args: TypeListArgBuilder) : TypeListArgBuilder by args {

    fun first(value: Int) = apply { addArg("first", value) }
    fun after(value: String) = apply { addArg("after", value) }
    fun startAt(value: Int) = apply { addArg("startAt", value) }
  }

  class FriendCountArgs(args: ArgBuilder) : ArgBuilder by args
}

object Location : QSchemaType {
  val latitude: Stub<Int> = QScalar.stub()
  val longitude: Stub<Int> = QScalar.stub()
  val streetAddress: Stub<String> = QScalar.stub()
  val city: Stub<String> = QScalar.stub()
  val state: Stub<String> = QScalar.stub()
  val zip: Stub<Int> = QScalar.stub()
}

object OtherUser : URL, Friendable, QSchemaType {
  override val friendCount = QScalar.configStub<Int, Friendable.FriendCountArgs> { Friendable.FriendCountArgs(it) }
  val name = QScalar.stub<String>()
  val enemies = QType.stub<OtherUser>()
  override val friends = QTypeList.configStub<OtherUser, Friendable.FriendsArgs> { Friendable.FriendsArgs(it) }
  val address = QType.configStub<Location, AddressArgs> { AddressArgs(it) }
  override val url = QScalar.stub<String>()

  class AddressArgs(args: TypeArgBuilder) : TypeArgBuilder by args {

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

data class MyUser(private val limitOfFriends: Int, private val lang: String) : QModel<OtherUser>(OtherUser::class) {
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
    println(foobar.enemies)
    println(OtherUser.enemies)
  }

}
