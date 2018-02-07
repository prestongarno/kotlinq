package org.kotlinq.api


interface GraphQlInstanceProvider {

  fun createNewInstance(typeName: String): GraphQlInstance

  companion object : GraphQlInstanceProvider by Configuration.graphQlInstanceProvider
}