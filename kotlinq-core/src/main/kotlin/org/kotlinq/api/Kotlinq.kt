package org.kotlinq.api


// TODO module component
interface Kotlinq {

  val adapterService: AdapterService

  fun createGraphQlInstance(typeName: String): GraphQlInstance

  // TODO make this delegate to runtime dependency configuration
  companion object : Kotlinq by TODO()
}

