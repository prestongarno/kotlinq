package com.prestongarno.ktq.type

import com.google.common.truth.Truth.assertThat
import com.prestongarno.ktq.ArgBuilder
import com.prestongarno.ktq.QEnumType
import com.prestongarno.ktq.QModel
import com.prestongarno.ktq.QSchemaType.*
import com.prestongarno.ktq.QType
import org.junit.Test

object Order : QType {
  val owner by QTypes.stub<Person>()

  val ownerAddress by QTypes.optionalConfigStub<Address, OwnerAddressArgs>()

  val destination by QTypes.configStub<Address, DestinationArgs>()

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

  class Me : QModel<Person>(Person) {
    val name by model.name {
      default = "Preston Garno"
    }
  }

  class DefaultAddress : QModel<Address>(Address) {
    val lineOne by model.line1 {
      default = "1234 GraphQL"
    }
  }

  @Test fun `no arg type stub is possible`() {
    val query = object : QModel<Order>(Order) {
      val personWhoPlacedTheOrder by model.owner.query(::Me)
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("{owner{name}}")
  }

  @Test fun `noArg type stub with invocation is possible`() {
    val query = object : QModel<Order>(Order) {
      val personWhoPlacedTheOrder by model.owner.query(::Me)() {
        config { }
      }
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("{owner{name}}")
  }

  @Test fun `noArg type stub with custom configuration is possible`() {
    val query = object : QModel<Order>(Order) {
      val personWhoPlacedTheOrder by model.owner.query(::Me)() {
        config { "Hello" with "World" }
      }
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("""{owner(Hello: \"World\"){name}}""")
  }

  @Test fun `noArg type stub with default field value returns value`() {
    val query = object : QModel<Order>(Order) {
      val personWhoPlacedTheOrder by model.owner.query(::Me)
    }
    assertThat(query.personWhoPlacedTheOrder.name)
        .isEqualTo("Preston Garno")
  }

  @Test fun `noArg type stub multiple custom arguments toGraphQL()`() {
    val query = object : QModel<Order>(Order) {
      val personWhoPlacedTheOrder by model.owner.query(::Me).invoke {
        config {
          "Hello" with "World"
          "Foo" with 1337
          "Bar" with false
        }
      }
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("""{owner(Hello: \"World\",Foo: 1337,Bar: false){name}}""")
  }

  @Test fun `optionalArg type stub with no arguments is possible`() {
    val query = object : QModel<Order>(Order) {
      val address by model.ownerAddress.query(::DefaultAddress)
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("{ownerAddress{line1}}")
  }

  @Test fun `optionalArg type stub with arguments is possible`() {
    val query = object : QModel<Order>(Order) {
      val address by model.ownerAddress.query(::DefaultAddress)(Order.OwnerAddressArgs()) {
        config {
          addressType = ContactAddressType.WORK
        }
      }
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("{ownerAddress(addressType: WORK){line1}}")
  }

  @Test fun `optionalArg type stub with provided + dynamic arguments is possible`() {
    val query = object : QModel<Order>(Order) {
      val address by model.ownerAddress.query(::DefaultAddress)(Order.OwnerAddressArgs()) {
        config {
          addressType = ContactAddressType.WORK
          "Hello" with "GraphQL"
        }
      }
    }
    assertThat(query.toGraphql(false))
        .isEqualTo("""{ownerAddress(addressType: WORK,Hello: \"GraphQL\"){line1}}""")
  }

  @Test fun `optionalArg type stub with arguments default values return correctly`() {
    val query = object : QModel<Order>(Order) {
      val address by model.ownerAddress.query(::DefaultAddress)(Order.OwnerAddressArgs()) {
        config {
          addressType = ContactAddressType.WORK
        }
      }
    }

    assertThat(query.address.lineOne)
        .isEqualTo("1234 GraphQL")
  }

  @Test fun `required config type stub enforces argument passed to stub`() {
    val query = object : QModel<Order>(Order) {
      val destinationAddress by model.destination.query(::DefaultAddress)(
          Order.DestinationArgs(ContactAddressType.HOME, false)
      )
    }
    assertThat(query.toGraphql(false)).isEqualTo(
        "{destination(addressType: HOME,canLeaveAtDoor: false){line1}}"
    )
  }

  @Test fun `required config type stub with optional arguments`() {
    val query = object : QModel<Order>(Order) {
      val destinationAddress by model.destination.query(::DefaultAddress)(
          Order.DestinationArgs(ContactAddressType.HOME, false)) {
        config { unrelatedMagicString = "DSLs are fucking unbelievable" }
      }
    }
    assertThat(query.toGraphql(false)).isEqualTo(
        """{destination(addressType: HOME,canLeaveAtDoor: false,unrelatedMagicString: \"DSLs are fucking unbelievable\"){line1}}"""
    )
  }

  @Test fun `required config type stub with dynamic arguments`() {
    val query = object : QModel<Order>(Order) {
      val destinationAddress by model.destination.query(::DefaultAddress)(
          Order.DestinationArgs(ContactAddressType.HOME, false)) {
        config { "expletive" with "censored" }
      }
    }
    assertThat(query.toGraphql(false)).isEqualTo(
        """{destination(addressType: HOME,canLeaveAtDoor: false,expletive: \"censored\"){line1}}"""
    )
  }

  @Test fun `required config type stub returns nested scalar correctly`() {
    val query = object : QModel<Order>(Order) {
      val destinationAddress by model.destination.query(::DefaultAddress)(
          Order.DestinationArgs(ContactAddressType.HOME, false)) {
        config { "expletive" with "censored" }
      }
    }
    assertThat(query.destinationAddress.lineOne)
        .isEqualTo("1234 GraphQL")
  }
}
