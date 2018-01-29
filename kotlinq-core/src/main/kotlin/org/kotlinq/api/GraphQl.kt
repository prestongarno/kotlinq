package org.kotlinq.api


// TODO module component
interface Kotlinq {

  fun <T> createPropertyDelegate()

  fun createGraphQlInstance(typeName: String): GraphQlInstance

  // TODO make this delegate to runtime dependency configuration
  companion object : Kotlinq by TODO() {

  }
}

