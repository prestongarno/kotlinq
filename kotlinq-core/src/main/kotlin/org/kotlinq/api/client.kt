package org.kotlinq.api

import org.kotlinq.adapters.Adapter


interface GraphQlTypeInstance {
  val graphQlTypeName: String
  val properties: Map<String, Adapter>
}