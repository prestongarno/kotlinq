package org.kotlinq.api


interface Kotlinq {

  val adapterService: AdapterService

  fun createGraphQlInstance(
      typeName: String,
      context: TypeContext
  ): GraphQlInstance


  companion object : Kotlinq {

    override
    val adapterService: AdapterService
      get() = Configuration.adapterService

    override fun createGraphQlInstance(typeName: String, context: TypeContext): GraphQlInstance =
        GraphQlInstanceProvider.createNewInstance(typeName, context)
  }
}

