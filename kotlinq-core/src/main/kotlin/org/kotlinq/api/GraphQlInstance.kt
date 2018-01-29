package org.kotlinq.api


interface GraphQlInstance {
  val graphQlTypeName: String
  val properties: Map<String, Adapter>
}