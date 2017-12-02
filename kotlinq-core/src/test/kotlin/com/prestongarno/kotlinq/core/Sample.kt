/*
 * Copyright (C) 2017 Preston Garno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

@file:Suppress("unused")

package com.prestongarno.kotlinq.core
/*
import org.junit.Test
import com.prestongarno.ktq.QSchemaType.QScalar
import com.prestongarno.ktq.QSchemaType.QType
import com.prestongarno.ktq.QSchemaType.QCustomScalar
import com.prestongarno.ktq.QSchemaType.QCustomScalarList
import com.prestongarno.ktq.QSchemaType.QTypeList
import com.google.common.truth.Truth
import com.prestongarno.ktq.adapters.custom.StringScalarListMapper
import com.prestongarno.ktq.adapters.custom.StringScalarMapper
import org.junit.Ignore
import java.io.File

interface UniformResourceLocatable {
  val url: CustomScalarInitStub<URL>
}

object URL: CustomScalar

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
  val latitude: Stub<Int> by QScalar.stubPrimitive()
  val longitude: Stub<Int> by QScalar.stubPrimitive()
  val streetAddress: Stub<String> by QScalar.stubPrimitive()
  val city: Stub<String> by QScalar.stubPrimitive()
  val state: Stub<String> by QScalar.stubPrimitive()
  val zip: Stub<Int> by QScalar.stubPrimitive()
}

object OtherUser : UniformResourceLocatable, Friendable, QSchemaType {
  override val friendCount by QScalar.stub<Int, Friendable.FriendCountArgs> { Friendable.FriendCountArgs(it) }
  val name by QScalar.stubPrimitive<String>()
  val enemies by QType.stub<OtherUser>()
  override val friends by QTypeList.stub<OtherUser, Friendable.FriendsArgs> { Friendable.FriendsArgs(it) }
  val address by QType.stub<Location, AddressArgs> { AddressArgs(it) }
  override val url by QCustomScalar.stub<URL>()
  val relatedUrls by QCustomScalarList.stub<URL>()
  val friendsUrls: CustomScalarListConfigStub<URL, FriendsUrlsArgs> by QCustomScalarList.stub { FriendsUrlsArgs(it) }

  class FriendsUrlsArgs(args: CustomScalarListArgBuilder) : CustomScalarListArgBuilder by args{
    fun shortUrls(value: Boolean) = apply { addArg("shortUrls", value) }

  }
  class AddressArgs(args: TypeArgBuilder) : TypeArgBuilder by args {

    fun language(value: String) = apply { addArg("language", value) }
  }
}

class SimpleAddress(exactValue: String) : QModel<Location>(Location) {
  val streetAddress = exactValue
}

class BasicUserInfo : QModel<OtherUser>(OtherUser) {
  val name by model.name
  val url by model.url.querying(StringScalarMapper {it.toInt()})
  val relatedLinks by model.relatedUrls.querying(StringScalarListMapper { it.toInt() })
  val friendsUrls by model.friendsUrls.scope {
    shortUrls(true)
  }.querying(StringScalarListMapper { File(it).toURI().toURL()!! })
}

data class MyUser(private val limitOfFriends: Int, private val lang: String) : QModel<OtherUser>(OtherUser) {
  val username by model.name.withDefault("ageen")
  val url by model.url.querying(StringScalarMapper {it})

  val enemies: BasicUserInfo by model.enemies
      .querying(::BasicUserInfo)

  val address: SimpleAddress by model.address.scope {
    language(lang)
  }.querying { SimpleAddress("7777 HelloWorld Lane") }

  val friends by model.friends.scope {
    first(limitOfFriends)
  }.querying(::BasicUserInfo)
}

class TestSample {
  @Ignore @Test fun testToGraphQlValid() {
    val foobaz = MyUser(1000, "ENGLISH")
    println(foobaz.toGraphql())
    Truth.assertThat(foobaz.toGraphql())
        .isEqualTo("""
          |{
          |  name,
          |  url,
          |  enemies{
          |    name,
          |    url,
          |    relatedUrls,
          |    friendsUrls(shortUrls: true)
          |  },
          |  address(language: \"ENGLISH\"),
          |  friends(first: 1000){
          |    name,
          |    url,
          |    relatedUrls,
          |    friendsUrls(shortUrls: true)
          |  }
          |}
        """.trimMargin())
  }

  @Ignore @Test fun testGraphQlWithArguments() {

    val foobaz = MyUser(6565, "FR")
    Truth.assertThat(foobaz.toGraphql())
        .isEqualTo("""
          |{
          |  name,
          |  url,
          |  enemies{
          |    name,
          |    url,
          |    relatedUrls,
          |    friendsUrls(shortUrls: true)
          |  },
          |  address(language: \"FR\"),
          |  friends(first: 6565){
          |    name,
          |    url,
          |    relatedUrls,
          |    friendsUrls(shortUrls: true)
          |  }
          |}
        """.trimMargin())
  }
}*/
