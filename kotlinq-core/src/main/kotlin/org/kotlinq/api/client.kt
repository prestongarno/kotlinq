package org.kotlinq.api

import org.kotlinq.adapters.Adapter


interface GraphQlInstance {
  val graphQlTypeName: String
  val properties: Map<String, Adapter>
}