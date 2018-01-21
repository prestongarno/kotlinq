/*
 * Copyright (C) 2018 Preston Garno
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

@file:Suppress("unused", "RemoveEmptyParenthesesFromLambdaCall")

package com.prestongarno.kotlinq.core.type

import com.google.common.truth.Truth.assertThat
import com.prestongarno.kotlinq.core.ArgBuilder
import com.prestongarno.kotlinq.core.schema.QEnumType
import com.prestongarno.kotlinq.core.Model
import com.prestongarno.kotlinq.core.QSchemaType.QEnum
import com.prestongarno.kotlinq.core.QSchemaType.QScalar
import com.prestongarno.kotlinq.core.QSchemaType.QTypes
import com.prestongarno.kotlinq.core.schema.QType
import org.junit.Test

object Order : QType {
  val owner by QTypes.stub<Person>()

  val ownerAddress by QTypes.optionallyConfigured<Address, OwnerAddressArgs>()

  val destination by QTypes.configured<Address, DestinationArgs>()

  class OwnerAddressArgs : ArgBuilder() {
    var addressType: ContactAddressType? by arguments
  }

  class DestinationArgs(addressType: ContactAddressType, canLeaveAtDoor: Boolean) : ArgBuilder() {
    init {
      "addressType" with addressType
      "canLeaveAtDoor" with canLeaveAtDoor
    }

    var unrelatedMagicString: String? by arguments
  }
}

object Person : QType {
  val name by QScalar.String.stub()

  val age by QScalar.Int.stub()

  val address by QTypes.stub<Address>()
}

object Address : QType {
  val line1 by QScalar.String.stub()

  val city by QScalar.String.stub()

  val state by QEnum.stub<State>()

  val zip by QScalar.Int.stub()
}

enum class ContactAddressType : QEnumType {
  HOME,
  WORK
}

enum class State : QEnumType {
  MI,
  CA
}

class TypeStubQueryable {

  class Me : Model<Person>(Person) {
    val name by model.name {
      default = "Preston Garno"
    }
  }

  class DefaultAddress : Model<Address>(Address) {
    val lineOne by Address.line1 {
      default = "1234 GraphQL"
    }
  }

  @Test fun `no arg type stub is possible`() {
    val query = object : Model<Order>(Order) {
      val personWhoPlacedTheOrder by Order.owner(TypeStubQueryable::Me)
    }
    assertThat(query.toGraphql())
        .isEqualTo("{owner{name}}")
  }

  @Test fun `noArg type stub with invocation is possible`() {
    val query = object : Model<Order>(Order) {
      val personWhoPlacedTheOrder by Order.owner(TypeStubQueryable::Me)() {
        config { }
      }
    }
    assertThat(query.toGraphql())
        .isEqualTo("{owner{name}}")
  }

  @Test fun `noArg type stub with custom configuration is possible`() {
    val query = object : Model<Order>(Order) {
      val personWhoPlacedTheOrder by Order.owner(TypeStubQueryable::Me)() {
        config { this.arguments.put("Hello", "World") }
      }
    }
    assertThat(query.toGraphql())
        .isEqualTo("""{owner(Hello: "World"){name}}""")
  }

  @Test fun `noArg type stub with default field value returns value`() {
    val query = object : Model<Order>(Order) {
      val personWhoPlacedTheOrder by Order.owner(TypeStubQueryable::Me)
    }
    assertThat(query.personWhoPlacedTheOrder.name)
        .isEqualTo("Preston Garno")
  }

  @Test fun `noArg type stub multiple custom arguments toGraphQL()`() {
    val query = object : Model<Order>(Order) {
      val personWhoPlacedTheOrder by Order.owner(TypeStubQueryable::Me).invoke {
        config {
          this.arguments.put("Hello", "World")
          this.arguments.put("Foo", 1337)
          this.arguments.put("Bar", false)
        }
      }
    }
    assertThat(query.toGraphql())
        .isEqualTo("""{owner(Hello: "World",Foo: 1337,Bar: false){name}}""")
  }

  @Test fun `optionalArg type stub with no arguments is possible`() {
    val query = object : Model<Order>(Order) {
      val address by Order.ownerAddress(TypeStubQueryable::DefaultAddress)
    }
    assertThat(query.toGraphql())
        .isEqualTo("{ownerAddress{line1}}")
  }

  @Test fun `optionalArg type stub with arguments is possible`() {
    val query = object : Model<Order>(Order) {
      val address by Order.ownerAddress(TypeStubQueryable::DefaultAddress)(Order.OwnerAddressArgs()) {
        config {
          addressType = ContactAddressType.WORK
        }
      }
    }
    assertThat(query.toGraphql())
        .isEqualTo("{ownerAddress(addressType: WORK){line1}}")
  }

  @Test fun `optionalArg type stub with provided + dynamic arguments is possible`() {
    val query = object : Model<Order>(Order) {
      val address by Order.ownerAddress(TypeStubQueryable::DefaultAddress)(Order.OwnerAddressArgs()) {
        config {
          addressType = ContactAddressType.WORK
          "Hello" with "GraphQL"
        }
      }
    }
    assertThat(query.toGraphql())
        .isEqualTo("""{ownerAddress(addressType: WORK,Hello: "GraphQL"){line1}}""")
  }

  @Test fun `optionalArg type stub with arguments default values return correctly`() {
    val query = object : Model<Order>(Order) {
      val address by Order.ownerAddress(TypeStubQueryable::DefaultAddress)(Order.OwnerAddressArgs()) {
        config {
          addressType = ContactAddressType.WORK
        }
      }
    }

    assertThat(query.address.lineOne)
        .isEqualTo("1234 GraphQL")
  }

  @Test fun `required config type stub enforces argument passed to stub`() {
    val query = object : Model<Order>(Order) {
      val destinationAddress by Order.destination(TypeStubQueryable::DefaultAddress)(
          Order.DestinationArgs(ContactAddressType.HOME, false)
      )
    }
    assertThat(query.toGraphql()).isEqualTo(
        "{destination(addressType: HOME,canLeaveAtDoor: false){line1}}"
    )
  }

  @Test fun `required config type stub with optional arguments`() {
    val query = object : Model<Order>(Order) {
      val destinationAddress by Order.destination(TypeStubQueryable::DefaultAddress)(
          Order.DestinationArgs(ContactAddressType.HOME, false)) {
        config { unrelatedMagicString = "DSLs are fucking unbelievable" }
      }
    }
    assertThat(query.toGraphql()).isEqualTo(
        """{destination(addressType: HOME,canLeaveAtDoor: false,unrelatedMagicString: "DSLs are fucking unbelievable"){line1}}"""
    )
  }

  @Test fun `required config type stub with dynamic arguments`() {
    val query = object : Model<Order>(Order) {
      val destinationAddress by Order.destination(TypeStubQueryable::DefaultAddress)(
          Order.DestinationArgs(ContactAddressType.HOME, false)) {
        config { "expletive" with "censored" }
      }
    }
    assertThat(query.toGraphql()).isEqualTo(
        """{destination(addressType: HOME,canLeaveAtDoor: false,expletive: "censored"){line1}}"""
    )
  }

  @Test fun `required config type stub returns nested scalar correctly`() {
    val query = object : Model<Order>(Order) {
      val destinationAddress by Order.destination(TypeStubQueryable::DefaultAddress)(
          Order.DestinationArgs(ContactAddressType.HOME, false)) {
        config { "expletive" with "censored" }
      }
    }
    assertThat(query.destinationAddress.lineOne)
        .isEqualTo("1234 GraphQL")
  }
}
