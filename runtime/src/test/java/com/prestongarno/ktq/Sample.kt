@file:Suppress("unused")

package com.prestongarno.ktq

import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.annotations.Id
import org.junit.Test

interface URL {
  val url: Stub<String>
}

interface Friendable {

  @Id("name")val friendCount: QConfigStub<Int, Friendable.FriendCountArgs>
  @Id("name")val friends: ListConfigType<OtherUser, FriendsArgs>

  class FriendsArgs(args: TypeListArgBuilder) : TypeListArgBuilder by args {

    fun first(value: Int) = apply { addArg("first", value) }
    fun after(value: String) = apply { addArg("after", value) }
    fun startAt(value: Int) = apply { addArg("startAt", value) }
  }

  class FriendCountArgs(args: ArgBuilder) : ArgBuilder by args
}

object Location : QSchemaType {
  @Id("latitude") val latitude: Stub<Int> = QScalar.stub()
  @Id("name")val longitude: Stub<Int> = QScalar.stub()
  @Id("name")val streetAddress: Stub<String> = QScalar.stub()
  @Id("name")val city: Stub<String> = QScalar.stub()
  @Id("name")val state: Stub<String> = QScalar.stub()
  @Id("name")val zip: Stub<Int> = QScalar.stub()
}

object OtherUser : URL, Friendable, QSchemaType {
  @Id("name")override val friendCount = QScalar.configStub<Int, Friendable.FriendCountArgs> { Friendable.FriendCountArgs(it) }
  @Id("name") val name = QScalar.stub<String>()
  @Id("name")val enemies = QType.stub<OtherUser>()
  @Id("name")override val friends = QTypeList.configStub<OtherUser, Friendable.FriendsArgs> { Friendable.FriendsArgs(it) }
  @Id("name")val address = QType.configStub<Location, AddressArgs> { AddressArgs(it) }
  @Id("name")override val url = QScalar.stub<String>()

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
    val foobar = MyUser(-69, "CHINESE")
/*    println(foobaz.toJson())
    println(foobar.toJson(5))*/
  }

}
