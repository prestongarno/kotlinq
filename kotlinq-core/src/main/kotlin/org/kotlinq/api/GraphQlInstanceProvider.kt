package org.kotlinq.api


interface GraphQlInstanceProvider {

  fun createNewInstance(typeName: String, typeContext: TypeContext): GraphQlInstance

  companion object : GraphQlInstanceProvider by Configuration.graphQlInstanceProvider
}